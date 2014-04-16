package com.demo.webapp.service;

import java.util.List;
import java.util.UUID;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.demo.webapp.domain.User;
import com.demo.webapp.repository.SaltRepository;
import com.demo.webapp.repository.UserRepository;
import com.demo.webapp.service.exception.PasswordIncorrectException;
import com.demo.webapp.service.exception.UsernameAlreadyExistsException;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private SaltRepository saltRepository;

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

		String salt = randomSalt();
		String password = encodePassword(user.getPassword(), salt);
		user.setPassword(password);
		user.setSalt(salt);

		userRepository.addUser(user);
	}

	public void resetPassword(String oldPassword, String newPassword) throws PasswordIncorrectException {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = userDetails.getUsername();

		if (!checkPassword(username, oldPassword)) {
			throw new PasswordIncorrectException("您输入的旧密码不正确，请重新输入！");
		}

		String salt = randomSalt();
		newPassword = encodePassword(newPassword, salt);
		userRepository.updatePassword(username, newPassword, salt);
	}

	public boolean checkPassword(String username, String password) {
		User user = this.getUser(username);
		password = encodePassword(password, user.getSalt());

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
		dbUser.setEmail(user.getEmail());

		updateUser(dbUser);
	}

	public void updateUser(User user) {
		userRepository.updateUser(user);
	}

	public void deleteUser(long id) {
		userRepository.deleteUser(id);
	}

	private String randomSalt() {
		String uuid = UUID.randomUUID().toString();
		return DigestUtils.md5Hex(uuid);
	}

	private String encodePassword(String rawPassword, String salt) {
		Md5PasswordEncoder encoder = new Md5PasswordEncoder();
		return encoder.encodePassword(rawPassword, salt);
	}

}
