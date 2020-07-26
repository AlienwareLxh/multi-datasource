package com.genius.framework.multidatasource;

import com.genius.framework.multitenancy.config.MultiTenantDataSourceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MultiDatasourceApplication {

    public static void main(String[] args) {
        SpringApplication.run(new Class[]{MultiDatasourceApplication.class, MultiTenantDataSourceConfig.class}, args);
    }

}
