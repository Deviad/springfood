package com.davidepugliese.springfood.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Data
@Entity
@Table(name = "users") // necessary if you want the table to be named users instead of user
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    @Column(length = 255, unique = true, nullable=false)
    private @Getter @Setter String username;
    @Column(length = 255, unique = true, nullable = false)
    private @Getter @Setter String password;
}
