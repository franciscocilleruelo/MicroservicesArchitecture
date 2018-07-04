package com.cilleruelo.microservices.invoices.services.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.Configuration;

import com.cilleruelo.microservices.invoices.core.events.InvoiceEvent;

/**
 * Configuration to use Event Driven Architecture (EDA) by a meessage broker (Kafka, RabbitMQ)
 * 
 * Source.class: This service will communicate as producer with the message broker via the default channel output
 * Sink.class: This service will communicate as consumer with the message broker via the default channel input
 * 
 * @author francisco.cilleruelo
 *
 */
@Configuration
@EnableBinding({Source.class, Sink.class})
public class EventDrivenConfiguration {
	
	private static final Logger LOG = LoggerFactory.getLogger(EventDrivenConfiguration.class);
	
	/**
	 * Listener method binded to the input channel to receive the events (messages) have been sent to the queue
	 * 
	 * @param event Event (InvoceEvent) received by the input channel to be processed
	 */
	@StreamListener(Sink.INPUT)
	public void eventReceived(InvoiceEvent event){
		LOG.info("A new event has been received about the invoice {} due to {}", event.getInvoice().getInvoiceNumber(), event.getMessage());
	}

}
