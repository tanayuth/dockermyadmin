package com.abc.dkadmin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DockerMyAdminController {

    @RequestMapping(value ="/")
    public String index() {
        return "index";
    }
}
