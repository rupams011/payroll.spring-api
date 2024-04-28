package com.payroll.springapi.repositories;

import com.payroll.springapi.entities.SubCategories;
import com.payroll.springapi.queryObjects.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface SubCategoryRepository extends JpaRepository<SubCategories, Integer> {

//    @Query(value = """
//            select s from SubCategories s inner join Categories c\s
//            on c.id = s.categoryId\s
//            where :currentTimestamp between s.subcategoryValidFrom and s.subcategoryValidTo\s
//            and :currentTimestamp between c.categoryValidFrom and c.categoryValidTo\s
//            order by s.id asc\s
//            """)
@Query(value = "select new com.payroll.springapi.queryObjects.SubCategory(s.id, c.id, c.categoryName, s.subcategoryName, c.categoryDescription, s.subcategoryDescription) " +
        "from SubCategories s inner join Categories c on c.id = s.categoryId " +
        "where :currentTimestamp between s.subcategoryValidFrom and s.subcategoryValidTo " +
        "and :currentTimestamp between c.categoryValidFrom and c.categoryValidTo order by s.id asc")
        public List<SubCategory> findAllActive(LocalDateTime currentTimestamp);
}
