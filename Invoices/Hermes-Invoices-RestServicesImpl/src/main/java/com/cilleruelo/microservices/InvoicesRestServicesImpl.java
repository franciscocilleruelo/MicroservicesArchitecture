package com.cilleruelo.microservices;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@ComponentScan("com.cilleruelo.microservices")
@EnableJpaRepositories("com.cilleruelo.microservices.invoices.persistence.repositories")
@EntityScan("com.cilleruelo.microservices.invoices.persistence.entities")
@EnableMBeanExport(defaultDomain="Invoices")
@RefreshScope // It allows to refresh the properties have changed on Git by calling the endpoint: http://{host}:{port}/refresh
@EnableDiscoveryClient // It allows to be registered on the discovery server and gets location info about other components from it
@EnableCircuitBreaker // It allows to implement resiliency patterns (circuit breaker, fallback processing and bulkheads) by Hystrix
public class InvoicesRestServicesImpl {
	
	private static final Logger LOG = LoggerFactory.getLogger(InvoicesRestServicesImpl.class);
	
	public static void main( String[] args ) 
    {
    	SpringApplication.run(InvoicesRestServicesImpl.class, args);
    	LOG.warn("XXXXXXXXXXXXXXXXXXXXXXXXX "+ System.getProperty("pf4j.pluginsDir", "plugins"));
    }
	
	/**
	 * Tells Spring Cloud to create a Ribbon backed RestTemplate class. 
	 * It allows to call another services´ endpoints by the service name.
	 * So, there is no need to know where the called service is hosted 
	 * 
	 * @return
	 */
	@LoadBalanced 
	@Bean
	public RestTemplate getRestTemaple(){
		return new RestTemplate();
		/**
		The way to use this template will be:
			1. Autowire the rest template on the class: 
				@Autowaired
				private RestTemplate restTemplate;
			2. Where the template has to be used to call some endpoint from another service
				resteTemplate.exchange("http://<serviceName>/<serviceEndpoint>", <HTTPMethod>,...<additionalParameters>);
				for instance: 
					ResponseEntity<Organization> restExchange = restTemplate.exchange("http://organizationservice/v1/organizations/{organizationId}", HttpMethod.GET, null, Organization.class, organizationId);
		**/
	}
}