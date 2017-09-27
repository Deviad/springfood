package com.davidepugliese.springfood.domain;

import com.davidepugliese.springfood.models.Post;

import java.util.List;
import java.util.Set;


public interface PostDAO {
    void savePost(Post thePost);
    void deletePost(Integer thePostId);
    Post getPost(Integer thePostId);
    List<Post> getPosts();
}
