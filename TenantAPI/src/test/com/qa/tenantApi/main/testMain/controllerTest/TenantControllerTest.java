package com.qa.tenantApi.main.testMain.controllerTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.ArgumentMatchers.notNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Before;
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
import com.fasterxml.jackson.databind.ObjectMapper;
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

	private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName(Constants.getCharset()));

	private Tenant controllerTestTenant;
	private String postContent;
	private String postContent2;
	private ObjectWriter ow;
	private List<Tenant> MOCKED_TENANTS;

	@Before
	public void setUp() throws JsonProcessingException {
		controllerTestTenant = Constants.getConstructedTenant();
		OBJECT_MAPPER.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ow = OBJECT_MAPPER.writer().withDefaultPrettyPrinter();
		postContent= ow.writeValueAsString(controllerTestTenant);
		postContent2 = ow.writeValueAsString(Constants.getDefaultBuilderTenant());
		MOCKED_TENANTS = new ArrayList<Tenant>();
	}

	@Test
	public void testTenantCreation() throws Exception {
		Mockito.when(service.createTenant((Tenant) notNull())).thenReturn(Constants.getCreationMessage());
		MvcResult result = mockMvc
				.perform(post(Constants.getCreateUrl()).contentType(APPLICATION_JSON_UTF8).content(postContent)).andReturn();
		assertThat(result.getResponse().getContentAsString()).contains(Constants.getCreationMessage());
	}

	@Test
	public void testGetAllTenants() throws Exception {
		MOCKED_TENANTS.add(Constants.getConstructedTenant());
		when(service.getAllTenants()).thenReturn(MOCKED_TENANTS);
		assertThat(mockMvc.perform(get(Constants.getGetAllUrl()).accept(MediaType.APPLICATION_JSON))
				.andExpect(content().string(containsString(Constants.getTestFirstName())))).isEqualTo(true);
	}

	@Test
	public void testTenantSearch() throws Exception {
		MOCKED_TENANTS.add(controllerTestTenant);
		MOCKED_TENANTS.add(Constants.getDefaultBuilderTenant());

		Mockito.when(service.tenantSearch((Tenant) notNull()))
				.thenReturn(MOCKED_TENANTS.stream().filter(x -> x.matches(controllerTestTenant)).collect(Collectors.toList()));
		MvcResult result = mockMvc
				.perform(get(Constants.getSearchUrl()).param(Constants.getFirstName(), Constants.getTestFirstName()).param(Constants.getLastName(), Constants.getTestLastName())
						.param(Constants.getGroupName(), Constants.getTestGroupName()).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
		String content = result.getResponse().getContentAsString();
		TypeReference<List<Tenant>> mapType = new TypeReference<List<Tenant>>() {
		};
		List<Tenant> list = OBJECT_MAPPER.readValue(content, mapType);
		assertThat(list.stream().filter(x -> x.matches(controllerTestTenant)).collect(Collectors.toList()).get(0)
				.matches(controllerTestTenant)).isEqualTo(true);
	}

	@Test
	public void testDeleteAll() throws Exception {
		MOCKED_TENANTS.add(controllerTestTenant);
		MOCKED_TENANTS.add(Constants.getDefaultBuilderTenant());
		Mockito.when(service.deleteAllTenants()).thenAnswer((Answer<?>) invocation -> {
			MOCKED_TENANTS.clear();
			return Constants.getAllDeletionMessage();
		});
		this.mockMvc.perform(MockMvcRequestBuilders.delete(Constants.getDeleteAllUrl()).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
		assertThat(MOCKED_TENANTS.size()).isEqualTo(0);
	}

	@Test
	public void testDeleteTenant() throws Exception {
		List<Tenant> MOCKED_TENANTS = new ArrayList<Tenant>();
		MOCKED_TENANTS.add(controllerTestTenant);
		MOCKED_TENANTS.add(Constants.getDefaultBuilderTenant());
		
		Mockito.when(service.tenantSearch((Tenant) notNull())).thenReturn(MOCKED_TENANTS);
		Mockito.when(service.deleteTenant((Tenant) notNull())).thenAnswer((Answer<?>) invocation -> {
			MOCKED_TENANTS.remove(controllerTestTenant);
			return Constants.getDeletionMessage();
		});
		this.mockMvc.perform(MockMvcRequestBuilders
				.delete(Constants.getDeleteUrl())
				.contentType(MediaType.APPLICATION_JSON)
				.content(postContent))
		.andExpect(status().isOk());
		assertThat(MOCKED_TENANTS.size()).isEqualTo(1);
	}

	@Test
	public void testUpdateTenant() throws Exception {
		Long id = controllerTestTenant.getId();
		Mockito.when(service.updateTenant((Long)notNull(), (Tenant)notNull())).thenAnswer((Answer<?>) invocation -> {
			controllerTestTenant = Constants.getDefaultBuilderTenant();
			controllerTestTenant.setId(id);
			return Constants.getUpdateMesssage();
		});
		this.mockMvc.perform(MockMvcRequestBuilders.put(Constants.getUpdateUrl(), id)
				.contentType(MediaType.APPLICATION_JSON)
				.content(postContent2))
		.andExpect(status().isOk());
		System.out.println(postContent2);
		System.out.println(controllerTestTenant.getFirstName());
		assertThat(controllerTestTenant.getFirstName()).isEqualTo(Constants.getNaString());
		assertThat(controllerTestTenant.getId()).isEqualTo(id).isEqualTo(true);
	}
}
