package com.crm.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.TestBase;

public class LoginPage extends TestBase {

	// Page Factory Object Repository
	@FindBy(name="username")
	@CacheLookup
	WebElement username;
	
	@FindBy(name="password")
	WebElement password;
	
	@FindBy(xpath="//input[@type='submit']")
	WebElement loginBtn;
	
	@FindBy(xpath="//a[contains(text(),'Sign Up')]")
	WebElement signUpBtn;
	
	@FindBy(xpath="//img[contains(@src,'https://classic.freecrm.com/img/logo.png')]")
	WebElement crmProLogo;
	
	// Initialize Page Factory
	// Here, "this" means pointing to the current class objects. We can also use "LoginPage.class" instead of this.
	public LoginPage()
	{
		PageFactory.initElements(driver, this);
	}
	
	// Actions
	public String validateLoginPageTitle()
	{
		return driver.getTitle();
	}
	
	public boolean validateCRMImage()
	{
		return crmProLogo.isDisplayed();
	}
	
	public HomePage login(String userName, String pwd)
	{
		username.sendKeys(userName);
		password.sendKeys(pwd);
		loginBtn.click();
		
		return new HomePage();  // Since the landing page for login is HomePage
	}
}
