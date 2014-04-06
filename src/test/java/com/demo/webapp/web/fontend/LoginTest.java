package com.demo.webapp.web.fontend;

import junit.framework.Assert;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginTest {
	private static WebDriver browser;

	@BeforeClass
	public static void beforeClass() {
		System.setProperty("webdriver.chrome.driver", "D:\\chromedriver.exe");
		browser = new ChromeDriver();
	}

	@AfterClass
	public static void afterClass() {
		// browser.close();
	}

	@Test
	public void t01_login_error() {
		browser.get("http://localhost:8080/webapp/login.html");

		browser.findElement(By.id("username")).sendKeys("1");
		browser.findElement(By.id("password")).sendKeys("2");
		browser.findElement(By.id("loginForm")).submit();

		String url = browser.getCurrentUrl();
		Assert.assertEquals("http://localhost:8080/webapp/login.html?error", url);
	}

	@Test
	public void t02_login_success() {
		browser.get("http://localhost:8080/webapp/login.html");

		browser.findElement(By.id("username")).sendKeys("1");
		browser.findElement(By.id("password")).sendKeys("1");
		browser.findElement(By.id("loginForm")).submit();

		String url = browser.getCurrentUrl();
		Assert.assertEquals("http://localhost:8080/webapp/security/overview", url);
	}

	@Test
	public void t03_addUser() {
		browser.get("http://localhost:8080/webapp/security/users");

		browser.findElement(By.className("add-user-trigger")).click();

		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
