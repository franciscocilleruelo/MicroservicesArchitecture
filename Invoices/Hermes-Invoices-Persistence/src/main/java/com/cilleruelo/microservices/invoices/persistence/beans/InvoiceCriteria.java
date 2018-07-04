package com.cilleruelo.microservices.invoices.persistence.beans;

import java.math.BigDecimal;

public class InvoiceCriteria extends PageableAndSortableCriteria {

	private static final long serialVersionUID = 5415143565180459159L;
	
	private String uuid;
	
	private String rfc;

	private String invoiceNumber;

	private BigDecimal totalAmount;

	private String serie;

	private String folio;

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getRfc() {
		return rfc;
	}

	public void setRfc(String rfc) {
		this.rfc = rfc;
	}

	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getSerie() {
		return serie;
	}

	public void setSerie(String serie) {
		this.serie = serie;
	}

	public String getFolio() {
		return folio;
	}

	public void setFolio(String folio) {
		this.folio = folio;
	}

}
