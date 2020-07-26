package com.genius.framework.multidatasource.rest;

import com.genius.framework.multidatasource.entity.TenantUser;
import com.genius.framework.multidatasource.service.TenantUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api")
@RestController
public class TenantUserResource {

    @Autowired
    private TenantUserService tenantUserService;

    @GetMapping("/tenant-users")
    public List<TenantUser> findAll() {
        return tenantUserService.findAllTenantUsers();
    }

    @PostMapping("/tenant-users")
    public TenantUser createTenantUser(@RequestBody TenantUser tenantUser) {
        return tenantUserService.save(tenantUser);
    }

    @GetMapping("/tenant-users/{tenantId}")
    public List<TenantUser> findAllByTenantId(@PathVariable String tenantId) {
        return tenantUserService.findAllByTenantId(tenantId);
    }
}
