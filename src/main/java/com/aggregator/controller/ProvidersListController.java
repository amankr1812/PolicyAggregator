package com.aggregator.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.validation.Valid;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
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
import com.aggregator.entity.AggregatorPlan;
import com.aggregator.entity.ProvidersList;
import com.aggregator.service.ProvidersListService;

@RestController
@RequestMapping("/api/v1")
public class ProvidersListController {

	@Autowired
	private ProvidersListService providersListService;

	private static RestTemplate restTemplate;

	private static final org.jboss.logging.Logger lo = LoggerFactory.logger(ProvidersListController.class);

	@GetMapping("/providersList")
	public List<List<AggregatorPlan>> getAllInsuranceProviders() {

		List<List<AggregatorPlan>> mainList = new ArrayList<List<AggregatorPlan>>();

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		restTemplate = new RestTemplate();

		String url;
		JSONArray jsonArray1 = new JSONArray();
		List<ProvidersList> list = getUrl();

		// Converting to JSON Array
		for (ProvidersList temp : list) {

			url = temp.getProviderUrl();
			jsonArray1 = restTemplate.exchange(url, HttpMethod.GET, entity, JSONArray.class).getBody();

			try {

				JSONParser parser = new JSONParser();
				String str = jsonArray1.toString();

				ArrayList<String> arr = new ArrayList<>();
				int pos1 = 0, pos2;
				String str2 = str.substring(1, str.length() - 1);

				int index1 = str2.lastIndexOf("},");

				for (int i = 0; i < str2.length(); i++) {
					if (i <= index1) {
						char x = str2.charAt(i); // }
						char y = str2.charAt(i + 1); // ,
						if (x == '}' && (y == ',' || i + 1 == str2.length())) {
							pos2 = i;
							String sub1 = str2.substring(pos1, pos2 + 1);
							pos1 = i + 2;
							arr.add(sub1);
						}
					}
				}

				String lastStr = str2.substring(index1 + 2, str2.length());
				arr.add(lastStr);

				JSONObject json = (JSONObject) parser.parse(arr.get(0));
				List<AggregatorPlan> subList = transform(arr, temp.getId());
				mainList.add(subList);

			} catch (Exception err) {
				lo.info("--------ERROR---------  " + err);
			}

		}

		return mainList;

	}

	public AggregatorPlan extractData(String temp, int id) {
		String providerPlanName = "", providerPlanId = "", providerPlanCoverage = "", providerProviderName = "";
		String providerPlanNameValue = "", providerPlanIdValue = "", providerProviderNameValue = "";
		int providerPlanCoverageValue = 0;

		AggregatorPlan tempObj = new AggregatorPlan();
		ProvidersList p = providersListService.getById(id);

		JSONParser parser = new JSONParser();
		try {

			JSONObject json = (JSONObject) parser.parse(p.getResponseType());
			JSONObject json2 = (JSONObject) parser.parse(temp);

			for (Object o : json.entrySet()) {

				if (o.toString().contains("planId")) {
					String str = o.toString();
					providerPlanId = str.substring(str.lastIndexOf("=") + 1);
				}

				if (o.toString().contains("providerName")) {
					String str = o.toString();
					providerProviderName = str.substring(str.lastIndexOf("=") + 1);
				}

				if (o.toString().contains("planName")) {
					String str = o.toString();
					providerPlanName = str.substring(str.lastIndexOf("=") + 1);
				}

				if (o.toString().contains("planCoverage")) {
					String str = o.toString();
					providerPlanCoverage = str.substring(str.lastIndexOf("=") + 1);
				}
			}

			for (Object o : json2.entrySet()) {

				if (o.toString().contains(providerPlanId)) {
					String str = o.toString();
					providerPlanIdValue = str.substring(str.lastIndexOf("=") + 1);
				}

				if (o.toString().contains(providerProviderName)) {
					String str = o.toString();
					providerProviderNameValue = str.substring(str.lastIndexOf("=") + 1);
				}

				if (o.toString().contains(providerPlanName)) {
					String str = o.toString();
					providerPlanNameValue = str.substring(str.lastIndexOf("=") + 1);
				}

				if (o.toString().contains(providerPlanCoverage)) {
					String str = o.toString();
					String tempStr = str.substring(str.lastIndexOf("=") + 1);
					providerPlanCoverageValue = Integer.parseInt(tempStr);
				}
			}

			tempObj.setPlanId(providerPlanIdValue);
			tempObj.setProviderName(providerProviderNameValue);
			tempObj.setPlanName(providerPlanNameValue);
			tempObj.setPlanCoverage(providerPlanCoverageValue);

		} catch (Exception e) {

			lo.info("***********Error**************" + e);

		}

		return tempObj;

	}

	public List<AggregatorPlan> transform(ArrayList<String> listdata, int id) {

		List<AggregatorPlan> subList = new ArrayList<>();

		for (String temp : listdata) {

			AggregatorPlan obj = new AggregatorPlan();
			obj = extractData(temp, id);
			subList.add(obj);

		}

		return subList;

	}

	@GetMapping("/AllUrl") // GET ALL URL METHOD
	public List<ProvidersList> getUrl() {

		return providersListService.getAll();
	}

	@PostMapping("/AllUrl") // REGISTER METHOD
	public ProvidersList createProvider(@Valid @RequestBody ProvidersList providersList) {
		return providersListService.createProvider(providersList);
	}

}
