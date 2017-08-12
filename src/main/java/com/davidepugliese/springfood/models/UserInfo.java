package com.davidepugliese.springfood.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

public @Data class UserInfo {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    @NotNull
    @Column(length = 255)
    private @Getter @Setter String name;
    @NotNull
    @Column(length = 255)
    private @Getter @Setter String surname;
    @NotNull
    @Column(length = 255)
    private @Getter @Setter String Address;

}
