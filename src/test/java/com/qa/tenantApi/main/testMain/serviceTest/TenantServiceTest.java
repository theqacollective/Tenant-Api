package com.qa.tenantApi.main.testMain.serviceTest;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.qa.tenantApi.main.Constants;
import com.qa.tenantApi.main.entities.Tenant;
import com.qa.tenantApi.main.entities.TenantBuilder;
import com.qa.tenantApi.main.repository.TenantRepo;
import static org.mockito.ArgumentMatchers.*;
import com.qa.tenantApi.main.service.TenantService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TenantServiceTest {
	@InjectMocks
	TenantService tenantService;
	@Mock
	TenantRepo tenantRepo;
	
	private List<Tenant> tenantList;
	private Tenant tenant;
	private List<Tenant> returnList;
	private String index;

	@Before
	public void setup() {
		this.tenantList = new ArrayList<Tenant>();
		this.tenantList.add(Constants.getDefaultBuilderTenant());
		this.tenantList.add(Constants.getConstructedTenant());  
		this.tenant = new Tenant();
		this.returnList = new ArrayList<Tenant>();  
		index=null;
	}

	@After
	public void deconstruct() {
		this.tenant = TenantBuilder.getTenantBuilder().tenantBuild();
		Constants.setDefaultBuilderTenant(this.tenant);
		this.tenantList.clear();
	}

	@Test
	public void getAllTest() {
		Mockito.when(this.tenantRepo.findAll()).thenReturn(this.tenantList);
		this.returnList = this.tenantService.getAllTenants();

		assertThat(this.returnList.size()).isEqualTo(2);
		assertThat(this.returnList.get(0)).isEqualToComparingFieldByField(Constants.getDefaultBuilderTenant());
		assertThat(this.returnList.get(1)).isEqualToComparingFieldByField(Constants.getConstructedTenant());
	}
	 
	@Test
	public void createTenantTest() {
		this.tenant = Constants.getConstructedTenant();
		Mockito.when(this.tenantRepo.save((Tenant)notNull())).thenAnswer((Answer<?>) invocation -> {
			this.tenantList.add(this.tenant);
			return Constants.getNullTenant();
		});
		assertThat(this.tenantService.createTenant(this.tenant)).isEqualTo(Constants.getCreationMessage());
		assertThat(this.tenantList.size()).isEqualTo(3);
		assertThat(this.tenantList.get(2)).isEqualToComparingFieldByField(Constants.getConstructedTenant());
	}
	
	@Test
	public void tenantSearchTest() {
		this.tenant = Constants.getConstructedTenant();
		Mockito.when(this.tenantRepo.findAll()).thenReturn(this.tenantList);
		assertThat(tenantService.tenantSearch(this.tenant).size()).isEqualTo(1);
		assertThat(tenantService.tenantSearch(this.tenant).get(0)).isEqualToComparingFieldByField(this.tenant);
	}
	
	@Test
	public void deleteTenantTest() {
		Mockito.when(this.tenantService.deleteTenant((Tenant)notNull())).thenAnswer((Answer<?>) invocation -> {
			this.tenantList.remove(Constants.getDefaultBuilderTenant());
			return Constants.getDeletionMessage();
		});
		assertThat(this.tenantService.deleteTenant(Constants.getDefaultBuilderTenant())).isEqualTo(Constants.getDeletionMessage());
		assertThat(this.tenantList.contains(Constants.getDefaultBuilderTenant())).isEqualTo(false);
		
	}
	
	@Test
	public void deleteAllTest() {
		Mockito.when(this.tenantService.deleteAllTenants()).thenAnswer((Answer<?>) invocation -> {
			this.tenantList.clear();
			return Constants.getAllDeletionMessage();
		});
		assertThat(this.tenantService.deleteAllTenants()).isEqualTo(Constants.getAllDeletionMessage());
		assertThat(this.tenantList.size()).isEqualTo(0);
	}
	
	@Ignore
	@Test
	public void updateTenantTest() throws CloneNotSupportedException {
		this.tenant = Constants.getConstructedTenant();
		Mockito.when(this.tenantRepo.findById((String)notNull())).thenReturn(Optional.ofNullable(this.tenantList.get(0)));
		Mockito.when(this.tenantRepo.save((Tenant)notNull())).thenAnswer((Answer<?>) invocation -> {
			this.tenantList.clear();
			this.tenantList.add(this.tenant);
			this.tenantList.add(Constants.getConstructedTenant());  
			return tenant;
		});

//		this.index = String.valueOf(String.valueOf(0));
		this.tenantService.updateTenant(Constants.getFirstName(), Constants.getLastName(), this.tenant);
		assertThat(this.tenantList.get(0).matches(Constants.getConstructedTenant()));
	}
}
