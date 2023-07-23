package com.pnp.dto;



import com.pnp.entity.Category;

import lombok.Data;
@Data
public class ProductDto {
	
    private long id;
    private String name;
    private double price;
    private String description;
    private  double weight;
    private String imageName;  
    private int categoryId;
}
