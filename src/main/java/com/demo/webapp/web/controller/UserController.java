package com.demo.webapp.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
import com.demo.webapp.service.exception.PasswordIncorrectException;

@Controller
@RequestMapping(value = { "/security" })
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	@Autowired
	private UserService userService;
	@Autowired
	private GroupService groupService;
	@Autowired
	private AuthorityService authorityService;

	@RequestMapping(value = "/users", method = { RequestMethod.GET })
	public void getUsers(Model model) {
		model.addAttribute("users", userService.getUsers());
	}

	@RequestMapping(value = "/user/{id}", method = { RequestMethod.GET })
	public Object getUser(@PathVariable("id") long id, Model model) {
		model.addAttribute("user", userService.getUser(id));
		model.addAttribute("groups", groupService.getGroups());
		model.addAttribute("authorities", authorityService.getAuthorities());

		return "/security/user";
	}

	@RequestMapping(value = "/user/{id}", method = { RequestMethod.PUT })
	public Object updateUser(@PathVariable("id") long id, User user, HttpServletRequest request, Model model) {
		logger.debug("update User with : {}", user);

		renderUser(user, request);
		userService.updateUser(user);

		return "redirect:/security/user/{id}";
	}

	/**
	 * 个人信息页面
	 */
	@RequestMapping(value = "/personal", method = RequestMethod.GET)
	public String getPersonalDetails(Model model) {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = userDetails.getUsername();

		model.addAttribute("user", userService.getUser(username));
		return "security/personal";
	}

	/**
	 * 修改个人信息页面
	 */
	@RequestMapping(value = "/personal", method = RequestMethod.PUT)
	public String updatePersonalDetails(User user, RedirectAttributes redirectAttributes) {
		logger.debug("修改个人信息 : {}", user);

		userService.updatePersonalDetails(user);

		return "redirect:/security/personal?success";
	}

	/**
	 * 修改密码页面
	 */
	@RequestMapping(value = "/personal/password", method = RequestMethod.GET)
	public String getPasswrodPage() {
		return "/security/password";
	}

	/**
	 * 修改个人密码
	 */
	@RequestMapping(value = "/personal/password", method = RequestMethod.PUT)
	public String changePassword(String oldPassword, String newPassword, Model model, RedirectAttributes redirectAttributes) {
		try {
			userService.changePassword(oldPassword, newPassword);
		} catch (PasswordIncorrectException e) {
			model.addAttribute("status", "error");
			model.addAttribute("message", e.getMessage());
			model.addAttribute("oldPassword", oldPassword);
			model.addAttribute("newPassword", newPassword);

			return "/security/password";
		}

		return "redirect:/security/personal/password?success";
	}

	private void renderUser(User user, HttpServletRequest request) {
		String[] groupIds = request.getParameterValues("group.id");
		String[] authorityIds = request.getParameterValues("authority.id");

		List<Group> groups = new ArrayList<Group>();
		if (groupIds != null) {
			for (String id : groupIds) {
				Group group = new Group();
				group.setId(Long.parseLong(id));
				groups.add(group);
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

		user.setGroups(groups);
		user.setAuthorities(authorities);
	}
}
