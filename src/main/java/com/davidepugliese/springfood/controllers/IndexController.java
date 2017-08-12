package com.davidepugliese.springfood.controllers;


import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexController {

    @RequestMapping(value = {"", "/", "/index"}, method = RequestMethod.GET)
    public String index() {
        return "index";
    }


}