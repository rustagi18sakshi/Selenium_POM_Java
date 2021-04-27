package com.qa.ExtentReportListener;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;


public class ExtentReportsFile {

	public static ExtentReports extent;
	public static ExtentTest extentTest;
	
	@BeforeSuite
	public void reportSetup() 
	{
        // create ExtentReports. True here signifies for override of the file.
        extent = new ExtentReports(System.getProperty("user.dir")+"/test-output/ExtentReport.html", true);
        extent.addSystemInfo("Host Name", "Sakshi");
		extent.addSystemInfo("User Name", "Sakshi Automation Labs");
	}
	
	@AfterSuite
	public void reportTeardown() 
	{
		// calling flush writes everything to the log file.
		extent.flush();
		extent.close();
	}
}
