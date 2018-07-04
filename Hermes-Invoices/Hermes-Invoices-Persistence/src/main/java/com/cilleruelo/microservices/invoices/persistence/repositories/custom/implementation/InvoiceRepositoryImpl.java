package com.cilleruelo.microservices.invoices.persistence.repositories.custom.implementation;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.util.StringUtils;

import com.cilleruelo.microservices.invoices.persistence.beans.InvoiceCriteria;
import com.cilleruelo.microservices.invoices.persistence.entities.Invoice;
import com.cilleruelo.microservices.invoices.persistence.repositories.custom.InvoiceRepositoryCustom;

public class InvoiceRepositoryImpl extends BaseRepositoryImpl<Invoice> implements InvoiceRepositoryCustom {

	@Override
	public List<Invoice> findInvoicesByCriteria(InvoiceCriteria criteria) {
		Criteria crit = buildCriteria(criteria);
		crit.setFirstResult(criteria.getFirstResult());
		crit.setMaxResults(criteria.getMaxResults());
		List<Invoice> result = crit.list();
		
		return result;
	}
	
	private Criteria buildCriteria(InvoiceCriteria criteria){
		Criteria crit = getSession().createCriteria(Invoice.class, "inv");

		if(!StringUtils.isEmpty(criteria.getUuid())){
			crit.add(Restrictions.eq("inv.uuid", criteria.getUuid()));
		}
		if(!StringUtils.isEmpty(criteria.getRfc())){
			crit.add(Restrictions.eq("inv.rfc", criteria.getRfc()));
		}
		
		if(!StringUtils.isEmpty(criteria.getInvoiceNumber())){
			crit.add(Restrictions.eq("inv.invoiceNumber", criteria.getInvoiceNumber()));
		}
		
		if(!StringUtils.isEmpty(criteria.getFolio())){
			crit.add(Restrictions.eq("cust.folio", criteria.getFolio()));
		}
		
		if(!StringUtils.isEmpty(criteria.getSerie())){
			crit.add(Restrictions.eq("cust.serie", criteria.getSerie()));
		}
		
		if(!StringUtils.isEmpty(criteria.getTotalAmount())){
			crit.add(Restrictions.eq("cust.totalAmount", criteria.getTotalAmount()));
		}
		
		return crit;
	}

}
