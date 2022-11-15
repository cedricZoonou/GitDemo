package FirstAutomation.Tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.sun.net.httpserver.Authenticator.Retry;

import FirstAutomation.TestComponents.BaseTest;
import FirstAutomation.pageobjects.CartPage;
import FirstAutomation.pageobjects.LandingPage;
import FirstAutomation.pageobjects.ProductCatalogue;

public class ErrorValidationTest extends BaseTest{
	
	@Test(groups= {"ErrorHandling"}, retryAnalyzer=FirstAutomation.TestComponents.Retry.class)
	public void LoginErrorValidation() throws IOException, InterruptedException { 
	
	
	
	ProductCatalogue productCatalogue =	landingPage.loginAplication("john.smith@aol.com", "Password");
	Assert.assertEquals("Incorrect email or password.",landingPage.getErrorMessage());
	
	
	}
	
	@Test
	public void ProductErrorValidation() throws IOException 
	{
		String productName = "ADIDAS ORIGINAL";
		ProductCatalogue productCatalogue =	landingPage.loginAplication("john.smith@aol.com", "Password1!");
		
		List<WebElement>products =	productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
		CartPage cartPage	= productCatalogue.goToCartPage();
		Boolean match  = cartPage.VerifyProductDisplay("ADIDAS 33");
		Assert.assertFalse(match);
		
		
		
		
	}
	
}
