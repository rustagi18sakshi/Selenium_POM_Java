package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class GoogleSearchPageObjects {

	WebDriver driver = null;
	
	//Setting elements locators by using selenium BY package for google search page
	By textbox_search = By.name("q");
	By button_search = By.name("btnK");
	
	public GoogleSearchPageObjects(WebDriver driver)
	{
		this.driver = driver;
	}
	
	public void textbox_search(String text)
	{
		driver.findElement(textbox_search).sendKeys(text);
	}

	public void button_search()
	{
		driver.findElement(textbox_search).sendKeys(Keys.RETURN);
	}
}
