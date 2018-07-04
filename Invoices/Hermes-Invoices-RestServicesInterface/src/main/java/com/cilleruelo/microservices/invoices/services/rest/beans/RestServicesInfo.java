package com.cilleruelo.microservices.invoices.services.rest.beans;

import java.io.Serializable;

/**
 * Additional service information (such as version)
 * 
 * @author francisco.cilleruelo
 *
 */
public class RestServicesInfo implements Serializable {

	private static final long serialVersionUID = -6527581986539465795L;
	
	private String version;

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
	
	

}
