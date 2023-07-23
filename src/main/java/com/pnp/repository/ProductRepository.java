package com.pnp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pnp.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
   List<Product> findAllByCategoryId(int category_id);
}
