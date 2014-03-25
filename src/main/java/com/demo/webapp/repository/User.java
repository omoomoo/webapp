package com.demo.webapp.repository;

import java.util.HashSet;
import java.util.Set;

public class User {
	private String username;

	private String password;

	private boolean enabled;

	private String email;

	private String ipaddress;

	private Set<Group> groups = new HashSet<Group>();

	private Set<Authority> authorities = new HashSet<Authority>();

	public User() {
	}

	public User(String username, String password, boolean enabled,
			String email, String ipaddress, Set<Group> groups,
			Set<Authority> authorities) {
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.email = email;
		this.ipaddress = ipaddress;
		this.groups = groups;
		this.authorities = authorities;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Set<Group> getGroups() {
		return groups;
	}

	public void setGroups(Set<Group> groups) {
		this.groups = groups;
	}

	public Set<Authority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Set<Authority> authorities) {
		this.authorities = authorities;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIpaddress() {
		return ipaddress;
	}

	public void setIpaddress(String ipaddress) {
		this.ipaddress = ipaddress;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((username == null) ? 0 : username.hashCode());
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
		User other = (User) obj;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	@Override
	public String toString() {
		"".toString();
		return "User [username=" + username + ", password=" + password
				+ ", enabled=" + enabled + ", email=" + email + ", ipaddress="
				+ ipaddress + ", groups=" + groups + ", authorities="
				+ authorities + "]";
	}

	public void setGroupsAndAuthorities(int[] groupIds, String[] authoritieNames) {
		this.groups.clear();
		this.authorities.clear();
		if (null != groupIds) {
			for (int id : groupIds) {
				Group group = new Group();
				group.setId(id);
				this.groups.add(group);
			}
		}
		if (null != authoritieNames) {
			for (String authorityName : authoritieNames) {
				Authority authority = new Authority();
				authority.setName(authorityName);
				this.authorities.add(authority);
			}
		}

	}

}
