package com.crm.qa.tests;

import java.io.IOException;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.pages.NewContactPage;
import com.crm.qa.util.TestUtil;

public class NewContactPageTest extends TestBase {

	LoginPage loginPage;
	HomePage homePage;
	NewContactPage newContactPage;
	// Initializing TestUtil class here since getCRMContactsTestData is executed before @BeforeMethod
	TestUtil testUtil = new TestUtil();
	// Defining excelPath and sheetName here only for simplicity
	String excelPath = System.getProperty("user.dir")+"/src/main/java/com/crm/qa/testData/freeCRMTestData.xlsx";
	String sheetName = "contacts";
	
	public NewContactPageTest()
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
		// Login into application, switch to frame and click on Contacts Link
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		testUtil.switchToFrame();
		newContactPage = homePage.clickOnNewContactsLink();
		
	}
	
	// Using dataprovider to run test multiple times with different data set
	@DataProvider(name="getCRMContactsData")
	public Object[][] getCRMContactsTestData() throws IOException
	{
		Object[][] data= testUtil.getTestData(excelPath, sheetName);
		return data;	
	}
	
	@Test(dataProvider="getCRMContactsData", priority = 1)
	public void validateCreateMultipleNewContractTest(String title, String firstName, String lastName, String company)
	{
		newContactPage.createNewContact(title, firstName, lastName, company);
	}

	@Test(priority = 2)
	public void validateCreateNewContractTest()
	{
		newContactPage.createNewContact("Mr.", "Tomy", "Sira", "Google");
	}
	
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}
}
