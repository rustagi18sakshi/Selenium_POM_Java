package com.crm.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.crm.qa.util.TestUtil;
import com.crm.qa.util.WebEventListener;
import com.qa.ExtentReportListener.ExtentReportsFile;

public class TestBase extends ExtentReportsFile {

	public static WebDriver driver; 
	public static Properties prop;
	
	// Initialize variables for EventFiringWebDriver and WebEventListener class
	public static EventFiringWebDriver e_driver;
	public static WebEventListener eventListener;
	
	// Default constructor to load properties(environment variables)
	public TestBase()
	{
		try 
		{
			prop = new Properties();
			FileInputStream ip = new FileInputStream(System.getProperty("user.dir") + "/src/main/java/com/crm/qa/config/config.properties");
			prop.load(ip);
	
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Function to instantiate Driver
	public void instantiateDriver() {

		String browserName = prop.getProperty("browser");
			
		if(browserName.equalsIgnoreCase("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/drivers/chromedriver/chromedriver.exe");
			driver = new ChromeDriver();
		} 
		else if(browserName.equalsIgnoreCase("firefox"))
		{
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"/drivers/geckodriver/geckodriver.exe");
			driver = new FirefoxDriver();
		}

		//*** Steps for using WebDriverEventListener ***
		e_driver = new EventFiringWebDriver(driver);
		// Now create object of EventListerHandler to register it with EventFiringWebDriver
		eventListener = new WebEventListener();
		e_driver.register(eventListener);
		driver = e_driver;
		//*** ------------ ***
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		// PAGE_LOAD_TIMEOUT and IMPLICIT_WAIT values are coming directly from TestUtils.java class 
		// so that we don't have any hard coded value inside base class
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
		
		driver.get(prop.getProperty("url"));
	}


	
}

