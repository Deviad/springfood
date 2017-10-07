package com.davidepugliese.springfood.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

//@ToString
@Entity
public class UserInfo {
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

    @JsonIgnore
    @OneToOne(optional = false) // Create FK constraint on PK column
    @PrimaryKeyJoinColumn
    @Getter @Setter
    protected User user;

}
