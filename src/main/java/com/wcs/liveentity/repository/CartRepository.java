package com.wcs.liveentity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wcs.liveentity.model.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {

}
