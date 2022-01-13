package com.wcs.liveentity.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.wcs.liveentity.dto.ProductDto;
import com.wcs.liveentity.model.Category;
import com.wcs.liveentity.model.Product;
import com.wcs.liveentity.repository.CategoryRepository;
import com.wcs.liveentity.repository.ProductRepository;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	ProductRepository productRepository;

	@Autowired
	CategoryRepository categoryRepository;

	// Create a new product
	//// http://localhost:8080/products
	@PostMapping
	public Product create(@Valid @RequestBody ProductDto productDto) {
		// On crée la liste de catégories qu'on va vouloir associer à notre nouveau
		// product
		List<Category> categories = new ArrayList<>();

		// Pour chaque id catégorie envoyé dans le product DTO
		for (Long categoryId : productDto.getCategoryIds()) {
			// On verifie si la categorie est existante
			Optional<Category> optCategory = categoryRepository.findById(categoryId);

			if (optCategory.isPresent()) {
				// Si elle est existante on la place dans la liste de categorie associée au
				// product
				categories.add(optCategory.get());
			} else {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND);
			}
		}

		Product product = new Product();

		product.setName(productDto.getName());
		product.setDescription(productDto.getDescription());
		product.setPrice(productDto.getPrice());
		product.setStock(productDto.getStock());

		product.setCategories(categories);
		return productRepository.save(product);
	}

	// Get one product
	//// http://localhost:8080/products/{id}
	@GetMapping("/{id}")
	public Product get(@PathVariable(required = true) Long id) {
		Optional<Product> optProduct = productRepository.findById(id);
		if (optProduct.isPresent()) {
			return optProduct.get();
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	}

	// Get all products
	//// http://localhost:8080/products
	@GetMapping
	public List<Product> getAll() {
		return productRepository.findAll();
	}

	// Update one product
	//// http://localhost:8080/products/{id}
	@PutMapping("/{id}")
	public Product update(@Valid @RequestBody ProductDto productDto, @PathVariable(required = true) Long id) {

		Optional<Product> optProduct = productRepository.findById(id);
		// Si mon objet optionnel contient pas de customer ,je renvoie une erreur
		if (optProduct.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		// On modifie l'entité
		Product product = optProduct.get();
		product.setName(productDto.getName());
		product.setDescription(productDto.getDescription());
		product.setPrice(productDto.getPrice());
		product.setStock(productDto.getStock());

		// On enregistre l'entité dans la Base de Données
		return productRepository.save(product);
	}

	// Delete one product
	//// http://localhost:8080/products/{id}
	@DeleteMapping("/{id}")
	public void delete(@PathVariable(required = true) Long id) {
		Boolean exist = productRepository.existsById(id);
		// Si mon product n'existe pas , je renvoie une erreur
		if (!exist) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		// Si il existe je le supprime de la Base de Données
		productRepository.deleteById(id);
	}
}
