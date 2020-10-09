package com.nasa.eonet.nasa.beans.dao;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "external_apis")
public class ExternalApi implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "host_name")
	private String hostName;
	@Column(name = "url_category")
	private String urlCategory;
	private String url;
	@Column(name = "n_retries")
	private String nRetries;
	@Column(name = "api_key")
	private String apiKey;

	public ExternalApi() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public String getUrlCategory() {
		return urlCategory;
	}

	public void setUrlCategory(String urlCategory) {
		this.urlCategory = urlCategory;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getnRetries() {
		return nRetries;
	}

	public void setnRetries(String nRetries) {
		this.nRetries = nRetries;
	}

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}
	
	@Override
	public String toString() {
		return "ExternalApi [id=" + id + ", hostName=" + hostName + ", urlCategory=" + urlCategory + ", url=" + url
				+ ", nRetries=" + nRetries + ", apiKey=" + apiKey + "]";
	}
}
