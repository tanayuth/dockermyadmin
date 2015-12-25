package com.abc.dkadmin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value ="/dockermyadmin")
public class DockerMyAdminController {

    @RequestMapping(value ={"/", "/index.html"})
    public String index() {
        return "index";
    }
}
