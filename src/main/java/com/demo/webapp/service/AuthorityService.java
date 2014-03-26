package com.demo.webapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class AuthorityService {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public Object getAuthorities() {
		return jdbcTemplate.queryForList("select * from security_authority");
	}
}
