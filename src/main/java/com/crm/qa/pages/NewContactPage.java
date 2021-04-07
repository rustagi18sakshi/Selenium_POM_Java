package com.crm.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.crm.qa.base.TestBase;

public class NewContactPage extends TestBase{

	// Page Factory Object Repository
	@FindBy(name="title")
	WebElement titleDropdown;
	
	@FindBy(id="first_name")
	WebElement firstName;
	
	@FindBy(id="surname")
	WebElement lastName;

	@FindBy(name="client_lookup")
	WebElement company;
	
	@FindBy(xpath="//input[@value='Save']")
	WebElement saveBtn;
	
	// Initialize Page Factory
	public NewContactPage()
	{
		PageFactory.initElements(driver, this);
	}
	
	// Actions
	public void createNewContact(String title, String ftName, String ltName, String comp)
	{
		// Enter title
		Select select = new Select(titleDropdown);
		select.selectByVisibleText(title);
		
		// Enter firstname, lastname and company. Click on save button
		firstName.sendKeys(ftName);
		lastName.sendKeys(ltName);
		company.sendKeys(comp);
		saveBtn.click();
	}
}
