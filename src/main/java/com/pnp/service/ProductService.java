package com.pnp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pnp.entity.Product;
import com.pnp.repository.ProductRepository;

@Service
public class ProductService {
    @Autowired
	private ProductRepository repo;
	public List<Product> getAllProduct(){
		return repo.findAll();
	}
	public void addProduct(Product product) {
		repo.save(product);
	}
	public void removeProduct(long id) {
		repo.deleteById(id);
	}
	public Optional<Product> getProductById(long id){
		return repo.findById(id);
	}
	public List<Product> getProductByCategoryId(int category_id){
		return repo.findAllByCategoryId(category_id);
		
	}
}
