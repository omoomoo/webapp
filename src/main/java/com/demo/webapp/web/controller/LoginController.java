package com.demo.webapp.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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

	@ResponseBody
	@RequestMapping(value = "/security/responseBody")
	public Map<String, String> tResponseBody() {
		System.out.println("test @ResponseBody");
		
		Map<String, String> rs = new HashMap<String, String>();
		rs.put("name", "tiger");

		return rs;
	}
}
