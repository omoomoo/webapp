package com.demo.webapp.domain.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.demo.webapp.domain.Authority;

public class AuthorityMapper implements RowMapper<Authority> {

	@Override
	public Authority mapRow(ResultSet rs, int rowNum) throws SQLException {
		Authority authority = new Authority();

		authority.setId(rs.getLong("id"));
		authority.setName(rs.getString("name"));

		return authority;
	}
}
