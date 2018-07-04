package com.cilleruelo.microservices.invoices.core.exceptions;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * Exception when an entity is not found on the persistence layer (DB)
 * 
 * @author francisco.cilleruelo
 *
 */
public class EntityNotFoundException extends InvoiceException {

	private static final long serialVersionUID = -6472295316487806302L;
	
	private final Object queriedKey;

    public EntityNotFoundException(String queriedKey, String message) {
        super(String.format("[%s]:".concat(message), queriedKey != null? queriedKey: "NULL"));
    	this.queriedKey = queriedKey;
    }
 
    public EntityNotFoundException(String queriedKey, String message, Exception e) {
        super(String.format("[%s]:".concat(message), queriedKey != null? queriedKey: "NULL"), e);
        this.queriedKey = queriedKey;
    }
    
    public Object getQueriedKey(){
    	return queriedKey;
    }
    
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
	
	

}
