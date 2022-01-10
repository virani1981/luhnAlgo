package com.luhn.algo.exceptions;

public class LuhnApplicationException extends Exception {

	private static final long serialVersionUID = 1L;

	public LuhnApplicationException(String message) {
		super(message);
	}
	
	public LuhnApplicationException(String message, Throwable t) {
		super(message, t);
	}
	
	public LuhnApplicationException(Throwable t) {
		super(t);
	}
}
