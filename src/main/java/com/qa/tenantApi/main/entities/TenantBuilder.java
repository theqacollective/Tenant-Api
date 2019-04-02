package com.qa.tenantApi.main.entities;

import java.util.Optional;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.qa.tenantApi.main.Constants;

@Component
@Scope("singleton")
public class TenantBuilder {
	private static TenantBuilder tenantBuilder;

	private String firstName;
	private String lastName;
	private String contactNumber;
	private String contactEmail;
	private String qaEmail;
	private String roomReference;
	private String groupName;
	private String startDate;
	private String endDate;
	private String notes;

	private TenantBuilder() {
	}

	public static  TenantBuilder getTenantBuilder() {
		if (tenantBuilder == null) {
			tenantBuilder = new TenantBuilder();
		}
		return tenantBuilder;
	}

	public TenantBuilder firstName(String firstName) {
		this.firstName = firstName;
		return this;
	}

	public TenantBuilder lastName(String lastName) {
		this.lastName = lastName;
		return this;
	}

	public TenantBuilder contactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
		return this;
	}

	public TenantBuilder contactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
		return this;
	}

	public TenantBuilder qaEmail(String qaEmail) {
		this.qaEmail = qaEmail;
		return this;
	}

	public TenantBuilder roomReference(String roomReference) {
		this.roomReference = roomReference;
		return this;
	}

	public TenantBuilder groupName(String groupName) {
		this.groupName = groupName;
		return this;
	}

	public TenantBuilder startDate(String startDate) {
		this.startDate = startDate;
		return this;
	}

	public TenantBuilder endDate(String endDate) {
		this.endDate = endDate;
		return this;
	}

	public TenantBuilder notes(String notes) {
		this.notes = notes;
		return this;
	}
	
	public static Tenant tenantBuild() {
		Tenant tenant = new Tenant(
				Optional.ofNullable(tenantBuilder.firstName).orElse(Constants.getNaString()),
				Optional.ofNullable(tenantBuilder.lastName).orElse(Constants.getNaString()),
				Optional.ofNullable(tenantBuilder.contactNumber).orElse(Constants.getNaString()),
				Optional.ofNullable(tenantBuilder.contactEmail).orElse(Constants.getNaString()),
				Optional.ofNullable(tenantBuilder.qaEmail).orElse(Constants.getNaString()),
				Optional.ofNullable(tenantBuilder.roomReference).orElse(Constants.getNaString()),
				Optional.ofNullable(tenantBuilder.groupName).orElse(Constants.getNaString()),
				Optional.ofNullable(tenantBuilder.startDate).orElse(Constants.getNaString()),
				Optional.ofNullable(tenantBuilder.endDate).orElse(Constants.getNaString()),
				Optional.ofNullable(tenantBuilder.notes).orElse(Constants.getNaString())
				);
		tenantBuilder= new TenantBuilder();
		return tenant;
	}
}
