package com.demo.webapp.service.exception;

public class GroupNameAlreadyExistsException extends Exception {
	private static final long serialVersionUID = 1L;

	public GroupNameAlreadyExistsException() {
		super("权限组名已经存在！");
	}

	public GroupNameAlreadyExistsException(String message) {
		super(message);
	}

	public GroupNameAlreadyExistsException(String message, Throwable cause) {
		super(message, cause);
	}

	public GroupNameAlreadyExistsException(Throwable cause) {
		super(cause);
	}

}
