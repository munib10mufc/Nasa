package com.nasa.eonet.nasa.dao.services;

import java.util.List;

import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nasa.eonet.nasa.beans.dao.Sources;
import com.nasa.eonet.nasa.repositories.SourcesRepository;

@Service
@Component
public class SourcesService {

	@Autowired
	private SourcesRepository sourcesRepository;

	public List<Sources> list() {
		return sourcesRepository.findAll();
	}
}
