package com.epam.project.repository;


import com.epam.project.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("delete from Product where id =:id")
    Long deleteProductById(Long id);
}
