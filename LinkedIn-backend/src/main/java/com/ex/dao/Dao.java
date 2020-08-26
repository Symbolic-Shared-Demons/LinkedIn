package com.ex.dao;

import com.ex.models.User;
import com.ex.models.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;


@Repository
public interface Dao {

    User addNewUser(User u);

    User getExistingUser(String u);

    List<Category> getAllCats();

    List<Post> getAllPosts();

    int deletePost(Post p);

    User userApply(String u, int p);

    User deleteApplied(User u, Post p);

    int deleteUser(String u);

    User updateUser(User u);

    boolean checkCreds(String username);

    List<User> getAllUsers();

    boolean checkCreds(String username, String password);

    Post getPostById(int p);

    Category getCategoryById(int c);

    List<Post> getPostsByCategory(Category c);

    User addCategoryForUser(Category c, User u);

    User addPostForUser(User u, Post p);

    List<Post> getPostsByUser(User u);

    Set<Post> getPostsByApplied(User u);

    Category addNewCategory(Category c);

    Post addNewPost(Post p);

}

