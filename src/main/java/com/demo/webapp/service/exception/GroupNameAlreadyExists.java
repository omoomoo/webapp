package com.demo.webapp.service.exception;

public class GroupNameAlreadyExists extends Exception {
	private static final long serialVersionUID = 1L;

	public GroupNameAlreadyExists() {
		this("权限组已经存在！");
	}

	public GroupNameAlreadyExists(String message) {
		super(message);
	}

}
