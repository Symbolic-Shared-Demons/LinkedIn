package com.ex.service;

import com.ex.dao.HibDao;
import com.ex.models.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private HibDao dao;

    @Autowired
    public CategoryService(HibDao dao){
        this.dao = dao;
    }

    public List<Category> getAllCats() {
        return dao.getAllCats();
    }

    public Category getCategory(int id) {
        return dao.getCategoryById(id);
    }


}
