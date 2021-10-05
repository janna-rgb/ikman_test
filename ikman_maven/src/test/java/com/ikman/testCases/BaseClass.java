package com.ikman.testCases;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.ikman.pageObject.Loginpage;
import com.ikman.pageObject.Logout;
import com.ikman.pageObject.PostAd;
import com.ikman.pageObject.PropertyDetails;
import com.ikman.utilities.ReadConfig;


public class BaseClass {

	ReadConfig readconfig = new ReadConfig();
	
	static WebDriver driver;
  	public String url= readconfig.getURL();
	public String uid= readconfig.get_username();
	public String pw= readconfig.get_password();
	  
	
	
	public static ExtentHtmlReporter htmlReporter;
	public static ExtentReports xReports;
	public static ExtentTest xTest;
	
	
	public static Logger logger = Logger.getLogger("BaseClass");
	
	
	@BeforeSuite
	public void startReport()
	{
		

		PropertyConfigurator.configure("Log4j.properties");
		
		
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "\\test-output\\MyHTMLReport.html");
		htmlReporter.config().setDocumentTitle("Test Automation Report - Ikman.lk");
		htmlReporter.config().setReportName("Functional Testing");
		htmlReporter.config().setTheme(Theme.DARK);
		
		xReports = new ExtentReports();
		xReports.attachReporter(htmlReporter);
		xReports.setSystemInfo("HostName", "localhost");
		xReports.setSystemInfo("OS", "Windows");
		xReports.setSystemInfo("Browser", "Chrome");
		xReports.setSystemInfo("Tester name", "Janna");
		
	}
	
		
	
	  @BeforeTest
	  public void setup()
	  {
		  
		  System.setProperty("webdriver.chrome.driver", readconfig.get_chrompath());
		  driver = new ChromeDriver();
	  }
		
	  
	  
	  @AfterMethod
	  public void generate_report(ITestResult results) throws IOException
	  {
		  if(results.getStatus() == ITestResult.FAILURE)
		  {
			  xTest.log(Status.FAIL, "The failed test is : " + results.getName());
			  xTest.log(Status.FAIL, "The failed test is : " + results.getThrowable());
			  
			  String screenshotPath = BaseClass.getScreenshot(results.getName());
			  xTest.addScreenCaptureFromPath(screenshotPath);// adding screen shot
		  }
		  else if (results.getStatus() == ITestResult.SUCCESS)
		  {
			  xTest.log(Status.PASS, "The passed test is :" +results.getName());
//			  String screenshotPath = BaseClass.getScreenshot(results.getName());
//			  xTest.addScreenCaptureFromPath(screenshotPath);// adding screen shot
			  
		  }
		  else if (results.getStatus() == ITestResult.SKIP)
		  {
			  xTest.log(Status.PASS, "The skipped test is :" +results.getName());
//			  String screenshotPath = BaseClass.getScreenshot(results.getName());
//			  xTest.addScreenCaptureFromPath(screenshotPath);// adding screen shot
		  }
		  
	  }
	  
		
		
	   @AfterTest
	   public  void tearDown()
	   {	
		   	Logout logout_page = PageFactory.initElements(driver, Logout.class);
			 //Thread.sleep(3000);
		   	
		   	logger.info("My account is clicked");
		   	logger.info("Setting is clicked");
		   	logger.info("Logout from the site");
			 logout_page.logout();
			 driver.close();
			 xReports.flush();
		
	   }
	   
	   public static String getScreenshot(String screenshotName) throws IOException {
		   
		   String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		   TakesScreenshot ts = (TakesScreenshot) driver;
		   File source = ts.getScreenshotAs(OutputType.FILE);
		     
		     // after execution, "FailedTestsScreenshots" folder will be created under src folder
		     String destination = System.getProperty("user.dir") + "/Screenshots/" + screenshotName + dateName + ".png";
		     File finalDestination = new File(destination);
		     FileUtils.copyFile(source, finalDestination);
		     return destination;
		    }
		
		
		
}
