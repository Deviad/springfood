package com.davidepugliese.springfood.controllers;


import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class IndexController {

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    List<Greeting> greeting() {
        Greeting greeting1 = new Greeting(1, "One");
        Greeting greeting2 = new Greeting(2, "Two");
        List<Greeting> list = new ArrayList<>();
        list.add(greeting1);
        list.add(greeting2);
        return list;
    }

    public class Greeting
    {
        private String message;
        private int count;

        public Greeting(int count, String message)
        {
            this.count = count;
            this.message = message;
        }

        public String getMessage()
        {
            return message;
        }

        public void setMessage(String message)
        {
            this.message = message;
        }
    }
}