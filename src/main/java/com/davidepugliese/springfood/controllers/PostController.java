package com.davidepugliese.springfood.controllers;

import com.davidepugliese.springfood.domain.PostDAO;
import com.davidepugliese.springfood.domain.UserDAO;
import com.davidepugliese.springfood.models.Post;
import com.davidepugliese.springfood.models.User;
import com.davidepugliese.springfood.security.Acl;
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

import java.sql.Timestamp;
import java.text.DateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/post/")
public class PostController {
    @Value("${jwt.secret}")
    private String secretKey;
    private UserDAO userService;
    private PostDAO postService;
    @Autowired
    public PostController(UserDAO userService, PostDAO postService) {
        this.userService = userService;
        this.postService = postService;
    }




    @RequestMapping(value="/{id}", method=RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)

    public @ResponseBody
    Post getPost(@PathVariable Integer id) {
        return postService.getPost(id);
    }

    @RequestMapping(value="/", method=RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)

    public @ResponseBody
    List<Post> getPosts() {
        return postService.getPosts();
    }



    //TODO: this must be completed
//    @Acl("whatever")
//    @RequestMapping(value="/username/{username:.+}", method=RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
//    public
//    ResponseEntity getPostsByUsername(@PathVariable String username, @RequestHeader(value="Authorization") String token) throws InvalidArgumentException {
//        Object user = userService.get(IEmail.create(username));
//        System.out.println(">>>>>>BEGIN TESTTTTTTT<<<<<<<");
//        System.out.println(user);
//        System.out.println(">>>>>>END TESTTTTT<<<<<<<");
//        Map<String, Object> response = new HashMap<>();
//
//        response.put("status", "success");
//        response.put("data", user);
//
//        return ResponseEntity.ok(response);
//    }


//    @Acl("whatever")
    @RequestMapping(value="/add", method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus( HttpStatus.CREATED )
    public
    ResponseEntity addPost(@RequestBody Post data) {

        try {
            Post post = new Post();
            post.setTitle(data.getTitle());
            post.setContents(data.getContents());
            LocalDateTime current = LocalDateTime.now();
            post.setCreatedAt(current);
            this.postService.savePost(post);

            Map<String, String> response = new HashMap<>();
            response.put("status", "success");
            response.put("message", "Post created successfully");
            return ResponseEntity.ok(response);
        } catch (DataIntegrityViolationException e) {
            Map<String, String> response = new HashMap<>();
            response.put("status", "fail");
            response.put("reason", "There was an error");
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(response);
        }
    }

    @RequestMapping(value="/delete", method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)

    public @ResponseBody
    void deletePost(@RequestBody Integer id) {
        this.postService.deletePost(id);
    }
}



