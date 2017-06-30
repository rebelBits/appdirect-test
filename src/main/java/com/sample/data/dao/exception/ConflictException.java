package com.sample.data.dao.exception;

public class ConflictException extends RuntimeException {

	private static final long serialVersionUID = -2470315794983394849L;

	public ConflictException(String message) {
		super(message);
	}
}
