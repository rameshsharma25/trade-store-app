package com.trade.app.exception;

public class LowerVersionException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int resourceId;
	
	public LowerVersionException(int resourceId, String message) {
		super(message);
		this.resourceId = resourceId;
	}

	public int getResourceId() {
		return resourceId;
	}
	
}
