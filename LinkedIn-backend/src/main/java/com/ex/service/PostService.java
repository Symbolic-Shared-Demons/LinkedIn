package com.ex.service;

import com.ex.dao.HibDao;
import com.ex.models.Category;
import com.ex.models.Post;
import com.ex.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class PostService {

    private HibDao dao;

    @Autowired
    public PostService(HibDao dao){
        this.dao = dao;
    }

    public List<Post> getAllPosts(){
        return dao.getAllPosts();
    }

    public Post getPost(int id){
        return dao.getPostById(id);
    }

    public List<Post> getPosts(String username) {

        User user = dao.getExistingUser(username);

        return dao.getPostsByUser(user);

    }

    public Set<Post> getAppliedPosts(String username) {

        User user = dao.getExistingUser(username);

        return dao.getPostsByApplied(user);
    }

    public List<Post> getPostsByCat(int id) {

        Category c = dao.getCategoryById(id);

        return dao.getPostsByCategory(c);
    }

    public boolean deletePost(int id){
        Post post = dao.getPostById(id);

        if(dao.deletePost(post) >= 1){
            return true;
        }
        else{
            return false;
        }
    }
}
