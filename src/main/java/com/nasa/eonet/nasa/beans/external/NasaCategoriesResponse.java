package com.nasa.eonet.nasa.beans.external;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class NasaCategoriesResponse extends NasaCommonResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Source> categories = null;

	public List<Source> getCategories() {
		return categories;
	}

	public void setCategories(List<Source> categories) {
		this.categories = categories;
	}

	@Override
	public String toString() {
		return "NasaCategoriesResponse [categories=" + categories + ", getTitle()=" + getTitle() + ", getDescription()="
				+ getDescription() + ", getLink()=" + getLink() + "]";
	}
	
}
