package com.cilleruelo.microservices.invoices.services.rest.controllers;

import java.util.Locale;

import com.cilleruelo.microservices.invoices.core.beans.InvoiceDTO;
import com.cilleruelo.microservices.invoices.services.rest.beans.StandardResponse;

public interface IInvoiceRestController {

	/**
	 * Get the list of invoices
	 * @return Standard response with list of invoices
	 */
	public StandardResponse getInvoiceList();

	/**
	 * Get invoice by its id
	 * 
	 * @param invoiceId 
	 * @return Standard response with the retrieved invoice
	 */
	public StandardResponse getInvoice(Long invoiceId);

	/**
	 * Add a new invoice
	 * 
	 * @param invoice Invoice to be added
	 * @param locale 
	 * @return Standard response with the new invoice just added
	 */
	public StandardResponse addInvoice(InvoiceDTO invoice, Locale locale);

	/**
	 * Update an existing invoice by its id
	 *  
	 * @param invoiceId
	 * @param invoice Invoice with the updated data
	 * @param locale
	 * @return Standard response with the invoice just updated
	 */
	public StandardResponse updateInvoice(Long invoiceId, InvoiceDTO invoice, Locale locale);

	/**
	 * Delete an existing invoice by its id
	 * 
	 * @param invoiceId
	 * @param locale
	 * @return Standard response with true (in case the invoice has been deleted successfully) or false (if something was wrong)
	 */
	public StandardResponse deleteInvoice(Long invoiceId, Locale locale);

}