package com.payroll.springapi.services;

import com.payroll.springapi.entities.SubCategories;
import com.payroll.springapi.queryObjects.SubCategory;
import com.payroll.springapi.repositories.SubCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SubCategoryService {

    private SubCategoryRepository subCategoryRepository = null;

    @Autowired
    public SubCategoryService(SubCategoryRepository subCategoryRepository){
        this.subCategoryRepository = subCategoryRepository;
    }


    public List<SubCategories> getSubcategories() {
        return subCategoryRepository.findAll();
    }

    public List<SubCategory> getActiveSubCategories() {
        return subCategoryRepository.findAllActive(LocalDateTime.now());
    }
}
