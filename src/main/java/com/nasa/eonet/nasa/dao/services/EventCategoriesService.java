package com.nasa.eonet.nasa.dao.services;

import java.util.List;

import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nasa.eonet.nasa.beans.dao.EventCategories;
import com.nasa.eonet.nasa.repositories.EventCategoriesRepository;

@Service
@Component
public class EventCategoriesService {

	@Autowired
	private EventCategoriesRepository eventCategoriesRepository;

	public List<EventCategories> list() {
		return eventCategoriesRepository.findAll();
	}
}
