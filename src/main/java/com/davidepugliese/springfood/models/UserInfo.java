package com.davidepugliese.springfood.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

//@ToString
@Entity
public class UserInfo {

    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id")

    @Id
    @GeneratedValue(generator = "userinfoKeyGenerator")
    @org.hibernate.annotations.GenericGenerator(
            name = "userinfoKeyGenerator",
            strategy = "foreign",
            parameters =
            @org.hibernate.annotations.Parameter(
                    name = "property", value = "user"
            )
    )
    @Getter
    private int id;
//    @NotNull
    @Column(length = 255, nullable = true)
    private @Getter @Setter String firstName;
//    @NotNull
    @Column(length = 255, nullable = true)
    private @Getter @Setter String lastName;
//    @NotNull
    @Column(length = 255, nullable = true)
    private @Getter @Setter String address;


    @OneToOne(optional = false) // Create FK constraint on PK column
    @PrimaryKeyJoinColumn
    @Getter @Setter
    protected User user;

}
