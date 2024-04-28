package com.payroll.springapi.controllers;

import com.payroll.springapi.entities.Categories;
import com.payroll.springapi.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/app/categories")
public class CategoryController {

    private CategoryService categoryService = null;

    @Autowired
    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @GetMapping(path = "/get-all")
    public ResponseEntity<List<Categories>> getAllCategories(){
        return ResponseEntity.ok(categoryService.getcategories());
    }

    @GetMapping(path = "/get-all-active")
    public ResponseEntity<List<Categories>> getAllActiveCategories(){
        return ResponseEntity.ok(categoryService.getActivecategories());
    }
}
