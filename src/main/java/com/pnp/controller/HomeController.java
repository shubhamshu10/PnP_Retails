package com.pnp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.pnp.global.GlobalData;
import com.pnp.service.CategoryService;
import com.pnp.service.ProductService;

@Controller
public class HomeController {
	@Autowired
	private ProductService productService;
	@Autowired
	private CategoryService categoryService;
	
   @GetMapping({"/","/home"})	
   public String home(Model model) {
	   model.addAttribute("cartCount", GlobalData.cart.size());
	   return "index";
   }
   @GetMapping("/shop")
   public String shop(Model model) {
	   model.addAttribute("categories", categoryService.getCategory());
	   model.addAttribute("products", productService.getAllProduct());
	   model.addAttribute("cartCount", GlobalData.cart.size());
	   return "shop";
   }
   @GetMapping("/shop/category/{id}")
   public String shopByCategory(Model model, @PathVariable int id) {
	   model.addAttribute("categories", categoryService.getCategory());
	   model.addAttribute("products", productService.getProductByCategoryId(id));
	   model.addAttribute("cartCount", GlobalData.cart.size());
	   return "shop";
   }
   @GetMapping("/shop/viewproduct/{id}")
   public String viewProduct(Model model, @PathVariable int id) {
	   model.addAttribute("product", productService.getProductById(id).get());
	   model.addAttribute("cartCount", GlobalData.cart.size());
	   return "viewProduct";
   }
   
}
