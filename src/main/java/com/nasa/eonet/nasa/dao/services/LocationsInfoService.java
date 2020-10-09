package com.nasa.eonet.nasa.dao.services;

import java.util.List;

import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.nasa.eonet.nasa.beans.dao.LocationsInfo;
import com.nasa.eonet.nasa.repositories.LocationsInfoRepository;

@Service
@Component
public class LocationsInfoService {

	@Autowired
	private LocationsInfoRepository locationsInfoRepository;

	public List<LocationsInfo> list() {
		return locationsInfoRepository.findAll();
	}

	public LocationsInfo getLocationDetails(String locationName) {
		return locationsInfoRepository.findByLocationNameIgnoreCase(locationName);
	}

	@Transactional
	public LocationsInfo saveLocation(LocationsInfo loc) {
		return locationsInfoRepository.save(loc);
	}
}
