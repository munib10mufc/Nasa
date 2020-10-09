package com.nasa.eonet.nasa.beans.external;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LocationIqResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	private String error = null;
	private List<String> boundingbox = null;

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public List<String> getBoundingbox() {
		return boundingbox;
	}

	public void setBoundingbox(List<String> boundingbox) {
		this.boundingbox = boundingbox;
	}

	@Override
	public String toString() {
		return "LocationIqResponse [error=" + error + ", boundingbox=" + boundingbox + "]";
	}

}
