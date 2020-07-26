package com.genius.framework.multidatasource.service;

import com.genius.framework.multidatasource.entity.TenantUser;
import com.genius.framework.multidatasource.repository.TenantUserRepository;
import com.genius.framework.multitenancy.event.TenantEvent;
import com.genius.framework.multitenancy.provider.MultiTenantContextProvider;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@EnableAspectJAutoProxy(exposeProxy = true)
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

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public List<TenantUser> findAllByTenantId(String tenantId){
        TenantUserService tenantUserService = (TenantUserService) AopContext.currentProxy();
        MultiTenantContextProvider.setCurrentTenant(tenantId);
        return tenantUserService.findAll();
    }

    @Transactional(readOnly = true)
    public List<TenantUser> findAll() {
        publisher.publishEvent(new TenantEvent(this));
        return tenantUserRepository.findAll();
    }
}
