package com.tk.service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tk.dto.ProductDto;
import com.tk.entity.Product;
import com.tk.repo.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductRepository repo;
	
	@Override
	public void save(ProductDto p) {
		Product prod = new Product();
		prod.setCode(p.getCode());
		prod.setCategory(p.getCategory());
		prod.setName(p.getName());
		prod.setPrice(p.getPrice());
		repo.save(prod);
	}

	@Override
	public List<Product> list() {
		return repo.findAll();
	}

	@Override
	public Product getByCode(int code) {
		return repo.findById(code).get();
	}

	@Override
	public void delete(int code) {
		//Product prod = new Product();
		repo.deleteById(code);

	}

	@Override
	public List<Product> listByCategory(String cat) {
		return repo.findAll().stream().filter(e->e.getCategory().equalsIgnoreCase(cat)).toList();
	}

	@Override
	public List<Product> listByPriceRange(double min, double max) {
		return repo.findAll().stream().filter(e->e.getPrice()>=min && e.getPrice()<=max).toList();
	}

	@Override
	public List<Product> listByPriceLowToHigh() {
		
		return repo.findAll().stream().sorted(Comparator.comparingDouble(p->p.getPrice())).collect(Collectors.toList());
	}

}
