package com.cilleruelo.microservices.invoices.services.rest.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Standard response is given back when a REST request is done
 * 
 * @author francisco.cilleruelo
 *
 */
public class StandardResponse implements Serializable {
	
	private static final long serialVersionUID = 3606805524740485148L;
	
	public static final Logger LOG = LoggerFactory.getLogger(StandardResponse.class);

	private Object content;
	
	private List<Message> messages = null;
	
	public Object getContent() {
		return content;
	}

	public List<Message> messages() {
		return messages;
	}

	public void setContent(Object content) {
		this.content = content;
	}

	public void setMessages(List<Message> messages) {
		if(this.messages != null){
			LOG.warn("Current response just containts a message list. You are overwriting it!");
		}
		this.messages = messages;
	}
	
	public List<Message> getMessages() {
		return messages;
	}

	public void appendMessage(Message message){
		if(messages == null){
			this.messages = new ArrayList<>();
		}
		this.messages.add(message);
	}

	

}
