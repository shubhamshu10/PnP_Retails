package com.pnp.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.pnp.dto.ProductDto;
import com.pnp.entity.Category;
import com.pnp.entity.Product;
import com.pnp.service.CategoryService;
import com.pnp.service.ProductService;

@Controller
public class AdminController {
	public static String uploadDir = System.getProperty("user.dir")+"/src/main/resources/static/productImages";
	@Autowired
	private CategoryService service;
	@Autowired
	private ProductService productService;
    @GetMapping("/admin")  
	public String adminHome() {
		return "adminHome";
	}
    // Category
    @GetMapping("/admin/categories")
    public String getCategory(Model model) {
    	model.addAttribute("categories", service.getCategory());
    	return "categories";
    }
    @GetMapping("/admin/categories/add")
    public String addCategory(Model model) {
    	model.addAttribute("category", new Category());
    	return "categoriesAdd";
    }
    @PostMapping("/admin/categories/add")
    public String saveCategory(@ModelAttribute("category") Category category ) {
    	service.addCategory(category);
    	return "redirect:/admin/categories";
    }
    @GetMapping("/admin/categories/delete/{id}")
    public String deleteCategory(@PathVariable int id) {
    	service.removeCategoryById(id);
    	return "redirect:/admin/categories";
    }
    @GetMapping("/admin/categories/update/{id}")
    public String updateCategory(@PathVariable int id,Model model) {
    	Optional<Category> category=service.getCategoryById(id);
    	if(category.isPresent()) {
    		model.addAttribute("category", category.get());
    		return "categoriesAdd";
    	}else {
    		return "404 category not found";
    	}
    	
    }
    // Product
    @GetMapping("/admin/products")
    public String getProduct(Model model) {
    	model.addAttribute("products", productService.getAllProduct());
    	return "products";
    }
    @GetMapping("/admin/products/add")
    public String addProduct(Model model) {
    	model.addAttribute("productDTO", new ProductDto());
    	model.addAttribute("categories", service.getCategory());
    	return "productsAdd";
    }
    @PostMapping("/admin/products/add")
    public String saveProduct(@ModelAttribute("productDto") ProductDto productDto,@RequestParam("productImage")MultipartFile file,@RequestParam("imgName")String imgName)throws IOException {
        Product product = new Product();
        product.setId(productDto.getId());
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setDescription(productDto.getDescription());
        product.setWeight(productDto.getWeight());
        //product.setImageName(productDto.getImageName());
        product.setCategory(service.getCategoryById(productDto.getCategoryId()).get());
        String imageUUID;
        if(!file.isEmpty()) {
        	imageUUID = file.getOriginalFilename();
        	Path fileNameandPath = Paths.get(uploadDir, imageUUID);
        	Files.write(fileNameandPath, file.getBytes());
        }else {
        	imageUUID = imgName;
        }
        product.setImageName(imageUUID);
        productService.addProduct(product);
    	return "redirect:/admin/products";
    }
    @GetMapping("/admin/product/delete/{id}")
    public String deleteProduct(@PathVariable long id) {
    	productService.removeProduct(id);
    	return "redirect:/admin/products";
    }
    @GetMapping("/admin/product/update/{id}")
    public String updateProduct(@PathVariable long id,Model model) {
    	Product product=productService.getProductById(id).get();
    	ProductDto dto = new ProductDto();
    	dto.setId(product.getId());
    	dto.setName(product.getName());
    	dto.setDescription(product.getDescription());
    	dto.setPrice(product.getPrice());
    	dto.setWeight(product.getWeight());
    	dto.setCategoryId(product.getCategory().getId());
    	dto.setImageName(product.getImageName());
    	model.addAttribute("categories", service.getCategory());
    	model.addAttribute("productDTO", dto);
    	return "productsAdd";
    }
}
