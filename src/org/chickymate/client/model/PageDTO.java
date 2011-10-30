package org.chickymate.client.model;

import java.util.List;

public class PageDTO extends ModelDTO {
	
	private static final int DEFAULT_PAGE_SIZE = 5;
	private static final String DEFAULT_ORDER_PROPERTY = "createdDate desc";
	private static final int DEFAULT_STARTING_PAGE = 0;
	public static final String ORDER_BY_DATE = "createdDate desc";
	public static final String ORDER_BY_VOTE = "votes desc";
	
	private List<? extends ModelDTO> models;
	private int page = DEFAULT_STARTING_PAGE;
	private boolean nextPage;
	private String orderProperty = DEFAULT_ORDER_PROPERTY;
	private int size = DEFAULT_PAGE_SIZE;
	
	public void setModels(List<? extends ModelDTO> models) {
		this.models = models;
	}
	
	public List<? extends ModelDTO> getModels() {
		return models;
	}
	
	public void setPage(int page) {
		this.page = page;
	}
	
	public int getPage() {
		return page;
	}
	
	public void setOrderProperty(String orderProperty) {
		this.orderProperty = orderProperty;
	}
	
	public String getOrderProperty() {
		return orderProperty;
	}
	
	public boolean hasPrevious() {
		if(page > 0) {
			return true;
		}
		return false;
	}
	
	public void setNextPage(boolean nextPage) {
		this.nextPage = nextPage;
	}
	
	public boolean hasNext() {
		return nextPage;
	}
	
	public void previousPage() {
		page--;
	}
	
	public void nextPage() {
		page++;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getSize() {
		return size;
	}
	
	public int getOffset() {
		return getPage() * getSize();
	}

	public boolean isOrderByDate() {
		if(ORDER_BY_DATE.equals(orderProperty)) {
			return true;
		}
		return false;
	}

	public boolean isOrderByVote() {
		if(ORDER_BY_VOTE.equals(orderProperty)) {
			return true;
		}
		return false;
	}

	public long getPageEnd() {
		return (getPage() + 1) * getSize()+ 1;
	}

}
