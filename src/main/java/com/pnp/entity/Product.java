package com.pnp.entity;



import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
	 @Id
	 @GeneratedValue(strategy=GenerationType.AUTO)
     private long id;
     private String name;
     private double price;
     private String description;
     private  double weight;
     private String imageName;
     @ManyToOne(fetch = FetchType.LAZY)
     @JoinColumn(name = "category_id",referencedColumnName = "category_id")
     private Category category;
}
