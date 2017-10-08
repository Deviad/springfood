package com.davidepugliese.springfood.domain;

import com.davidepugliese.springfood.models.User;
import com.davidepugliese.springfood.models.UserInfo;

import java.util.List;

public interface UserInfoDAO {
    UserInfo getInfoByUserId(Integer id);
    List<User> getUsersByLastName(String lastName);
}
