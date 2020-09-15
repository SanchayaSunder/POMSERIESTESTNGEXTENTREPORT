package com.qa.hubspot.testpages;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.pages.LoginPage;
import com.qa.hubspot.util.Constants;

public class LoginPageTest {
	BasePage basepage;
	Properties prop;
	WebDriver driver;
	LoginPage loginpage;
	//BM-T-AM
	@BeforeTest
	public void setup()
	{
		basepage=new BasePage();
		prop=basepage.init_properties();		
		String browser=prop.getProperty("browser");
		driver=basepage.init_driver(browser);
		
		//driver.manage().window().fullscreen();
		//driver.manage().deleteAllCookies();
		String url=prop.getProperty("url");
		driver.get(url);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		loginpage=new LoginPage(driver);
	}
	@Test(priority=1)
	public void VerifyLoginPageTitle()
	{
		 String title=loginpage.getPageTitle();
		 Assert.assertEquals(title, Constants.LoginTitle);
		 System.out.println(title);
	}
	
	
	@Test(priority=2)
	public void VerifySignUpLink()
	{
		Assert.assertTrue(loginpage.verifySignUp());
		
	}
	@Test(priority=3)
	public void LoginTest()
	{
		String username=prop.getProperty("username");
		String password=prop.getProperty("pwd");
		loginpage.dologin(username, password);
		
	}
	
	@AfterTest
	public void teardown()
	{
		//driver.quit();
	}
}
