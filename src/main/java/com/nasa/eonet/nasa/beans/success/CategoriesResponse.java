package com.nasa.eonet.nasa.beans.success;

import java.util.List;

import com.nasa.eonet.nasa.beans.external.Source;

public class CategoriesResponse extends SuccessResponse {

	private List<Source> availableCategories = null;

	public CategoriesResponse(String respCode, String respDesc) {
		super(respCode, respDesc);
	}

	public CategoriesResponse(String respCode, String respDesc, List<Source> availableCategories) {
		super(respCode, respDesc);
		this.availableCategories = availableCategories;
	}

	public List<Source> getAvailableCategories() {
		return availableCategories;
	}

	public void setAvailableCategories(List<Source> availableCategories) {
		this.availableCategories = availableCategories;
	}

	@Override
	public String toString() {
		return "CategoriesResponse [availableCategories=" + availableCategories + ", getResponseCode()="
				+ getResponseCode() + ", getResponseDesc()=" + getResponseDesc() + "]";
	}
}
