package com.crm.qa.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.crm.qa.base.TestBase;

public class TestUtil extends TestBase {
	
	public static long PAGE_LOAD_TIMEOUT = 20;
	public static long IMPLICIT_WAIT = 10;
	
	public static XSSFWorkbook workbook;
	public static XSSFSheet sheet;
	
	public void switchToFrame()
	{
		driver.switchTo().frame("mainpanel");
	}
	
	public Object[][] getTestData(String excelSheetPath, String sheetName) throws IOException 
	{
		workbook = new XSSFWorkbook(excelSheetPath);
		sheet = workbook.getSheet(sheetName);
		
		int rowCount = sheet.getPhysicalNumberOfRows();
		int colCount = sheet.getRow(0).getPhysicalNumberOfCells();
		
		Object[][] data = new Object[rowCount-1][colCount];
		
		for(int i=1; i<rowCount ;i++)
		{
			for(int j=0; j<colCount ;j++)
			{
				data[i-1][j] = sheet.getRow(i).getCell(j).toString();
			}
		}
		return data;
	}
	
	public static void takeScreenshotAtEndOfTest() throws IOException 
	{
		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String currentDir = System.getProperty("user.dir");
		FileUtils.copyFile(srcFile, new File(currentDir + "/screenshots/" + System.currentTimeMillis() + ".png"));
	}
	
	public static String getScreenshot(String screenshotName) throws IOException{
		
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddhhmmss");
		String dateName = formatter.format(date);

		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		
		// after execution, you could see a folder "FailedTestsScreenshots" under src folder
		String currentDir = System.getProperty("user.dir");
		String destination = currentDir+"/FailedTestsScreenshots/"+screenshotName+dateName+".png";

		FileUtils.copyFile(srcFile, new File(destination));
		
		return destination;
	}
}

























