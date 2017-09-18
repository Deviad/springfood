package com.davidepugliese.springfood.domain;

import com.davidepugliese.springfood.models.Role;
import com.davidepugliese.springfood.models.User;

import java.util.Set;


public interface UserDAO {

//    public User getUser();

    void saveUser(User theUser);
    User getUser(Integer id);
    User getUserByUsername(String username);
//    Set<Role> getRolesByUsername(String username);
}