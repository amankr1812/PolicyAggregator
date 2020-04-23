package com.aggregator;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import com.aggregator.controller.ProvidersListController;

@SpringBootApplication
public class AggregatorInsuranceApplication {

	private static final org.jboss.logging.Logger lo= LoggerFactory.logger(ProvidersListController.class);
	

	public static void main(String[] args) {
		SpringApplication.run(AggregatorInsuranceApplication.class, args);
	}

}
