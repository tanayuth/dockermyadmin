package com.abc.dkadmin.transformer;

import com.abc.dkadmin.model.ContainerModel;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class DockerContainerTransformer {

    private static final Logger log = LoggerFactory.getLogger(DockerContainerTransformer.class);

    private static final String IN_COMMON_SPACE = "  ";

    private static final String EXITED_STATUS = "Exited";

    public List<ContainerModel> transform(String consoleOutput) {
        if (StringUtils.isBlank(consoleOutput)) {
            return Collections.emptyList();
        }
        List<ContainerModel> containerModelList = new ArrayList<>();

        //Split each image
        String[] containers = consoleOutput.split("\n");

        for (int i = 1; i < containers.length; i++) {
            log.info("Transforming to container model: \"{}\"", containers[i]);
            ContainerModel containerModel = new ContainerModel();

            //Split each field and remove empty stuff
            List<String> containerFields = Arrays.asList(containers[i].split(IN_COMMON_SPACE));
            containerFields = containerFields.stream()
                    .filter(StringUtils::isNotBlank)
                    .collect(Collectors.toList());

            //Set to model
            for (String field : containerFields) {
                if (StringUtils.isNotBlank(containerModel.getStatus()) &&
                        containerModel.getStatus().contains(EXITED_STATUS)) {
                    containerModel =
                            setContainerModelField(containerModel, containerFields.indexOf(field), field, true);
                } else {
                    containerModel =
                            setContainerModelField(containerModel, containerFields.indexOf(field), field, false);
                }
            }

            //Add model to list
            containerModelList.add(containerModel);
        }
        return containerModelList;
    }

    private ContainerModel setContainerModelField(ContainerModel containerModel, int index, String fieldValue,
                                                  boolean isExited) {
        switch (index) {
            case 0:
                containerModel.setId(fieldValue.trim());
                break;
            case 1:
                containerModel.setImage(fieldValue.trim());
                break;
            case 2:
                containerModel.setCommand(fieldValue.trim());
                break;
            case 3:
                containerModel.setCreated(fieldValue.trim());
                break;
            case 4:
                containerModel.setStatus(fieldValue.trim());
                break;
            case 5:
                if (isExited) {
                    containerModel.setName(fieldValue.trim());
                } else {
                    containerModel.setPorts(fieldValue.trim());
                }
                break;
            case 6:
                containerModel.setName(fieldValue.trim());
                break;
            default:
                log.info("field doesn't map with model");
                break;
        }
        return containerModel;
    }
}
