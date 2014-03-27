package com.demo.webapp.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.demo.webapp.domain.Authority;
import com.demo.webapp.domain.Group;
import com.demo.webapp.domain.User;
import com.demo.webapp.domain.mapper.AuthorityMapper;
import com.demo.webapp.domain.mapper.GroupMapper;
import com.demo.webapp.domain.mapper.UserMapper;
import com.demo.webapp.service.exception.GroupNameAlreadyExistsException;

@Service
public class GroupService {
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

	public void updateGroup(Group group) throws GroupNameAlreadyExistsException {
		// 需要判断group name是否已经存在

		jdbcTemplate.update("update security_group set name = ? where id = ? ",
				new Object[] { group.getName(), group.getId() });

		jdbcTemplate.update("delete from security_group_users where group_id = ?", new Object[] { group.getId() });
		jdbcTemplate
				.update("delete from security_group_authorities where group_id = ?", new Object[] { group.getId() });

		// TODO 改用批量更新
		for (User user : group.getUsers()) {
			jdbcTemplate.update("insert into security_group_users(group_id, user_id) values(?, ?)", new Object[] {
					group.getId(), user.getId() });
		}
		for (Authority authority : group.getAuthorities()) {
			jdbcTemplate.update("insert into security_group_authorities(group_id, authority_id) values(?, ?)",
					new Object[] { group.getId(), authority.getId() });
		}
	}

	public boolean isGroupExists(String name) {
		return this.getGroup(name) != null;
	}

	public Group getGroup(String name) {
		List<Group> groups = jdbcTemplate.query("select * from security_group where name = ? limit 1",
				new Object[] { name }, new GroupMapper());

		return groups.size() > 0 ? groups.get(0) : null;
	}

	public Group getGroup(long id, String name) {
		List<Group> groups = jdbcTemplate.query("select * from security_group where id = ? and name = ? limit 1",
				new Object[] { id, name }, new GroupMapper());

		return groups.size() > 0 ? groups.get(0) : null;
	}
}
