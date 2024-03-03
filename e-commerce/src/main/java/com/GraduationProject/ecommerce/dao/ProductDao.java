package com.GraduationProject.ecommerce.dao;

import com.GraduationProject.ecommerce.entity.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDao extends CrudRepository<Product, Integer> {

//    @Query("SELECT p FROM Product p WHERE p.productName LIKE %:searchKey% " +
//            "OR p.productDescription LIKE %:searchKey%")
    @Query("SELECT p FROM Product p WHERE p.productName LIKE CONCAT('%',:searchKey,'%') " +
            "OR p.productDescription LIKE CONCAT('%',:searchKey,'%')") // named parameter searchKey shouldn't be in '' like ':searchKey' ,as The presence of single quotes around :searchKey makes it treat :searchKey as a literal string rather than a parameter.
    List<Product> findInSearchBox(Pageable pageable, @Param("searchKey") String searchKey);

    List<Product> findAll(Pageable pageable);
}
