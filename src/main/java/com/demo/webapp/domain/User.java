package com.demo.webapp.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

import com.demo.webapp.domain.validator.UserAdd;
import com.demo.webapp.domain.validator.UserChangePassword;
import com.demo.webapp.domain.validator.UserPersonalUpdate;
import com.demo.webapp.domain.validator.UserUpdate;

@Entity
@Table(name = "security_user")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column(nullable = false, unique = true)
	@Length(message = "用户名长度必须为4-64位字符串！", min = 4, max = 64, groups = { UserAdd.class })
	private String username;
	@Column
	@Length(message = "密码长度必须为8-64位字符串！", min = 8, max = 64, groups = { UserAdd.class, UserUpdate.class,
			UserPersonalUpdate.class, UserChangePassword.class, })
	private String password;
	@Column(nullable = false)
	@NotNull(groups = { UserAdd.class, UserUpdate.class })
	private boolean enabled;
	@Column
	@Email(message = "邮箱格式不合法！", groups = { UserAdd.class, UserUpdate.class, UserPersonalUpdate.class, })
	private String email;
	@ManyToMany(mappedBy = "users", cascade = { CascadeType.REFRESH })
	private List<Group> groups = new ArrayList<Group>();
	@ManyToMany
	@JoinTable(name = "security_user_authorities", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "authority_id", referencedColumnName = "id"))
	private List<Authority> authorities = new ArrayList<Authority>();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Group> getGroups() {
		return groups;
	}

	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}

	public List<Authority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(List<Authority> authorities) {
		this.authorities = authorities;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", enabled=" + enabled
				+ ", email=" + email + ", groups=" + groups + ", authorities=" + authorities + "]";
	}
}
