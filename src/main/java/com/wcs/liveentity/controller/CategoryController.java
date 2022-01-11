package com.wcs.liveentity.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wcs.liveentity.model.Category;
import com.wcs.liveentity.repository.CategoryRepository;

@RestController
public class CategoryController {

	@Autowired
	CategoryRepository categoryRepository;

	// Create a new category
	////

	// Get one category
	////

	// Get all categories
	////
	@GetMapping
	public List<Category> getAll() {
		return categoryRepository.findAll();
	}

	// Update one category
	////

	// Delete one category
	////
}
