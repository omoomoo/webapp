package com.demo.webapp.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.demo.webapp.service.AuthorityService;
import com.demo.webapp.service.GroupService;
import com.demo.webapp.service.UserService;

@Controller
@RequestMapping("/security")
public class AuthorityController {
	@Autowired
	private GroupService groupService;
	@Autowired
	private UserService userService;
	@Autowired
	private AuthorityService authorityService;

	@RequestMapping(value = "/authorities", method = RequestMethod.GET)
	public String getAuthorities(Model model) {
		model.addAttribute("authorities", authorityService.getAuthorities());
		return "/security/authorities";
	}

}
