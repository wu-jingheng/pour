package com.moxie.multi_tenant.blog.controller;

import com.moxie.multi_tenant.blog.dto.TenantDto;
import com.moxie.multi_tenant.blog.service.TenantService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tenants")
public class TenantController {

    private final TenantService tenantService;

    public TenantController(TenantService tenantService) {
        this.tenantService = tenantService;
    }

    @GetMapping
    public List<TenantDto> getAll() {
        return tenantService.getAllTenants();
    }

    @PostMapping
    public ResponseEntity<TenantDto> create(@Valid @RequestBody TenantDto tenantDto) {
        return ResponseEntity.ok(tenantService.createTenant(tenantDto));
    }

    @GetMapping("/{id}")
    public TenantDto getById(@PathVariable Long id) {
        return tenantService.getTenantById(id);
    }
}
