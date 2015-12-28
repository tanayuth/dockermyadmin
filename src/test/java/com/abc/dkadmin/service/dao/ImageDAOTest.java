package com.abc.dkadmin.service.dao;

import com.abc.dkadmin.ConfigurationTest;
import com.abc.dkadmin.transformer.DockerContainerTransformer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ConfigurationTest.class)
public class ImageDAOTest {

    @Autowired
    private DockerContainerTransformer dockerContainerTransformer;

    private ImageDAO imageDAO;

    private final String ALL_IMAGES = "REPOSITORY                TAG                 IMAGE ID            CREATED             VIRTUAL SIZE\n" +
            "windperson/docker-whale   latest              8cd451eccdc0        4 weeks ago         255.5 MB\n" +
            "webcenter/activemq        latest              8afeb54b82c5        4 months ago        543.3 MB";


    @Before
    public void setUp() throws Exception {
        imageDAO = new ImageDAO();
    }

    @Test
    public void TestFindByRepository(){

    }
}