package com.wcs.liveentity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wcs.liveentity.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
