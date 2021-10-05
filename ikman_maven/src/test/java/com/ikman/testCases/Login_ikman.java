package com.ikman.testCases;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.ikman.pageObject.Loginpage;



public class Login_ikman extends BaseClass{

	
	@Test(priority=1)
	public void login()
	
	{
	xTest = xReports.createTest("Login to Ikman.lk"); //entry into extend report	
			
	driver.manage().window().maximize();
	
	logger.info("URL is opened");
	driver.get(url);
	

	//create page object for loginpage using Page Factory
	Loginpage login_page = PageFactory.initElements(driver, Loginpage.class);

	//Navigate to login link and click
	logger.info("Screen is maximized");
	driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	
	logger.info("Login link is clicked");
	login_page.login_click();
	
	logger.info("'continue with Email' is selected");
	logger.info("User name is Entered");
	logger.info("Password is entered");
	login_page.email_login(uid,pw);
	 
	}
	
	
	@Test(priority=2)
	public void verify_ikman_title()

	{
		
	xTest = xReports.createTest("Ikman.lk Title verification");
	
	logger.info("Title of the page is verifying");
	String title = driver.getTitle();
	Reporter.log("The title of the site is :" +title);
	
	Assert.assertEquals(title, "ikman.lk");
	logger.info("Test is done");	
	}
}
