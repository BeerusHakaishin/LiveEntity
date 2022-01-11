package com.wcs.liveentity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wcs.liveentity.model.CartLine;

public interface CartLineRepository extends JpaRepository<CartLine, Long> {

}
