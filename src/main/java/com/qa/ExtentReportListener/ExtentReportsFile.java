package com.qa.ExtentReportListener;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class ExtentReportsFile {

	public static ExtentHtmlReporter htmlReporter;
	public static ExtentReports extent;
	
	@BeforeSuite
	public void reportSetup() 
	{
		// start reporters
        htmlReporter = new ExtentHtmlReporter("extent.html");
    
        // create ExtentReports and attach reporter(s)
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
	}
	
	@AfterSuite
	public void reportTeardown() 
	{
		// calling flush writes everything to the log file
		extent.flush();
	}
}
