package com.demo.webapp.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.demo.webapp.domain.Authority;
import com.demo.webapp.domain.Group;
import com.demo.webapp.domain.User;
import com.demo.webapp.service.AuthorityService;
import com.demo.webapp.service.GroupService;
import com.demo.webapp.service.UserService;
import com.demo.webapp.service.exception.GroupNameAlreadyExistsException;

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

	@RequestMapping(value = "/group/{id}", method = RequestMethod.PUT)
	public String getGroup(Group group, Model model, HttpServletRequest request, RedirectAttributes redirect) {

		renderGroup(group, request);
		try {
			groupService.updateGroup(group);
		} catch (GroupNameAlreadyExistsException e) {
			model.addAttribute("users", userService.getUsers());
			model.addAttribute("authorities", authorityService.getAuthorities());
			return "/security/group";
		}

		redirect.addFlashAttribute("success", "更新权限组信息成功！");

		return "redirect:/security/group/{id}";
	}

	private void renderGroup(Group group, HttpServletRequest request) {
		String[] userIds = request.getParameterValues("user.id");
		String[] authorityIds = request.getParameterValues("authority.id");

		List<User> users = new ArrayList<User>();
		if (userIds != null) {
			for (String id : userIds) {
				User user = new User();
				user.setId(Long.parseLong(id));
				users.add(user);
			}
		}

		List<Authority> authorities = new ArrayList<Authority>();
		if (authorityIds != null) {
			for (String id : authorityIds) {
				Authority authority = new Authority();
				authority.setId(Long.parseLong(id));
				authorities.add(authority);
			}
		}

		group.setUsers(users);
		group.setAuthorities(authorities);
	}
}
