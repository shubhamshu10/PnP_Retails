package com.pnp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pnp.entity.Category;

public interface CategoryRepository extends JpaRepository<Category,Integer>{

}
