package com.demo.webapp.web.controller;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.demo.webapp.domain.User;
import com.demo.webapp.service.UserService;

@Controller
@RequestMapping(value = { "/security" })
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/users", method = { RequestMethod.GET })
	public void getUsers(Model model) {
		model.addAttribute("users", userService.getUsers());
	}

	@ResponseBody
	@RequestMapping(value = "/user/{id}", params = { "format" }, method = { RequestMethod.GET })
	public Object getUser(@PathVariable("id") long id) {
		return userService.getUser(id);
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
		return "/security/person";
	}

	/**
	 * 修改个人密码
	 */
	@RequestMapping(value = "/personal/password", method = RequestMethod.PUT)
	public String changePassword() {
		return "/security/person";
	}
}
