package com.pnp.global;

import java.util.ArrayList;
import java.util.List;

import com.pnp.entity.Product;

public class GlobalData {
     public static List<Product> cart;
     static {
    	 cart= new ArrayList<Product>();
     }
}
