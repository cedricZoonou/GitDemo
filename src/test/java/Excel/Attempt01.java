package Excel;

import java.util.HashMap;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class Attempt01 extends dataProvider {

	DataFormatter formatter= new DataFormatter();
	@Test(dataProvider="driveTest")
	public void testCaseData(String email, String password, String product) 
	{
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\CedricIvars\\Downloads\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/client/");
		driver.findElement(By.xpath("//input[@id='userEmail']")).sendKeys(email);
		driver.findElement(By.xpath("//input[@id='userPassword']")).sendKeys(password);
		driver.findElement(By.xpath("//input[@id='login']")).click();
		
		
		driver.close();
	}
}
