package com.demo.webapp.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = { "/security" })
public class AuthenticationController {

	@RequestMapping(value = "/users", method = { RequestMethod.GET })
	public String getUsers(Model model) {
		Map<String, String> rs = new HashMap<String, String>();
		rs.put("name", "tiger");

		model.addAllAttributes(rs);
		return null;
	}

	@RequestMapping(value = "/user/{id}", params = { "format" }, method = { RequestMethod.GET })
	public @ResponseBody
	Object getUser() {
		return null;
	}

	@RequestMapping(value = "/user/{id}", params = { "format" }, method = { RequestMethod.POST })
	public @ResponseBody
	Object addUser() {
		return null;
	}

	@RequestMapping(value = "/user/{id}", params = { "format" }, method = { RequestMethod.PUT })
	public @ResponseBody
	Object updateUser() {
		return null;
	}

	@RequestMapping(value = "/user/{id}", params = { "format" }, method = { RequestMethod.DELETE })
	public @ResponseBody
	Object deleteUser() {
		return null;
	}

	@RequestMapping(value = "/groups", params = { "format" }, method = { RequestMethod.GET })
	public @ResponseBody
	Object getGroups() {
		return null;
	}

	@RequestMapping(value = "/group/{id}", params = { "format" }, method = { RequestMethod.GET })
	public @ResponseBody
	Object getGroup() {
		return null;
	}

	@RequestMapping(value = "/authorities", params = { "format" }, method = { RequestMethod.GET })
	public @ResponseBody
	Object getAuthorities() {
		return null;
	}

	@RequestMapping(value = "/authority/{id}", params = { "format" }, method = { RequestMethod.GET })
	public @ResponseBody
	Object getAuthority() {
		return null;
	}

}
