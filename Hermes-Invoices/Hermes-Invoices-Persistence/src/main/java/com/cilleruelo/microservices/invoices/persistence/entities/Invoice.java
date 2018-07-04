package com.cilleruelo.microservices.invoices.persistence.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * Persistent class for invoices
 * 
 * @author francisco.cilleruelo
 *
 */
@Entity
@Table(name = "INVOICES")
public class Invoice implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GenericGenerator(name = "SEQ_INVOICEID_GEN", strategy = "increment")
	@GeneratedValue(generator = "SEQ_INVOICEID_GEN")
	@Column(name = "INVOICEID")
	private Long invoiceid;
	
	@Column(name = "UUID")
	private String uuid;
	
	@Column(name = "RFC")
	private String rfc;

	@Column(name = "INVOICE_NUMBER")
	private String invoiceNumber;

	@Column(name = "TOTAL_AMOUNT")
	private BigDecimal totalAmount;

	@Column(name = "SERIE")
	private String serie;

	@Column(name = "FOLIO")
	private String folio;
	
	public Invoice() {
	}

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