package com.cilleruelo.microservices.invoices.core.test.configuration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTestContextBootstrapper;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.cilleruelo.microservices.invoices.core.events.InvoiceEvent;

@ComponentScan("com.cilleruelo.microservices")
@EntityScan("com.cilleruelo.microservices.invoices.persistence.entities")
@EnableJpaRepositories(basePackages = "com.cilleruelo.microservices.invoices.persistence.repositories")
@EnableAutoConfiguration(exclude = {LiquibaseAutoConfiguration.class})
@EnableBinding({Source.class, Sink.class})
@SpringBootApplication
public class CoreTestConfiguration extends SpringBootTestContextBootstrapper {
	public static final String DISABLE_H2_CLIENT = "DISABLE_H2_CLIENT";

	public static void main(String[] args) {
		SpringApplication.run(CoreTestConfiguration.class, args);
	}
	
	/*@Bean 
	public InvoiceExtension<InvoiceDTO> getInvoiceExtension(){
		return new InvoiceExtensionImpl();
	}*/
	
	@StreamListener(Sink.INPUT)
	public void eventReceived(InvoiceEvent event){
	}
}
