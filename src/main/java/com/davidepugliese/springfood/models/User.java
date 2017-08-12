package com.davidepugliese.springfood.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Data
@Entity
@Table(name = "users") // not necessary but useful to know on the fly where data are saved exactly
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    @Column(length = 255)
    private @Getter @Setter String username;
    @Column(length = 255)
    private @Getter @Setter String password;
}
