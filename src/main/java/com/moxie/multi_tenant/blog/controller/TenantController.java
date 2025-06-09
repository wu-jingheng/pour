package com.moxie.multi_tenant.blog.controller;

import com.moxie.multi_tenant.blog.common.ApiResponse;
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

    @PostMapping
    public ResponseEntity<ApiResponse<TenantDto>> create(@Valid @RequestBody TenantDto tenantDto) {
        TenantDto tenant = tenantService.createTenant(tenantDto);
        return ResponseEntity.ok(new ApiResponse<>(tenant));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<TenantDto>>> getAll() {
        List<TenantDto> tenantsDto = tenantService.getAllTenants();
        return ResponseEntity.ok(new ApiResponse<>(tenantsDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<TenantDto>> getById(@PathVariable Long id) {
        TenantDto tenantDto = tenantService.getTenantById(id);
        return ResponseEntity.ok(new ApiResponse<>(tenantDto));
    }
}
