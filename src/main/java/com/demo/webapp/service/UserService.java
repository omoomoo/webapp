package com.demo.webapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.demo.webapp.domain.User;
import com.demo.webapp.repository.UserRepository;
import com.demo.webapp.service.exception.PasswordIncorrectException;
import com.demo.webapp.service.exception.UsernameAlreadyExistsException;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;

	public List<User> getUsers() {
		return userRepository.getUsers();
	}

	public User getUser(long id) {
		return userRepository.getUser(id);
	}

	public void addUser(User user) throws UsernameAlreadyExistsException {
		// 用户名是否存在
		if (getUser(user.getUsername()) != null) {
			throw new UsernameAlreadyExistsException("用户名已经存在！");
		}

		userRepository.addUser(user);
	}

	public void changePassword(String oldPassword, String newPassword) throws PasswordIncorrectException {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = userDetails.getUsername();

		if (!checkPassword(username, oldPassword)) {
			throw new PasswordIncorrectException("您输入的旧密码不正确，请重新输入！");
		}

		userRepository.updatePassword(username, newPassword);
	}

	public boolean checkPassword(String username, String password) {
		User user = this.getUser(username);

		return user.getPassword().equals(password);
	}

	/**
	 * 获取当前用户
	 */
	public User getUser() {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = userDetails.getUsername();

		return getUser(username);
	}

	public User getUser(String username) {
		return userRepository.getUser(username);
	}

	public void updatePersonalDetails(User user) {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = userDetails.getUsername();

		User dbUser = getUser(username);

		// Not all attributes can be changed
		dbUser.setPassword(user.getPassword());
		dbUser.setEmail(user.getEmail());

		updateUser(dbUser);
	}

	public void updateUser(User user) {
		userRepository.updateUser(user);
	}

	public void deleteUser(long id) {
		userRepository.deleteUser(id);
	}

}
