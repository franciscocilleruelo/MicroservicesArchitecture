package com.cilleruelo.microservices.invoices.core.events;

import java.io.Serializable;

import com.cilleruelo.microservices.invoices.core.beans.InvoiceDTO;

/**
 * Invoice event example for an event driven architecture
 * 
 * @author francisco.cilleruelo
 *
 */
public class InvoiceEvent implements Serializable{
	
	private static final long serialVersionUID = -3595848478751498987L;
	
	private InvoiceDTO invoice;
	private String message;
	
	public InvoiceEvent() {
		super();
	}

	public InvoiceEvent(InvoiceDTO invoice, String message) {
		super();
		this.invoice = invoice;
		this.message = message;
	}
	
	public InvoiceDTO getInvoice() {
		return invoice;
	}
	
	public void setInvoice(InvoiceDTO invoice) {
		this.invoice = invoice;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}

}
