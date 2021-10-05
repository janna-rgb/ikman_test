package com.ikman.testCases;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.ikman.pageObject.Logout;
import com.ikman.pageObject.PostAd;
import com.ikman.pageObject.PropertyDetails;

public class PostAd_ikman extends BaseClass{

	
	 @Test(priority=3)
	 public void post_ikmn() throws InterruptedException
	   	{
		 xTest = xReports.createTest("Posting an Ad");
		
		PostAd post_ad_ikman = PageFactory.initElements(driver, PostAd.class);
		Thread.sleep(2000);
		
		logger.info("POST YOUR AD is selected");
		logger.info("Property is selected");
		logger.info("House for Sale is selected");
		logger.info("District is selected");
		post_ad_ikman.post_property();;
		Thread.sleep(3000);
		
	   	}
	 
	 
	 @Test(priority=4)
	 public void setproperty_details() throws InterruptedException
	 {
		
		 xTest = xReports.createTest("Providing details of Ad");
		 
		 PropertyDetails prop = PageFactory.initElements(driver, PropertyDetails.class); 
		
		 logger.info("Providing property details....");
		 prop.property_details();
		 
		 Thread.sleep(2000);
	 }
	 
	/* @Test(priority=5)
	 public  void logout()
	   {	
		 	xTest = xReports.createTest("Logout from Ikman.lk");
		  
			 Logout logout_page = PageFactory.initElements(driver, Logout.class);
			 //Thread.sleep(3000);
			 logout_page.logout();
			 driver.close();
		
	   }
		*/

}
