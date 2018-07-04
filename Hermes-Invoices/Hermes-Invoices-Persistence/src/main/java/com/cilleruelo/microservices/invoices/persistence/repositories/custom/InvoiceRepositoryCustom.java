package com.cilleruelo.microservices.invoices.persistence.repositories.custom;

import java.util.List;

import org.springframework.data.repository.NoRepositoryBean;

import com.cilleruelo.microservices.invoices.persistence.beans.InvoiceCriteria;
import com.cilleruelo.microservices.invoices.persistence.entities.Invoice;

/**
 * Repository to define customized persistence methods (such as searching by criteria) 
 * 
 * @author francisco.cilleruelo
 *
 */
@NoRepositoryBean
public interface InvoiceRepositoryCustom {
	
	 /**
	 * Find invoices by criteria
	 * 
	 * @param criteria
	 * @return
	 */
	public List<Invoice> findInvoicesByCriteria(InvoiceCriteria criteria);

}
