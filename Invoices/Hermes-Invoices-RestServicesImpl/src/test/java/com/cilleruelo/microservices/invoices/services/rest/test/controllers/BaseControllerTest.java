package com.cilleruelo.microservices.invoices.services.rest.test.controllers;

import org.mockito.Spy;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

/**
 * Base class to be extended by every test class in order to use common properties, such as the messageSource 
 * 
 * @author francisco.cilleruelo
 *
 */
public class BaseControllerTest {
	
	@Spy
	private static ReloadableResourceBundleMessageSource messageSource;

	static {
		messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setUseCodeAsDefaultMessage(true);
		messageSource.setBasename("classpath:i18n/messages");
		messageSource.setDefaultEncoding("UTF-8");
	}

}
