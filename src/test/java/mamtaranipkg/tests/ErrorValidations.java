package mamtaranipkg.tests;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import mamtaranipkg.TestComponents.BaseTest;
import mamtaranipkg.TestComponents.Retry;
import mamtaranipkg.pageobjects.CartPage;
import mamtaranipkg.pageobjects.CheckoutPage;
import mamtaranipkg.pageobjects.ConfirmationPage;
import mamtaranipkg.pageobjects.ProductCatalogue;

public class ErrorValidations extends BaseTest {

	@Test(groups = { "ErrorHandling" }, retryAnalyzer = Retry.class)
	public void LoginErrorValidation() throws IOException, InterruptedException {
		landingPage.loginApplication("er.mamta25@gmail.com", "Iamqueen@00");
		Assert.assertEquals("Incorrect email password.", landingPage.getErrorMessage());
	}

	@Test
	public void ProductErrorValidation() throws IOException, InterruptedException {
		String productName = "ZARA COAT 3";

		ProductCatalogue productCatalogue = landingPage.loginApplication("shetty@gmail.com", "Iamking@000");
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
		CartPage cartPage = productCatalogue.goToCartPage();
		Boolean match = cartPage.verifyProductDisplay(productName);
		Assert.assertFalse(match);
	}

}
