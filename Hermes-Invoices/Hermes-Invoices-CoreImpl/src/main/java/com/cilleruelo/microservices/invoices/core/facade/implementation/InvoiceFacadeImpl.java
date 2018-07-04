package com.cilleruelo.microservices.invoices.core.facade.implementation;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.DependsOn;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.cilleruelo.microservices.invoices.core.beans.InvoiceDTO;
import com.cilleruelo.microservices.invoices.core.events.InvoiceEvent;
import com.cilleruelo.microservices.invoices.core.exceptions.EntityNotFoundException;
import com.cilleruelo.microservices.invoices.core.facade.InvoiceFacade;
import com.cilleruelo.microservices.invoices.persistence.entities.Invoice;
import com.cilleruelo.microservices.invoices.persistence.repositories.InvoiceRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@Service
@Transactional
@DependsOn("pluginManager")
public class InvoiceFacadeImpl implements InvoiceFacade {

	@Autowired
	private InvoiceRepository invoiceRepository;

	@Autowired
	private Source source; // It is used to send events by the input channel to the message broker

	private static final Logger LOG = LoggerFactory.getLogger(InvoiceFacadeImpl.class);

	@Override
	@HystrixCommand(fallbackMethod = "getFallbackInvoices", threadPoolKey = "getInvoicesThreadPool", threadPoolProperties = {
			@HystrixProperty(name = "coreSize", value = "10"), @HystrixProperty(name = "maxQueueSize", value = "5") })
	// Mark methods as being managed by Hystrix circuit breaker
	// Generate a proxy that will wrapper it and manage all calls to it through a thread pool of threads specifically set aside to handle remote calls
	// If the timeout is exceeded (1000 msg by default):
	// . If there is no fallback method: HystrixRuntimeException will be thrown
	// . If there is a fallback method: That method will be called as alternative path
	// In addition, according to Bulkhead Pattern, we have set a specific thread
	// pool for this circuit breaker with some properties (coreSize, maxQueueSize...)
	public List<InvoiceDTO> getInvoices() throws Exception {
		LOG.info("Recuperando facturas");
		List<Invoice> listInvoices = invoiceRepository.findAll();
		InvoiceDTO invoiceDto;
		List<InvoiceDTO> listInvoicesDto = new ArrayList<>();
		for (Invoice invoice : listInvoices) {
			invoiceDto = new InvoiceDTO();
			BeanUtils.copyProperties(invoiceDto, invoice);
			listInvoicesDto.add(invoiceDto);
		}
		return listInvoicesDto;
	}

	/**
	 * Fallback method must have same signature method signature as the originating method
	 * 
	 * @return
	 */
	public List<InvoiceDTO> getFallbackInvoices() {
		return new ArrayList<InvoiceDTO>();
	}
	
	@Override
	public InvoiceDTO getInvoice(Long invoiceId) throws Exception {
		LOG.info("Recuperando factura por su id {}", invoiceId);
		Invoice invoiceEntity = invoiceRepository.findOne(invoiceId);
		if (invoiceEntity != null) {
			InvoiceDTO invoice = new InvoiceDTO();
			BeanUtils.copyProperties(invoiceEntity, invoice);			
			return invoice;
		} else {
			throw new EntityNotFoundException(invoiceId.toString(), "Invoice to get does not exists");
		}
	}

	@Override
	public InvoiceDTO addInvoice(InvoiceDTO invoice) throws Exception {
		Invoice invoiceEntity = new Invoice();
		BeanUtils.copyProperties(invoiceEntity, invoice);
		invoiceEntity = invoiceRepository.save(invoiceEntity);

		// Send a new event to message broker as producer (sender)
		InvoiceEvent event = new InvoiceEvent(invoice, "A new invoice has been added");
		// The event (message) is sent by the output channel
		source.output().send(MessageBuilder.withPayload(event).build()); 
		
		InvoiceDTO invoiceAdded = new InvoiceDTO();
		BeanUtils.copyProperties(invoiceAdded, invoiceEntity);
		return invoiceAdded;
	}

	@Override
	public InvoiceDTO updateInvoice(Long invoiceId, InvoiceDTO invoice) throws Exception {
		Invoice invoiceEntity = invoiceRepository.findOne(invoiceId);
		if (invoiceEntity != null) {
			BeanUtils.copyProperties(invoiceEntity, invoice);
			invoiceEntity.setInvoiceid(invoiceId);
			invoiceEntity = invoiceRepository.save(invoiceEntity);
			
			InvoiceDTO invoiceUpdated = new InvoiceDTO();
			BeanUtils.copyProperties(invoiceUpdated, invoiceEntity);
			return invoiceUpdated;
		} else {
			throw new EntityNotFoundException(invoiceId.toString(), "Invoice to delete does not exists");
		}
	}

	@Override
	public void deleteInvoice(Long invoiceId) throws Exception {
		Invoice invoiceEntity = invoiceRepository.findOne(invoiceId);
		if (invoiceEntity != null) {
			invoiceRepository.delete(invoiceId);
		} else {
			throw new EntityNotFoundException(invoiceId.toString(), "Invoice to update does not exists");
		}
	}

}
