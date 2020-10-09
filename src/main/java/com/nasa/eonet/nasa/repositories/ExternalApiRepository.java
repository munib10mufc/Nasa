package com.nasa.eonet.nasa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nasa.eonet.nasa.beans.dao.ExternalApi;

@Repository
public interface ExternalApiRepository extends JpaRepository<ExternalApi, Long> {
	ExternalApi findByUrlCategoryIgnoreCase(String apiName);
}
