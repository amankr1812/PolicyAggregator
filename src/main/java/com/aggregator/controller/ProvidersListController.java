package com.aggregator.controller;

import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.aggregator.entity.ProvidersList;
import com.aggregator.service.ProvidersListService;




@RestController
@RequestMapping("/api/v1")
public class ProvidersListController {

	@Autowired
	private ProvidersListService providersListService;

	private static RestTemplate restTemplate;
	
	private static final org.jboss.logging.Logger lo= LoggerFactory.logger(ProvidersListController.class);
	
	
	
	
	@GetMapping("/providersList")
	public  String getAllInsuranceProviders() {		
		
		HttpHeaders headers=new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity=new HttpEntity<String>(headers);
        
        restTemplate = new RestTemplate();
       String url=providersListService.getAll().get(0).getProviderUrl();
       
       String value= restTemplate.exchange(url,HttpMethod.GET,entity,String.class).getBody();
       lo.info("----------FINAL VALUE-------  "+value);
       return value;
				  
	   
	  }
	
	@GetMapping("/AllUrl")
	public List<ProvidersList> getUrl() {
	
		return providersListService.getAll(); 
	}
	
	@PostMapping("/AllUrl")
	  public ProvidersList createProvider(@Valid @RequestBody ProvidersList providersList) {
	    //return insuranceProviderRepository.save(insuranceProvider);
		  return providersListService.createProvider(providersList);
	  }
	
}
