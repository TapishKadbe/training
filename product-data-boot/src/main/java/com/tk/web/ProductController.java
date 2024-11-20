package com.tk.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tk.dto.ProductDto;
import com.tk.entity.Product;
import com.tk.service.ProductService;

import jakarta.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping(value="/product")
public class ProductController {
	
	@Autowired
	private ProductService service;
	
	@PostMapping(value="/save", consumes="application/json")
	public ResponseEntity<String> save(@RequestBody @Valid ProductDto p){
		service.save(p);
		return new ResponseEntity<String>("Product has been saved", HttpStatus.OK);
	}
	
	@GetMapping(value="/list", produces="application/json")
	public ResponseEntity<List<Product>> list(){ //why i can't use List<ProductDto>??
		return new ResponseEntity<List<Product>>(service.list(), HttpStatus.OK);
	}
	
	@GetMapping(value="/{cat}", produces="application/json")
	public ResponseEntity<List<Product>> findByCategory(@PathVariable String cat){
		return new ResponseEntity<List<Product>>(service.listByCategory(cat), HttpStatus.OK);
	}
	
	@GetMapping(value="/filterLowToHigh", produces="application/json")
	public ResponseEntity<List<Product>> filterLowToHight(){
		return new ResponseEntity<List<Product>>(service.listByPriceLowToHigh(), HttpStatus.OK);
	}
	
	@GetMapping(value="/pricerange", produces="application/json")
	public ResponseEntity<List<Product>> listPriceRange(@RequestParam double min, double max){
		return new ResponseEntity<List<Product>>(service.listByPriceRange(min, max), HttpStatus.OK);
	}
	
	@GetMapping(value="/find/{code}", produces="application/json") 		//showing exception when i only keep /{code}
	public ResponseEntity<Product> find(@PathVariable int code){
		return new ResponseEntity<Product>(service.getByCode(code), HttpStatus.OK);
	}
	
	@DeleteMapping(value="/remove/{code}")
	public ResponseEntity<String> remove(@PathVariable int code){
		service.delete(code);
		return new ResponseEntity<String>("Product removed.", HttpStatus.OK);
	}
}
