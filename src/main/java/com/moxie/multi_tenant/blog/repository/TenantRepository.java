package com.moxie.multi_tenant.blog.repository;

import com.moxie.multi_tenant.blog.model.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TenantRepository extends JpaRepository<Tenant, Long> {
}
