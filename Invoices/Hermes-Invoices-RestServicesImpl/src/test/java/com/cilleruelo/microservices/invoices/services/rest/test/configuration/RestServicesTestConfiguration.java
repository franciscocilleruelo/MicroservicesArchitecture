package com.cilleruelo.microservices.invoices.services.rest.test.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Common configuration for every test class, declared on each of them by the ContextConfiguration annotation  
 * 
 * @author francisco.cilleruelo
 *
 */
@PropertySource("classpath:application.properties")
@Configuration
public class RestServicesTestConfiguration {

	// You can add any common additional configuration needed for the unit tests
	
}
