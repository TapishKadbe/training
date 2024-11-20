package com.tk.service;

import java.util.List;

import com.tk.dto.ProductDto;
import com.tk.entity.Product;

public interface ProductService {
	
	void save(ProductDto p); //Why we used ProductDto to save why not Product 
	List<Product> list();
	Product getByCode(int code);
	void delete(int code);
	
	List<Product> listByCategory(String cat);
	List<Product> listByPriceRange(double min, double max);
	List<Product> listByPriceLowToHigh();
}
