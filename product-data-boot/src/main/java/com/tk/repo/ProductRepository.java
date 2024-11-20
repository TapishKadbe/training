package com.tk.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tk.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
