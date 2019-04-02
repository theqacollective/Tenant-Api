package com.qa.tenantApi.main.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.qa.tenantApi.main.Constants;
import com.qa.tenantApi.main.entities.Tenant;
import com.qa.tenantApi.main.entities.TenantBuilder;
import com.qa.tenantApi.main.service.TenantService;

@RestController
@CrossOrigin
public class TenantController {

	private TenantService tenantService;

	public TenantController(TenantService tenantService) {
		this.tenantService = tenantService;
	}

	@PostMapping(Constants.CREATE_URL)
	public String createTenant(@RequestBody Tenant tenant) {
		return this.tenantService.createTenant(tenant);
	}

	@GetMapping(Constants.GET_ALL_URL)
	public List<Tenant> getAllTenants() {
		return this.tenantService.getAllTenants();
	}

	@GetMapping(Constants.SEARCH_URL)
	public List<Tenant> tenantSearch(String firstName, String lastName, String groupName) {
		TenantBuilder.getTenantBuilder().firstName(firstName).lastName(lastName).groupName(groupName);
		Tenant tenant = TenantBuilder.tenantBuild();
		return this.tenantService.tenantSearch(tenant);
	}
	
	@GetMapping(Constants.GROUP_SEARCH_URL)
	public List<Tenant> tenantGroupSearch(@PathVariable("groupName")String groupName) {
		TenantBuilder.getTenantBuilder().groupName(groupName);
		Tenant tenant = TenantBuilder.tenantBuild();
		return this.tenantService.tenantSearch(tenant);
	}
	
	@DeleteMapping(Constants.DELETE_ALL_URL)
	public String deleteAllTenants() {
		return this.tenantService.deleteAllTenants();
	}
	
	@DeleteMapping(Constants.DELETE_GROUP_URL)
	public String deleteTenantGroup(@PathVariable("groupName") String groupName) {
		List<Tenant> tenants = this.tenantGroupSearch(groupName);
		return this.tenantService.deleteTenantGroup(tenants);
	}
	
	@DeleteMapping(Constants.DELETE_URL)
	public String deleteTenant(String firstName, String lastName, String groupName) {
		List<Tenant> tenants = this.tenantSearch(firstName, lastName, groupName);
		for(int i = 0; i < tenants.size();i++) {
			this.tenantService.deleteTenant(tenants.get(i));
		}
		return Constants.getTenantsDeletionMessage();
	}
	
	@PutMapping(Constants.UPDATE_URL)
	public String updateTenant(@PathVariable("id")String id, @RequestBody Tenant tenantUpdate) {
		return this.tenantService.updateTenant(id, tenantUpdate);
	}
	
	@PutMapping(Constants.UPDATE_GROUP_URL)
	public String updateTenantGroup(@PathVariable("groupName") String setGroupName, @RequestBody Tenant tenantUpdate) {
		List<Tenant> tenants = this.tenantGroupSearch(setGroupName);
		return this.tenantService.updateTenantGroup(tenants, tenantUpdate);
	}
}
