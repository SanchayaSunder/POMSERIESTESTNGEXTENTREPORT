package com.qa.hubspot.util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Util {
	WebDriver driver;
	public Util(WebDriver driver)
	{
		this.driver=driver;
	}
	/**
	 * GetElement from locator
	 */
	public WebElement GetElement(By locator)
	{
		WebElement element=null;
		
		try {
			element=driver.findElement(locator);
		} catch (Exception e) {
			System.out.println("Some exception while creating webelement");
			System.out.println(e.getMessage());
			
		}
		return element;
	}
	public void WaitForElementPresent(By locator, int timeout)
	{
		WebDriverWait wait =new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}
	public String WaitforTitlePresent(String title, int timeout)
	{
		WebDriverWait wait =new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.titleContains(title));
		return title;
	}
	/*
	 * This method is used to click on element
	 * 
	 */
	public void doClick(By locator)
	{
		
		try {
			GetElement(locator).click();
		} catch (Exception e) {
			
			System.out.println("Some exception while clicking  web element");
			System.out.println(e.getMessage());
		}
		
	}
	public void doActionClick(By locator)
	{
		
		try {
			Actions action=new Actions(driver);
			action.click(GetElement(locator)).build().perform();
			
		} catch (Exception e) {
			
			System.out.println("Some exception while clicking  web element");
			System.out.println(e.getMessage());
		}
		
	}
	public void doSendKeys(By locator,String value)
	{
		try {
			GetElement(locator).sendKeys(value);
		} catch (Exception e) {
			
			System.out.println("Some exception while sending data to web element");
			System.out.println(e.getMessage());
		}
	}
	public void doActionSendKeys(By locator,String value)
	{
		try {
			Actions action =new Actions(driver);
			action.sendKeys(GetElement(locator), value).build().perform();
			GetElement(locator).sendKeys(value);
		} catch (Exception e) {
			
			System.out.println("Some exception while sending data to web element");
			System.out.println(e.getMessage());
		}
	}
	public String doGetText(By locator,String value)
	{
		try {
			return GetElement(locator).getText();
		} catch (Exception e) {
			
			System.out.println("Some exception while getting data from web element");
			System.out.println(e.getMessage());
			return null;
		}
	}
	public boolean isElementDisplayed(By locator)
	{
		try {
			GetElement(locator).isDisplayed();
			return true;
		} catch (Exception e) {
			return false;
		}
		
		
	}
	
}
