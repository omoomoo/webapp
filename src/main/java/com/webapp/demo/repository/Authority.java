package com.webapp.demo.repository;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.validator.constraints.NotEmpty;

public class Authority {
	@NotEmpty(message = "权限名不能为空")
	private String name;
	@NotEmpty(message = "权限描述不能为空")
	private String descript;
	private Set<Group> groups = new HashSet<Group>();
	private Set<User> users = new HashSet<User>();

	public Authority() {
	}

	public Authority(String name, String descript, Set<Group> groups,
			Set<User> users) {
		this.name = name;
		this.descript = descript;
		this.groups = groups;
		this.users = users;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescript() {
		return descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;
	}

	public Set<Group> getGroups() {
		return groups;
	}

	public void setGroups(Set<Group> groups) {
		this.groups = groups;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Authority other = (Authority) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Authority [name=" + name + ", descript=" + descript
				+ ", groups=" + groups + ", users=" + users + "]";
	}

	public void setGroupsAndUsers(int[] groupIds, String[] usernames) {
		this.groups.clear();
		this.users.clear();
		if (groupIds != null) {
			for (int id : groupIds) {
				Group group = new Group();
				group.setId(id);
				this.groups.add(group);
			}
		}
		if (usernames != null) {
			for (String username : usernames) {
				User user = new User();
				user.setUsername(username);
				this.users.add(user);
			}
		}
	}

}
