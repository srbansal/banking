// added a comment to check git feature
package scripts;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;

public class BrowserCommands {
	WebDriver driver;
	
  @Test
  public void printAllGoogleLinks() throws InterruptedException {
	  driver.get("http://www.wikipedia.org");
	  driver.get("http://www.google.com");
	  
	  Thread.sleep(2000);
	  driver.navigate().back();
	  Thread.sleep(2000);
	  driver.navigate().forward();
	  Thread.sleep(2000);
	  driver.navigate().refresh();
	  Thread.sleep(2000);
	  driver.navigate().to("http://www.nichethyself.com");
	  Dimension size = driver.manage().window().getSize();
	  Thread.sleep(2000);
	  driver.manage().window().maximize();
	  Thread.sleep(2000);
	  driver.manage().window().setSize(size);
	  driver.manage().deleteAllCookies();
	  String currentUrl = driver.getCurrentUrl();
	  String pageContents = driver.getPageSource();
	  System.out.println(currentUrl + " " + pageContents.charAt(0));
	  
	  
  }
  
  
  @BeforeClass
  public void beforeClass() {
	  System.setProperty("webdriver.chrome.driver", "test\\resources\\chromedriver.exe");
	  driver  = new ChromeDriver();
  }

  @AfterClass
  public void afterClass() {
	  driver.quit();
  }

}
