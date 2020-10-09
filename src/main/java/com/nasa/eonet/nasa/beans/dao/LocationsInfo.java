package com.nasa.eonet.nasa.beans.dao;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "locations_info")
public class LocationsInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "location_name")
	private String locationName;
	@Column(name = "min_lat")
	private String minLatitude;
	@Column(name = "max_lat")
	private String maxLatitude;
	@Column(name = "min_lon")
	private String minLongitude;
	@Column(name = "max_lon")
	private String maxLongitude;

	public LocationsInfo() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public String getMinLatitude() {
		return minLatitude;
	}

	public void setMinLatitude(String minLatitude) {
		this.minLatitude = minLatitude;
	}

	public String getMaxLatitude() {
		return maxLatitude;
	}

	public void setMaxLatitude(String maxLatitude) {
		this.maxLatitude = maxLatitude;
	}

	public String getMinLongitude() {
		return minLongitude;
	}

	public void setMinLongitude(String minLongitude) {
		this.minLongitude = minLongitude;
	}

	public String getMaxLongitude() {
		return maxLongitude;
	}

	public void setMaxLongitude(String maxLongitude) {
		this.maxLongitude = maxLongitude;
	}

	@Override
	public String toString() {
		return "LocationsInfo [id=" + id + ", locationName=" + locationName + ", minLatitude=" + minLatitude
				+ ", maxLatitude=" + maxLatitude + ", minLongitude=" + minLongitude + ", maxLongitude=" + maxLongitude
				+ "]";
	}

}
