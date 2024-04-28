package com.payroll.springapi.queryObjects;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SubCategory {
    private int id;
    private int categoryId;
    private String categoryName;
    private String subcategoryName;
    private String categoryDescription;
    private String subcategoryDescription;
}
