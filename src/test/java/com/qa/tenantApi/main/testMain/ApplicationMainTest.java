package com.qa.tenantApi.main.testMain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.qa.tenantApi.main.Constants;
import com.qa.tenantApi.main.TenantApiApplication;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationMainTest {
	
	
	private TenantApiApplication app;
	
	@Ignore
	@Test
	public void appRuns() {
		this.app = new TenantApiApplication();
		String[] args = new String[] {Constants.getHelloWorld()};
		this.app.main(args);
		assertThat(this.app).isNotNull();
	}
	} 
