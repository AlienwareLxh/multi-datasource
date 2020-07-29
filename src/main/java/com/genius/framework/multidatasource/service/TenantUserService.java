package com.genius.framework.multidatasource.service;

import com.genius.framework.multidatasource.entity.TenantUser;
import com.genius.framework.multidatasource.repository.TenantUserRepository;
import com.genius.framework.multitenancy.annotation.MultiTenancyMethod;
import com.genius.framework.multitenancy.annotation.MultiTenancyRepository;
import com.genius.framework.multitenancy.annotation.MultiTenancyService;
import com.genius.framework.multitenancy.event.TenantEvent;
import com.genius.framework.multitenancy.provider.MultiTenantContextProvider;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@MultiTenancyService
public class TenantUserService {

    @Autowired
    private ApplicationEventPublisher publisher;

    private TenantUserRepository tenantUserRepository;

    public TenantUserService(
            TenantUserRepository tenantUserRepository
    ) {
        this.tenantUserRepository = tenantUserRepository;
    }

    @Transactional(readOnly = true)
    public List<TenantUser> findAllTenantUsers() {
        return tenantUserRepository.findAll();
    }

    @Transactional
    public TenantUser save(TenantUser tenantUser) {
        return tenantUserRepository.save(tenantUser);
    }

    @MultiTenancyMethod
    public List<TenantUser> findAllByTenantId(String tenantId){
        List<TenantUser> users = new ArrayList<>();
        TenantUserService tenantUserService = (TenantUserService) AopContext.currentProxy();
        // tenant tenantId
        MultiTenantContextProvider.setCurrentTenant(tenantId);
        List<TenantUser> tenantUsers = tenantUserService.findAll();
        if (tenantUsers.size() > 0) {
            users.addAll(tenantUsers);
        }

        // tenant tenant_01
        MultiTenantContextProvider.setCurrentTenant("tenant_01");
        List<TenantUser> annotationUser = tenantUserService.findAllByAnnotation();
        if (annotationUser.size() > 0) {
            users.addAll(annotationUser);
        }

        // tenant tenant_default
        List<TenantUser> tenantUsers1 = tenantUserService.findAll();
        if (tenantUsers1.size() > 0) {
            users.addAll(tenantUsers1);
        }
        return users;
    }

    @Transactional(readOnly = true)
    public List<TenantUser> findAll() {
        publisher.publishEvent(new TenantEvent(this));
        return tenantUserRepository.findAll();
    }

    @MultiTenancyRepository
    @Transactional(readOnly = true)
    public List<TenantUser> findAllByAnnotation() {
//        publisher.publishEvent(new TenantEvent(this));
        return tenantUserRepository.findAll();
    }
}
