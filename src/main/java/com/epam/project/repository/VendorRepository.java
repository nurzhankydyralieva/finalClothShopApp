package com.epam.project.repository;


import com.epam.project.model.entity.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface VendorRepository extends JpaRepository<Vendor, Long> {

    Vendor findVendorById(Long id);

}
