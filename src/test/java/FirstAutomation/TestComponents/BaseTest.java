package FirstAutomation.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import FirstAutomation.pageobjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest 
{
	public WebDriver driver;
	public LandingPage landingPage;
	
	public WebDriver initialisedDriver() throws IOException 
	{
		
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\FirstAutomation\\resources\\GlobalData.properties");
		prop.load(fis);
		
		String browserName =	 System.getProperty("browser")!=null	? System.getProperty("browser") : prop.getProperty("browser");
		//	prop.getProperty("browser");
		
		if(browserName.contains("chrome"))
		{
			ChromeOptions options = new ChromeOptions();
			WebDriverManager.chromedriver().setup();
			
			
			if(browserName.contains("headless")) {
				options.addArguments("headless");
			}
			driver = new ChromeDriver(options);
			driver.manage().window().setSize(new Dimension(1440,900)); //help to run in full screen
		}
		else if (browserName.equalsIgnoreCase("firefox")) 
		{
			//Firefox
			System.setProperty("webdriver.gecko.driver", "C:\\Users\\CedricIvars\\Downloads\\geckodriver-v0.31.0-win64\\geckodriver.exe");
			
			driver = new FirefoxDriver();
		}
		else if (browserName.equalsIgnoreCase("Edge")) 
		{
			System.setProperty("webdriver.edge.driver", "C:\\Users\\CedricIvars\\Downloads\\edgedriver_win64\\msedgedriver.exe");
			
			driver = new EdgeDriver();
		}
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;
	}
	
	
public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException
	
	{
		
		//read json to string
	@SuppressWarnings("deprecation")
	String jsonContent =	FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
	
	//String to HasMap Jackson Databind
	
	ObjectMapper mapper = new ObjectMapper();
	List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {
	});
	return data;
	//
	
	}

public String getScreenshot(String testCaseName, WebDriver dirver) throws IOException
{
	TakesScreenshot ts = (TakesScreenshot)driver;
	File source =	ts.getScreenshotAs(OutputType.FILE);
	File file = new File (System.getProperty("user.dir")+ "//Reports//" + testCaseName + ".png");
	FileUtils.copyFile(source, file);
	return System.getProperty("user.dir")+ "//Reports//" + testCaseName + ".png";
}





	
	@BeforeMethod(alwaysRun=true)
	public LandingPage launchApplication() throws IOException 
	{
		driver = initialisedDriver();
		landingPage = new LandingPage(driver);
		landingPage.goTo();
		return landingPage;
	}
	
	@AfterMethod(alwaysRun=true)
	public void tearDown() 
	{
		driver.quit();
	}
}
