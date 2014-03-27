package com.demo.webapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.demo.webapp.domain.User;
import com.demo.webapp.repository.UserRepository;
import com.demo.webapp.service.exception.PasswordIncorrectException;

@Service
public class UserService {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private UserRepository userRepository;

	public List<User> getUsers() {
		return userRepository.getUsers();
	}

	public User getUser(long id) {
		return userRepository.getUser(id);
	}

	public void addUser(User user) {

	}

	public void changePassword(String oldPassword, String newPassword) throws PasswordIncorrectException {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = userDetails.getUsername();

		if (!checkPassword(username, oldPassword)) {
			throw new PasswordIncorrectException("您输入的旧密码不正确，请重新输入！");
		}

		userRepository.updatePassword(username, oldPassword);
	}

	public boolean checkPassword(String username, String password) {
		User user = this.getUser(username);

		return user.getPassword() == password;
	}

	public User getUser(String username) {
		return userRepository.getUser(username);
	}

	public void updatePersonalDetails(User user) {
		// Not all attributes can be changed
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = userDetails.getUsername();

		user.setUsername(username);
		jdbcTemplate.update("update security_user set password = ?, email = ? where username = ?",
				new Object[] { user.getPassword(), user.getEmail(), user.getUsername() });
	}

	public void updateUser(User user) {
		userRepository.updateUser(user);
	}

}
