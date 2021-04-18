package com.crm.qa.tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtil;

public class ContactsPageTest extends TestBase {
	
	LoginPage loginPage;
	HomePage homePage;
	ContactsPage contactsPage;
	TestUtil testUtil;
	
	public ContactsPageTest()
	{
		super();	// This will call the TestBase class constructor so that our properties are loaded.
	}
	
	// Using @BeforeMethod so that test cases are independent of each other.
	// before each test case -- launch the browser, login, switch to frame and click on Contacts link
	// test - execute test case
	// after each test case -- quit the browser
	@BeforeMethod
	public void setUp()
	{
		instantiateDriver();
		loginPage = new LoginPage();
		testUtil = new TestUtil();
		// Login into application, switch to frame and click on Contacts Link
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		testUtil.switchToFrame();
		contactsPage = homePage.clickOnContactsLink();
	}
	
	@Test(priority = 1)
	public void verifyContactsPageLabelTest()
	{
		boolean status = contactsPage.verifyContactsLabel();
		Assert.assertTrue(status, "Contact page label is not present on the page");
	}
	
	@Test(priority = 2)
	public void selectContactsCheckboxTest()
	{
		contactsPage.selectContactsByName("chaitanya ram");
	}
	
	@Test(priority = 3)
	public void selectMultipleContactsCheckboxTest()
	{
		contactsPage.selectContactsByName("chaitanya ram");
		contactsPage.selectContactsByName("Chandru Selvam");
	}
	
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}
}
