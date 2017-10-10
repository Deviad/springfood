package com.davidepugliese.springfood.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import javafx.geometry.Pos;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.*;


//@Data
//@ToString
@Entity
@Table(name = "users") // necessary if you want the table to be named users instead of user
public class User {
//    @JsonManagedReference

    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id")


    @OrderBy("id ASC")

    @ManyToMany(mappedBy = "users", cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    protected @Getter @Setter List<Role> roles = new ArrayList<>();
    @ManyToMany(mappedBy = "users", cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    protected @Getter @Setter List<Post> posts = new ArrayList<>();

    @Id @GeneratedValue(strategy= GenerationType.AUTO) protected @Getter int id;

    @OneToOne(
            mappedBy = "user",
            cascade = CascadeType.PERSIST
    )

    protected @Getter @Setter UserInfo userInfo;


    @Column(length = 255, unique = true, nullable=false) protected @Getter @Setter String username;
    @Column(length = 255, unique = true, nullable = false) protected @Getter @Setter String password;
}
