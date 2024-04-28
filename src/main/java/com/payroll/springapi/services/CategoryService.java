package com.payroll.springapi.services;

import com.payroll.springapi.entities.Categories;
import com.payroll.springapi.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CategoryService {

    private CategoryRepository categoryRepository = null;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }


    public List<Categories> getcategories() {
        return categoryRepository.findAll();
    }

    public List<Categories> getActivecategories() {
        return categoryRepository.findAllActive(LocalDateTime.now());
    }
}
