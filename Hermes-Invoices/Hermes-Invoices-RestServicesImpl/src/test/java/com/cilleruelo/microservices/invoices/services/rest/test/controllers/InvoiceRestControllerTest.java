package com.cilleruelo.microservices.invoices.services.rest.test.controllers;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.core.IsNull;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.cilleruelo.microservices.invoices.core.beans.InvoiceDTO;
import com.cilleruelo.microservices.invoices.core.facade.InvoiceFacade;
import com.cilleruelo.microservices.invoices.services.rest.beans.MessageType;
import com.cilleruelo.microservices.invoices.services.rest.controllers.IInvoiceRestController;
import com.cilleruelo.microservices.invoices.services.rest.test.configuration.RestServicesTestConfiguration;

/**
 * Test class to run all the unit tests about the invoice´s REST services implemented on InvoiceRestController class 
 * 
 * @author francisco.cilleruelo
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RestServicesTestConfiguration.class})
public class InvoiceRestControllerTest extends BaseControllerTest {
	
	private MockMvc mockMvc;
	
	private static final int NUM_INVOICES = 3;

	@Mock
	private InvoiceFacade invoiceFacade;

	@InjectMocks
	private IInvoiceRestController invoiceRestController;

	@Before
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders.standaloneSetup(invoiceRestController).build();
	}
	
	private List<InvoiceDTO> getTestInvoices(){
		List<InvoiceDTO> invoices = new ArrayList<>();
		for (int i=0; i<NUM_INVOICES; i++){
			InvoiceDTO invoice = new InvoiceDTO();
			invoice.setInvoiceNumber("InvoiceNumber" + i);
			invoice.setSerie("Serie" + i);
			invoices.add(invoice);
		}
		return invoices;
	}
	
	@Test
	public void getInvoiceListCorrectTest() throws Exception{
		when(invoiceFacade.getInvoices()).thenReturn(getTestInvoices());

		mockMvc.perform(get("/invoices/").accept(MediaType.APPLICATION_JSON_VALUE)
				.contentType(MediaType.APPLICATION_JSON_VALUE)).andDo(print()).andExpect(status().isOk())
				.andExpect(jsonPath("$.content").exists())
				.andExpect(jsonPath("$.content").isNotEmpty())
				.andExpect(jsonPath("$.content[0].invoiceNumber").value("InvoiceNumber0"))
				.andExpect(jsonPath("$.content[1].serie").value("Serie1"))
				.andExpect(jsonPath("$.messages").value(IsNull.notNullValue()));
	}
	
	@Test
	public void getInvoiceListExceptionTest() throws Exception{
		when(invoiceFacade.getInvoices()).thenThrow(Exception.class);

		mockMvc.perform(get("/invoices/").accept(MediaType.APPLICATION_JSON_VALUE)
				.contentType(MediaType.APPLICATION_JSON_VALUE)).andDo(print()).andExpect(status().isOk())
				.andExpect(jsonPath("$.content").exists())
				.andExpect(jsonPath("$.content").isNotEmpty())
				.andExpect(jsonPath("$.content").value(false))
				.andExpect(jsonPath("$.messages").value(IsNull.notNullValue()))
				.andExpect(jsonPath("$.messages[0].messageType").value(MessageType.E.toString()));
	}
	
	@Test
	public void getInvoiceCorrectTest() throws Exception{
		InvoiceDTO invoice = new InvoiceDTO();
		invoice.setInvoiceid(12L);
		invoice.setInvoiceNumber("RF453");
		invoice.setSerie("Serie6");
		
		when(invoiceFacade.getInvoice(any(Long.class))).thenReturn(invoice);

		mockMvc.perform(get("/invoices/{invoiceId}", "1").accept(MediaType.APPLICATION_JSON_VALUE)
				.contentType(MediaType.APPLICATION_JSON_VALUE)).andDo(print()).andExpect(status().isOk())
				.andExpect(jsonPath("$.content").exists())
				.andExpect(jsonPath("$.content").isNotEmpty())
				.andExpect(jsonPath("$.content.invoiceNumber").value("RF453"))
				.andExpect(jsonPath("$.content.serie").value("Serie6"))
				.andExpect(jsonPath("$.messages").value(IsNull.notNullValue()))
				.andExpect(jsonPath("$.messages").isEmpty());
	}
	
	@Test
	public void getInvoiceExceptionTest() throws Exception{
		when(invoiceFacade.getInvoice(any(Long.class))).thenThrow(Exception.class);

		mockMvc.perform(get("/invoices/{invoiceId}", "1").accept(MediaType.APPLICATION_JSON_VALUE)
				.contentType(MediaType.APPLICATION_JSON_VALUE)).andDo(print()).andExpect(status().isOk())
				.andExpect(jsonPath("$.content").exists())
				.andExpect(jsonPath("$.content").isNotEmpty())
				.andExpect(jsonPath("$.content").value(false))
				.andExpect(jsonPath("$.messages").value(IsNull.notNullValue()))
				.andExpect(jsonPath("$.messages[0].messageType").value(MessageType.E.toString()));
	}
	
	@Test
	public void addInvoiceCorrectTest() throws Exception{
		InvoiceDTO invoiceAdded = new InvoiceDTO();
		invoiceAdded.setInvoiceid(23L);
		invoiceAdded.setInvoiceNumber("HR456");
		invoiceAdded.setSerie("Serie5");

		when(invoiceFacade.addInvoice(any(InvoiceDTO.class))).thenReturn(invoiceAdded);

		mockMvc.perform(post("/invoices/").accept(MediaType.APPLICATION_JSON_VALUE)
				.contentType(MediaType.APPLICATION_JSON_VALUE)).andDo(print()).andExpect(status().isOk())
				.andExpect(jsonPath("$.content").exists())
				.andExpect(jsonPath("$.content").isNotEmpty())
				.andExpect(jsonPath("$.content.invoiceNumber").value("HR456"))
				.andExpect(jsonPath("$.content.serie").value("Serie5"))
				.andExpect(jsonPath("$.messages").value(IsNull.notNullValue()))
				.andExpect(jsonPath("$.messages[0].messageType").value(MessageType.S.toString()));
	}
	
	@Test
	public void addInvoiceExceptionTest() throws Exception{
		when(invoiceFacade.addInvoice(any(InvoiceDTO.class))).thenThrow(Exception.class);

		mockMvc.perform(post("/invoices/").accept(MediaType.APPLICATION_JSON_VALUE)
				.contentType(MediaType.APPLICATION_JSON_VALUE)).andDo(print()).andExpect(status().isOk())
		.andExpect(jsonPath("$.content").exists())
		.andExpect(jsonPath("$.content").isNotEmpty())
		.andExpect(jsonPath("$.content").value(false))
		.andExpect(jsonPath("$.messages").value(IsNull.notNullValue()))
		.andExpect(jsonPath("$.messages[0].messageType").value(MessageType.E.toString()));
	}
	
	@Test
	public void updateInvoiceCorrectTest() throws Exception{
		InvoiceDTO invoiceUpdated = new InvoiceDTO();
		invoiceUpdated.setInvoiceid(43L);
		invoiceUpdated.setInvoiceNumber("T45DR");
		invoiceUpdated.setSerie("Serie9");

		when(invoiceFacade.updateInvoice(any(Long.class), any(InvoiceDTO.class))).thenReturn(invoiceUpdated);

		mockMvc.perform(put("/invoices/{invoiceId}", "1").accept(MediaType.APPLICATION_JSON_VALUE)
				.contentType(MediaType.APPLICATION_JSON_VALUE)).andDo(print()).andExpect(status().isOk())
				.andExpect(jsonPath("$.content").exists())
				.andExpect(jsonPath("$.content").isNotEmpty())
				.andExpect(jsonPath("$.content.invoiceNumber").value("T45DR"))
				.andExpect(jsonPath("$.content.serie").value("Serie9"))
				.andExpect(jsonPath("$.messages").value(IsNull.notNullValue()))
				.andExpect(jsonPath("$.messages[0].messageType").value(MessageType.S.toString()));
	}
	
	@Test
	public void updateInvoiceExceptionTest() throws Exception{
		when(invoiceFacade.updateInvoice(any(Long.class), any(InvoiceDTO.class))).thenThrow(Exception.class);

		mockMvc.perform(put("/invoices/{invoiceId}", "1").accept(MediaType.APPLICATION_JSON_VALUE)
				.contentType(MediaType.APPLICATION_JSON_VALUE)).andDo(print()).andExpect(status().isOk())
		.andExpect(jsonPath("$.content").exists())
		.andExpect(jsonPath("$.content").isNotEmpty())
		.andExpect(jsonPath("$.content").value(false))
		.andExpect(jsonPath("$.messages").value(IsNull.notNullValue()))
		.andExpect(jsonPath("$.messages[0].messageType").value(MessageType.E.toString()));
	}
	
	@Test
	public void deleteInvoiceCorrectTest() throws Exception{
		doNothing().when(invoiceFacade).deleteInvoice(any(Long.class));

		mockMvc.perform(delete("/invoices/{invoiceId}", "1").accept(MediaType.APPLICATION_JSON_VALUE)
				.contentType(MediaType.APPLICATION_JSON_VALUE)).andDo(print()).andExpect(status().isOk())
				.andExpect(jsonPath("$.content").exists())
				.andExpect(jsonPath("$.content").isNotEmpty())
				.andExpect(jsonPath("$.content").value(true))
				.andExpect(jsonPath("$.messages").value(IsNull.notNullValue()))
				.andExpect(jsonPath("$.messages[0].messageType").value(MessageType.S.toString()));
	}
	
	@Test
	public void deleteInvoiceExceptionTest() throws Exception{
		doThrow(Exception.class).when(invoiceFacade).deleteInvoice(any(Long.class));

		mockMvc.perform(delete("/invoices/{invoiceId}", "1").accept(MediaType.APPLICATION_JSON_VALUE)
				.contentType(MediaType.APPLICATION_JSON_VALUE)).andDo(print()).andExpect(status().isOk())
		.andExpect(jsonPath("$.content").exists())
		.andExpect(jsonPath("$.content").isNotEmpty())
		.andExpect(jsonPath("$.content").value(false))
		.andExpect(jsonPath("$.messages").value(IsNull.notNullValue()))
		.andExpect(jsonPath("$.messages[0].messageType").value(MessageType.E.toString()));
	}

}
