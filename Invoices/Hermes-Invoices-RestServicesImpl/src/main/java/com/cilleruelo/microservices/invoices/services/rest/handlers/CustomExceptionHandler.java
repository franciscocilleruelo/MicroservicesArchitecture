package com.cilleruelo.microservices.invoices.services.rest.handlers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.cilleruelo.microservices.invoices.services.rest.beans.Message;
import com.cilleruelo.microservices.invoices.services.rest.beans.MessageType;
import com.cilleruelo.microservices.invoices.services.rest.beans.StandardResponse;
import com.cilleruelo.microservices.invoices.services.rest.utils.ExceptionUtil;
import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * Exception handler to catch the exceptions are not caught and processed by the rest controllers
 * Due to ControllerAdvice annotation, it affects every rest controller class 
 * 
 * @author francisco.cilleruelo
 *
 */
@ControllerAdvice
public class CustomExceptionHandler {
	
	private static final Logger LOG = LoggerFactory.getLogger(CustomExceptionHandler.class);
	
	@Autowired  
	private MessageSource messageSource;
	
	/**
	 * Method to capture every generic exceptions
	 * 
	 * @param request
	 * @param response
	 * @param locale
	 * @param e
	 */
	@ExceptionHandler(value = {Exception.class, RuntimeException.class})
	public void defaultErrorHandler(HttpServletRequest request, HttpServletResponse response, Locale locale, Exception e) {
		LOG.info("Ruta que ha provocado el error: {}", request.getRequestURL());
		
		// Crear el mensaje de error
		String tituloMensaje = this.messageSource.getMessage("error.unexpected", null, locale);
		String textoMensaje = ExceptionUtil.exceptionToString(e);
		if(textoMensaje.contains(ExceptionUtil.EXCEPTION_SEPARATOR)) {
			String[] arrayMensaje = textoMensaje.split(ExceptionUtil.EXCEPTION_SEPARATOR);
			tituloMensaje = arrayMensaje[0];
			textoMensaje = arrayMensaje[1];
		} 
		
		try{
			StandardResponse standardResponse = new StandardResponse();
			List<Message> listMsgs = new ArrayList<>();
			Message message = new Message(MessageType.E, tituloMensaje, textoMensaje);
			listMsgs.add(message);
			standardResponse.setMessages(listMsgs);
			standardResponse.setContent(request.getRequestURL());
			response.setContentType("application/json");
		    response.setCharacterEncoding("UTF-8");
		    ObjectMapper mapper = new ObjectMapper();
		    String respuestaJson = mapper.writeValueAsString(standardResponse);
		    response.getWriter().write(respuestaJson);
		} catch (IOException ioEx){	
			LOG.error("Error devolviendo los mensajes de error commo JSON: {}", ioEx);
		}
		
	}


}
