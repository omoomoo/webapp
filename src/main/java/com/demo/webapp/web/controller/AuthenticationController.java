package com.demo.webapp.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = { "/security" })
public class AuthenticationController {

	@RequestMapping(value = { "/users" }, method = { RequestMethod.GET })
	public void getPage() {

	}

	@RequestMapping(value = "/users", params = { "format" }, method = { RequestMethod.GET })
	public @ResponseBody
	Object getUsers() {
		System.out.println("/securit/users?format");
		Map<String, String> rs = new HashMap<String, String>();
		rs.put("name", "tiger");
		return rs;
	}

}
