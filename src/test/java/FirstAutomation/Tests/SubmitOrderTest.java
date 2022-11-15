package FirstAutomation.Tests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import FirstAutomation.TestComponents.BaseTest;
import FirstAutomation.pageobjects.CartPage;
import FirstAutomation.pageobjects.CheckoutPage;
import FirstAutomation.pageobjects.ConfirmationPage;
import FirstAutomation.pageobjects.LandingPage;
import FirstAutomation.pageobjects.OrderPage;
import FirstAutomation.pageobjects.ProductCatalogue;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SubmitOrderTest extends BaseTest{
	String productName = "ADIDAS ORIGINAL";
	
	@Test(dataProvider="getData", groups= {"Purchase"})
	public void SubmitOrder(HashMap<String, String> input) throws IOException 
	{
		
		ProductCatalogue productCatalogue =	landingPage.loginAplication(input.get("email"), input.get("password"));
		
		List<WebElement>products =	productCatalogue.getProductList();
		productCatalogue.addProductToCart(input.get("product"));
		CartPage cartPage	= productCatalogue.goToCartPage();
		Boolean match  = cartPage.VerifyProductDisplay(input.get("product"));
		Assert.assertTrue(match);
		CheckoutPage checkoutPage =	cartPage.goToCheckout();
		checkoutPage.selectCountry("united");
		ConfirmationPage confirmationPage =	checkoutPage.submitOrder();
		
		String confirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		
		
		
	}
	
	@Test(dependsOnMethods= {"SubmitOrder"}, dataProvider="getData")
	public void OrderHistoryTest(HashMap<String, String> input) throws IOException 
	{
		
		ProductCatalogue productCatalogue =	landingPage.loginAplication(input.get("email"), input.get("password"));
		OrderPage ordersPage =	productCatalogue.goToOrdersPage();
		Assert.assertTrue(ordersPage.VerifyOrderDisplay(input.get("product")));
		
		
	}
	
	
	
	//Extent Reports -
	
	@DataProvider
	public Object[][] getData() throws IOException
	{
		
		
		
	
//	HashMap<String, String> map = new HashMap<String, String>();
//	map.put("email", "john.smith@aol.com");
//	map.put("password", "Password1!");
//	map.put("product", "ADIDAS ORIGINAL");
//	
//	HashMap<String, String> map1 = new HashMap<String, String>();
//	map1.put("email", "benkenobi@aol.com");
//	map1.put("password", "Password2");
//	map1.put("product", "ZARA COAT 3");
		
		List<HashMap<String, String>> data =	getJsonDataToMap(System.getProperty("user.dir")+ "//src//test//java//FirstAutomation//data//PurchaseOrder.json");	
	return	new Object[] [] {{data.get(0)}, {data.get(1)}};
	}
	
	
}
