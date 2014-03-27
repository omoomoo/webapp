package com.demo.webapp.repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.demo.webapp.domain.Authority;
import com.demo.webapp.domain.Group;
import com.demo.webapp.domain.User;
import com.demo.webapp.domain.mapper.AuthorityMapper;
import com.demo.webapp.domain.mapper.GroupMapper;
import com.demo.webapp.domain.mapper.UserMapper;

@Repository
public class UserRepository {
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

	public User getUser(long id) {
		Object[] params = new Object[] { id };

		User user = jdbcTemplate.queryForObject(QUERY_USER_BY_ID_SQL, params, new UserMapper());
		List<Group> groups = jdbcTemplate.query(QUERY_GROUPS_BY_USERID, params, new GroupMapper());
		List<Authority> authorities = jdbcTemplate.query(QUERY_AUTHORITIES_BY_USERID, params, new AuthorityMapper());

		user.setGroups(groups);
		user.setAuthorities(authorities);

		return user;
	}

	public User getUser(String username) {
		User dbUser = jdbcTemplate.queryForObject("select * from security_user where username = ? ", new Object[] { username }, new UserMapper());

		if (dbUser != null) {
			return getUser(dbUser.getId());
		}

		return null;
	}

	public List<User> getUsers() {
		return jdbcTemplate.query(QUERY_USERS_SQL, new UserMapper());
	}

	public void updateUser(User user) {
		Session session = sessionFactory.getCurrentSession();

		User dbUser = this.getUser(user.getId());

		user.setUsername(dbUser.getUsername());
		session.update(user);

		// jdbcTemplate.update("update security_user set password = ?, email = ?, enabled = ? where id = ?",
		// new Object[] {
		// user.getPassword(), user.getEmail(), user.isEnabled(), user.getId()
		// });
		//
		// jdbcTemplate.update("delete from security_group_users where user_id = ?",
		// new Object[] { user.getId() });
		// jdbcTemplate.update("delete from security_user_authorities where user_id = ?",
		// new Object[] { user.getId() });
		//
		// for (Group group : user.getGroups()) {
		// jdbcTemplate.update("insert into security_group_users(group_id, user_id) values(?, ?)",
		// new Object[] {
		// group.getId(), user.getId() });
		// }
		// for (Authority authority : user.getAuthorities()) {
		// jdbcTemplate.update("insert into security_user_authorities(user_id, authority_id) values(?, ?)",
		// new Object[] { user.getId(), authority.getId() });
		// }
	}

	public void updatePassword(String username, String password) {
		jdbcTemplate.update(UPDATE_PASSWORD_SQL, new Object[] { username, password });
	}
}
