package com.qa.tenantApi.main.testMain.entitiesTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNull;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.qa.tenantApi.main.Constants;
import com.qa.tenantApi.main.entities.Tenant;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TenantTest {

	
	private Tenant tenant;
	@Test
	public void superConstructorTest() {
		this.tenant = new Tenant();
		assertThat(this.tenant).isNotNull();
	}

	@Test
	public void fullConstructorTest() {
		this.tenant = new Tenant(Constants.getNaString(), Constants.getNaString(), Constants.getNaString(),
				Constants.getNaString(), Constants.getNaString(), Constants.getNaString(), Constants.getNaString(),
				Constants.getNaString(), Constants.getNaString(), Constants.getNaString());
		assertThat(this.tenant.matches(Constants.getDefaultBuilderTenant())).isEqualTo(true);
	}
	
	@Ignore
	@Test
	public void getterTest() {
		assertNull(Constants.getNullTenant().getFirstName());
		assertNull(Constants.getNullTenant().getLastName());
		assertNull(Constants.getNullTenant().getContactNumber());
		assertNull(Constants.getNullTenant().getContactEmail());
		assertNull(Constants.getNullTenant().getQaEmail());
	 	assertNull(Constants.getNullTenant().getRoomReference());
		assertNull(Constants.getNullTenant().getGroupName());
		assertNull(Constants.getNullTenant().getStartDate());
		assertNull(Constants.getNullTenant().getEndDate());
		assertNull(Constants.getNullTenant().getNotes());
		assertThat(Constants.getNullTenant().getId()).isEqualTo(0);
	}

	@Test
	public void setterTest() {
		this.tenant = Constants.getNullTenant();
		this.tenant.setFirstName(Constants.getNaString());
		this.tenant.setLastName(Constants.getNaString());
		this.tenant.setContactNumber(Constants.getNaString());
		this.tenant.setContactEmail(Constants.getNaString());
		this.tenant.setQaEmail(Constants.getNaString());
		this.tenant.setRoomReference(Constants.getNaString());
		this.tenant.setGroupName(Constants.getNaString());
		this.tenant.setStartDate(Constants.getNaString());
		this.tenant.setEndDate(Constants.getNaString());
		this.tenant.setNotes(Constants.getNaString());
		assertThat(this.tenant.matches(Constants.getDefaultBuilderTenant())).isEqualTo(true);
	}
}
