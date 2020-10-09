package com.nasa.eonet.nasa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nasa.eonet.nasa.beans.dao.EventCategories;

public interface EventCategoriesRepository extends JpaRepository<EventCategories, Long> {
}

