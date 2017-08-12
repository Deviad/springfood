package com.davidepugliese.springfood.controllers;

import com.davidepugliese.springfood.domain.UserDAO;
import com.davidepugliese.springfood.models.User;
//import com.davidepugliese.springfood.services.CustomRestExceptionHandler;
import com.davidepugliese.springfood.validators.IValidator;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.davidepugliese.springfood.services.EncryptionUtilities;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/user/")
public class UserController {

    private UserDAO userService;
    private IValidator<String> validatorService;
    @Autowired
    public UserController(UserDAO userService, IValidator validatorService) {
        this.userService = userService;
        this.validatorService = validatorService;
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

    @RequestMapping(value="/username/{username:.+}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity getUserByUsername(@PathVariable String username) {

        if (this.validatorService.validate(username)) {
            Gson gson = new Gson();
            String data = gson.toJson(userService.getUserByUsername(username));
            Map<String, Object> response = new HashMap<>();
            response.put("status", "success");
            response.put("data", data);
            String json = gson.toJson(response);
            return ResponseEntity.ok(json);
        } else {
            Map<String, String> response = new HashMap<>();
            response.put("status", "fail");
            response.put("reason", "Wrong email format");
            Gson gson = new Gson();
            String json = gson.toJson(response);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(json);
        }

    }

    @RequestMapping(value="/add", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
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


