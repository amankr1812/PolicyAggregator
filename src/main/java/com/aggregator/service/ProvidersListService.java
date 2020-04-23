package com.aggregator.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aggregator.entity.ProvidersList;
import com.aggregator.repository.ProvidersListRepository;


@Service
public class ProvidersListService {

	@Autowired
	private  ProvidersListRepository providersListRepository;
	
	public List<ProvidersList> getAll() {
		return providersListRepository.findAll();
	}
	
	public Optional<ProvidersList> getById(Integer id) {
		return providersListRepository.findById(id);
	}
	
	public ProvidersList createProvider(ProvidersList providersList) {
	
		return providersListRepository.saveAndFlush(providersList);
	}
}
