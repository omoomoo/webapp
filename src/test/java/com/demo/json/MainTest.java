package com.demo.json;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class MainTest {
	private User user;
	private Group group;

	@Before
	public void before() {
		user = new User();
		user.setId(1);
		user.setUsername("tiger");

		group = new Group();
		group.setId(2);
		group.setName("group_admin");

		Set<User> users = new HashSet<User>();
		users.add(user);
		Set<Group> groups = new HashSet<Group>();
		groups.add(group);

		user.setGroups(groups);
		group.setUsers(users);
	}

	@Test
	public void t1() throws JsonGenerationException, JsonMappingException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);

		String userJson = objectMapper.writeValueAsString(user);
		System.out.println(userJson);
	}

	@Test
	public void t2() throws JsonGenerationException, JsonMappingException, IOException {
		String groupJson = new ObjectMapper().writeValueAsString(group);
		System.out.println(groupJson);
	}
}
