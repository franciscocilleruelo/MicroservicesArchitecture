package com.cilleruelo.microservices.invoices.services.rest.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cilleruelo.microservices.invoices.core.beans.InvoiceDTO;
import com.cilleruelo.microservices.invoices.core.facade.InvoiceFacade;
import com.cilleruelo.microservices.invoices.services.rest.beans.Message;
import com.cilleruelo.microservices.invoices.services.rest.beans.MessageType;
import com.cilleruelo.microservices.invoices.services.rest.beans.StandardResponse;
import com.cilleruelo.microservices.invoices.services.rest.utils.ConstantesRest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * REST Controller example for the Invoices Domain
 * 
 * @author francisco.cilleruelo
 *
 */
@RestController
@RequestMapping(ConstantesRest.URL_REST_INVOICES)
@Api(value = "Operations on the invoices domain")
@DependsOn("pluginManager")
public class InvoiceRestController extends BaseController implements IInvoiceRestController{

	private static final Logger LOG = LoggerFactory.getLogger(InvoiceRestController.class);

	@Autowired
	public InvoiceFacade invoiceFacade;

	/* (non-Javadoc)
	 * @see com.cilleruelo.microservices.invoices.services.rest.controllers.IInvoiceRestController#getInvoiceList()
	 */
	@Override
	@RequestMapping(value = "/", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Get Inovoice list by searching")
	public StandardResponse getInvoiceList() {
		StandardResponse response = new StandardResponse();
		Message message;
		List<Message> msgs = new ArrayList<>();
		try {
			List<InvoiceDTO> lst = invoiceFacade.getInvoices();
			response.setContent(lst);
		} catch (Exception e) {
			LOG.error("Error searching invoices: {}", e);
			response.setContent(false);
			message = new Message();
			message.setMessageType(MessageType.E);
			message.setMessage(e.getMessage());
			msgs.add(message);
		}
		response.setMessages(msgs);
		return response;
	}
	
	/* (non-Javadoc)
	 * @see com.cilleruelo.microservices.invoices.services.rest.controllers.IInvoiceRestController#getInvoice(java.lang.Long)
	 */
	@Override
	@RequestMapping(value = "/{invoiceId}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Get Inovoice list by id")
	public StandardResponse getInvoice(@PathVariable Long invoiceId) {
		StandardResponse response = new StandardResponse();
		Message message;
		List<Message> msgs = new ArrayList<>();
		try {
			InvoiceDTO invoice = invoiceFacade.getInvoice(invoiceId);
			response.setContent(invoice);
		} catch (Exception e) {
			LOG.error("Error searching invoices: {}", e);
			response.setContent(false);
			message = new Message();
			message.setMessageType(MessageType.E);
			message.setMessage(e.getMessage());
			msgs.add(message);
		}
		response.setMessages(msgs);
		return response;
	}
	
	/* (non-Javadoc)
	 * @see com.cilleruelo.microservices.invoices.services.rest.controllers.IInvoiceRestController#addInvoice(com.cilleruelo.microservices.invoices.core.beans.InvoiceDTO, java.util.Locale)
	 */
	@Override
	@RequestMapping(value = "/", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Adding Inovoice")
	public StandardResponse addInvoice(InvoiceDTO invoice, Locale locale) {
		StandardResponse response = new StandardResponse();
		Message message;
		List<Message> msgs = new ArrayList<>();
		message = new Message();
		try {
			InvoiceDTO invoiceAdded = invoiceFacade.addInvoice(invoice);
			response.setContent(invoiceAdded);
			message.setMessageType(MessageType.S);
			message.setMessage(this.getI18nMessage("invoice.added.success", locale));
		} catch (Exception e) {
			LOG.error("Error adding invoice: {}", e);
			response.setContent(false);
			message.setMessageType(MessageType.E);
			message.setMessage(e.getMessage());
		}
		msgs.add(message);
		response.setMessages(msgs);
		return response;
	}
	
	/* (non-Javadoc)
	 * @see com.cilleruelo.microservices.invoices.services.rest.controllers.IInvoiceRestController#updateInvoice(java.lang.Long, com.cilleruelo.microservices.invoices.core.beans.InvoiceDTO, java.util.Locale)
	 */
	@Override
	@RequestMapping(value = "/{invoiceId}", method = RequestMethod.PUT, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Updating Inovoice")
	public StandardResponse updateInvoice(@PathVariable Long invoiceId, InvoiceDTO invoice, Locale locale) {
		StandardResponse response = new StandardResponse();
		Message message;
		List<Message> msgs = new ArrayList<>();
		message = new Message();
		try {
			InvoiceDTO invoiceUpdated = invoiceFacade.updateInvoice(invoiceId, invoice);
			response.setContent(invoiceUpdated);
			message.setMessageType(MessageType.S);
			message.setMessage(this.getI18nMessage("invoice.updated.success", locale));
		} catch (Exception e) {
			LOG.error("Error updating invoice {}: {}", invoiceId, e);
			response.setContent(false);
			message.setMessageType(MessageType.E);
			message.setMessage(e.getMessage());		
		}
		msgs.add(message);
		response.setMessages(msgs);
		return response;
	}
	
	/* (non-Javadoc)
	 * @see com.cilleruelo.microservices.invoices.services.rest.controllers.IInvoiceRestController#deleteInvoice(java.lang.Long, java.util.Locale)
	 */
	@Override
	@RequestMapping(value = "/{invoiceId}", method = RequestMethod.DELETE, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Deleting Inovoice")
	public StandardResponse deleteInvoice(@PathVariable Long invoiceId, Locale locale) {
		StandardResponse response = new StandardResponse();
		Message message;
		List<Message> msgs = new ArrayList<>();
		message = new Message();
		try {
			invoiceFacade.deleteInvoice(invoiceId);
			response.setContent(true);
			message.setMessageType(MessageType.S);
			message.setMessage(this.getI18nMessage("invoice.deleted.success", locale));
		} catch (Exception e) {
			LOG.error("Error deleting invoice{}: {}", invoiceId, e);
			response.setContent(false);
			message.setMessageType(MessageType.E);
			message.setMessage(e.getMessage());
		}
		msgs.add(message);
		response.setMessages(msgs);
		return response;
	}

}
