package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Product;



public interface ProductService {
	
	void addProd(Product prod);
	void deleteProd(int id);
	void updateProd(Product prod);
	Product getProdById(int id);
	List<Product>getAllProds();


}
