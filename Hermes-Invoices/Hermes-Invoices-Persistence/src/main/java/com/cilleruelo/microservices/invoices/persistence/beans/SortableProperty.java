package com.cilleruelo.microservices.invoices.persistence.beans;

public class SortableProperty {
	
	public enum SortEnum{
		ASC, DESC;
	}
	
	private SortEnum direction;
	private String property;
	
	public SortEnum getDirection() {
		return direction;
	}
	
	public void setDirection(SortEnum direction) {
		this.direction = direction;
	}
	
	public String getProperty() {
		return property;
	}
	
	public void setProperty(String property) {
		this.property = property;
	}


}
