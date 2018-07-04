package com.cilleruelo.microservices.invoices.core.exceptions;

public class InvoiceException extends Exception {

	private static final long serialVersionUID = -3349849956029765502L;

	public InvoiceException() {
		super();
	}

	public InvoiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public InvoiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvoiceException(String message) {
		super(message);
	}

	public InvoiceException(Throwable cause) {
		super(cause);
	}
	
	

}
