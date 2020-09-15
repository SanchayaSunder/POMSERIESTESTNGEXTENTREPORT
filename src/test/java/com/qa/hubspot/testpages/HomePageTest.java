package com.qa.hubspot.testpages;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.pages.HomePage;
import com.qa.hubspot.pages.LoginPage;

public class HomePageTest {

	public class LoginPageTest {
		BasePage basepage;
		Properties prop;
		WebDriver driver;
		HomePage homepage;
		
		//BM-T-AM
		@BeforeTest
		public void setup() throws InterruptedException
		{
			basepage=new BasePage();
			prop=basepage.init_properties();		
			String browser=prop.getProperty("browser");
			driver=basepage.init_driver(browser);
			LoginPage loginpage;
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
			homepage=loginpage.dologin(prop.getProperty("username"),prop.getProperty("pwd"));
			Thread.sleep(10000);
		}
		
	
		@Test(priority=1)
		public void VerifyLoginPageTitle()
		{
			 String title=homepage.GetHomeTitle();
			// Assert.assertEquals(title, "HubSpot Login");
			 System.out.println(title);
		}
		
		
		@Test(priority=3)
		public void Verifyheaderpresent()
		{
			Assert.assertTrue(homepage.IsHeaderPresent());
			
		}
		
		
		@AfterTest
		public void teardown()
		{
			//driver.quit();
		}
	}

}
