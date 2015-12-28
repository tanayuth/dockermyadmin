package com.abc.dkadmin.controller;

import com.abc.dkadmin.manager.FrontpageManager;
import com.abc.dkadmin.model.ImageModel;
import com.abc.dkadmin.service.dao.ImageDAO;
import com.abc.dkadmin.transport.FrontpageTransport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value ="/dockermyadmin")
public class DockerMyAdminController {

    @Autowired
    private FrontpageManager frontpageManager;

    @Autowired
    private ImageDAO imageDAO;

    @RequestMapping(value ={"/", "/index.html"})
    public String index(Model model) {
        Map<String, List<FrontpageTransport>> imageMap = frontpageManager.buildTransport();
        List<ImageModel> imageModelList = imageDAO.findAllImages();
        model.addAttribute("imageMap", imageMap);
        model.addAttribute("imageModelList", imageModelList);
        return "index";
    }
}
