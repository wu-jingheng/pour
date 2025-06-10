package com.moxie.pour.controller;

import com.moxie.pour.common.ApiResponse;
import com.moxie.pour.dto.TenantDto;
import com.moxie.pour.service.TenantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tenants")
@Tag(name = "Tenant", description = "Tenant management APIs")
public class TenantController {

    private final TenantService tenantService;

    public TenantController(TenantService tenantService) {
        this.tenantService = tenantService;
    }

    @Operation(summary = "Create a tenant")
    @PostMapping
    public ResponseEntity<ApiResponse<TenantDto>> create(@Valid @RequestBody TenantDto tenantDto) {
        TenantDto tenant = tenantService.createTenant(tenantDto);
        return ResponseEntity.ok(new ApiResponse<>(tenant));
    }

    @Operation(summary = "Get all tenants (paginated)")
    @GetMapping("/paged")
    public ResponseEntity<ApiResponse<Page<TenantDto>>> getAllPaged(Pageable pageable) {
        Page<TenantDto> page = tenantService.getAllTenants(pageable);
        return ResponseEntity.ok(new ApiResponse<>(page));
    }

    @Operation(summary = "Get all tenants (non-paginated)")
    @GetMapping
    public ResponseEntity<ApiResponse<List<TenantDto>>> getAll() {
        List<TenantDto> tenantsDto = tenantService.getAllTenants();
        return ResponseEntity.ok(new ApiResponse<>(tenantsDto));
    }

    @Operation(summary = "Get tenant by ID")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<TenantDto>> getById(@PathVariable Long id) {
        TenantDto tenantDto = tenantService.getTenantById(id);
        return ResponseEntity.ok(new ApiResponse<>(tenantDto));
    }
}
