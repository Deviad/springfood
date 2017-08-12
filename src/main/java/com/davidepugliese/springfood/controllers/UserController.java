package com.davidepugliese.springfood.controllers;

import com.davidepugliese.springfood.domain.UserDAO;
import com.davidepugliese.springfood.models.User;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.davidepugliese.springfood.services.EncryptionUtilities;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/user/")
public class UserController {

    private UserDAO userService;

    @Autowired
    public UserController(UserDAO userService) {
        this.userService = userService;
    }

    @RequestMapping(value="/{id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    User getUser(@PathVariable Integer id) {

//        User user = new User();
//        user.setUsername(id);
//        user.setPassword(EncryptionUtilities.encryptPassword("Pippo"));
//        return user;

        return userService.getUser(id);

    }
    @RequestMapping(method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus( HttpStatus.CREATED )

    public @ResponseBody
    String addUser(@RequestBody User data, Model model) {
        User user = new User();
        user.setUsername(data.getUsername());
        user.setPassword(EncryptionUtilities.encryptPassword(data.getPassword()));
        this.userService.saveUser(user);
//        model.addAttribute("user", user);

        Map<String, String> response = new HashMap<>();
        response.put("status", "success");
        response.put("message", "User created successfully");
        Gson gson = new Gson();
        return gson.toJson(response);
    }
}


