package com.wcs.liveentity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wcs.liveentity.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
