package com.demo.webapp.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
import com.demo.webapp.domain.validator.UserAdd;
import com.demo.webapp.domain.validator.UserChangePassword;
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

	@RequestMapping(value = "/user", method = { RequestMethod.GET })
	public void getUserPage(Model model) {
		model.addAttribute("groups", groupService.getGroups());
		model.addAttribute("authorities", authorityService.getAuthorities());
	}

	@RequestMapping(value = "/user", method = { RequestMethod.POST })
	public void addUser(@Validated(value = UserAdd.class) User user, HttpServletRequest request, Model model) {
		logger.debug("add User with : {}", user);

		renderUser(user, request);
		userService.addUser(user);
	}

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
	public Object updateUser(@PathVariable("id") long id, User user, HttpServletRequest request, Model model,
			RedirectAttributes redirect) {
		logger.debug("update User with : {}", user);

		renderUser(user, request);
		userService.updateUser(user);

		redirect.addFlashAttribute("success", "更新用户信息成功！");

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
	public String getPasswrodPage(@ModelAttribute("user") User user) {
		return "/security/password";
	}

	/**
	 * 修改个人密码
	 */
	@RequestMapping(value = "/personal/password", method = RequestMethod.PUT)
	public String changePassword(@Validated(value = { UserChangePassword.class }) @ModelAttribute("user") User user,
			BindingResult bindingResult, String oldPassword, Model model, RedirectAttributes redirect) {
		if (bindingResult.hasErrors()) {
			return "/security/password";
		}

		try {
			userService.changePassword(oldPassword, user.getPassword());
		} catch (PasswordIncorrectException e) {
			model.addAttribute("status", "error");
			model.addAttribute("message", e.getMessage());
			model.addAttribute("newPassword", user.getPassword());

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

	public static Map<String, Object> SUCCESS_MESSAGE(String message) {
		Map<String, Object> rs = new HashMap<String, Object>();
		rs.put("status", true);
		rs.put("message", message);
		return rs;
	}
}
