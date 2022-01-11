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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.wcs.liveentity.dto.CustomerDto;
import com.wcs.liveentity.model.Customer;
import com.wcs.liveentity.repository.CustomerRepository;

@RestController
@RequestMapping("/customers")
public class CustomerController {

	@Autowired
	CustomerRepository customerRepository;

	// Create customer
	//// http://localhost:8080/customers
	@PostMapping
	public Customer create(@Valid @RequestBody CustomerDto customerDto) {
		// Create a new customer
		Customer customer = new Customer();
		customer.setFirstname(customerDto.getFirstname());
		customer.setAddress(customerDto.getAddress());
		customer.setBirthdate(customerDto.getBirthdate());
		customer.setEmail(customerDto.getEmail());
		customer.setLastname(customerDto.getLastname());
		customer.setPassword(customerDto.getPassword());
		customer.setPhone(customerDto.getPhone());

		return customerRepository.save(customer);

	}

	// Get one customer
	//// http://localhost:8080/customers/{id}
	@GetMapping("/{id}")
	public Customer get(@PathVariable(required = true) Long id) {
		Optional<Customer> optCustomer = customerRepository.findById(id);
		if (optCustomer.isPresent()) {
			return optCustomer.get();
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	}

	// Get all customers
	//// http://localhost:8080/customers
	@GetMapping
	public List<Customer> getAll() {
		return customerRepository.findAll();
	}

	// Update one customer
	//// http://localhost:8080/customers/{id}
	@PutMapping("/{id}")
	public Customer update(@Valid @RequestBody CustomerDto customerDto, @PathVariable(required = true) Long id) {

		Optional<Customer> optCustomer = customerRepository.findById(id);
		// Si mon objet optionnel contient pas de customer ,je renvoie une erreur
		if (optCustomer.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		// On modifie l'entité
		Customer customer = optCustomer.get();
		customer.setFirstname(customerDto.getFirstname());
		customer.setAddress(customerDto.getAddress());
		customer.setBirthdate(customerDto.getBirthdate());
		customer.setEmail(customerDto.getEmail());
		customer.setLastname(customerDto.getLastname());
		customer.setPassword(customerDto.getPassword());
		customer.setPhone(customerDto.getPhone());
		// On enregistre l'entité dans la Base de Données
		return customerRepository.save(customer);
	}

	// Delete one customer
	//// http://localhost:8080/customers/{id}
	@DeleteMapping("/{id}")
	public void delete(@PathVariable(required = true) Long id) {
		Boolean exist = customerRepository.existsById(id);
		// Si mon customer n'existe pas , je renvoie une erreur
		if (!exist) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		// Si il existe je le supprime de la Base de Données
		customerRepository.deleteById(id);
	}
}
