package com.davidepugliese.springfood.models;

import com.davidepugliese.springfood.LocalDateTimeAttributeConverter;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@ToString
@Entity
@Table(name = "posts")
public class Post implements GenericEntity {
    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id")

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "post_user",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    @JoinColumn (name="user_id", referencedColumnName="id",nullable=true,unique=false)
    protected @Getter @Setter List<User> users = new ArrayList<>();


    @Id
    @GeneratedValue(strategy= GenerationType.AUTO) protected @Getter int id;
    @Column(length = 255, unique = false, nullable=false) protected @Getter @Setter String title;
    @Column(columnDefinition = "TEXT", unique = false, nullable = false) protected String contents;
    @Convert(converter = LocalDateTimeAttributeConverter.class)
    @Column(name = "created_at") protected  @Getter @Setter
    LocalDateTime createdAt;
    @Convert(converter = LocalDateTimeAttributeConverter.class)
    @Column(name = "updated_at") protected  @Getter @Setter LocalDateTime updatedAt;
}