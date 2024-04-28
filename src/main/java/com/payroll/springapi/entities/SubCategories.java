package com.payroll.springapi.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "subcategories")
@Data
@RequiredArgsConstructor
public class SubCategories {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private int id;

//    @Column(name = "category_id")
//    private int categoryId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoryId", referencedColumnName = "id")
    private Categories category;

    @Column(name = "name")
    private String subcategoryName;

    @Column(name = "description")
    private String subcategoryDescription;

    @Column(name = "valid_from")
    private LocalDateTime subcategoryValidFrom;

    @Column(name = "valid_to")
    private LocalDateTime subcategoryValidTo;
}
