package com.abc.dkadmin.service;

import com.abc.dkadmin.configuration.DockerMyAdminConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DockerMyAdminConfiguration.class)
public class DockerCommandWrapperTest {

    @Autowired
    private DockerCommandWrapper dockerCommandWrapper;

    @Test
    public void testPullDockerImage() throws Exception {
        //String result;
        //dockerCommandWrapper.pullDockerImage("tozd/mongodb");
        //String result = dockerCommandWrapper.listAllDockerImages();
        //result = dockerCommandWrapper.listAllDockerContainer();
        //dockerCommandWrapper.listAllDockerActiveContainer();
        //dockerCommandWrapper.removeDockerImage("63949f45e5d2");
        // result = dockerCommandWrapper.removeDockerContainer("cc011a4337da");
        //dockerCommandWrapper.stopDockerContainer("dd30d683fe4a");
        //dockerCommandWrapper.startDockerContainer("dd30d683fe4a");
        //dockerCommandWrapper.listAllPortOfContainer("dd30d683fe4a");
        //dockerCommandWrapper.getInformationOfImageOrContainer("dd30d683fe4a");
    }
}