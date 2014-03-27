package com.demo.webapp.service;

import java.util.List;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.webapp.domain.Authority;
import com.demo.webapp.domain.Group;
import com.demo.webapp.domain.User;
import com.demo.webapp.service.exception.PasswordIncorrectException;

@Service
public class UserService {
	private final static String CHECK_PASSPORD_SQL = "select * from security_user where username=? and password=?";
	private final static String QUERY_USERS_SQL = "select * from security_user";
	private final static String QUERY_USER_BY_ID_SQL = "select * from security_user where id=?";
	private final static String QUERY_GROUPS_BY_USERID = "select g.* from security_group as g left join security_group_users as gu on g.id = gu.group_id left join security_user as u on u.id = gu.user_id where u.id=?";
	private final static String QUERY_AUTHORITIES_BY_USERID = "select a.* from security_authority as a left join security_user_authorities as ua on a.id = ua.authority_id left join security_user as u on u.id = ua.user_id where u.id=?";
	private final static String UPDATE_PASSWORD_SQL = "update security_user set password = ? where username = ?";
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private SessionFactory sessionFactory;

	public Object getUsers() {
		return jdbcTemplate.queryForList(QUERY_USERS_SQL, new Object[] {});
	}

	public Object getUser(long id) {
		Map<String, Object> user = jdbcTemplate.queryForMap(QUERY_USER_BY_ID_SQL, new Object[] { id });
		List<Map<String, Object>> groups = jdbcTemplate.queryForList(QUERY_GROUPS_BY_USERID, new Object[] { id });
		List<Map<String, Object>> authorities = jdbcTemplate.queryForList(QUERY_AUTHORITIES_BY_USERID, new Object[] { id });

		user.put("groups", groups);
		user.put("authorities", authorities);

		return user;
	}

	public void addUser(User user) {
		sessionFactory.getCurrentSession().save(user);
	}

	public void changePassword(String oldPassword, String newPassword) throws PasswordIncorrectException {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = userDetails.getUsername();

		if (!checkPassword(username, oldPassword)) {
			throw new PasswordIncorrectException("您输入的旧密码不正确，请重新输入！");
		}

		jdbcTemplate.update(UPDATE_PASSWORD_SQL, new Object[] { newPassword, username });
	}

	public boolean checkPassword(String username, String password) {
		List<Map<String, Object>> users = jdbcTemplate.queryForList(CHECK_PASSPORD_SQL, new Object[] { username, password });

		return users.size() == 1;
	}

	public Object getUser(String username) {
		long id = jdbcTemplate.queryForObject("select id from security_user where username = ? ", new Object[] { username }, Long.class);
		if (id <= 0) {
			return null;
		}

		return this.getUser(id);
	}

	public void updatePersonalDetails(User user) {
		// Not all attributes can be changed
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = userDetails.getUsername();

		user.setUsername(username);
		jdbcTemplate.update("update security_user set password = ?, email = ? where username = ?", new Object[] { user.getPassword(),
				user.getEmail(), user.getUsername() });
	}

	@Transactional
	public void updateUser(User user) {
		jdbcTemplate.update("update security_user set password = ?, email = ?, enabled = ? where id = ?",
				new Object[] { user.getPassword(), user.getEmail(), user.isEnabled(), user.getId() });

		jdbcTemplate.update("delete from security_group_users where user_id = ?", new Object[] { user.getId() });
		jdbcTemplate.update("delete from security_user_authorities where user_id = ?", new Object[] { user.getId() });
		for (Group group : user.getGroups()) {
			jdbcTemplate.update("insert into security_group_users(group_id, user_id) values(?, ?)", new Object[] { group.getId(), user.getId() });
		}

		for (Authority authority : user.getAuthorities()) {
			jdbcTemplate.update("insert into security_user_authorities(user_id, authority_id) values(?, ?)",
					new Object[] { user.getId(), authority.getId() });
		}

	}
}
