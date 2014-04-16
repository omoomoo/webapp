package com.demo.webapp.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.demo.webapp.domain.Authority;
import com.demo.webapp.domain.Group;
import com.demo.webapp.domain.User;
import com.demo.webapp.domain.mapper.AuthorityMapper;
import com.demo.webapp.domain.mapper.GroupMapper;
import com.demo.webapp.domain.mapper.UserMapper;

@Repository
public class UserRepository {
	private final static String QUERY_USERS_SQL = "select * from security_user";
	private final static String QUERY_USER_BY_ID_SQL = "select * from security_user where id=?";
	private final static String QUERY_GROUPS_BY_USERID = "select g.* from security_group as g left join security_group_users as gu on g.id = gu.group_id left join security_user as u on u.id = gu.user_id where u.id=?";
	private final static String QUERY_AUTHORITIES_BY_USERID = "select a.* from security_authority as a left join security_user_authorities as ua on a.id = ua.authority_id left join security_user as u on u.id = ua.user_id where u.id=?";
	private final static String UPDATE_PASSWORD_SQL = "update security_user set password = ?, salt = ? where username = ?";
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public User getUser(long id) {
		Object[] params = new Object[] { id };

		List<User> users = jdbcTemplate.query(QUERY_USER_BY_ID_SQL, params, new UserMapper());
		User user = null;
		if (users.size() == 0) {
			return null;
		}
		user = users.get(0);

		List<Group> groups = jdbcTemplate.query(QUERY_GROUPS_BY_USERID, params, new GroupMapper());
		List<Authority> authorities = jdbcTemplate.query(QUERY_AUTHORITIES_BY_USERID, params, new AuthorityMapper());

		user.setGroups(groups);
		user.setAuthorities(authorities);

		return user;
	}

	public User getUser(String username) {
		List<User> users = jdbcTemplate.query("select * from security_user where username = ?",
				new Object[] { username }, new UserMapper());

		User user = users.size() == 0 ? null : users.get(0);
		if (user != null) {
			user = getUser(user.getId());
		}

		return user;
	}

	public List<User> getUsers() {
		return jdbcTemplate.query(QUERY_USERS_SQL, new UserMapper());
	}

	public void updateUser(User user) {
		// Could not reset password and salt
		jdbcTemplate.update("update security_user set email = ?, enabled = ? where id = ?",
				new Object[] { user.getEmail(), user.isEnabled(), user.getId() });

		jdbcTemplate.update("delete from security_group_users where user_id = ?", new Object[] { user.getId() });
		jdbcTemplate.update("delete from security_user_authorities where user_id = ?", new Object[] { user.getId() });

		for (Group group : user.getGroups()) {
			jdbcTemplate.update("insert into security_group_users(group_id, user_id) values(?, ?)", new Object[] {
					group.getId(), user.getId() });
		}
		for (Authority authority : user.getAuthorities()) {
			jdbcTemplate.update("insert into security_user_authorities(user_id, authority_id) values(?, ?)",
					new Object[] { user.getId(), authority.getId() });
		}
	}

	public void updatePassword(String username, String password, String salt) {
		jdbcTemplate.update(UPDATE_PASSWORD_SQL, new Object[] { password, salt, username });
	}

	public void addUser(final User user) {
		KeyHolder keyHolder = new GeneratedKeyHolder();

		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(
						"insert into security_user(username, password, salt, email, enabled) values(?, ?, ?, ?, ?)",
						new String[] { "id" });
				ps.setString(1, user.getUsername());
				ps.setString(2, user.getPassword());
				ps.setString(3, user.getSalt());
				ps.setString(4, user.getEmail());
				ps.setBoolean(5, user.isEnabled());
				return ps;
			}
		}, keyHolder);
		user.setId(keyHolder.getKey().longValue());

		for (Group group : user.getGroups()) {
			jdbcTemplate.update("insert into security_group_users(group_id, user_id) values(?, ?)", new Object[] {
					group.getId(), user.getId() });
		}
		for (Authority authority : user.getAuthorities()) {
			jdbcTemplate.update("insert into security_user_authorities(user_id, authority_id) values(?, ?)",
					new Object[] { user.getId(), authority.getId() });
		}
	}

	public void deleteUser(long id) {
		Object[] params = new Object[] { id };

		jdbcTemplate.update("delete from security_user_authorities where user_id = ?", params);
		jdbcTemplate.update("delete from security_group_users where user_id = ?", params);
		jdbcTemplate.update("delete from security_user where id = ? ", params);
	}

	public Object getSalt(String username) {
		String salt = jdbcTemplate.queryForObject("select salt from security_user where username = ? limit 1",
				new Object[] { username }, String.class);

		return salt;
	}
}
