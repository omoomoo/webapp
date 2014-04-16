package com.demo.webapp.codec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Assert;
import org.junit.Test;

public class Base64Test {

	@Test
	public void t01_base64Encode() {
		String plainText = "cokepluscarbon";
		String ciphertext = Base64.encodeBase64String(plainText.getBytes());

		Assert.assertEquals("Y29rZXBsdXNjYXJib24=", ciphertext);
	}

	@Test
	public void t02_base64Decode() {
		String ciphertext = "Y29rZXBsdXNjYXJib24=";
		String plainText = new String(Base64.decodeBase64(ciphertext));

		Assert.assertEquals("cokepluscarbon", plainText);
	}

	@Test
	public void t03_md5Hash() {
		String plainText = "cokepluscarbon";
		String ciphertext = DigestUtils.md5Hex(plainText);

		Assert.assertEquals("b2ae5ff940a47bf9a5a06695089d414c", ciphertext);
	}

	@Test
	public void t04_md5HashWithSalt() {
		String plainText = "cokepluscarbon";
		String salt = "IAMSALT";
		String ciphertext = DigestUtils.md5Hex(plainText + salt);

		Assert.assertEquals("20f23dbff3f91aa59f25b29a93d996e0", ciphertext);
	}

}
