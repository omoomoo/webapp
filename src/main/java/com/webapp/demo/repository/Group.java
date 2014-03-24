package com.webapp.demo.repository;

import java.util.HashSet;
import java.util.Set;

public class Group {
	private int id;

	private String name;

	private Set<Authority> authorities = new HashSet<Authority>();

	private Set<User> users = new HashSet<User>();

	public Group() {
	}

	public Group(int id, String name, Set<Authority> authorities,
			Set<User> users) {
		this.id = id;
		this.name = name;
		this.authorities = authorities;
		this.users = users;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Authority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Set<Authority> authorities) {
		this.authorities = authorities;
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
		result = prime * result + id;
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
		Group other = (Group) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Group [id=" + id + ", name=" + name + ", authorities="
				+ authorities + ", users=" + users + "]";
	}

	public void setUsersAndAuthorities(String[] usernames,
			String[] authorityNames) {
		this.authorities.clear();
		this.users.clear();
		if (usernames != null) {
			for (String str : usernames) {
				User user = new User();
				user.setUsername(str);
				this.users.add(user);
			}
		}
		if (authorityNames != null) {
			for (String authorityName : authorityNames) {
				Authority authority = new Authority();
				authority.setName(authorityName);
				this.authorities.add(authority);
			}
		}
	}

}
