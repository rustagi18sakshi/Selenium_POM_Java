package com.crm.qa.tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtil;

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
		ExtentTest test1 = extent.createTest("verifyHomePageTitleTest","This test is used to verify title of home page");
		
		test1.log(Status.INFO, "Verifying home page title");
		String title = homePage.validateHomePageTitle();
		Assert.assertEquals(title, "CRMPRO", "Home page title doesn't match");
		test1.pass("Home page title is verified");
	}
	
	//@Test(priority = 2)
	public void verifyCorrectUserNameLabelTest()
	{
		testUtil.switchToFrame();
		boolean status = homePage.verifyCorrectUsername();
		Assert.assertTrue(status);
	}
	
	//@Test(priority = 3)
	public void verifyContactsLinkTest()
	{
		testUtil.switchToFrame();
		contactPage = homePage.clickOnContactsLink();	
	}
	
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}

}
