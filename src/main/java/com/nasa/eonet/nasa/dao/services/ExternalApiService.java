package com.nasa.eonet.nasa.dao.services;

import java.util.List;

import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nasa.eonet.nasa.beans.dao.ExternalApi;
import com.nasa.eonet.nasa.repositories.ExternalApiRepository;

@Service
@Component
public class ExternalApiService {

	@Autowired
	private ExternalApiRepository externalApiRepository;

	public List<ExternalApi> list() {
		return externalApiRepository.findAll();
	}

	
	  public ExternalApi getExternalApi(String apiName) { return
	  externalApiRepository.findByUrlCategoryIgnoreCase(apiName); }
	 
}
