package com.cilleruelo.microservices.invoices.services.rest.utils;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.springframework.util.StringUtils;

/**
 * @author francisco.cilleruelo
 *
 */
public class ExceptionUtil {
	
	public static final String EXCEPTION_SEPARATOR = "##";
	
	/**
	 * Convert the Stack Trace of an exception in String
	 * 
	 * @param th the exception to convert to String
	 * @return A string with the Stack Trace content
	 */
	public static String exceptionToString(Throwable th) {
		if (th == null)
			return "";

		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		th.printStackTrace(pw);
		return sw.toString();
	}

	/**
	 * Returns the message string of the cause of the exception.
	 *  If the message is not located, it returns as a standard message: 'Undefined error. Please refer to server log. '
	 * 
	 * @param th The exception to get the cause from.
	 * @return The cause of the exception or standard message indicated if it is not localized
	 */
	public static String exceptionGetCause(Throwable th) {
		String causaError = "Undefined error. Please refer to server log.";
		if (th.getCause() != null) {
			if (StringUtils.hasText(th.getCause().getMessage()))
				causaError = th.getCause().getMessage();
		} else {
			if (StringUtils.hasText(th.getMessage()))
				causaError = th.getMessage();
		}
		return causaError;
	}

}
