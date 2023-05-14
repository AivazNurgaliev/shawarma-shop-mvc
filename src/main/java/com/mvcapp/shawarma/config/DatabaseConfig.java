package com.mvcapp.shawarma.config;

import com.zaxxer.hikari.HikariDataSource;
import org.flywaydb.core.Flyway;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

public class DatabaseConfig {
    @Bean
    public DataSource dataSource() {
        // create and configure the DataSource
        return new HikariDataSource();
    }

    @Bean(initMethod = "migrate")
    public Flyway flyway(DataSource dataSource) {
        // create and configure the Flyway instance
        Flyway flyway = Flyway.configure()
                .dataSource(dataSource)
                .load();
        return flyway;
    }
}
