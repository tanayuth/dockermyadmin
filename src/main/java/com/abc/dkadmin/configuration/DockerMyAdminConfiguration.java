package com.abc.dkadmin.configuration;

import com.abc.dkadmin.properties.DockerMyAdminProperties;
import com.abc.dkadmin.service.DockerCommandWrapper;
import com.abc.dkadmin.service.UnixProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
}


