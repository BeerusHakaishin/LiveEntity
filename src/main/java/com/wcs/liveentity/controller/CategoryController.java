package com.wcs.liveentity.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.wcs.liveentity.dto.CategoryDto;
import com.wcs.liveentity.model.Category;
import com.wcs.liveentity.model.Product;
import com.wcs.liveentity.repository.CategoryRepository;

@RestController
@RequestMapping("/categories")
public class CategoryController {

	@Autowired
	CategoryRepository categoryRepository;

	// Create a new category
	//// http://localhost:8080/categories
	@PostMapping
	public Category create(@Valid @RequestBody CategoryDto categoryDto) {
		// create a new category
		Category category = new Category();

		category.setName(categoryDto.getName());
		category.setDisplayOrder(categoryDto.getDisplayOrder());
		// On ajoute la catégorie à la BDD
		return categoryRepository.save(category);
	}

	// Get one category
	//// http://localhost:8080/categories/{id}/products
	@GetMapping("/{id}/products")
	public List<Product> getProductsFromCategory(@PathVariable(required = true) Long id) {
		// On va chercher la categorie , si elle n'existe pas on lance une exception
		Category category = categoryRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		// On retourne la liste de produits liée à la catégorie
		return category.getProducts();
	}

	// Get all categories
	//// http://localhost:8080/categories
	@GetMapping
	public List<Category> getAll() {
		return categoryRepository.findAll();
	}

	// Update one category
	//// http://localhost:8080/categories/{id}
	@GetMapping("/{id}")
	public Category update(@Valid @RequestBody CategoryDto categoryDto, @PathVariable(required = true) Long id) {
		Optional<Category> optCategory = categoryRepository.findById(id);
		// Si mon objet obtionnel ne contient pas de catégorie je renvoie une erreur
		if (optCategory.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		// Si il existe on modifie l'entité
		Category category = optCategory.get();
		category.setName(categoryDto.getName());
		category.setDisplayOrder(categoryDto.getDisplayOrder());
		// On enregistre l'entité dans la Base de Données
		return categoryRepository.save(category);
	}

	// Delete one category
	//// http://localhost:8080/categories/{id}
	@DeleteMapping("/{id}")
	public void delete(@PathVariable(required = true) Long id) {
		Boolean exist = categoryRepository.existsById(id);
		// Si ma catégorie n'existe pas je renvoie une erreur
		if (!exist) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		// Si il existe je le supprime de ma BDD
		categoryRepository.deleteById(id);
	}
}
