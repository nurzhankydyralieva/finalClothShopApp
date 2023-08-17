package com.epam.project.repository;


import com.epam.project.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("delete from Product where id =:id")
    Long deleteProductById(Long id);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM Product p WHERE p.vendor_id=:id", nativeQuery = true)
    void deleteByVendorId(@Param("id") Long id);





}
