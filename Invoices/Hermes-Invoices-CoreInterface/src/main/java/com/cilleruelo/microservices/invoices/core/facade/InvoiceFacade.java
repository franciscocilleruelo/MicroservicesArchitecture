package com.cilleruelo.microservices.invoices.core.facade;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cilleruelo.microservices.invoices.core.beans.InvoiceDTO;

@Service
public interface InvoiceFacade {

	/**
	 * Get list of invoices
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<InvoiceDTO> getInvoices() throws Exception;
	
	/**
	 * Get an invoice by its id
	 * 
	 * @param invoiceId
	 * @return
	 * @throws Exception
	 */
	public InvoiceDTO getInvoice(Long invoiceId) throws Exception;
	
	/**
	 * Add a new invoice
	 * 
	 * @param invoice Invoice to be added
	 * @return
	 * @throws Exception
	 */
	public InvoiceDTO addInvoice(InvoiceDTO invoice) throws Exception;
	
	/**
	 * Update an existing invoice
	 * 
	 * @param invoiceId 
	 * @param invoice Invoice to be update
	 * @return
	 * @throws Exception
	 */
	public InvoiceDTO updateInvoice(Long invoiceId, InvoiceDTO invoice) throws Exception;
	
	/**
	 * Delete an existing invoice
	 * 
	 * @param invoiceId
	 * @throws Exception
	 */
	public void deleteInvoice(Long invoiceId) throws Exception;
}
