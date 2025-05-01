package com.project.catalog.repository;

import com.project.catalog.entity.Product;
import com.project.catalog.record.ProductRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findByCode(String code);

}
