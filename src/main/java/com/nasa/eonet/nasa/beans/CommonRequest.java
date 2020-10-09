package com.nasa.eonet.nasa.beans;

import java.util.List;

public class CommonRequest {
	private String serviceName = null;

	private List<String> sourcesList = null;
	private int limit = 5;
	private String startDate = null;
	private String endDate = null;
	private String locationName = null;

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public List<String> getSourcesList() {
		return sourcesList;
	}

	public void setSourcesList(List<String> sourcesList) {
		this.sourcesList = sourcesList;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String countryName) {
		this.locationName = countryName;
	}

	@Override
	public String toString() {
		return "CommonRequest [serviceName=" + serviceName + ", sourcesList="
				+ sourcesList + ", limit=" + limit + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", locationName=" + locationName + ", toString()=" + super.toString() + "]";
	}

}
