package com.nasa.eonet.nasa.beans.success;

import java.util.List;

import com.nasa.eonet.nasa.beans.external.Source;

public class SourcesResponse extends SuccessResponse {

	private List<Source> availableSources = null;

	
	public SourcesResponse(String respCode, String respDesc) {
		super(respCode, respDesc);
	}

	public SourcesResponse(String respCode, String respDesc, List<Source> availableSources) {
		super(respCode, respDesc);
		this.availableSources = availableSources;
	}

	public List<Source> getAvailableSources() {
		return availableSources;
	}

	public void setAvailableSources(List<Source> availableSources) {
		this.availableSources = availableSources;
	}

	@Override
	public String toString() {
		return "SourcesResponse [availableSources=" + availableSources + ", getResponseCode()=" + getResponseCode()
				+ ", getResponseDesc()=" + getResponseDesc() + " toString()=" + super.toString() + "]";
	}

}
