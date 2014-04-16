package com.demo.webapp.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public class SaltRepository implements SaltSource {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public Object getSalt(UserDetails userDetails) {
		String username = userDetails.getUsername();

		String salt = jdbcTemplate.queryForObject("select salt from security_user where username = ? limit 1",
				new Object[] { username }, String.class);

		return salt;
	}
}
