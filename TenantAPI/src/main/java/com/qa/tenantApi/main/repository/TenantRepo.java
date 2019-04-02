package com.qa.tenantApi.main.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.qa.tenantApi.main.entities.Tenant;

@Repository
public interface TenantRepo extends MongoRepository<Tenant,String>{
}
