package com.qa.hubspot.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BasePage {

	WebDriver driver;
	Properties prop;
	
	public WebDriver getDriver() {
		return driver;
	}

	public WebDriver init_driver(String browserName) {

		if (browserName.equals("Chrome")) {
			WebDriverManager.chromedriver().setup();
			//driver = new ChromeDriver();
			driver = new ChromeDriver();
			//tldriver.set(new ChromeDriver());
		} else if (browserName.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			//driver = new FirefoxDriver();
		} else if (browserName.equals("safari")) {

		} else {
			System.out.println(browserName + " Browser value is wrong, please pass the correct browser name....");
		}

		driver.manage().window().fullscreen();
		driver.manage().deleteAllCookies();
		//getDriver().manage().window().fullscreen();
		//getDriver().manage().deleteAllCookies();

		return driver;
	//	return getDriver();
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
