package com.moxie.pour.service;

import com.moxie.pour.dto.TenantDto;
import com.moxie.pour.mapper.TenantMapper;
import com.moxie.pour.model.Tenant;
import com.moxie.pour.repository.TenantRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TenantService {
    private final TenantRepository tenantRepository;

    private final TenantMapper tenantMapper;


    public TenantService(TenantRepository tenantRepository, TenantMapper tenantMapper) {
        this.tenantRepository = tenantRepository;
        this.tenantMapper = tenantMapper;
    }

    public TenantDto createTenant(TenantDto tenantDto) {
        Tenant tenant = tenantMapper.toEntity(tenantDto);
        tenant = tenantRepository.save(tenant);
        return tenantMapper.toDto(tenant);
    }

    public List<TenantDto> getAllTenants() {
        return tenantRepository.findAll().stream()
                .map(tenantMapper::toDto)
                .toList();
    }

    public TenantDto getTenantById(Long id) {
        Tenant tenant = tenantRepository.findById(id).orElseThrow();
        return tenantMapper.toDto(tenant);
    }
}
