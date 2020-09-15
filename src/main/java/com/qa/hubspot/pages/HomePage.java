package com.qa.hubspot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.base.BasePage;

public class HomePage extends BasePage{

	WebDriver driver;
	By homelink=By.xpath("//div[@class='nav-links']//a[@id='nav-primary-home']");
	By header=By.xpath("//span//h1[@class='dashboard-selector__title']']");
	By createdashboard=By.xpath("//button[@class='uiButton private-button private-button--secondary private-button--default private-button--non-link']");
	public HomePage(WebDriver driver)
	{
		this.driver=driver;
	}

	
	public String GetHomeTitle()
	{
		return driver.getTitle();
	}
	
	public boolean IsHeaderPresent()
	{
		return driver.findElement(header).isDisplayed();
	}
	public boolean IsHomelinkPresent()
	{
		return driver.findElement(homelink).isDisplayed();
	}
	public void HomeClick()
	{
	 driver.findElement(homelink).click();
	}
}
