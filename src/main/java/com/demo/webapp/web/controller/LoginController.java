package com.demo.webapp.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

	@RequestMapping(value = "/login.html", method = RequestMethod.GET)
	public String loginPage() {
		return "login";
	}

	@RequestMapping(value = "/security/overview", method = RequestMethod.GET)
	public String overview() {
		return "/security/overview";
	}

	@RequestMapping(value = "/extjs/index")
	public String extjs() {
		return "/extjs/index";
	}

	@RequestMapping(value = "/extjs/test")
	public String extjsTest() {
		return "/extjs/test";
	}
}
