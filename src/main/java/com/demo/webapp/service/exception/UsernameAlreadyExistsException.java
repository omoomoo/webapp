package com.demo.webapp.service.exception;

public class UsernameAlreadyExistsException extends Exception {
	private static final long serialVersionUID = 1L;

	public UsernameAlreadyExistsException() {
		super("用户名已经存在！");
	}

	public UsernameAlreadyExistsException(String message) {
		super(message);
	}

	public UsernameAlreadyExistsException(String message, Throwable cause) {
		super(message, cause);
	}

	public UsernameAlreadyExistsException(Throwable cause) {
		super(cause);
	}

}
