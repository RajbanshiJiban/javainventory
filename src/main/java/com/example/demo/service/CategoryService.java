package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Category;

public interface CategoryService {
	
	
	void addCat(Category cat);
	void deleteCat(int id);
	void updateCat(Category cat);
	Category getCatById(int id);
	List<Category>getAllCats();

}
