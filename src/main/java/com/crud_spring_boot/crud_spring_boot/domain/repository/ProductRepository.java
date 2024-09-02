package com.crud_spring_boot.crud_spring_boot.domain.repository;

import com.crud_spring_boot.crud_spring_boot.domain.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
}
