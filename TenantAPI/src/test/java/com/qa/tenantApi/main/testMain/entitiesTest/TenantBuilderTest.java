package com.qa.tenantApi.main.testMain.entitiesTest;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.qa.tenantApi.main.Constants;
import com.qa.tenantApi.main.entities.TenantBuilder;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TenantBuilderTest {
	
	@Before
	public void getBuilder() {
		assertThat(TenantBuilder.getTenantBuilder()).isNotNull();
	}

	@After
	public void resetBuilder() {
		assertThat(TenantBuilder.getTenantBuilder()).isNotNull();
	}

	@Test
	public void blankBuild() {
		System.out.println(Constants.getDefaultBuilderTenant().getFirstName());
		assertThat(TenantBuilder.tenantBuild().matches(Constants.getDefaultBuilderTenant())).isEqualTo(true);
	}

	@Test
	public void setterBuild() {
		TenantBuilder.getTenantBuilder().firstName(Constants.getTestFirstName()).lastName(Constants.getTestLastName())
				.contactNumber(Constants.getTestContactNumber()).contactEmail(Constants.getTestContactEmail())
				.qaEmail(Constants.getTestQaEmail()).roomReference(Constants.getTestRoomReference())
				.groupName(Constants.getTestGroupName()).startDate(Constants.getTestStartDate())
				.endDate(Constants.getTestEndDate()).notes(Constants.getTestNotes());
		assertThat(TenantBuilder.tenantBuild())
		.isEqualToComparingFieldByField(Constants.getConstructedTenant());
	}
}
