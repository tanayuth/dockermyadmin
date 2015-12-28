package com.abc.dkadmin;

import com.abc.dkadmin.service.dao.ConfigDAO;
import com.abc.dkadmin.transformer.DockerContainerTransformer;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import liquibase.integration.spring.SpringLiquibase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

@Configuration
public class ConfigurationTest {

    @Bean
    public DataSource dataSource() throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setDriverClass("org.hsqldb.jdbcDriver");
        dataSource.setJdbcUrl("jdbc:hsqldb:mem://");
        dataSource.setDataSourceName("a");
        return dataSource;
    }

    @Bean
    public SpringLiquibase liquibase(DataSource dataSource) {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setDataSource(dataSource);
        liquibase.setChangeLog("classpath:/db/changelog/dkmyadmin.db.changelog.xml");
        liquibase.setDropFirst(true);
        return liquibase;
    }

    @Bean
    public DockerContainerTransformer dockerContainerTransformer() {
        return new DockerContainerTransformer();
    }

    @Bean
    public ConfigDAO configDAO() {
        return new ConfigDAO();
    }
}