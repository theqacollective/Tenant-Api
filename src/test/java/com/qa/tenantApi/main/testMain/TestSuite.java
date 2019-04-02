
package com.qa.tenantApi.main.testMain;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.springframework.boot.test.context.SpringBootTest;

import com.qa.tenantApi.main.testMain.controllerTest.TenantControllerTest;
import com.qa.tenantApi.main.testMain.entitiesTest.TenantBuilderTest;
import com.qa.tenantApi.main.testMain.entitiesTest.TenantTest;
import com.qa.tenantApi.main.testMain.repositoryTest.TenantRepoTest;
import com.qa.tenantApi.main.testMain.serviceTest.TenantServiceTest;

@RunWith(Suite.class)

@SuiteClasses({TenantTest.class, TenantServiceTest.class,
	 TenantBuilderTest.class,  TenantRepoTest.class, ApplicationMainTest.class,  TenantControllerTest.class })

@SpringBootTest
public class TestSuite {

}
