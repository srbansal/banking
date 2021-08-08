package scripts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class MyFirstSeleniumTest {
	// Added a comment
	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "test\\resources\\chromedriver.exe");
		WebDriver driver  = new ChromeDriver();
		
		driver.get("https://www.wikipedia.org/");
		WebElement englishLink = driver.findElement(By.id("js-link-box-en"));
		englishLink.click();
//		driver.findElement(By.id("js-link-box-en")).click();
		
//		String myName = "Sunil";
//		myName.toUpperCase().equals("SUNIL");
		
		driver.findElement(By.id("searchInput")).sendKeys("Selenium");
//		driver.findElement(By.id("searchButton")).click();
//		For reference purpose only
//		driver.findElement(By.cssSelector("#searchButton")).click();
		String expectedOutput =  "Selenium";
		
		/*String actualOutput =  driver.findElement(By.id("firstHeading")).getText();
		actualOutput.equals(expectedOutput);*/
//		driver.findElement(By.id("firstHeading")).getText().equals(expectedOutput);
		
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
		
		
		driver.close();
//		System.out.println("Hello");
	}

}
