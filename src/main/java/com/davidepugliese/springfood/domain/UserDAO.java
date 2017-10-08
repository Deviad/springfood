package com.davidepugliese.springfood.domain;

import com.davidepugliese.springfood.models.Role;
import com.davidepugliese.springfood.models.User;
import com.davidepugliese.springfood.models.UserInfo;


public interface UserDAO {

//    public User getUser();

    void saveUser(User theUser);
    void updateUser(User theUser);
    void updateUserInfo(UserInfo userInfo);
    User getUser(Integer id);
    User getUserByUsername(String username);
//    Set<Role> getRolesByUsername(String username);
}