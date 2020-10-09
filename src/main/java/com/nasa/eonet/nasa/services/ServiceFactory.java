package com.nasa.eonet.nasa.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceFactory {

	@Autowired
	private List<ServiceHandlerInterface> services;

	private static final Map<String, ServiceHandlerInterface> myServiceCache = new HashMap<>();

	@PostConstruct
	public void initMyServiceCache() {
		for (ServiceHandlerInterface service : services) {
			myServiceCache.put(service.getServiceName(), service);
		}
	}

	public static ServiceHandlerInterface getService(String type) {
		ServiceHandlerInterface service = myServiceCache.get(type);
		if (service == null)
			throw new RuntimeException("Unknown service type: " + type);
		return service;
	}
}
