package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import pageObject.GoogleSearchPageObjects;

public class GoogleSearchPageTest {

	private static WebDriver driver = null;
	
	public static void main(String[] args) {
		
		googleSearchTest();		
	}

	public static void googleSearchTest()
	{
		String projectPath = System.getProperty("user.dir");
		System.out.println("Project path is :"+projectPath);
		// For running on Chrome browser
		System.setProperty("webdriver.chrome.driver", projectPath+"/drivers/chromedriver/chromedriver.exe");
		
		driver = new ChromeDriver();
		driver.get("https://www.google.com/");
		
		GoogleSearchPageObjects object = new GoogleSearchPageObjects(driver);
		
		object.textbox_search("Step by step automation");
		object.button_search();
		
		driver.close();
		driver.quit();
		System.out.println("Test case executed successfully");
	}
}
