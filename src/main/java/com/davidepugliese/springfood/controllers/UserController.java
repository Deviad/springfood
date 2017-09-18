package com.davidepugliese.springfood.controllers;

import com.davidepugliese.springfood.domain.RoleDAO;
import com.davidepugliese.springfood.domain.UserDAO;
import com.davidepugliese.springfood.models.User;
import com.davidepugliese.springfood.security.Acl;
import com.davidepugliese.springfood.services.EncryptionUtilities;
import com.davidepugliese.springfood.adt.IEmail;
import com.fasterxml.jackson.annotation.JsonView;
import com.sun.javaws.exceptions.InvalidArgumentException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/user/")
public class UserController {
    @Value("${jwt.secret}")
    private String secretKey;
    private UserDAO userService;
    private RoleDAO roleService;
    @Autowired
    public UserController(UserDAO userService, RoleDAO roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }




    @RequestMapping(value="/{id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)

    public @ResponseBody
    User getUser(@PathVariable Integer id) {
        return userService.getUser(id);
    }

    @Acl("whatever")
    @RequestMapping(value="/username/{username:.+}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    public
    ResponseEntity getUserByUsername(@PathVariable String username, @RequestHeader(value="Authorization") String token) throws InvalidArgumentException {
            Object user = userService.getUserByUsername(IEmail.create(username));
            System.out.println(">>>>>>BEGIN TESTTTTTTT<<<<<<<");
            System.out.println(user);
            System.out.println(">>>>>>END TESTTTTT<<<<<<<");
            Map<String, Object> response = new HashMap<>();

            response.put("status", "success");
            response.put("data", user);

            return ResponseEntity.ok(response);
    }

    @RequestMapping(value="/role/{rolename:.+}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    public
    ResponseEntity getUsersByRole(@PathVariable String rolename, @RequestHeader(value="Authorization") String token) throws InvalidArgumentException {
        Object users = roleService.getUsersByRole(rolename);
        System.out.println(">>>>>>BEGIN TESTTTTTTT<<<<<<<");
        System.out.println(users);
        System.out.println(">>>>>>END TESTTTTT<<<<<<<");
        Map<String, Object> response = new HashMap<>();

        response.put("status", "success");
        response.put("data", users);

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
    @RequestMapping(value="/login", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus( HttpStatus.OK )
    public
    ResponseEntity login(@RequestBody User login, Model model) {


            String jwtToken;

            if (login.getUsername() == null || login.getPassword() == null) {
                Map<String, String> response = new HashMap<>();
                response.put("status", "fail");
                response.put("reason", "Insert username and password");
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(response);
            }

            String email = login.getUsername();

            String password = login.getPassword();

            User user = userService.getUserByUsername(email);

            if (user == null) {
                Map<String, String> response = new HashMap<>();
                response.put("status", "fail");
                response.put("reason", "Username not found");
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(response);
            }

            String pwd = user.getPassword();
            if (!EncryptionUtilities.matches(password, pwd)) {
                Map<String, String> response = new HashMap<>();
                response.put("status", "fail");
                response.put("reason", "Wrong password");
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(response);
            }
            jwtToken = Jwts.builder().setSubject(email).claim("roles", "user").setIssuedAt(new Date())
                    .signWith(SignatureAlgorithm.HS256, secretKey).compact();
            Map<String, Object> response = new HashMap<>();
            Object status_message = "success";
            response.put("status", status_message);
            response.put("data", jwtToken);
            return ResponseEntity.ok(response);
        }
}



