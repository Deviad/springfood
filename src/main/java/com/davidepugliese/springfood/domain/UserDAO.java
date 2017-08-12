package com.davidepugliese.springfood.domain;

import com.davidepugliese.springfood.models.User;

import java.util.List;



public interface UserDAO {

//    public User getUser();

    void saveUser(User theUser);
    User getUser(Integer id);
    User getUserByEmail(String email);
}