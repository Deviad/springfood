package com.davidepugliese.springfood.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.*;


@Data
@ToString
@Entity
@Table(name = "users") // necessary if you want the table to be named users instead of user
public class User {
//    @JsonManagedReference
    @ManyToMany(mappedBy = "users")
    @OrderBy("id ASC")
    protected @Getter @Setter List<Role> roles = new ArrayList<>();
    @Id @GeneratedValue(strategy= GenerationType.AUTO) protected @Getter int id;
    @Column(length = 255, unique = true, nullable=false) protected @Getter @Setter String username;
    @Column(length = 255, unique = true, nullable = false) protected @Getter @Setter String password;
}
