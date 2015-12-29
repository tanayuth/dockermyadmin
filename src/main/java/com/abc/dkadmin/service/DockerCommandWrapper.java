package com.abc.dkadmin.service;

import com.abc.dkadmin.model.ContainerModel;
import com.abc.dkadmin.transformer.DockerContainerTransformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DockerCommandWrapper {

    private static final Logger log = LoggerFactory.getLogger(DockerCommandWrapper.class);

    @Autowired
    private DockerContainerTransformer containerTransformer;

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

    public String createDockerContainer(String param, String imageId) {
        param = param.replaceAll("--rm", " ");
        param = param.replaceAll("-d", " ");
        String result = unixProcessor.executeCommand("docker run -d " + param + " " + imageId);
        log.info("create docker container: docker run -d " + param + " " + imageId);
        log.info("Result : " + result);
        return result;
    }

    public String deleteNoneAsciiChars() {
        String result = unixProcessor.executeCommand("docker rmi $(docker images | grep \"^<none>\" | awk '{print $3}')");
        log.info("Delete none-asciee chars ");
        log.info("Result : " + result);
        return result;
    }

    public String deleteAllUnusedContainers() {
        String result = unixProcessor.executeCommand("docker ps -q -a | xargs docker rm");
        log.info("Delete unused container ");
        log.info("Result : " + result);
        return result;
    }

    public String deleteUntaggedImages() {
        String result = unixProcessor.executeCommand("docker ps -q -a | xargs docker rm");
        log.info("Delete unused container ");
        log.info("Result : " + result);
        return result;
    }

    public String restartDockerEngine() {
        List<ContainerModel> containerModelList = containerTransformer.transform(listAllDockerActiveContainer());
        String result = unixProcessor.executeCommand("sudo service docker restart");
        log.info("Restart Docker Engine.");
        for (ContainerModel containerModel : containerModelList) {
          result +=  startDockerContainer(containerModel.getId());
        }
        log.info("Result : " + result);
        return result;
    }


}
