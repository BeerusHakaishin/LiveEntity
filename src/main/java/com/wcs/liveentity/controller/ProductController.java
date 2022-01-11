package com.wcs.liveentity.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wcs.liveentity.dto.ProductDto;
import com.wcs.liveentity.model.Category;
import com.wcs.liveentity.model.Product;
import com.wcs.liveentity.repository.CategoryRepository;
import com.wcs.liveentity.repository.ProductRepository;

@RestController
@RequestMapping("products")
public class ProductController {

	@Autowired
	ProductRepository productRepository;

	@Autowired
	CategoryRepository categoryRepository;

	// Create a new product
	//// http://localhost:8080/products
	@PostMapping
	public Product create(@Valid @RequestBody ProductDto productDto) {
		Product product = new Product();

		product.setName(productDto.getName());
		product.setDescription(productDto.getDescription());
		product.setPrice(productDto.getPrice());
		product.setStock(productDto.getStock());
		// On crée la liste de catégories qu'on va vouloir associer à notre nouveau
		// product
		List<Category> categories = new ArrayList<Category>();

		// Pour chaque id catégorie envoyé dans le product DTO
		for (Long categoryId : productDto.getCategoryIds()) {
			// On verifie si la categorie est existante
			Optional<Category> optCategory = categoryRepository.findById(categoryId);

			if (optCategory.isPresent()) {
				// Si elle est existante on la place dans la liste de categorie associée au
				// product
				categories.add(optCategory.get());
			}
		}
		product.setCategories(categories);
		return productRepository.save(product);
	}

	// Get one product
	////

	// Get all products
	////
	@GetMapping
	public List<Product> getAll() {
		return productRepository.findAll();
	}

	// Update one product
	////

	// Delete one product
	////
}
