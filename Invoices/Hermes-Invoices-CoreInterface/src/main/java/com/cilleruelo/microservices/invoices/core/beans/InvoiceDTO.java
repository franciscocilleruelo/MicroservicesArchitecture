package com.cilleruelo.microservices.invoices.core.beans;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Basic Core DTO
 * 
 * @author francisco.cilleruelo
 *
 */
public class InvoiceDTO implements Serializable{

	private static final long serialVersionUID = 1788824903203083731L;
	
	private Long invoiceid;
	
	private String uuid;

	private String invoiceNumber;

	private String rfc;

	private BigDecimal totalAmount;
	
	private String serie;
	
	private String folio;

	public Long getInvoiceid() {
		return invoiceid;
	}

	public void setInvoiceid(Long invoiceid) {
		this.invoiceid = invoiceid;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	public String getRfc() {
		return rfc;
	}

	public void setRfc(String rfc) {
		this.rfc = rfc;
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
