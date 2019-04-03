package com.qa.tenantApi.main.testMain.controllerTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.ArgumentMatchers.notNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.qa.tenantApi.main.Constants;
import com.qa.tenantApi.main.controllers.TenantController;
import com.qa.tenantApi.main.entities.Tenant;
import com.qa.tenantApi.main.entities.TenantBuilder;
import com.qa.tenantApi.main.service.TenantService;

@RunWith(SpringRunner.class)
@WebMvcTest(TenantController.class)
@AutoConfigureMockMvc 
public class TenantControllerTest {
	
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private TenantService service;
	@MockBean
	private TenantBuilder builder;
	@MockBean
	RestTemplateBuilder rtb;

	private Tenant controllerTestTenant;
	private String postContent;
	private String postContent2;
	private ObjectWriter ow;
	private List<Tenant> mockedTenants;
	private MvcResult result;
	private String content;
	private TypeReference<List<Tenant>> mapType;
	private List<Tenant> list;
	private String id;

	@Before
	public void setUp() throws JsonProcessingException {
		this.controllerTestTenant = Constants.getConstructedTenant();
		Constants.OBJECT_MAPPER.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		this.ow = Constants.OBJECT_MAPPER.writer().withDefaultPrettyPrinter();
		this.postContent= this.ow.writeValueAsString(this.controllerTestTenant);
		this.postContent2 = this.ow.writeValueAsString(Constants.getDefaultBuilderTenant());
		this.mockedTenants = new ArrayList<Tenant>();
	}

	@Test
	public void testTenantCreation() throws Exception {
		Mockito.when(this.service.createTenant((Tenant) notNull())).thenReturn(Constants.getCreationMessage());
		this.result = this.mockMvc
				.perform(post(Constants.CREATE_URL).contentType(Constants.APPLICATION_JSON_UTF8).content(this.postContent)).andReturn();
		assertThat(result.getResponse().getContentAsString()).contains(Constants.getCreationMessage());
	}

	@Test
	public void testGetAllTenants() throws Exception {
		this.mockedTenants.add(Constants.getConstructedTenant());
		when(this.service.getAllTenants()).thenReturn(this.mockedTenants);
		assertThat(this.mockMvc.perform(get(Constants.GET_ALL_URL).accept(MediaType.APPLICATION_JSON))
				.andExpect(content().string(containsString(Constants.getTestFirstName()))));
	}
	
	@Test
	public void testTenantSearch() throws Exception {
		this.mockedTenants.add(this.controllerTestTenant);
		this.mockedTenants.add(Constants.getDefaultBuilderTenant());

		Mockito.when(this.service.tenantSearch((Tenant) notNull()))
				.thenReturn(this.mockedTenants.stream().filter(x -> x.matches(this.controllerTestTenant)).collect(Collectors.toList()));
		this.result = this.mockMvc
				.perform(get(Constants.SEARCH_URL).param(Constants.getFirstName(), Constants.getTestFirstName()).param(Constants.getLastName(), Constants.getTestLastName())
						.param(Constants.getGroupName(), Constants.getTestGroupName()).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
		this.content = this.result.getResponse().getContentAsString();
	    this.mapType = new TypeReference<List<Tenant>>() {
		};
		this.list = Constants.OBJECT_MAPPER.readValue(this.content, this.mapType);
		assertThat(this.list.stream().filter(x -> x.matches(this.controllerTestTenant)).collect(Collectors.toList()).get(0)
				.matches(this.controllerTestTenant)).isEqualTo(true);
	}

	@Test
	public void testDeleteAll() throws Exception {
		this.mockedTenants.add(this.controllerTestTenant);
		this.mockedTenants.add(Constants.getDefaultBuilderTenant());
		Mockito.when(service.deleteAllTenants()).thenAnswer((Answer<?>) invocation -> {
			this.mockedTenants.clear();
			return Constants.getAllDeletionMessage();
		});
		this.mockMvc.perform(MockMvcRequestBuilders.delete(Constants.DELETE_ALL_URL).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
		assertThat(this.mockedTenants.size()).isEqualTo(0);
	}

	@Test
	public void testDeleteTenant() throws Exception {
		this.mockedTenants = new ArrayList<Tenant>(); 
		this.mockedTenants.add(this.controllerTestTenant);
		this.mockedTenants.add(Constants.getDefaultBuilderTenant());
		
		Mockito.when(this.service.tenantSearch((Tenant) notNull())).thenReturn(this.mockedTenants);
		Mockito.when(this.service.deleteTenant((Tenant) notNull())).thenAnswer((Answer<?>) invocation -> {
			this.mockedTenants.remove(this.controllerTestTenant);
			return Constants.getDeletionMessage();
		});
		this.mockMvc.perform(MockMvcRequestBuilders
				.delete(Constants.DELETE_URL)
				.contentType(MediaType.APPLICATION_JSON)
				.content(this.postContent))
		.andExpect(status().isOk());
		assertThat(this.mockedTenants.size()).isEqualTo(1);
	}
	
	@Ignore
	@Test
	public void testUpdateTenant() throws Exception {
		this.id = controllerTestTenant.getId();
		Mockito.when(this.service.updateTenant((String)notNull(), (String)notNull(), (Tenant)notNull())).thenAnswer((Answer<?>) invocation -> {
			this.controllerTestTenant = Constants.getDefaultBuilderTenant();
			this.controllerTestTenant.setId(id);
			return Constants.getUpdateMesssage();
		});
		this.mockMvc.perform(MockMvcRequestBuilders.put(Constants.UPDATE_URL, this.id)
				.contentType(MediaType.APPLICATION_JSON)
				.content(this.postContent2))
		.andExpect(status().isOk());
		assertThat(this.controllerTestTenant.getFirstName()).isEqualTo(Constants.getNaString());
		assertThat(this.controllerTestTenant.getId()).isEqualTo(id);
	}
}
