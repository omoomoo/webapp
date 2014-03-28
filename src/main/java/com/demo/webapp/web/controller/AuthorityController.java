package com.demo.webapp.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.demo.webapp.service.AuthorityService;
import com.demo.webapp.service.GroupService;
import com.demo.webapp.service.UserService;

@Controller
public class AuthorityController {
	@Autowired
	private GroupService groupService;
	@Autowired
	private UserService userService;
	@Autowired
	private AuthorityService authorityService;

}
