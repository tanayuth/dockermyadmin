package com.abc.dkadmin.controller;

import com.abc.dkadmin.exception.ContainerNotFoundException;
import com.abc.dkadmin.exception.DockerMyAdminException;
import com.abc.dkadmin.model.ContainerModel;
import com.abc.dkadmin.service.DockerCommandWrapper;
import com.abc.dkadmin.service.dao.ContainerDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping(value = "/dockermyadmin/ajax")
public class DockerMyAdminAjaxController {

    private static final Logger log = LoggerFactory.getLogger(DockerMyAdminAjaxController.class);

    @Autowired
    private DockerCommandWrapper dockerCommandWrapper;

    private ContainerDAO containerDAO;

    @Autowired
    public void setContainerDAO(ContainerDAO containerDAO) {
        this.containerDAO = containerDAO;
    }

    @RequestMapping(value = "/pullimage", method = RequestMethod.POST)
    @ResponseBody
    public String pullImage(@RequestParam(value = "imagename") String imageName,
                            HttpServletResponse response) {
        try {
            String result = dockerCommandWrapper.pullDockerImage(imageName);
            log.info(result);
            if (result.toLowerCase().contains("error")) {
                handleErrorResponse(response, HttpStatus.BAD_REQUEST.value(), result);
                return null;
            }
            return "Pull image " + imageName + " Success. ";
        } catch (Exception ex) {
            handleErrorResponse(response, HttpStatus.BAD_REQUEST.value(), "Can not pull image " + imageName);
        }
        return null;
    }

    @RequestMapping(value ="/container/stop",  method = RequestMethod.POST)
    @ResponseBody
    public String stopContainer(@RequestParam(value = "containerid") String containerId, HttpServletResponse response) {
        try {
            dockerCommandWrapper.stopDockerContainer(containerId);
            Thread.sleep(3000);
            return "Container id: " + containerId + " stopped.";
        } catch (Exception ex) {
            handleErrorResponse(response, HttpStatus.BAD_REQUEST.value(), "Can not stop container id: " + containerId);
        }
        return null;
    }

    @RequestMapping(value ="/image/delete",  method = RequestMethod.DELETE)
    @ResponseBody
    public void deleteImage(@RequestParam(value = "imagename") String imageName, HttpServletResponse response) {
        try {
            dockerCommandWrapper.removeDockerImage(imageName);
        } catch (Exception ex) {
            handleErrorResponse(response, HttpStatus.BAD_REQUEST.value(), "Can not delete image name: " + imageName);
        }
    }

    private void handleErrorResponse(HttpServletResponse response, int code, String message) {
        try {
            response.setStatus(code);
            response.getWriter().println(message);
            response.getWriter().flush();
        } catch (IOException ex) {
            throw new DockerMyAdminException("Something wrong with io", ex);
        }
    }

    @RequestMapping(value = "/start/{containerid}", method = RequestMethod.POST)
    @ResponseBody
    public ContainerModel startContainer(@PathVariable(value = "containerid") String containerId) {
        String result = dockerCommandWrapper.startDockerContainer(containerId);
        validateContainerResult(result, containerId);
        return containerDAO.getContainerById(containerId);
    }

    @RequestMapping(value = "/restart/{containerid}", method = RequestMethod.POST)
    @ResponseBody
    public ContainerModel restartContainer(@PathVariable(value = "containerid") String containerId) {
        String result = dockerCommandWrapper.reStartDockerContainer(containerId);
        validateContainerResult(result, containerId);
        return containerDAO.getContainerById(containerId);
    }

    @RequestMapping(value = "/stop/{containerid}")
    @ResponseBody
    public ContainerModel stopContainer(@PathVariable(value = "containerid") String containrId) {

        String result = dockerCommandWrapper.stopDockerContainer(containrId);
        validateContainerResult(result, containrId);
        return containerDAO.getContainerById(containrId);
    }

    private boolean validateContainerResult(String resultMessage, String containerId) {

        if (resultMessage.toLowerCase().contains("error")) {
            throw new ContainerNotFoundException(containerId);
        }
        return true;
    }
}
