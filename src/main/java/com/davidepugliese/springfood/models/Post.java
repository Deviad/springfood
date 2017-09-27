package com.davidepugliese.springfood.models;

import com.davidepugliese.springfood.LocalDateTimeAttributeConverter;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@ToString
@Entity
public class Post {
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