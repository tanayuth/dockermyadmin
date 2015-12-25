package com.abc.dkadmin.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
public class DockerMyAdminMvcConfiguration extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/dockermyadmin/assets/**")
                .addResourceLocations("classpath:/assets/");

        registry.addResourceHandler("/dockermyadmin/images/**")
                .addResourceLocations("classpath:/images/");
    }
}
