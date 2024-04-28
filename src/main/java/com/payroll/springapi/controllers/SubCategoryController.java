package com.payroll.springapi.controllers;

import com.payroll.springapi.entities.SubCategories;
import com.payroll.springapi.queryObjects.SubCategory;
import com.payroll.springapi.services.SubCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/app/subcategories")
public class SubCategoryController {

    private SubCategoryService subCategoryService = null;

    @Autowired
    public SubCategoryController(SubCategoryService subCategoryService){
        this.subCategoryService = subCategoryService;
    }

    @GetMapping(path = "/get-all")
    public ResponseEntity<List<SubCategories>> getAllSubCategories(){
        return ResponseEntity.ok(subCategoryService.getSubcategories());
    }

    @GetMapping(path = "/get-all-active")
    public ResponseEntity<List<SubCategory>> getAllActiveSubCategories(){
        return ResponseEntity.ok(subCategoryService.getActiveSubCategories());
    }
}
