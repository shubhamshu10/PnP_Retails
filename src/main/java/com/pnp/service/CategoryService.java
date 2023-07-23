package com.pnp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pnp.entity.Category;
import com.pnp.repository.CategoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService {
	  @Autowired
      private CategoryRepository categoryRepo;
      public List<Category> getCategory(){
    	  return categoryRepo.findAll();
      }
      public void addCategory(Category category) {
    	  categoryRepo.save(category);
      }
      public void removeCategoryById(int id) {
    	  categoryRepo.deleteById(id);
      }
      public Optional<Category> getCategoryById(int id) {
    	  return categoryRepo.findById(id);
      }
}
