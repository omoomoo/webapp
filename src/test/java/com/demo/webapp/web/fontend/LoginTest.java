package com.demo.webapp.web.fontend;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class LoginTest {
	@Before
	public void t(){
		
	}
	
	@After
	public void d(){
		
	}
	
	@Test
	public void t01_estDf() {
		Assert.assertEquals(false, false);
	}

	@Test
	public void t02_estDf() {
		Assert.assertEquals(true, true);
	}
	

}
