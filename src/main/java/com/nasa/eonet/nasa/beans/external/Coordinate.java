package com.nasa.eonet.nasa.beans.external;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Coordinate implements Serializable {

	private static final long serialVersionUID = 1L;

	List<List<Double>> coordinates = null;

	public List<List<Double>> getCoordinates() {
		return coordinates;
	}
	public void setCoordinates(List<List<Double>> coordinates) {
		this.coordinates = coordinates;
	}
	@Override
	public String toString() {
		return "Coordinate [ coordinates=" + coordinates + "]";
	}
	
}
