package com.abc.dkadmin.properties;

import com.abc.dkadmin.exception.DockerMyAdminException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Properties;

@Component
public class DockerMyAdminProperties {

    private static final Logger log = LoggerFactory.getLogger(DockerMyAdminProperties.class);

    private Properties properties;

    public DockerMyAdminProperties() {
        this.properties = new Properties();
        try {
            properties.load(this.getClass().getResourceAsStream("/dockermyadmin.properties"));
        } catch (IOException e) {
            log.error("Cannot load property file", e);
            throw new DockerMyAdminException("Cannot load property file", e);
        }
    }

    protected String getValue(String propertyKey) {
        return properties.getProperty(propertyKey);
    }

    public String getSuperUserPassword() {
        return getValue("superuser.password");
    }
}
