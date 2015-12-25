package com.abc.dkadmin.configuration;

import com.abc.dkadmin.properties.DockerMyAdminProperties;
import com.abc.dkadmin.service.DockerCommandWrapper;
import com.abc.dkadmin.service.UnixProcessor;
import com.abc.dkadmin.service.dao.ConfigDAO;
import com.abc.dkadmin.service.dao.ContainerDAO;
import com.abc.dkadmin.transformer.DockerContainerTransformer;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import liquibase.integration.spring.SpringLiquibase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

@Configuration
public class DockerMyAdminConfiguration {

    @Bean
    public DockerMyAdminProperties dockerMyAdminProperties() {
        return new DockerMyAdminProperties();
    }

    @Bean
    public UnixProcessor unixProcessor() {
        return new UnixProcessor();
    }

    @Bean
    public DockerCommandWrapper dockerCommandWrapper() {
        return new DockerCommandWrapper();
    }

    @Bean
    public DockerContainerTransformer dockerContainerTransformer(){
        return new DockerContainerTransformer();
    }

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
    public ConfigDAO configDAO() {
        return new ConfigDAO();
    }

//    @Bean
//    public ContainerDAO containerDAO() {
//        return new ContainerDAO();
//    }
}


