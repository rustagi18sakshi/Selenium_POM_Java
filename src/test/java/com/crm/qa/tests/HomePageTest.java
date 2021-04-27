package com.crm.qa.tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtil;
import com.relevantcodes.extentreports.LogStatus;

public class HomePageTest extends TestBase {
	
	LoginPage loginPage; 
	HomePage homePage ;
	TestUtil testUtil;
	ContactsPage contactPage;
	
	public HomePageTest()
	{
		super(); // This will call the TestBase class constructor so that our properties are loaded.
	}
	
	// Using @BeforeMethod so that test cases are independent of each other.
	// before each test case -- launch the browser and login
	// test - execute test case
	// after each test case -- quit the browser
	@BeforeMethod
	public void setUp()
	{
		instantiateDriver();
		testUtil = new TestUtil();
		loginPage = new LoginPage();
		// To reach the Home page we have to login first.
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test(priority = 1)
	public void verifyHomePageTitleTest()
	{
		extentTest = extent.startTest("verifyHomePageTitleTest");
		
		extentTest.log(LogStatus.INFO, "Verifying home page title");
		String title = homePage.validateHomePageTitle();
		
		Assert.assertEquals(title, "CRMPRO", "Home page title doesn't match");
		extentTest.log(LogStatus.PASS,"Home page title is verified");
	}
	
	@Test(priority = 2)
	public void verifyCorrectUserNameLabelTest()
	{
		extentTest = extent.startTest("verifyCorrectUserNameLabelTest");

		extentTest.log(LogStatus.INFO, "Switching to the frame");
		testUtil.switchToFrame();
		
		extentTest.log(LogStatus.INFO, "Verifying that username is being displayed");
		boolean status = homePage.verifyCorrectUsername();
		
		Assert.assertTrue(status);
		extentTest.log(LogStatus.PASS,"Username is displayed");
	}
	
	@Test(priority = 3)
	public void verifyContactsLinkTest()
	{
		extentTest = extent.startTest("verifyContactsLinkTest");
		
		extentTest.log(LogStatus.INFO, "Switching to the frame");
		testUtil.switchToFrame();
		
		extentTest.log(LogStatus.INFO, "Clicking on Contact Link");
		contactPage = homePage.clickOnContactsLink();
		
		extentTest.log(LogStatus.PASS,"Clicked on Contacts Link");
	}
	
	@AfterMethod
	public void tearDown(ITestResult result) throws IOException
	{
		if(result.getStatus()==ITestResult.FAILURE){
			extentTest.log(LogStatus.FAIL, "TEST CASE FAILED IS "+result.getName()); //to add name in extent report
			extentTest.log(LogStatus.FAIL, "TEST CASE FAILED IS "+result.getThrowable()); //to add error/exception in extent report
			
			String screenshotPath = testUtil.getScreenshot(result.getName());
			extentTest.log(LogStatus.FAIL, extentTest.addScreenCapture(screenshotPath)); //to add screenshot in extent report
		}
		else if(result.getStatus()==ITestResult.SKIP){
			extentTest.log(LogStatus.SKIP, "Test Case SKIPPED IS " + result.getName());
		}
		else if(result.getStatus()==ITestResult.SUCCESS){
			extentTest.log(LogStatus.PASS, "Test Case PASSED IS " + result.getName());

		}
		
		extent.endTest(extentTest); //ending test 
		
		driver.quit();
	}

}
