package com.trade.app.exception;

public class MaturityDateException  extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		
	private int resourceId;
	
	public MaturityDateException(int resourceId, String message) {
		super(message);
		this.resourceId = resourceId;
	}

	public int getResourceId() {
		return resourceId;
	}

}
