package com.davidepugliese.springfood.models;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.*;




@Entity
@Table(name = "roles")
public class Role {

    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id")

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "role_user",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    protected @Getter @Setter List<User> users = new ArrayList<>();
    @Id @GeneratedValue(strategy= GenerationType.AUTO) @Getter protected int id;
    @Column(length = 255, unique = true, nullable=false) protected @Getter @Setter String role;

}
