package com.demo.webapp.codec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

public class MD5Test {
	@Test
	public void t01_MD5Hash() {
		System.out.println(DigestUtils.md5Hex("i"));
	}
}
