package com.qa.tenantApi.main.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.qa.tenantApi.main.Constants;
import com.qa.tenantApi.main.entities.Tenant;
import com.qa.tenantApi.main.repository.TenantRepo;

@Service
public class TenantService {

	private TenantRepo tenantRepo;

	public TenantService(TenantRepo tenantRepo) {
		this.tenantRepo = tenantRepo; 
	}

	public List<Tenant> getAllTenants() {
		return this.tenantRepo.findAll();
	}
	
	public List<Tenant> tenantSearch(Tenant tenant){
		return this.getAllTenants().stream().filter(x->x.matches(tenant)).collect(Collectors.toList());
	}
	
	public String createTenant(Tenant tenant) {
		this.tenantRepo.save(tenant);
		return Constants.getCreationMessage();
	}
	
	public String deleteTenant(Tenant tenant) {
		this.tenantRepo.delete(tenant);
		return Constants.getDeletionMessage();
	}
	
	public String deleteTenantGroup(List<Tenant> tenants) {
		this.tenantRepo.deleteAll(tenants);
		return Constants.getGroupDeletionMessage();
	}
	
	public String deleteAllTenants() {
		this.tenantRepo.deleteAll();
		return Constants.getAllDeletionMessage();
	}
	
	public String updateTenant(String id, Tenant updateTenant) {
		Tenant tenantToUpdate = this.tenantRepo.findById(id).orElse(new Tenant());
		tenantToUpdate.update(updateTenant);
		this.tenantRepo.save(tenantToUpdate);
		this.tenantRepo.delete(tenantToUpdate);
		return Constants.getUpdateMesssage();
	}
	
	public String updateTenantGroup(List<Tenant> tenants, Tenant updateTenant) {
		for(int i = 0; i<tenants.size();i++) {
			Tenant tenantToUpdate = tenants.get(i);
			this.updateTenant(tenantToUpdate.getId(),updateTenant);
		}
		return Constants.getGroupUpdateMessage();
	}
}
