package com.abc.dkadmin.service;

import com.abc.dkadmin.configuration.DockerMyAdminConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DockerMyAdminConfiguration.class)
public class DockerCommandWrapperTest {

    @Autowired
    private DockerCommandWrapper dockerCommandWrapper;

    @Test
    public void testPullDockerImage() throws Exception {
        //dockerCommandWrapper.pullDockerImage("tozd/mongodb");
        //dockerCommandWrapper.listAllDockerImages();
        //dockerCommandWrapper.listAllDockerContainer();
        //dockerCommandWrapper.listAllDockerActiveContainer();
        //dockerCommandWrapper.removeDockerImage("63949f45e5d2");
        //dockerCommandWrapper.stopDockerContainer("dd30d683fe4a");
        //dockerCommandWrapper.startDockerContainer("dd30d683fe4a");
        //dockerCommandWrapper.listAllPortOfContainer("dd30d683fe4a");
        dockerCommandWrapper.getInformationOfImageOrContainer("dd30d683fe4a");
    }
}