package com.abc.dkadmin.controller;

import com.abc.dkadmin.exception.DockerMyAdminException;
import com.abc.dkadmin.service.DockerCommandWrapper;
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
@RequestMapping(value ="/dockermyadmin/ajax")
public class DockerMyAdminAjaxController {

    private static final Logger log = LoggerFactory.getLogger(DockerMyAdminAjaxController.class);

    @Autowired
    private DockerCommandWrapper dockerCommandWrapper;

    @RequestMapping(value ="/pullimage",  method = RequestMethod.POST)
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

    private void handleErrorResponse(HttpServletResponse response, int code, String message) {
        try {
            response.setStatus(code);
            response.getWriter().println(message);
            response.getWriter().flush();
        } catch (IOException ex) {
            throw new DockerMyAdminException("Something wrong with io", ex);
        }
    }
}