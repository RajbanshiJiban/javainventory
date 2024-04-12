package com.example.demo.service.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Category;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{
	

@Autowired	
private CategoryRepository catRepo;
	@Override
	public void addCat(Category cat) {
		catRepo.save(cat);
		
	}

	@Override
	public void deleteCat(int id) {
		catRepo.deleteById(id);
		
	}

	@Override
	public void updateCat(Category cat) {
		catRepo.save(cat);
		
	}

	

	@Override
	public List<Category> getAllCats() {
		
		return catRepo.findAll();
	}

	@Override
	public Category getCatById(int id) {
		
		return catRepo.findById(id).get();
	}

}
