package com.wcs.liveentity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.wcs.liveentity.repository.CartLineRepository;

@RestController
public class CartLineController {

	@Autowired
	CartLineRepository cartLineRepository;

	// Create a new cartLine
	////
	@PostMapping
	
	// Get one cartLine
	////
	@GetMapping

	// Get all cartLine
	////
	@GetMapping

	// Update one cartLine
	////
	@PutMapping

	// Delete one cartLine
	////
	@DeleteMapping

}
