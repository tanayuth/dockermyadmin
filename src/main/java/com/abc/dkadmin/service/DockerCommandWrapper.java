package com.abc.dkadmin.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DockerCommandWrapper {

    private static final Logger log = LoggerFactory.getLogger(DockerCommandWrapper.class);

    @Autowired
    private UnixProcessor unixProcessor;

    public String pullDockerImage(String imageName) {
        String result = unixProcessor.executeCommand("docker pull " + imageName);
        log.info("Pull docker image : " + imageName);
        log.info("Result : " + result);
        return result;
    }

    public String lisAllDockerImages() {
        String result = unixProcessor.executeCommand("docker images ");
        log.info("List all docker images.");
        log.info("Result : " + result);
        return result;
    }


}
