package com.cilleruelo.microservices.invoices.core.test.facade.implementation;

import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cilleruelo.microservices.invoices.core.beans.InvoiceDTO;
import com.cilleruelo.microservices.invoices.core.exceptions.EntityNotFoundException;
import com.cilleruelo.microservices.invoices.core.facade.InvoiceFacade;
import com.cilleruelo.microservices.invoices.core.test.configuration.CoreTestConfiguration;
import com.cilleruelo.microservices.invoices.persistence.entities.Invoice;
import com.cilleruelo.microservices.invoices.persistence.repositories.InvoiceRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {CoreTestConfiguration.class})
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class InvoiceFacadeImplTest {
	
	private static final int NUM_INVOICES = 3;
	
	@Autowired
	private InvoiceFacade invoiceFacade;
	
	@Autowired
	private InvoiceRepository invoiceRepository;
	
	private void initializeData(){
		for (int i=0; i<NUM_INVOICES; i++){
			Invoice invoice = new Invoice();
			invoice.setInvoiceNumber("InvoiceNumber" + i);
			invoice.setSerie("Serie" + i);
			invoiceRepository.save(invoice);
		}
	}
	
	@Test
	public void getInvoicesTest() throws Exception{
		initializeData();
		try {
			List<InvoiceDTO> invoices = invoiceFacade.getInvoices();
			Assert.assertNotNull(invoices);
			Assert.assertFalse(invoices.isEmpty());
			Assert.assertEquals(NUM_INVOICES, invoices.size());
			for (int i=0; i<NUM_INVOICES; i++){
				Assert.assertNotNull(invoices.get(i));
				Assert.assertTrue(invoices.get(i).getInvoiceNumber().contains("InvoiceNumber"));
				Assert.assertTrue(invoices.get(i).getSerie().contains("Serie"));
			}
		} catch (Exception e){
			Assert.fail(e.getMessage());
		}
	}
	
	@Test
	public void deleteInvoiceRightTest() throws Exception{
		initializeData();
		try {
			List<Invoice> invoices = invoiceRepository.findAll();
			Invoice invoiceToDelete = invoices.get(0);
			invoiceFacade.deleteInvoice(invoiceToDelete.getInvoiceid());
			invoices = invoiceRepository.findAll();
			Assert.assertNotNull(invoices);
			Assert.assertFalse(invoices.isEmpty());
			Assert.assertEquals(NUM_INVOICES-1, invoices.size());
		} catch (Exception e){
			Assert.fail(e.getMessage());
		}
	}
	
	@Test
	public void deleteInvoiceNotExistTest() throws Exception{
		initializeData();
		try {
			invoiceFacade.deleteInvoice(new Long(342356345346L));
        	Assert.fail("Invoice ID does not exist");
        } catch (Exception e) {
        	Assert.assertTrue(e instanceof EntityNotFoundException);
        }
	}
	
	@Test
	public void addInvoiceTest() throws Exception{
		try {
			InvoiceDTO invoice = new InvoiceDTO();
			invoice.setInvoiceNumber("NumberToAdd");
			invoice.setRfc("RfcToAdd");
			invoiceFacade.addInvoice(invoice);
			List<Invoice> invoices = invoiceRepository.findAll();
			Assert.assertNotNull(invoices);
			Assert.assertFalse(invoices.isEmpty());
			Assert.assertEquals(1, invoices.size());
			Assert.assertNotNull(invoices.get(0));
			Assert.assertEquals("NumberToAdd", invoices.get(0).getInvoiceNumber());
			Assert.assertEquals("RfcToAdd", invoices.get(0).getRfc());
		} catch (Exception e){
			Assert.fail(e.getMessage());
		}
	}
	
	@Test
	public void updateInvoiceRightTest() throws Exception{
		initializeData();
		try {
			List<Invoice> invoices = invoiceRepository.findAll();
			Invoice invoice = invoices.get(0);
			invoice.setInvoiceNumber("NumberToUpdate");
			invoice.setRfc("RfcToUpdate");
			InvoiceDTO invoiceDTO = new InvoiceDTO();
			BeanUtils.copyProperties(invoiceDTO, invoice);
			invoiceFacade.updateInvoice(invoice.getInvoiceid(), invoiceDTO);
			invoice = invoiceRepository.findOne(invoice.getInvoiceid());
			Assert.assertNotNull(invoice);
			Assert.assertEquals("NumberToUpdate", invoice.getInvoiceNumber());
			Assert.assertEquals("RfcToUpdate", invoice.getRfc());
		} catch (Exception e){
			Assert.fail(e.getMessage());
		}
	}
	
	public void updateInvoiceNotExistTest() throws Exception{
		InvoiceDTO invoiceDTO = new InvoiceDTO();
		invoiceDTO.setInvoiceid(new Long(34234234234L)); 
		invoiceDTO.setInvoiceNumber("NotExist");
		try {
			invoiceFacade.updateInvoice(invoiceDTO.getInvoiceid(), invoiceDTO);
        	Assert.fail("Invoice ID does not exist");
        } catch (Exception e) {
        	Assert.assertTrue(e instanceof EntityNotFoundException);
        }
	}

}
