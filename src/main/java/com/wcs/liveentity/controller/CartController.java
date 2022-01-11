package com.wcs.liveentity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.wcs.liveentity.repository.CartRepository;

@RestController
public class CartController {

	@Autowired
	CartRepository cartRepository;

	// Create a new cart
	////
	@PostMapping

	// Get one cart
	////
	@GetMapping
	
	// Get all cart
	////
	@GetMapping
	
	// Update one cart
	////
	@PutMapping
	
	// Delete one cart
	////
	@DeleteMapping
}
