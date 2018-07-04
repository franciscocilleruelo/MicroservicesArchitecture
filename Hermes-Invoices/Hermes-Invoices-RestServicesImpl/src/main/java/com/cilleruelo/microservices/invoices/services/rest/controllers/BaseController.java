package com.cilleruelo.microservices.invoices.services.rest.controllers;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;

/**
 * Every Rest Controller should extend this class to get access to the messages according to the language (locale)
 * 
 * @author francisco.cilleruelo
 *
 */
@Controller
public class BaseController {
	
	@Autowired  
	protected MessageSource messageSource;
	
	/**
	 * Obtiene un mensaje i18n a partir del key en el idioma del locale
	 * 
	 * @param key		clave de resources i18n (messages_es.properties | messages_en.properties ! ...)
	 * @param locale	locale para recuperar el mensaje
	 * @return 			String con el mensaje correspondiente al key y locale
	 */
	protected String getI18nMessage(String key, Locale locale) {
		return this.messageSource.getMessage(key, null, locale);
	}
	
	/**
	 * Mismo caso que obtenerMensaje pero inyectando los argumentos del array al mensaje 
	 * (argumento {0}, {1}, ... {n} respectivamente, tantos como el número de argumentos)
	 */
	protected String getI18nMessageWithArguments(String key, Locale locale, Object... argumentos) {
		return this.messageSource.getMessage(key, argumentos, locale);
	}
	
}
