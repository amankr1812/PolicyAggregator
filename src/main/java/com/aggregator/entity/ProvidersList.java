package com.aggregator.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "insuranceProvidersList")
public class ProvidersList {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
	
	@Column(name = "provider_url", nullable = false)
    private String providerUrl;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProviderUrl() {
		return providerUrl;
	}

	public void setProviderUrl(String providerUrl) {
		this.providerUrl = providerUrl;
	}

	@Override
	public String toString() {
		return "InsuranceProviders [id=" + id + ", providerUrl=" + providerUrl + "]";
	}

	public ProvidersList(int id, String providerUrl) {
		super();
		this.id = id;
		this.providerUrl = providerUrl;
	}

	public ProvidersList() {

	}
	
	
}
