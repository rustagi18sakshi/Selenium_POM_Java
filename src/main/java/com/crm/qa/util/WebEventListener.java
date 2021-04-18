package com.crm.qa.util;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;

/*************************************** PURPOSE **********************************
- This class implements the WebDriverEventListener, which is included under events.
The purpose of implementing this interface is to override all the methods and define certain useful  Log statements 
which would be displayed/logged as the application under test is being run.
Do not call any of these methods, instead these methods will be invoked automatically
as an when the action done (click, findBy etc). 
*/

import com.crm.qa.base.TestBase;

public class WebEventListener extends TestBase implements WebDriverEventListener{

	public void onException(Throwable exception, WebDriver driver) 
	{
		System.out.println("Exception occured: " + exception);
		
		try 
		{
			// Able to use without creating TestUtil object, since this method is declared static.
			TestUtil.takeScreenshotAtEndOfTest(); 
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void beforeAlertAccept(WebDriver driver)
	{
		System.out.println("Going to accept alert");	
	}

	public void afterAlertAccept(WebDriver driver) 
	{
		System.out.println("Accepted alert");	
	}
	
	public void beforeAlertDismiss(WebDriver driver) 
	{
		System.out.println("Going to dismiss alert");	
	}

	public void afterAlertDismiss(WebDriver driver) 
	{
		System.out.println("Dismissed alert");	
	}

	public void beforeChangeValueOf(WebElement element, WebDriver driver, CharSequence[] arg2) 
	{
		System.out.println("Value of element : "+element.toString()+" before any changes are made : "+arg2.toString() );	
	}

	public void afterChangeValueOf(WebElement element, WebDriver driver, CharSequence[] arg2) 
	{
		System.out.println("Element : "+element.toString()+" value changed to : "+arg2.toString() );	
	}
	
	public void beforeClickOn(WebElement element, WebDriver driver) 
	{
		System.out.println("Trying to click on element : "+element.toString());
	}

	public void afterClickOn(WebElement element, WebDriver driver) 
	{
		System.out.println("Clicked on element : "+element.toString());
	}

	public void beforeNavigateBack(WebDriver driver) 
	{
		System.out.println("Navigating back to previous page");	
	}
	
	public void afterNavigateBack(WebDriver driver) 
	{
		System.out.println("Navigated back to previous page");
	}

	public void beforeNavigateForward(WebDriver driver) 
	{
		System.out.println("Navigating forward to next page");
	}
	
	public void afterNavigateForward(WebDriver driver) 
	{
		System.out.println("Navigated forward to next page");
	}

	public void beforeNavigateTo(String url, WebDriver driver) 
	{
		System.out.println("Before navigating to URL : "+url);	
	}
	
	public void afterNavigateTo(String url, WebDriver driver) 
	{
		System.out.println("Navigated to URL : "+url);
	}
	
	public void beforeNavigateRefresh(WebDriver driver) 
	{
		System.out.println("Going to refresh page");
	}
	
	public void afterNavigateRefresh(WebDriver driver)
	{
		System.out.println("Page is refreshed");
	}

	public void beforeFindBy(By by, WebElement element, WebDriver driver) 
	{
		System.out.println("Trying to find Element By : " + by.toString());
	}
	
	public void afterFindBy(By by, WebElement element, WebDriver driver) 
	{
		System.out.println("Found Element By : " + by.toString());
	}
	
	public void afterScript(String arg0, WebDriver arg1) {
		// TODO Auto-generated method stub
		
	}

	public void afterSwitchToWindow(String arg0, WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	public <X> void beforeGetScreenshotAs(OutputType<X> arg0) {
		// TODO Auto-generated method stub
		
	}

	public void beforeGetText(WebElement arg0, WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	public void beforeScript(String arg0, WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	public void beforeSwitchToWindow(String arg0, WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	public <X> void afterGetScreenshotAs(OutputType<X> arg0, X arg1) {
		// TODO Auto-generated method stub
		
	}

	public void afterGetText(WebElement arg0, WebDriver driver, String arg2) {
		// TODO Auto-generated method stub
		
	}
	
}
