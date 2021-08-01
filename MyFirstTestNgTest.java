package scripts;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.testng.Assert; //for using Assert.assertEquals (otherwise import method statically) 
import org.testng.annotations.AfterMethod;

public class MyFirstTestNgTest {
	WebDriver driver;
	
  @Test
  public void wikiTest() {
//	  All test steps go here
	  driver.get("https://www.wikipedia.org/");
	  driver.findElement(By.id("js-link-box-en")).click();
		
		driver.findElement(By.id("searchInput")).sendKeys("Selenium");
		driver.findElement(By.id("searchButton")).click();
		String expectedOutput =  "Selenium";
		
		if(driver.findElement(By.id("firstHeading")).getText().equals(expectedOutput))
		{
			System.out.println("Heading Test case passed");
		}
		else
		{
			System.out.println("Heading Test case failed");
		}
		
		String expectedPageTitle = "Selenium - Wikipedia";
		String actualPageTitle = driver.getTitle();
		
		if(expectedPageTitle.equals(actualPageTitle)) 
		{
			System.out.println("Title Test case passed");
		}
		else
		{
			System.out.println("Title Test case failed");
		}
		assertEquals(expectedPageTitle, actualPageTitle);
	  
  }
  
  @Test
  public void googleTest()
  {
	  driver.get("https://www.google.com/");
	  driver.findElement(By.name("q")).sendKeys("Selenium Tutorials");
  }
  
  @BeforeMethod
  public void beforeMethod() {
//	  You should code pre-requisite here
//	  e.g. open the browser
		System.setProperty("webdriver.chrome.driver", "test\\resources\\chromedriver.exe");
		driver  = new ChromeDriver();
  }

  @AfterMethod
  public void afterMethod() {
//	  Test case cleanup will go here
//	  e.g. close the browser
	  driver.close();
  }

}
