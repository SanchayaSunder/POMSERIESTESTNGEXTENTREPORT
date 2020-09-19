package com.qa.hubspot.testpages;

import org.testng.annotations.Test;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.pages.HomePage;
import com.qa.hubspot.pages.LoginPage;

public class LoginPageTest {
	BasePage basepage;
	Properties prop;
	public WebDriver driver;
	LoginPage loginpage;
	HomePage homepage;	
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

	@BeforeClass
	public void bfclass()
	{
		
	}
	@Test(priority=1)
	public void VerifyLoginPageTitle()
	{
		 String title=loginpage.getPageTitle();
		 Assert.assertEquals(title,"hfhgf");
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
		String uname=prop.getProperty("username");
		String pword=prop.getProperty("pwd");
		
		try {
			homepage=loginpage.dologin(uname, pword);
		} catch (InterruptedException e) {
			System.out.println("some exception with the login");
		}
			
	}
	
	@AfterTest
	public void teardown()
	{
		
		driver.quit();
	}
	
	

}
