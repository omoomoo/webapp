package com.demo.webapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class GroupService {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public Object getGroups() {
		return jdbcTemplate.queryForList("select * from security_group");
	}
}
