package com.qa.hubspot.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BasePage {

	WebDriver driver;
	Properties prop;
	
	/*
	 * 
	 * 
	 */
	public WebDriver init_driver(String browserName) 
	{
		if(browserName.equals("Chrome"))
		{
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
		}
		else if(browserName.equals("FireFox"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
		}
		else 
		{
			WebDriverManager.edgedriver().setup();
			driver=new EdgeDriver();
		}
		driver.manage().window().fullscreen();
		//driver.manage().deleteAllCookies();
		
		return driver;
		
	}
	/*
	 * this method is used to read the properties from config.properties file.
	 */
	public Properties init_properties()
	{
		prop=new Properties();
		try {
			FileInputStream ip=new FileInputStream("D:\\SanchayaJavaPractise\\SAN DOWNLOADS\\Sanchaya downloads\\Sanchayadownloadworkspace\\POMSeriesSeptember2020\\src\\main\\java\\com\\qa\\hubspot\\config\\config.properties");
			prop.load(ip);
		} 
		catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		return prop;
	}
}
