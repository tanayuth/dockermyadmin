package com.abc.dkadmin.manager;

import com.abc.dkadmin.model.ContainerModel;
import com.abc.dkadmin.model.ImageModel;
import com.abc.dkadmin.service.dao.ContainerDAO;
import com.abc.dkadmin.service.dao.ImageDAO;
import com.abc.dkadmin.transport.FrontpageTransport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FrontpageManager {

    @Autowired
    private ContainerDAO containerDAO;

    @Autowired
    private ImageDAO imageDAO;

    public Map<String, List<FrontpageTransport>> buildTransport() {
        Map<String, List<FrontpageTransport>> imageMap = new HashMap<>();
        List<ImageModel> imageModelList = imageDAO.findAllImages();
        List<ContainerModel> containerModelList = containerDAO.getAllContainer();

        for (ImageModel imageModel : imageModelList) {
            List<FrontpageTransport> frontpageContainerTransportList = new ArrayList<>();
            containerModelList.stream()
                    .filter(containerModel -> isUseImage(imageModel, containerModel.getImage()))
                    .forEach(containerModel -> {
                        FrontpageTransport frontpageContainerTransport = new FrontpageTransport();
                        frontpageContainerTransport.setName(containerModel.getName());
                        frontpageContainerTransport.setId(containerModel.getId());
                        frontpageContainerTransport.setStatus(containerModel.getStatus());
                        frontpageContainerTransport.setRepository(imageModel.getRepository());
                        frontpageContainerTransport.setTag(imageModel.getTag());
                        frontpageContainerTransportList.add(frontpageContainerTransport);
                    });
            imageMap.put(imageModel.getImageId(), frontpageContainerTransportList);
        }
        return imageMap;
    }

    private boolean isUseImage(ImageModel imageModel, String containerImage) {
        return containerImage.equals(imageModel.getRepository()) ||
                containerImage.equals(imageModel.getImageId()) ||
                containerImage.equals(imageModel.getRepository() + ":" + imageModel.getTag());
    }
}
