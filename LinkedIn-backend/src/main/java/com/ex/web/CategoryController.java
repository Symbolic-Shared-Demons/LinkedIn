package com.ex.web;

import com.ex.models.Category;
import com.ex.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;


@RestController
@RequestMapping(path="/categories")
public class CategoryController {

    private CategoryService service;

    @Autowired
    public CategoryController(CategoryService service) {
            this.service = service;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity getAllCategories() {
        try {
            return new ResponseEntity(this.service.getAllCats(), HttpStatus.OK);
        } catch (EntityNotFoundException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping(path="{id}", produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity getCategoryById(@PathVariable int id){
        try{
            return new ResponseEntity((this.service.getCategory(id)), HttpStatus.OK);
        }catch(EntityNotFoundException ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}
