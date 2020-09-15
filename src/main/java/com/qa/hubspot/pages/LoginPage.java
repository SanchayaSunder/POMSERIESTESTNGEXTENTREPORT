package com.qa.hubspot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.base.BasePage;

public class LoginPage extends BasePage{

	WebDriver driver;
	//https://app.hubspot.com/login
	/**
	 1.Page Objects
	 */
	By email=By.id("username");
	By password=By.id("password");
	By loginbtn=By.id("loginBtn");
	By Signup=By.xpath("//i18n-string[text()='Sign up']");
	By homelink=By.xpath("//div[@class='nav-links']//a[@id='nav-primary-home']");
	/*
	 * 
	 *2. Constructor of Page Class
	 * 
	 */
	public LoginPage(WebDriver driver)
	{
		this.driver=driver;
	}
	/*
	 * 3.PageMethods
	 * 
	 */
	public String getPageTitle()
	{
		String title=driver.getTitle();
		System.out.println("login title is "+title);
		return title;
	}
	
	public boolean verifySignUp()
	{
		Boolean signuptxt=driver.findElement(Signup).isDisplayed();
		return signuptxt;
	}
	
	public HomePage dologin(String un, String pwd)
	{
		driver.findElement(email).sendKeys(un);
		driver.findElement(password).sendKeys(pwd);
		driver.findElement(loginbtn).click();
		
		driver.findElement(homelink).click();
		
		return new HomePage(driver);
		
	}
	
	
}
