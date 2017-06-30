package com.sample.data.dao.exception;

public class NotFoundException extends RuntimeException {

	private static final long serialVersionUID = 6261703525958502202L;

	public NotFoundException(String message) {
		super(message);
	}
}
