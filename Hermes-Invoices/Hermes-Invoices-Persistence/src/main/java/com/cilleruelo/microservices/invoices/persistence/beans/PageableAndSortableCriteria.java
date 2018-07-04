package com.cilleruelo.microservices.invoices.persistence.beans;

import java.io.Serializable;
import java.util.List;

public class PageableAndSortableCriteria implements Serializable {

	private static final long serialVersionUID = -2206331775978053350L;

	private Integer firstResult;
	private Integer maxResults;
	private int pageNumber;
	private List<SortableProperty> sortableProperties;

	public Integer getFirstResult() {
		return firstResult;
	}

	public void setFirstResult(Integer firstResult) {
		this.firstResult = firstResult;
	}

	public Integer getMaxResults() {
		return maxResults;
	}

	public void setMaxResults(Integer maxResults) {
		this.maxResults = maxResults;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public List<SortableProperty> getSortableProperties() {
		return sortableProperties;
	}

	public void setSortableProperties(List<SortableProperty> sortableProperties) {
		this.sortableProperties = sortableProperties;
	}

}