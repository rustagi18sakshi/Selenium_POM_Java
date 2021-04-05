package com.crm.qa.tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;

public class LoginPageTest extends TestBase{

	LoginPage loginPage; 
	HomePage homePage ;
	
	public LoginPageTest() {
		super(); // This will call the TestBase class constructor so that our properties are loaded.
	}
	
	// This will open browser 3 times since we have 3 tests below
	// This is done so that test cases are independent of each other.
	@BeforeMethod
	public void setUp()
	{
		instantiateDriver();
		loginPage = new LoginPage();
	}
	
	@Test(priority = 1)
	public void loginPageTitleTest()
	{
		String title = loginPage.validateLoginPageTitle();
		Assert.assertEquals(title, "CRMPRO - CRM software for customer relationship management, sales, and support.");
	}
	
	@Test(priority = 2)
	public void crmLogoImageTest()
	{
		boolean status = loginPage.validateCRMImage();
		Assert.assertTrue(status);
	}
	
	@Test(priority = 3)
	public void loginTest()
	{
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}
}
