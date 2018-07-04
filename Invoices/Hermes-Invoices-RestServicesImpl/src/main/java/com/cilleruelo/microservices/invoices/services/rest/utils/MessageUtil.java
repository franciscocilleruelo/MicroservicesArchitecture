package com.cilleruelo.microservices.invoices.services.rest.utils;

import java.util.List;

import com.cilleruelo.microservices.invoices.services.rest.beans.Message;
import com.cilleruelo.microservices.invoices.services.rest.beans.MessageType;
import com.cilleruelo.microservices.invoices.services.rest.beans.StandardResponse;

/**
 * @author francisco.cilleruelo
 *
 */
public class MessageUtil {
	
	private MessageUtil() {
		throw new IllegalAccessError("Static Utility class");
	}
	
	/**
	 * Append a new message to the standard response message list
	 * 
	 * @param response		Standard response object
	 * @param messageText   Text message
	 * @param messageType	Message type
	 * 
	 * @return Message object added to the list
	 */
	public static Message appendMessageResponse(StandardResponse response, String messageText, MessageType messageType){
		if(response == null){
			response = new StandardResponse();
		}

		Message message = new Message();
		message.setMessage(messageText);
		message.setMessageType(messageType);
		response.appendMessage(message);
		return message;
	}
	
	/**
	 * Verify if response message list contains error messages( MessageType.E = Error )
	 * 
	 * @param messageList  List of messages to verify
	 * @return {@code true}  - if message list contains at least one error message ({@code MessageType.E})<br/>
	 *         {@code false} - otherwise
	 */
	public static boolean existsErrorMessage(List<Message> messageList){
		if( messageList != null ) {
			for(Message message : messageList) {
				if(message.getMessageType() == MessageType.E) {
					return true;
				}
			}
		}
		return false;
	}
}
