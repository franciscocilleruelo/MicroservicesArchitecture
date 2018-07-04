package com.cilleruelo.microservices.invoices.persistence.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cilleruelo.microservices.invoices.persistence.entities.Invoice;
import com.cilleruelo.microservices.invoices.persistence.repositories.custom.InvoiceRepositoryCustom;

/**
 * JPA repository for invoices
 * 
 * @author francisco.cilleruelo
 *
 */
@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long>, InvoiceRepositoryCustom {

	/**
	 * Find invoices by serie, folio and uuid fields
	 * 
	 * @param serie
	 * @param folio
	 * @param uuid
	 * @return
	 */
	public List<Invoice> findBySerieAndFolioAndUuid(String serie, String folio, String uuid);
	
	/**
	 * Find invoices by their number
	 * 
	 * @param invoiceNumber
	 * @return
	 */
	public List<Invoice> findByInvoiceNumber(String invoiceNumber);
	
	/**
	 * Find invoices by uuid field
	 * 
	 * @param uuid
	 * @return
	 */
	public Invoice findByUuid(String uuid);
	
	/**
	 * Find invoices by invoice number and rfc field
	 * 
	 * @param invoiceNumber
	 * @param rfc
	 * @return
	 */
	public Invoice findByInvoiceNumberAndRfc(String invoiceNumber, String rfc);
	
	/**
	 * Find invoices by uuid, invoice number and rfc fields
	 * 
	 * @param uuid
	 * @param invoiceNumber
	 * @param rfc
	 * @return
	 */
	public Invoice findByUuidAndInvoiceNumberAndRfc(String uuid, String invoiceNumber, String rfc);

}
