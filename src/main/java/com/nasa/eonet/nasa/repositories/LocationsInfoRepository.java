package com.nasa.eonet.nasa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nasa.eonet.nasa.beans.dao.LocationsInfo;

@Repository
public interface LocationsInfoRepository extends JpaRepository<LocationsInfo, Long> {
	LocationsInfo findByLocationNameIgnoreCase(String locationName);
}
