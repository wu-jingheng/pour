package com.moxie.pour.mapper;

import com.moxie.pour.dto.TenantDto;
import com.moxie.pour.model.Tenant;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TenantMapper {
    TenantDto toDto(Tenant tenant);
    Tenant toEntity(TenantDto tenantDto);
}
