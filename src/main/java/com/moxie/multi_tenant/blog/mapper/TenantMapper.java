package com.moxie.multi_tenant.blog.mapper;

import com.moxie.multi_tenant.blog.dto.TenantDto;
import com.moxie.multi_tenant.blog.model.Tenant;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TenantMapper {
    TenantDto toDto(Tenant tenant);
    Tenant toEntity(TenantDto tenantDto);
}
