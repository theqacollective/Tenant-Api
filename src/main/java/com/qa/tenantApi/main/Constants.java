package com.qa.tenantApi.main;

import java.nio.charset.Charset;

import org.springframework.http.MediaType;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.tenantApi.main.entities.Tenant;
import com.qa.tenantApi.main.entities.TenantBuilder;

public class Constants {
	
	private static final Tenant NULL_TENANT = new Tenant();
	private static Tenant DEFAULT_BUILDER_TENANT = TenantBuilder.getTenantBuilder().tenantBuild();
	
	public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName(Constants.getCharset()));

	private static final String TEST_FIRST_NAME = "TestFirst";
	private static final String TEST_LAST_NAME = "TestLast";
	private static final String TEST_CONTACT_NUMBER = "TestNumber";
	private static final String TEST_CONTACT_EMAIL = "TestEmail";
	private static final String TEST_QA_EMAIL = "TestQaEmail";
	private static final String TEST_ROOM_REFERENCE = "TestRoomReference";
	private static final String TEST_GROUP_NAME = "TestGroupName";
	private static final String TEST_START_DATE = "TestStartDate";
	private static final String TEST_END_DATE = "TestEndDate";
	private static final String TEST_NOTES = "TestNotes";
	private static final String NA_STRING = "N/A";
	private static final String CREATION_MESSAGE = "New Tenant Created";
	private static final String DELETION_MESSAGE = "Tenant Deleted";
	private static final String ALL_DELETION_MESSAGE = "All Tenants Deleted";
	private static final String UPDATE_MESSSAGE = "Tenant Updated";
	private static final String GROUP_UPDATE_MESSAGE = "Tenant Group Updated";
	private static final String TENANTS_DELETION_MESSAGE = "Tenant(s) Deleted";
	private static final String GROUP_DELETION_MESSAGE = "Tenant Group Deleted";
	private static final String HELLO_WORLD = "Hello World";
	private static final String GROUP_NAME = "groupName";
	private static final String FIRST_NAME = "firstName";
	private static final String LAST_NAME = "Name";
	private static final String ID = "id";
	private static final String CHARSET = "utf8";
	
	public static final String SEARCH_BY_FIRST_NAME = "/findTenantByFirstName/{firstName}";
	public static final String SEARCH_BY_LAST_NAME = "/findTenantByLastName/{LastName}";
	public static final String SEARCH_BY_GROUP_NAME = "/findTenantByGroupName/{groupName}";
	public static final String CREATE_URL = "/createTenant";
	public static final String GET_ALL_URL = "/getAllTenants";
	public static final String SEARCH_URL = "/tenantSearch";
	public static final String GROUP_SEARCH_URL = "/tenantGroupSearch/{groupName}";
	public static final String DELETE_ALL_URL="/deleteAllTenants";
	public static final String DELETE_GROUP_URL ="/deleteTenantGroup/{groupName}";
	public static final String DELETE_URL="/deleteTenant";
	public static final String UPDATE_URL="/updateTenant/{id}";
	public static final String UPDATE_GROUP_URL="/updateTenantGroup/{groupName}";
	

	public static void setDefaultBuilderTenant(Tenant tenant) {
		DEFAULT_BUILDER_TENANT = tenant;
	}
	
	public static String getTenantsDeletionMessage() {
		return TENANTS_DELETION_MESSAGE;
	}

	public static String getFirstName() {
		return FIRST_NAME;
	}
	
	public static String getLastName() {
		return LAST_NAME;
	}

	public static String getId() {
		return ID;
	}

	public static String getGroupName() {
		return GROUP_NAME;
	}

	public static String getCharset() {
		return CHARSET;
	}

	public static String getGroupDeletionMessage() {
		return GROUP_DELETION_MESSAGE;
	}

	public static String getCreationMessage() {
		return CREATION_MESSAGE;
	}

	public static String getDeletionMessage() {
		return DELETION_MESSAGE;
	}

	public static String getAllDeletionMessage() {
		return ALL_DELETION_MESSAGE;
	}

	public static String getUpdateMesssage() {
		return UPDATE_MESSSAGE;
	}

	public static String getGroupUpdateMessage() {
		return GROUP_UPDATE_MESSAGE;
	}

	private static final Tenant CONSTRUCTED_TENANT = new Tenant(TEST_FIRST_NAME, TEST_LAST_NAME, TEST_CONTACT_NUMBER,
			TEST_CONTACT_EMAIL, TEST_QA_EMAIL, TEST_ROOM_REFERENCE, TEST_GROUP_NAME, TEST_START_DATE, TEST_END_DATE,
			TEST_NOTES);

	public static String getNaString() {
		return NA_STRING;
	}

	public static Tenant getNullTenant() {
		return NULL_TENANT;
	}

	public static Tenant getDefaultBuilderTenant() {
		return DEFAULT_BUILDER_TENANT;
	}

	public static String getTestFirstName() {
		return TEST_FIRST_NAME;
	}

	public static String getTestLastName() {
		return TEST_LAST_NAME;
	}

	public static String getTestContactNumber() {
		return TEST_CONTACT_NUMBER;
	}

	public static String getTestContactEmail() {
		return TEST_CONTACT_EMAIL;
	}

	public static String getTestQaEmail() {
		return TEST_QA_EMAIL;
	}

	public static String getTestRoomReference() {
		return TEST_ROOM_REFERENCE;
	}

	public static String getTestGroupName() {
		return TEST_GROUP_NAME;
	}

	public static String getTestStartDate() {
		return TEST_START_DATE;
	}

	public static String getTestEndDate() {
		return TEST_END_DATE;
	}

	public static String getTestNotes() {
		return TEST_NOTES;
	}

	public static Tenant getConstructedTenant() {
		return CONSTRUCTED_TENANT;
	}

	public static String getHelloWorld() {
		return HELLO_WORLD;
	}

}
