package com.abc.dkadmin.service.dao;

import com.abc.dkadmin.ConfigurationTest;
import com.abc.dkadmin.model.ConfigModel;
import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ConfigurationTest.class)
public class ConfigDAOTest {

    @Autowired
    private ConfigDAO configDAO;

    private ConfigModel configModel;

    @Before
    public void setUp() throws Exception {

        configModel = new ConfigModel();
        configModel.setContainerId("containerID1");
        configModel.setOther("other");
        configModel.setName("docker1");
        configModel.setModifiedTime(new DateTime("2015-01-01"));
        configModel.setCreatedTime(new DateTime("2015-01-01"));
    }

    @Test
    public void testInsert() throws Exception {

        ConfigModel newModel = configDAO.insert(configModel);
        Assert.assertNotNull(newModel);

        configModel.setName("docker2");
        configModel.setContainerId("containerID2");
        configDAO.update(configModel);

        newModel = configDAO.findById(0L);
        Assert.assertEquals(0L, newModel.getId());
        Assert.assertEquals("docker2", newModel.getName());
        Assert.assertEquals("containerID2", newModel.getContainerId());
        Assert.assertEquals("other", newModel.getOther());
        Assert.assertEquals("2015-01-01", newModel.getModifiedTime().toString("yyyy-MM-dd"));
        Assert.assertEquals("2015-01-01", newModel.getCreatedTime().toString("yyyy-MM-dd"));

        newModel = configDAO.findByContainerId("containerID2");
        Assert.assertEquals(0L, newModel.getId());
        Assert.assertEquals("docker2", newModel.getName());
        Assert.assertEquals("containerID2", newModel.getContainerId());
        Assert.assertEquals("other", newModel.getOther());
        Assert.assertEquals("2015-01-01", newModel.getModifiedTime().toString("yyyy-MM-dd"));
        Assert.assertEquals("2015-01-01", newModel.getCreatedTime().toString("yyyy-MM-dd"));

        ConfigModel sameNameModel = new ConfigModel();
        sameNameModel.setContainerId("contentId3");
        sameNameModel.setOther("other");
        sameNameModel.setName("docker2");
        sameNameModel.setModifiedTime(new DateTime("2015-01-01"));
        sameNameModel.setCreatedTime(new DateTime("2015-01-01"));

        configDAO.insert(sameNameModel);
        Assert.assertEquals(2, configDAO.findByName("docker2").size());
        Assert.assertEquals(0, configDAO.findByName("docker1").size());
    }

}