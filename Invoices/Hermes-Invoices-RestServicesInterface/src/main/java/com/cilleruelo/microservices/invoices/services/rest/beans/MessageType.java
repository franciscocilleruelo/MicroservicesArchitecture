package com.cilleruelo.microservices.invoices.services.rest.beans;

/**
 * Message type of the message is given as part of a response
 * 
 * @author francisco.cilleruelo
 *
 */
public enum MessageType {
	
	/** SUCCESSFULLY*/
	S,
	
	/** INFORMATION */
	I,
	
	/** WARNING */
	W,
	
	/** ERROR */
	E;
}
