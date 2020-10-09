package com.nasa.eonet.nasa.beans;

import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

import io.swagger.annotations.ApiModelProperty;

public class DisasterInfoRequest{
	private List<String> sourcesList = null;
	@Min(1)
	@Max(50)
	@ApiModelProperty(notes = "Limit the size of result, min 1 max 50, default value = 5")
	private int limit = 5;
	@Pattern(regexp = "^\\d{4}\\-(0[1-9]|1[012])\\-(0[1-9]|[12][0-9]|3[01])$", message = "Start Date Pattern must be YYYY-MM-DD")
	@ApiModelProperty(notes = "Start date of search criteria - Format = YYYY-MM-DD")
	private String startDate = null;
	@Pattern(regexp = "^\\d{4}\\-(0[1-9]|1[012])\\-(0[1-9]|[12][0-9]|3[01])$", message = "End Date Pattern must be YYYY-MM-DD")
	@ApiModelProperty(notes = "End date of search criteria - Format = YYYY-MM-DD")
	private String endDate = null;
	@ApiModelProperty(notes = "Location name where disaster are to be searched")
	private String locationName = null;

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

	public String getlocationName() {
		return locationName;
	}

	public void setlocationName(String countryName) {
		this.locationName = countryName;
	}

	@Override
	public String toString() {
		return "DisasterInfoRequest [sourcesList=" + sourcesList + ", limit=" + limit + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", locationName=" + locationName + ", toString()=" + super.toString() + "]";
	}

}
