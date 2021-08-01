package scripts;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;

public class HandlingPopUps {
	WebDriver driver;

	@Test(enabled = false)
	public void handlingAlert() throws InterruptedException {
		String url = "https://nichethyself.com/tourism/home.html";
		driver.get(url);

		driver.findElement(By.name("username")).sendKeys("abc");
		driver.findElement(By.name("username")).submit(); // equivalent of enter key to submit the form
		Thread.sleep(2000);
		// driver.findElement(By.name("password")).sendKeys("password"); // not allowed
		// without handling alert
		Alert myAlert;
		try {
			myAlert = driver.switchTo().alert();
			String actualMessage = myAlert.getText();
			String expectedMessage = "Please enter Password";
			assertEquals(actualMessage, expectedMessage);
			myAlert.accept();
			// myAlert.dismiss(); // to cancel the alert
			// myAlert.sendKeys("Pune"); // enter text in the alert, if applicable
		} catch (NoAlertPresentException e) {
			fail("Alert was expected but it's not there. please log a defect");
		}
		driver.findElement(By.name("password")).sendKeys("password");

		// switchTo() method returns TargetLocator and alert() method returns an Alert

		driver.findElement(By.name("password")).sendKeys("password"); // allowed only after handling alert

	}

	@Test
  public void handlingPopup() throws InterruptedException {
	  String url = "https://nichethyself.com/tourism/home.html";
	  driver.get(url);
	  
	  String parentWindowHandle = driver.getWindowHandle();
	  System.out.println("Parent window "+driver.getTitle());
	  
	  driver.findElement(By.xpath("//button[text()='Contact us!']")).click();
	  Thread.sleep(3000);
	  
	  try {
		  driver.switchTo().window("Contact"); // used name of the window as param
		  Thread.sleep(1000);
		  driver.findElement(By.className("glyphicon-search"));//.sendKeys("Selenium");
		  Thread.sleep(1000);
	  }
	  catch(NoSuchWindowException e) {
		  fail("Contact us window as expected but seems it didnot appear");
	  }
	  finally {
//		  driver.quit(); // it will close both the windows ie. parent window as well
		  driver.close(); // it will close current window only
	  }
	  
	  driver.switchTo().window(parentWindowHandle);
	  System.out.println("Parent window "+driver.getTitle());
	  driver.findElement(By.xpath("//button[text()='Write to us!']")).click();
	  
	 Set<String> handles = driver.getWindowHandles();
	 Iterator<String> it = handles.iterator();
	  
	  while(it.hasNext())
	  {
		  String childWindow = it.next();
		  System.out.println(childWindow);
		  
		  if(!parentWindowHandle.equalsIgnoreCase(childWindow))
		  {
//			  Switch to child window
			  
			  try {
				  driver.switchTo().window(childWindow);
				  System.out.println("popup found "+driver.getTitle());
				  driver.findElement(By.name("name")).sendKeys("Sunil");
				  Thread.sleep(2000);
			  }
			  catch(NoSuchWindowException e) {
				  System.out.println("write to us window was expected but not found");
			  }
			  finally {
				  driver.close();
			  }
		  }
	  }
	   
	  driver.switchTo().window(parentWindowHandle);
	  System.out.println("Parent window "+driver.getTitle());
	  
  }

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", "test\\resources\\chromedriver.exe");
		driver = new ChromeDriver();
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
