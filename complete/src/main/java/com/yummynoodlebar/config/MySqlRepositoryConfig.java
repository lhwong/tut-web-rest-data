package com.yummynoodlebar.config;

import org.hibernate.dialect.MySQL5Dialect;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.yummynoodlebar.persistence.repository.OrdersRepository;

@Configuration
//@Profile("mysql")
//@EnableJpaRepositories("org.cloudfoundry.samples.music.repositories.jpa")
@EnableJpaRepositories(basePackages = "com.yummynoodlebar.persistence.repository",
includeFilters = @ComponentScan.Filter(value = {OrdersRepository.class}, type = FilterType.ASSIGNABLE_TYPE))
public class MySqlRepositoryConfig extends AbstractJpaRepositoryConfig {

    protected String getHibernateDialect() {
        return MySQL5Dialect.class.getName();
    }

}
