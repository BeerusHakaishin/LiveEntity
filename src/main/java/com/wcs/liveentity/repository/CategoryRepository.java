package com.wcs.liveentity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wcs.liveentity.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
