package com.moxie.multi_tenant.blog.controller;

import com.moxie.multi_tenant.blog.model.Tenant;
import com.moxie.multi_tenant.blog.repository.TenantRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tenants")
public class TenantController {

    private final TenantRepository tenantRepository;

    public TenantController(TenantRepository tenantRepository) {
        this.tenantRepository = tenantRepository;
    }

    @GetMapping
    public List<Tenant> getAll() {
        return tenantRepository.findAll();
    }

    @PostMapping
    public Tenant create(@RequestBody Tenant tenant) {
        return tenantRepository.save(tenant);
    }

    @GetMapping("/{id}")
    public Tenant getById(@PathVariable Long id) {
        return tenantRepository.findById(id).orElseThrow();
    }
}
