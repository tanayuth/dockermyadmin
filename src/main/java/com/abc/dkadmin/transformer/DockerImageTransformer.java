package com.abc.dkadmin.transformer;

import com.abc.dkadmin.model.ImageModel;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DockerImageTransformer {

    private static final Logger log = LoggerFactory.getLogger(DockerImageTransformer.class);

    private static final String IN_COMMON_SPACE = "  ";

    public List<ImageModel> transform(String consoleOutput) {
        List<ImageModel> imageModelList = new ArrayList<>();

        //Split each image
        String[] images = consoleOutput.split("\n");
        for (int i=1; i<images.length; i++) {
            log.info("Transforming to image model: \"{}\"", images[i]);
            ImageModel imageModel = new ImageModel();

            //Split each field and remove empty stuff
            List<String> imageFields = Arrays.asList(images[i].split(IN_COMMON_SPACE));
            imageFields = imageFields.stream()
                    .filter(StringUtils::isNotBlank)
                    .collect(Collectors.toList());

            //Set to model
            for (String field : imageFields) {
                imageModel = setImageModelField(imageModel, imageFields.indexOf(field), field);
            }

            //Add model to list
            imageModelList.add(imageModel);
        }
        return imageModelList;
    }

    private ImageModel setImageModelField(ImageModel imageModel, int index, String fieldValue) {
        switch (index) {
            case 0:
                imageModel.setRepository(fieldValue);
                break;
            case 1:
                imageModel.setTag(fieldValue);
                break;
            case 2:
                imageModel.setImageId(fieldValue);
                break;
            case 3:
                imageModel.setCreated(fieldValue);
                break;
            case 4:
                imageModel.setSize(fieldValue);
                break;
            default:
                log.info("field doesn't map with model");
                break;
        }
        return imageModel;
    }
}
