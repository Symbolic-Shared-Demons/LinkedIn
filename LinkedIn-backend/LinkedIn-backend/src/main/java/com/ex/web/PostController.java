package com.ex.web;


import com.ex.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

@RestController
@RequestMapping(path="/posts")
public class PostController {

    private PostService service;

    @Autowired
    public PostController(PostService service) {
        this.service = service;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity getAllPosts() {
        try {
            return new ResponseEntity(this.service.getAllPosts(), HttpStatus.OK);
        } catch (EntityNotFoundException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(path="{id}", produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity getPostById(@PathVariable  int id){
        try{
            return new ResponseEntity((this.service.getPost(id)), HttpStatus.OK);
        }catch(EntityNotFoundException ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(path="poster/{username}", produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity getPostByUser(@PathVariable String username){
        try{
            return new ResponseEntity((this.service.getPosts(username)), HttpStatus.OK);
        }catch(EntityNotFoundException ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(path="applied/{username}", produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity getPostByApplied(@PathVariable String username){
        try{
            return new ResponseEntity((this.service.getAppliedPosts(username)), HttpStatus.OK);
        }catch(EntityNotFoundException ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(path="category/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity getPostByCategory(@PathVariable int id){
        try{
            return new ResponseEntity((this.service.getPostsByCat(id)), HttpStatus.OK);
        }catch(EntityNotFoundException ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(path="delete/{id}", produces=MediaType.TEXT_PLAIN_VALUE)
    @ResponseBody
    public String deletePost(@PathVariable int id){
        if(this.service.deletePost(id)){
            return "deleted";
        }
        else{
            return "not-deleted or not found";
        }
    }
}
