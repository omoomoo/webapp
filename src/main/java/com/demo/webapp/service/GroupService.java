package com.demo.webapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.demo.webapp.domain.Group;
import com.demo.webapp.repository.GroupRepository;
import com.demo.webapp.service.exception.GroupNameAlreadyExistsException;

@Service
public class GroupService {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private GroupRepository groupRepository;

	public List<Group> getGroups() {
		return groupRepository.getGroups();
	}

	public Group getGroup(long id) {
		return groupRepository.getGroup(id);
	}

	public void updateGroup(Group group) throws GroupNameAlreadyExistsException {
		// 需要判断group name是否已经存在
		groupRepository.updateGroup(group);
	}

	public Group getGroup(String name) {
		return groupRepository.getGroup(name);
	}
}
