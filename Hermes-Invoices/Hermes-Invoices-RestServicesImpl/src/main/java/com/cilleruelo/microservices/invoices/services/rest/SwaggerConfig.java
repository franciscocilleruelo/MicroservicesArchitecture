package com.cilleruelo.microservices.invoices.services.rest;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Configuration to manage the REST services API by Swagger
 * The main Swagger page can be accessed by the URL http://{host}:{port}/swagger-ui.html
 * 
 * @author francisco.cilleruelo
 *
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.cilleruelo.microservices.invoices.services.rest.controllers")) // Package where the REST services are located
				.paths(PathSelectors.any()).build().pathMapping("/").apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("REST API documentation")
				.description("Rest services documentacion").version("1.0").license("Licence")
				.licenseUrl("https://licenseLink/LICENSE").build();
	}

}