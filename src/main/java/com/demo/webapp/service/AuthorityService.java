package com.demo.webapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.demo.webapp.domain.Authority;

@Service
public class AuthorityService extends AbstractService {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@SuppressWarnings("unchecked")
	public List<Authority> getAuthorities() {
		return this.getSession().createCriteria(Authority.class).list();
	}

}
