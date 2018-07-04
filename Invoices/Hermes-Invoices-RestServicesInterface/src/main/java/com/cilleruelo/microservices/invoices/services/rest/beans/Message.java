package com.cilleruelo.microservices.invoices.services.rest.beans;

import java.io.Serializable;

/**
 * Message is given as part of a response
 * 
 * @author francisco.cilleruelo
 *
 */
public class Message  implements Serializable{

	private static final long serialVersionUID = 6960655765789952225L;

	private MessageType messageType;
	
	private String summaryMessage;
	private String message;
	
	public Message() {
		super();
	}

	public Message(MessageType messageType, String summaryMessage, String message) {
		super();
		this.messageType = messageType;
		this.summaryMessage = summaryMessage;
		this.message = message;
	}
	
	public Message(MessageType messageType, String message) {
		super();
		this.messageType = messageType;
		this.message = message;
	}

	public MessageType getMessageType() {
		return messageType;
	}

	public String getMessage() {
		return message;
	}

	public void setMessageType(MessageType messageType) {
		this.messageType = messageType;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getSummaryMessage() {
		return summaryMessage;
	}

	public void setSummaryMessage(String summaryMessage) {
		this.summaryMessage = summaryMessage;
	}
	
	
}
