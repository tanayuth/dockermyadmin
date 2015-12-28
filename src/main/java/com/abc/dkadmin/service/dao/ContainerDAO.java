package com.abc.dkadmin.service.dao;

import com.abc.dkadmin.model.ContainerModel;
import com.abc.dkadmin.service.DockerCommandWrapper;
import com.abc.dkadmin.transformer.DockerContainerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class ContainerDAO {

    private DockerCommandWrapper commandWrapper;

    private DockerContainerTransformer containerTransformer;

    @Autowired
    public void setCommandWrapper(DockerCommandWrapper commandWrapper) {
        this.commandWrapper = commandWrapper;
    }

    @Autowired
    public void setContainerTransformer(DockerContainerTransformer containerTransformer) {
        this.containerTransformer = containerTransformer;
    }

    public List<ContainerModel> getAllContainer() {

        return containerTransformer.transform(commandWrapper.listAllDockerContainer());
    }

    public List<ContainerModel> getAllActiveContainer() {
        return containerTransformer.transform(commandWrapper.listAllDockerActiveContainer());
    }

    public boolean deleteContainerByContainerId(String containerId) {
        String result = commandWrapper.removeDockerContainer(containerId);
        return result.contains("Error") ? false : true;
    }

    public ContainerModel getContainerById(String containerId) {
        List<ContainerModel> containers = containerTransformer.transform(commandWrapper.listAllDockerContainer());
        return containers.stream()
                .filter(containerModel -> containerModel.getId().equals(containerId))
                .findFirst()
                .orElse(null);
    }

}
