package com.payroll.springapi.repositories;

import com.payroll.springapi.entities.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface CategoryRepository extends JpaRepository<Categories, Integer> {

    @Query(value = """
            select c from Categories c where :currentTimestamp between c.categoryValidFrom and c.categoryValidTo\s
            """)
    public List<Categories> findAllActive(LocalDateTime currentTimestamp);
}
