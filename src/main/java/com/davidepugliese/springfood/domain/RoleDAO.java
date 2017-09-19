package com.davidepugliese.springfood.domain;

import com.davidepugliese.springfood.models.Role;
import com.davidepugliese.springfood.models.User;

import java.util.List;
import java.util.Set;

public interface RoleDAO {
    void saveRole(Role rolename);
    List<User> getUsersByRole(String role);
    List<String> getRoles();
}
