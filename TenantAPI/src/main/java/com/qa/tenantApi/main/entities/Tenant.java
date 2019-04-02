package com.qa.tenantApi.main.entities;

import java.util.Optional;

import org.springframework.data.annotation.Id;

import com.qa.tenantApi.main.Constants;

public class Tenant {

	@Id
	private String id;

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

	public Tenant() {
		
	}
	
	public Tenant(String firstName, String lastName, String contactNumber, String contactEmail, String qaEmail,
			String roomReference, String groupName, String startDate, String endDate, String notes) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.contactNumber = contactNumber;
		this.contactEmail = contactEmail;
		this.qaEmail = qaEmail;
		this.roomReference = roomReference;
		this.groupName = groupName;
		this.startDate = startDate;
		this.endDate = endDate;
		this.notes = notes;
	}

	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id=id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String secondName) {
		this.lastName = secondName;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getContactEmail() {
		return contactEmail;
	}

	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}

	public String getQaEmail() {
		return qaEmail;
	}

	public void setQaEmail(String qaEmail) {
		this.qaEmail = qaEmail;
	}

	public String getRoomReference() {
		return roomReference;
	}

	public void setRoomReference(String roomReference) {
		this.roomReference = roomReference;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public boolean matches(Tenant tenant) {
		//This is a project standard.
		Boolean firstCheck = this.getFirstName().contentEquals(tenant.getFirstName());
		Boolean firstNull = tenant.getFirstName().contentEquals(Constants.getNaString());
		Boolean lastCheck = this.getLastName().contentEquals(tenant.getLastName());
		Boolean lastNull = tenant.getLastName().contentEquals(Constants.getNaString());
		Boolean groupCheck = this.getGroupName().contentEquals(tenant.getGroupName());
	    Boolean groupNull = tenant.getGroupName().contentEquals(Constants.getNaString());
		
		firstCheck = firstCheck||firstNull;
		lastCheck = lastCheck||lastNull;
		groupCheck = groupCheck||groupNull;
		return (firstCheck && lastCheck && groupCheck);
	}
	
	public void update(Tenant updateTenant) {
		this.setFirstName(Optional.ofNullable(updateTenant.getFirstName()).orElse(Optional.ofNullable(this.getFirstName()).orElse(Constants.getNaString())));
		this.setLastName(Optional.ofNullable(updateTenant.getLastName()).orElse(Optional.ofNullable(this.getLastName()).orElse(Constants.getNaString())));
		this.setContactNumber(Optional.ofNullable(updateTenant.getContactNumber()).orElse(Optional.ofNullable(this.getContactNumber()).orElse(Constants.getNaString())));
		this.setContactEmail(Optional.ofNullable(updateTenant.getContactEmail()).orElse(Optional.ofNullable(this.getContactEmail()).orElse(Constants.getNaString())));
		this.setQaEmail(Optional.ofNullable(updateTenant.getQaEmail()).orElse(Optional.ofNullable(this.getQaEmail()).orElse(Constants.getNaString())));
		this.setRoomReference(Optional.ofNullable(updateTenant.getRoomReference()).orElse(Optional.ofNullable(this.getRoomReference()).orElse(Constants.getNaString())));
		this.setGroupName(Optional.ofNullable(updateTenant.getGroupName()).orElse(Optional.ofNullable(this.getGroupName()).orElse(Constants.getNaString())));
		this.setStartDate(Optional.ofNullable(updateTenant.getStartDate()).orElse(Optional.ofNullable(this.getStartDate()).orElse(Constants.getNaString())));
		this.setEndDate(Optional.ofNullable(updateTenant.getEndDate()).orElse(Optional.ofNullable(this.getEndDate()).orElse(Constants.getNaString())));
		this.setNotes(Optional.ofNullable(updateTenant.getNotes()).orElse(Optional.ofNullable(this.getNotes()).orElse(Constants.getNaString())));
	}
}
