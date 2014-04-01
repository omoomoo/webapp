package com.demo.webapp.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.demo.webapp.domain.Authority;
import com.demo.webapp.domain.Group;
import com.demo.webapp.domain.User;
import com.demo.webapp.domain.validator.GroupAdd;
import com.demo.webapp.domain.validator.GroupUpdate;
import com.demo.webapp.service.AuthorityService;
import com.demo.webapp.service.GroupService;
import com.demo.webapp.service.UserService;
import com.demo.webapp.service.exception.GroupNameAlreadyExists;
import com.demo.webapp.service.exception.GroupNameAlreadyExistsException;

@Controller
@RequestMapping(value = { "/security" })
public class GroupController {
	private final static Logger logger = LoggerFactory.getLogger(GroupController.class);
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

	@RequestMapping(value = "/group", method = RequestMethod.GET)
	public String getGroupPage(@ModelAttribute("group") Group group, Model model) {
		model.addAttribute("users", userService.getUsers());
		model.addAttribute("authorities", authorityService.getAuthorities());
		return null;
	}

	@RequestMapping(value = "/group/{id}", method = RequestMethod.GET)
	public String getGroup(@PathVariable("id") long id, Model model) {
		model.addAttribute("group", groupService.getGroup(id));
		model.addAttribute("users", userService.getUsers());
		model.addAttribute("authorities", authorityService.getAuthorities());

		return "/security/group";
	}

	@RequestMapping(value = "/group", method = RequestMethod.POST)
	public String addGroup(@Validated(GroupAdd.class) @ModelAttribute("group") Group group,
			BindingResult bindingResult, Model model, HttpServletRequest request, RedirectAttributes redirect) {

		renderGroup(group, request);
		if (bindingResult.hasErrors()) {
			model.addAttribute("users", userService.getUsers());
			model.addAttribute("authorities", authorityService.getAuthorities());
			return "/security/group";
		}

		try {
			groupService.addGroup(group);
		} catch (GroupNameAlreadyExists e) {
			String error = String.format("您添加的权限组名'%s'已经存在！", group.getName());

			model.addAttribute("users", userService.getUsers());
			model.addAttribute("authorities", authorityService.getAuthorities());
			model.addAttribute("error", error);
			return "/security/group";
		}

		redirect.addFlashAttribute("success", "添加权限组操作成功！");
		return "redirect:/security/group/" + group.getId();
	}

	@RequestMapping(value = "/group/{id}", method = RequestMethod.PUT)
	public String getGroup(@Validated(GroupUpdate.class) @ModelAttribute("group") Group group,
			BindingResult bindingResult, Model model, HttpServletRequest request, RedirectAttributes redirect) {

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

	@RequestMapping(value = "/group/{id}", method = RequestMethod.DELETE)
	public String deleteGroup(@PathVariable("id") long id, Model model) {
		groupService.deleteGroup(id);
		model.addAttribute("success", "删除权限组操作成功！");
		return "security/group";
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
