package com.demo.webapp.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.demo.webapp.service.AuthorityService;
import com.demo.webapp.service.GroupService;
import com.demo.webapp.service.UserService;

@Controller
@RequestMapping(value = { "/security" })
public class GroupController {
	@Autowired
	private GroupService groupService;
	@Autowired
	private UserService userService;
	@Autowired
	private AuthorityService authorityService;

	@RequestMapping(value = "/groups", method = RequestMethod.GET)
	public String getGroups(Model model) {
		model.addAttribute("groups", groupService.getGroups());
		return null;
	}

	@RequestMapping(value = "/group/{id}", method = RequestMethod.GET)
	public String getGroup(@PathVariable("id") long id, Model model) {
		model.addAttribute("group", groupService.getGroup(id));
		model.addAttribute("users", userService.getUsers());
		model.addAttribute("authorities", authorityService.getAuthorities());

		return "security/group";
	}
}
