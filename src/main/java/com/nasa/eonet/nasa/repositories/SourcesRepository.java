package com.nasa.eonet.nasa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nasa.eonet.nasa.beans.dao.Sources;

public interface SourcesRepository extends JpaRepository<Sources, Long> {
}
