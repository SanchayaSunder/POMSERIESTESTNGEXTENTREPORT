
package com.qa.hubspot.listeners;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.testpages.LoginPageTest;


public class ExtentReportListener extends BasePage implements ITestListener {

	private static final String OUTPUT_FOLDER = "./build/";
	private static final String FILE_NAME = "TestExecutionReport.html";

	private static ExtentReports extent = init();
	public static ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();
	public ExtentTest extentTest;
	private static ExtentReports init() {

		Path path = Paths.get(OUTPUT_FOLDER);
		// if directory exists?
		if (!Files.exists(path)) {
			try {
				Files.createDirectories(path);
			} catch (IOException e) {
				// fail to create directory
				e.printStackTrace();
			}
		}
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(OUTPUT_FOLDER + FILE_NAME);
		htmlReporter.config().setDocumentTitle("Automation Test Results");
		htmlReporter.config().setReportName("Automation Test Results");
		htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
		htmlReporter.config().setTheme(Theme.STANDARD);

		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setReportUsesManualConfiguration(true);

		return extent;
	}

	public synchronized void onStart(ITestContext context) {
		System.out.println("Test Suite started!");
	}

	public synchronized void onFinish(ITestContext context) {
		System.out.println(("Test Suite is ending!"));
		extent.flush();
		test.remove();
	}

	public synchronized void onTestStart(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		String qualifiedName = result.getMethod().getQualifiedName();
		int last = qualifiedName.lastIndexOf(".");
		int mid = qualifiedName.substring(0, last).lastIndexOf(".");
		String className = qualifiedName.substring(mid + 1, last);

		System.out.println(methodName + " started!");
		 extentTest = extent.createTest(result.getMethod().getMethodName(),
				result.getMethod().getDescription());

		extentTest.assignCategory(result.getTestContext().getSuite().getName());
		/*
		 * methodName = StringUtils.capitalize(StringUtils.join(StringUtils.
		 * splitByCharacterTypeCamelCase(methodName), StringUtils.SPACE));
		 */
		extentTest.assignCategory(className);
		test.set(extentTest);
		test.get().getModel().setStartTime(getTime(result.getStartMillis()));
	}

	public synchronized void onTestSuccess(ITestResult result) {
		System.out.println((result.getMethod().getMethodName() + " passed!"));
		test.get().pass("Test passed");
		test.get().getModel().setEndTime(getTime(result.getEndMillis()));
	}

	public synchronized void onTestFailure(ITestResult res) {
		String methodname=res.getMethod().getMethodName();
		String exceptionmessage=Arrays.toString(res.getThrowable().getStackTrace());
		
		extentTest.fail("<details><summary><b><font color=red>Exception occured, click to see details:" + "</font></b></summary>" + exceptionmessage.replaceAll(",", "<br>")+"</details> \n");
		WebDriver driver=((LoginPageTest)res.getInstance()).driver;
		String path=takeScreenShot(res.getMethod().getMethodName(),driver);
		try
		{
			extentTest.fail("<b><font color=red>"+"Screenshot of failure"+"</font></b>",
					MediaEntityBuilder.createScreenCaptureFromPath(path).build());
		
		}
		catch(IOException e)
		{
		extentTest.fail("Test failed cannot attach screenshot");	
		}
		String logText="<b>Test Method"+methodname+"Failed</b>";
		Markup m=MarkupHelper.createLabel(logText, ExtentColor.RED);
		extentTest.log(Status.FAIL, m);
		test.get().getModel().setEndTime(getTime(res.getEndMillis()));
		
	}

	public synchronized void onTestSkipped(ITestResult result) {
		System.out.println((result.getMethod().getMethodName() + " skipped!"));
		
		test.get().getModel().setEndTime(getTime(result.getEndMillis()));
	}

	public synchronized void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		System.out.println(("onTestFailedButWithinSuccessPercentage for " + result.getMethod().getMethodName()));
	}

	private Date getTime(long millis) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(millis);
		return calendar.getTime();
	}
	public String GetScreenshotName(String methodname)
	{
		Date d=new Date();
		String filename=methodname+"_"+d.toString().replace(":", "_").replace(" ", "_")+".png";
		return filename;
	}
	public String takeScreenShot(String methodName, WebDriver driver) {
		String filename=GetScreenshotName(methodName);
		
		String directory = System.getProperty("user.dir") + "\\screenshots\\" ;
		new File(directory).mkdirs();
		String path=directory+filename;
		try {
				File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(screenshot, new File(path));
				System.out.println("***********************************");
				System.out.println("***********SCREENSHOT STORED AT************************"+path);
				System.out.println("***********************************");
		}
		catch (Exception e) {
			System.out.println("Capture Failed " + e.getMessage());
		}
		return path;
	}

	
}

