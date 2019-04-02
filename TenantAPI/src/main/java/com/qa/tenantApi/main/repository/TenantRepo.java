package com.qa.tenantApi.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.qa.tenantApi.main.entities.Tenant;

@Repository
public interface TenantRepo extends JpaRepository<Tenant,Long>{
}
