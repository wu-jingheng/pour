package com.moxie.multi_tenant.blog.controller;

import com.moxie.multi_tenant.blog.dto.TenantDto;
import com.moxie.multi_tenant.blog.mapper.TenantMapper;
import com.moxie.multi_tenant.blog.model.Tenant;
import com.moxie.multi_tenant.blog.repository.TenantRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tenants")
public class TenantController {

    private final TenantRepository tenantRepository;

    private final TenantMapper tenantMapper;

    public TenantController(TenantRepository tenantRepository, TenantMapper tenantMapper) {
        this.tenantRepository = tenantRepository;
        this.tenantMapper = tenantMapper;
    }

    @GetMapping
    public List<TenantDto> getAll() {
        return tenantRepository.findAll().stream()
                .map(tenantMapper::toDto)
                .toList();
    }

    @PostMapping
    public TenantDto create(@RequestBody TenantDto tenantDto) {
        Tenant tenant = tenantMapper.toEntity(tenantDto);
        tenant = tenantRepository.save(tenant);
        return tenantMapper.toDto(tenant);
    }

    @GetMapping("/{id}")
    public TenantDto getById(@PathVariable Long id) {
        Tenant tenant = tenantRepository.findById(id).orElseThrow();
        return tenantMapper.toDto(tenant);
    }
}
