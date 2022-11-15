package SeleniumGrid;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

public class FirefoxTest {
	
	@Test
	public void HomepageTest() throws MalformedURLException 
	{
		
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setBrowserName("firefox");
		caps.setPlatform(Platform.WIN10);
		
		WebDriver driver = new RemoteWebDriver(new URL("http://192.168.1.51:4444"), caps);
		driver.get("http://google.com");
		System.out.println(driver.getTitle());
		driver.findElement(By.name("q")).sendKeys("dnd");
	}

}