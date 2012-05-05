package com.dtorres.ejb.exceptions;

public class PgIdentityException extends Exception {
	private static final long serialVersionUID = 1L;

	public PgIdentityException(String message) {
		super(message);
	}

	public PgIdentityException(Throwable throwable) {
		super(throwable);
	}

	public PgIdentityException(String message, Throwable throwable) {
		super(message, throwable);
	}

}
