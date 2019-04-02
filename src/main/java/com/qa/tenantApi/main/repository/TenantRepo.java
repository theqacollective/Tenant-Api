package com.qa.tenantApi.main.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.qa.tenantApi.main.entities.Tenant;

@Repository
public interface TenantRepo extends MongoRepository<Tenant,String>{
	public Tenant getLandlordsByFirstNameAndLastName(String firstName, String lastName);
}
