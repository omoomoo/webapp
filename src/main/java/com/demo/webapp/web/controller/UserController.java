package com.demo.webapp.web.controller;

import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.demo.webapp.repository.User;

@Controller
@RequestMapping(value = { "/security" })
public class UserController {
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@RequestMapping(value = "/users", method = { RequestMethod.GET })
	public void getUsers(Model model) {
		List<Map<String, Object>> users = jdbcTemplate.queryForList("select * from security_user", new Object[] {});

		model.addAttribute("users", users);
	}

	@ResponseBody
	@RequestMapping(value = "/user/{id}", params = { "format" }, method = { RequestMethod.GET })
	public Object getUser(@PathVariable("id") long id) {
		Map<String, Object> user = jdbcTemplate.queryForMap("select * from security_user where id=?", new Object[] { id });
		List<Map<String, Object>> groups = jdbcTemplate
				.queryForList(
						"select g.* from security_group as g left join security_group_users as gu on g.id = gu.group_id left join security_user as u on u.id = gu.user_id where u.id = ?",
						new Object[] { id });
		List<Map<String, Object>> authorities = jdbcTemplate
				.queryForList(
						"select a.* from security_authority as a left join security_user_authorities as ua on a.id = ua.authority_id left join security_user as u on u.id = ua.user_id where u.id = ?",
						new Object[] { id });
		user.put("groups", groups);
		user.put("authorities", authorities);

		return user;
	}
}
