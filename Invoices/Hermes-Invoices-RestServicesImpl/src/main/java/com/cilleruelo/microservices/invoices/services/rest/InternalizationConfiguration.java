package com.cilleruelo.microservices.invoices.services.rest;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

/**
 * Configuration to work with messages on different languages (i18n)
 * 
 * @author francisco.cilleruelo
 *
 */
@Configuration
public class InternalizationConfiguration {
	
	/**
	 * In case the locale is not defined, the locale by default will be Spanish (es)
	 */
	@Value("${locale.default:es}") 
	private String localeDefault;

	@Bean
	public LocaleResolver localeResolver() {
		SessionLocaleResolver slr = new SessionLocaleResolver();
		Locale defaultLocale = new Locale(localeDefault);
		slr.setDefaultLocale(defaultLocale);
		return slr;
	}

	@Bean
	public ResourceBundleMessageSource messageSource() {
		ResourceBundleMessageSource source = new ResourceBundleMessageSource();
		source.setBasenames("i18n/messages"); // Where the message files (message_{locale}.properties) are located 
		source.setUseCodeAsDefaultMessage(true);
		return source;
	}

}
