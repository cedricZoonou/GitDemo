package FirstAutomation.stepDefinition;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import FirstAutomation.TestComponents.BaseTest;
import FirstAutomation.pageobjects.CartPage;
import FirstAutomation.pageobjects.CheckoutPage;
import FirstAutomation.pageobjects.ConfirmationPage;
import FirstAutomation.pageobjects.LandingPage;
import FirstAutomation.pageobjects.ProductCatalogue;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitionImpl extends BaseTest {

	
	public LandingPage landingPage;
	public ProductCatalogue productCatalogue;
	public ConfirmationPage confirmationPage;
	@Given("I landed on the Ecommerce Page")
	public void I_landed_on_Ecommerce_Page() throws IOException 
	{
		landingPage =	launchApplication();
	}
	
	
	@Given ("^I logged in with username (.+) and (.+)$") 
	public void logged_in_unsername_and_password(String username, String password)
	{
		productCatalogue =	landingPage.loginAplication(username,password);
	}
	
	@When ("^I add the product (.+) to Cart$")
	public void  I_add_the_product_to_cart(String productName) 
	{
		List<WebElement>products =	productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
	}
	
	@When("^Checkout (.+) and submit order$")
	public void checkout_submit_order(String productName)
	{
		CartPage cartPage	= productCatalogue.goToCartPage();
		Boolean match  = cartPage.VerifyProductDisplay(productName);
		Assert.assertTrue(match);
		CheckoutPage checkoutPage =	cartPage.goToCheckout();
		checkoutPage.selectCountry("united");
		confirmationPage =	checkoutPage.submitOrder();
	}
	
	@Then("{string} message is displayed on ConfirmationPage")
	public void message_displayed_confirmationPage(String string) 
	{
		String confirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
		driver.close();
	}

    @Then("^\"([^\"]*)\" message is displayed$")
    public void something_message_is_displayed(String strArg1) throws Throwable {
    	
    	Assert.assertEquals(strArg1,landingPage.getErrorMessage());
    	driver.close();
    }
}
