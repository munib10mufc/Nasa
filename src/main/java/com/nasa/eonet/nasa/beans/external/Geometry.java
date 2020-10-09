package com.nasa.eonet.nasa.beans.external;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Geometry implements Serializable {

	private static final long serialVersionUID = 1L;

	private String magnitudeValue = null;
	private String magnitudeUnit = null;
	private String date = null;
	private String type = null;
	private List<Double> coordinates = null;

	public String getMagnitudeValue() {
		return magnitudeValue;
	}

	public void setMagnitudeValue(String magnitudeValue) {
		this.magnitudeValue = magnitudeValue;
	}

	public String getMagnitudeUnit() {
		return magnitudeUnit;
	}

	public void setMagnitudeUnit(String magnitudeUnit) {
		this.magnitudeUnit = magnitudeUnit;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Double> getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(List<Double> coordinates) {
		this.coordinates = coordinates;
	}

	@Override
	public String toString() {
		return "Geometry [magnitudeValue=" + magnitudeValue + ", magnitudeUnit=" + magnitudeUnit + ", date=" + date
				+ ", type=" + type + ", coordinates=" + coordinates + "]";
	}

}
