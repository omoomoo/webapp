package com.demo.webapp.codec;

import org.junit.Test;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

public class SpringSalt {
	@Test
	public void t01() {
		Md5PasswordEncoder encoder = new Md5PasswordEncoder();
		System.out.println(encoder.encodePassword("test", "test"));
	}

}
