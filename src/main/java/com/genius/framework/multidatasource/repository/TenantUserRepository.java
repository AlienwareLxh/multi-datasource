package com.genius.framework.multidatasource.repository;

import com.genius.framework.multidatasource.entity.TenantUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TenantUserRepository extends JpaRepository<TenantUser, String> {
}
