package restAssuredsUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import io.restassured.RestAssured;

public class BaseClass {

	public WebDriver driver;
	@BeforeClass
	public void setupBeforeClass()
	{
		driver=new ChromeDriver();
		RestAssured.baseURI="";
	}
	
	@AfterClass
	public void afterClass()
	{
		if (driver != null)
		{
            driver.quit();
        }
	}
}
