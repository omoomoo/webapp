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
public class GroupRepository {
	private static final String QUERY_GROUPS_SQL = "select * from security_group";
	private static final String QUERY_GROUP_BY_ID_SQL = "select * from security_group where id = ?";
	private static final String QUERY_USERS_BY_GROUPID = "select u.* from security_user as u left join security_group_users as gu on gu.user_id = u.id left join security_group as g on g.id = gu.group_id where g.id = ?";
	private static final String QUERY_AUTHORITIES_BY_GROUPID = "select a.* from security_authority as a left join security_group_authorities as ga on ga.authority_id = a.id left join security_group as g on g.id = ga.group_id where g.id = ?";
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<Group> getGroups() {
		return jdbcTemplate.query(QUERY_GROUPS_SQL, new GroupMapper());
	}

	public Group getGroup(long id) {
		Object[] params = new Object[] { id };

		Group group = jdbcTemplate.queryForObject(QUERY_GROUP_BY_ID_SQL, params, new GroupMapper());
		List<User> users = jdbcTemplate.query(QUERY_USERS_BY_GROUPID, params, new UserMapper());
		List<Authority> authorities = jdbcTemplate.query(QUERY_AUTHORITIES_BY_GROUPID, params, new AuthorityMapper());

		group.setUsers(users);
		group.setAuthorities(authorities);

		return group;
	}

	public void updateGroup(Group group) {
		jdbcTemplate.update("update security_group set name = ? where id = ? ",
				new Object[] { group.getName(), group.getId() });

		jdbcTemplate.update("delete from security_group_users where group_id = ?", new Object[] { group.getId() });
		jdbcTemplate
				.update("delete from security_group_authorities where group_id = ?", new Object[] { group.getId() });

		for (User user : group.getUsers()) {
			jdbcTemplate.update("insert into security_group_users(group_id, user_id) values(?, ?)", new Object[] {
					group.getId(), user.getId() });
		}
		for (Authority authority : group.getAuthorities()) {
			jdbcTemplate.update("insert into security_group_authorities(group_id, authority_id) values(?, ?)",
					new Object[] { group.getId(), authority.getId() });
		}
	}

	public Group getGroup(String name) {
		List<Group> groups = jdbcTemplate.query("select * from security_group where name = ? limit 1",
				new Object[] { name }, new GroupMapper());

		Group group = null;
		if (groups.size() > 0) {
			group = this.getGroup(groups.get(0).getId());
		}

		return group;
	}

	public void addGroup(final Group group) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement("insert into security_group(name) values(?)",
						new String[] { "id" });
				ps.setString(1, group.getName());

				return ps;
			}
		}, keyHolder);

		group.setId(keyHolder.getKey().longValue());

		// TODO 重复代码
		for (User user : group.getUsers()) {
			jdbcTemplate.update("insert into security_group_users(group_id, user_id) values(?, ?)", new Object[] {
					group.getId(), user.getId() });
		}
		for (Authority authority : group.getAuthorities()) {
			jdbcTemplate.update("insert into security_group_authorities(group_id, authority_id) values(?, ?)",
					new Object[] { group.getId(), authority.getId() });
		}
	}

	public void deleteGroup(long id) {
		Object[] params = new Object[] { id };

		jdbcTemplate.update("delete from security_group_users where group_id = ?", params);
		jdbcTemplate.update("delete from security_group_authorities where group_id = ?", params);
		jdbcTemplate.update("delete from security_group where id = ?", params);
	}
}
