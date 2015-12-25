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

    public String listAllDockerImages() {
        String result = unixProcessor.executeCommand("docker images ");
        log.info("List all docker images.");
        log.info("Result : " + result);
        return result;
    }

    public String listAllDockerContainer() {
        String result = unixProcessor.executeCommand("docker ps -a");
        log.info("List all docker containers.");
        log.info("Result : " + result);
        return result;
    }

    public String listAllDockerActiveContainer() {
        String result = unixProcessor.executeCommand("docker ps");
        log.info("List all active containers.");
        log.info("Result : " + result);
        return result;
    }

    public String removeDockerImage(String imageName) {
        String result = unixProcessor.executeCommand("docker rmi " + imageName);
        log.info("Delete image : {} .", imageName);
        log.info("Result : " + result);
        return result;
    }

    public String removeDockerContainer(String containerId) {
        String result = unixProcessor.executeCommand("docker rm " + containerId);
        log.info("Delete container : {} .", containerId);
        log.info("Result : " + result);
        return result;
    }

    public String startDockerContainer(String containerId) {
        String result = unixProcessor.executeCommand("docker start " + containerId);
        log.info("Start container : {} .", containerId);
        log.info("Result : " + result);
        return result;
    }

    public String reStartDockerContainer(String containerId) {
        String result = unixProcessor.executeCommand("docker restart " + containerId);
        log.info("Re-start container : {} .", containerId);
        log.info("Result : " + result);
        return result;
    }

    public String stopDockerContainer(String containerId) {
        String result = unixProcessor.executeCommand("docker stop " + containerId);
        log.info("Stop container : {} .", containerId);
        log.info("Result : " + result);
        return result;
    }

    public String listAllPortOfContainer(String containerId) {
        String result = unixProcessor.executeCommand("docker port " + containerId);
        log.info("List port of container : {} .", containerId);
        log.info("Result : " + result);
        return result;
    }

    public String getInformationOfImageOrContainer(String imageOrContainerId) {
        String result = unixProcessor.executeCommand("docker inspect " + imageOrContainerId);
        log.info("Information of  image/container : {} .", imageOrContainerId);
        log.info("Result : " + result);
        return result;
    }


}
