package com.nasa.eonet.nasa.beans.external;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Event implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id = null;
	private String title = null;
	private String description = null;
	private String link = null;
	private List<Source> categories = null;
	private List<Source> sources = null;
	private List<Geometry> geometry = null;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public List<Source> getCategories() {
		return categories;
	}
	public void setCategories(List<Source> categories) {
		this.categories = categories;
	}
	public List<Source> getSources() {
		return sources;
	}
	public void setSources(List<Source> sources) {
		this.sources = sources;
	}
	public List<Geometry> getGeometry() {
		return geometry;
	}
	public void setGeometry(List<Geometry> geometry) {
		this.geometry = geometry;
	}
	@Override
	public String toString() {
		return "Event [id=" + id + ", title=" + title + ", description=" + description + ", link=" + link
				+ ", categories=" + categories + ", sources=" + sources + ", geometry=" + geometry + "]";
	}

}
