package com.aggregator.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.validation.Valid;
import org.json.simple.JSONArray;
import org.json.*;
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
	public  JSONArray getAllInsuranceProviders() {		
		
		HttpHeaders headers=new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity=new HttpEntity<String>(headers);
        restTemplate = new RestTemplate();
        String url;
        JSONArray jsonArray1 = new JSONArray();
        JSONArray jsonArray2 = new JSONArray();
        
        for (ProvidersList temp : providersListService.getAll()) {
        	lo.info("----------URLS-------  "+temp.getProviderUrl());
        	url=temp.getProviderUrl();
        	jsonArray1=restTemplate.exchange(url,HttpMethod.GET,entity,JSONArray.class).getBody();

        	try {
        	    for (int i = 0; i < jsonArray1.size(); i++) {
        	        Object jsonObject = jsonArray1.get(i);
        	        jsonArray2.add(jsonObject);
        	    }
        	} catch (Exception e) {
        	    e.printStackTrace();
        	}
        	
        }
        
       lo.info("----------FINAL VALUE-------  "+jsonArray2);
       
//       try {
//    	     JSONArray jsonarray = new JSONArray(value);
//    	     lo.info("-----------------  "+jsonarray);
//    	     
//    	     // CONVERTING TO LIST
//    	     ArrayList<String> listdata = new ArrayList<String>();  
//    	     if (jsonarray != null) { 
//    	    	   for (int i=0;i<jsonarray.length();i++){ 
//    	    	    listdata.add(jsonarray.getString(i));
//    	    	   } 
//    	    	} 
//    	     lo.info("-------------LIST-------------------"+listdata);
//    	     
//    	     // CONVERTING TO SINGLE JSON OBJECTS
//    	     for(int i=0; i < jsonarray.length(); i++) {
//    	    	    JSONObject jsonobject = jsonarray.getJSONObject(i);
//    	    	    lo.info("-----------------  "+jsonobject.getString("providerName"));
//    	    	}
//    	}catch (Exception err){
//    		lo.info("--------ERROR---------  "+err);
//    	}
       
       
       return jsonArray2;
				  
	   
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
