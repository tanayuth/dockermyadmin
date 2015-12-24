package com.abc.dkadmin.service.dao;

import com.abc.dkadmin.model.ImageModel;
import com.abc.dkadmin.service.DockerCommandWrapper;
import com.abc.dkadmin.transformer.DockerImageTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageDAO {

    private DockerCommandWrapper dockerCommandWrapper;

    @Autowired
    public void setDockerCommandWrapper(DockerCommandWrapper dockerCommandWrapper) {
        this.dockerCommandWrapper = dockerCommandWrapper;
    }

    private DockerImageTransformer dockerImageTransformer;

    @Autowired
    public void setDockerImageTransformer(DockerImageTransformer dockerImageTransformer) {
        this.dockerImageTransformer = dockerImageTransformer;
    }

    public ImageModel findByRepository(String repository) {

        List<ImageModel> imageModelList = dockerImageTransformer.transform(dockerCommandWrapper.listAllDockerImages());
        return imageModelList.stream()
                .filter(imageModel -> imageModel.getRepository().equals(repository))
                .findFirst()
                .orElse(null);
    }

    public ImageModel findByImageId(String imageId) {

        List<ImageModel> imageModelList = dockerImageTransformer.transform(dockerCommandWrapper.listAllDockerImages());
        return imageModelList.stream()
                .filter(imageModel -> imageModel.getImageId().equals(imageId))
                .findFirst()
                .orElse(null);
    }
}
