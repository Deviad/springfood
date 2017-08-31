package com.davidepugliese.springfood.controllers;

import com.davidepugliese.springfood.domain.UserDAO;
import com.davidepugliese.springfood.models.User;
import com.davidepugliese.springfood.services.EncryptionUtilities;
import com.davidepugliese.springfood.adt.IEmail;
import com.google.gson.Gson;
import com.sun.javaws.exceptions.InvalidArgumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

//@CrossOrigin(origins = "http://localhost:5001")
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
        return userService.getUser(id);
    }

    @RequestMapping(value="/username/{username:.+}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    public
    ResponseEntity getUserByUsername(@PathVariable String username) throws InvalidArgumentException {

            Object data = userService.getUserByUsername(IEmail.create(username));
            Map<String, Object> response = new HashMap<>();
            response.put("status", "success");
            response.put("data", data);
            return ResponseEntity.ok(response);
    }

    @RequestMapping(value="/add", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus( HttpStatus.CREATED )
    public
    ResponseEntity addUser(@RequestBody User data, Model model) {

        try {
            User user = new User();
            user.setUsername(data.getUsername());
            user.setPassword(EncryptionUtilities.encryptPassword(data.getPassword()));
            this.userService.saveUser(user);
            Map<String, String> response = new HashMap<>();
            response.put("status", "success");
            response.put("message", "User created successfully");
            return ResponseEntity.ok(response);
        } catch (DataIntegrityViolationException e) {
            Map<String, String> response = new HashMap<>();
            response.put("status", "fail");
            response.put("reason", "Username exists already");
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(response);
        }
    }
}


