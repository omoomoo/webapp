package com.demo.webapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.demo.webapp.domain.Authority;
import com.demo.webapp.domain.mapper.AuthorityMapper;

@Service
public class AuthorityService {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<Authority> getAuthorities() {
		return jdbcTemplate.query("select * from security_authority", new AuthorityMapper());
	}
}
