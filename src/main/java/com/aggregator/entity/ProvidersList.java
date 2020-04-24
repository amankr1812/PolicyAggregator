package com.aggregator.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.json.simple.JSONObject;

@Entity
@Table(name = "insuranceProvidersList")
public class ProvidersList implements Serializable {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
	
	@Column(name = "provider_name", nullable = false)
    private String providerName;
	
	@Column(name = "provider_url", nullable = false)
    private String providerUrl;
	
	@Column(name = "response_type", nullable = false)
	private String responseType; // can be json, xml

	@Column(name = "response_format", nullable = false)
	private String responseFormat; // String is taken so that later on, based on responseType value, we can convert it to required format

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProviderName() {
		return providerName;
	}

	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}

	public String getProviderUrl() {
		return providerUrl;
	}

	public void setProviderUrl(String providerUrl) {
		this.providerUrl = providerUrl;
	}

	public String getResponseType() {
		return responseType;
	}

	public void setResponseType(String responseType) {
		this.responseType = responseType;
	}

	public String getResponseFormat() {
		return responseFormat;
	}

	public void setResponseFormat(String responseFormat) {
		this.responseFormat = responseFormat;
	}



	public ProvidersList(int id,String providerName, String providerUrl, String responseType, String responseFormat) {
		super();
		this.id = id;
		this.providerName=providerName;
		this.providerUrl = providerUrl;
		this.responseType = responseType;
		this.responseFormat = responseFormat;
	}

	public ProvidersList() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
}
