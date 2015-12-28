package com.abc.dkadmin.service.dao;

import com.abc.dkadmin.ConfigurationTest;
import com.abc.dkadmin.configuration.DockerMyAdminConfiguration;
import com.abc.dkadmin.model.ContainerModel;
import com.abc.dkadmin.properties.DockerMyAdminProperties;
import com.abc.dkadmin.service.DockerCommandWrapper;
import com.abc.dkadmin.transformer.DockerContainerTransformer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ConfigurationTest.class)
public class ContainerDAOTest {

    private ContainerDAO containerDAO;

    @Autowired
    private DockerContainerTransformer dockerContainerTransformer;

    private DockerCommandWrapper dockerCommandWrapper;

    private final String ALL_CONTAINER = "CONTAINER ID        IMAGE                     COMMAND                  CREATED              STATUS                          PORTS               NAMES\n" +
            "f02b9cfcf9b3        windperson/docker-whale   \"/bin/sh -c '/usr/gam\"   About a minute ago   Exited (0) About a minute ago                       yuttana_v2\n" +
            "ef4d37a69653        windperson/docker-whale   \"/bin/sh -c '/usr/gam\"   57 minutes ago       Exited (0) 57 minutes ago                           yuttana\n";

    @Before
    public void setUp() throws Exception {
        containerDAO = new ContainerDAO();

        dockerCommandWrapper = Mockito.mock(DockerCommandWrapper.class);
        containerDAO.setCommandWrapper(dockerCommandWrapper);

        containerDAO.setContainerTransformer(dockerContainerTransformer);
    }

    @Test
    public void testGetAllContainer() {


        Mockito.when(dockerCommandWrapper.listAllDockerContainer()).thenReturn(ALL_CONTAINER);
        List<ContainerModel> containers = containerDAO.getAllContainer();
        Assert.assertEquals("f02b9cfcf9b3", containers.get(0).getId());
        Assert.assertEquals("windperson/docker-whale", containers.get(0).getImage());
        Assert.assertEquals("\"/bin/sh -c '/usr/gam\"", containers.get(0).getCommand());
        Assert.assertEquals("About a minute ago", containers.get(0).getCreated());
        Assert.assertEquals("Exited (0) About a minute ago", containers.get(0).getStatus());
        Assert.assertEquals("yuttana_v2", containers.get(0).getName());
        Assert.assertEquals(2, containers.size());
    }

    @Test
    public void testGetAllActiveContainer() {

        String ACTIVE_CONTAINER = "CONTAINER ID        IMAGE                   COMMAND             CREATED             STATUS              PORTS                    NAMES\n" +
                "792ee3df22ab        amedia/docker-escenic   \"/docker/run.sh\"    3 days ago          Up 3 days           0.0.0.0:8081->8080/tcp   escenic_tang_v5\n";

        Mockito.when(dockerCommandWrapper.listAllDockerActiveContainer()).thenReturn(ACTIVE_CONTAINER);
        List<ContainerModel> containers = containerDAO.getAllActiveContainer();
        Assert.assertEquals("792ee3df22ab", containers.get(0).getId());
        Assert.assertEquals("amedia/docker-escenic", containers.get(0).getImage());
        Assert.assertEquals("\"/docker/run.sh\"", containers.get(0).getCommand());
        Assert.assertEquals("3 days ago", containers.get(0).getCreated());
        Assert.assertEquals("Up 3 days", containers.get(0).getStatus());
        Assert.assertEquals("0.0.0.0:8081->8080/tcp", containers.get(0).getPorts());
        Assert.assertEquals("escenic_tang_v5", containers.get(0).getName());
        Assert.assertEquals(1, containers.size());
    }

    @Test
    public void testDeleteConatinerByContainerId() {
        String containerId = "792ee3df22ab";
        String wrongContainerId = "f02b9cfcf9b";

        String removeError = "Error response from daemon: no such id: f02b9cfcf9b\n" +
                "Error: failed to remove containers: [f02b9cfcf9b]\n";

        Mockito.when(dockerCommandWrapper.removeDockerContainer("792ee3df22ab")).thenReturn(containerId);
        Mockito.when(dockerCommandWrapper.removeDockerContainer("f02b9cfcf9b")).thenReturn(removeError);

        Assert.assertTrue(containerDAO.deleteContainerByContainerId(containerId));
        Assert.assertFalse(containerDAO.deleteContainerByContainerId(wrongContainerId));
    }

    @Test
    public void testGetContainerById() {
        Mockito.when(dockerCommandWrapper.listAllDockerContainer()).thenReturn(ALL_CONTAINER);
        Assert.assertEquals("f02b9cfcf9b3", containerDAO.getContainerById("f02b9cfcf9b3").getId());
    }

    @Test
    public void testGetContainerByIdShouldReturnNull() {
        Mockito.when(dockerCommandWrapper.listAllDockerContainer()).thenReturn(ALL_CONTAINER);
        Assert.assertNull(containerDAO.getContainerById("noid"));
    }


}