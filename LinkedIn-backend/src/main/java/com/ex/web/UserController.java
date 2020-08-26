package com.ex.web;

import com.ex.models.Post;
import com.ex.models.User;
import com.ex.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

@RestController
@RequestMapping(path="/users")
public class UserController {
    private UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity getAllUsers() {
        System.out.println("hit");
        try {
            return new ResponseEntity(this.service.getAllUsers(), HttpStatus.OK);
        } catch (EntityNotFoundException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(path="/getUser/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity getOtherUser(@PathVariable String username) {
        try {
            User u = this.service.getUser(username);
            return new ResponseEntity(u, HttpStatus.OK);
        } catch (EntityNotFoundException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping(path="/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity getUserByUsername(@PathVariable String username) {
        try {
            User u = this.service.logInUser(username);
            return new ResponseEntity(u, HttpStatus.OK);
        } catch (EntityNotFoundException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value="/addNewUser", method = RequestMethod.POST, produces =  MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity getNewUser(@RequestParam("username") String username,@RequestParam("password") String password,
                                     @RequestParam("first_name") String firstName, @RequestParam("last_name") String lastName,
                                     @RequestParam("email") String email){
            User u = new User();
            u.setUsername(username);
            u.setEmail(email);
            u.setFirstName(firstName);
            u.setLastName(lastName);
            u.setPassword(password);
        try{
            return new ResponseEntity(this.service.getNewUser(u), HttpStatus.OK);
        }catch(EntityNotFoundException ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path="deleteUser/{username}", produces = MediaType.TEXT_PLAIN_VALUE)
    public String deleteUser(@PathVariable String username){

        this.service.deleteUser(username);
        return "deleted";

    }

    @GetMapping(path="{username}/apply/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity applyUser(@PathVariable int id, @PathVariable String username){
        try{
            return new ResponseEntity(this.service.applyUser(username,id), HttpStatus.OK);
        }catch(EntityNotFoundException ex){
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path="editUser/category/{username}/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity addCategoryUser(@PathVariable int id, @PathVariable String username){
        try{
            return new ResponseEntity(this.service.addCategory(id,username), HttpStatus.OK);
        }catch(EntityNotFoundException ex){
            return new ResponseEntity(ex.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path="addPost/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity addPostUser(@PathVariable int id, @RequestBody Post p){
        try {
            return new ResponseEntity(this.service.addPost(id, p), HttpStatus.OK);
        }catch(EntityNotFoundException ex){
            return new ResponseEntity(ex.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path="checkCreds", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity checkCreds(@RequestParam("username") String username,@RequestParam("password") String password){
        try {
            return new ResponseEntity(this.service.checkCreds(username,password), HttpStatus.OK);
        }catch(EntityNotFoundException ex){
            return new ResponseEntity(ex.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path="deleteApply/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity deleteApply(@RequestBody Post p, @PathVariable String username){
        try {
            return new ResponseEntity(this.service.deleteApply(username,p), HttpStatus.OK);
        }catch(EntityNotFoundException ex){
            return new ResponseEntity(ex.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

}
