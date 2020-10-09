package com.nasa.eonet.nasa.beans.external;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class NasaSourcesResponse extends NasaCommonResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<Source> sources = null;

	public List<Source> getSources() {
		return sources;
	}

	public void setSources(List<Source> sources) {
		this.sources = sources;
	}

	@Override
	public String toString() {
		return "NasaSourcesResponse [sources=" + sources + ", getTitle()=" + getTitle() + ", getDescription()="
				+ getDescription() + ", getLink()=" + getLink() + "]";
	}

}
