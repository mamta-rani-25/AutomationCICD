package mamtaranipkg.tests;

import java.awt.im.InputContext;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import mamtaranipkg.TestComponents.BaseTest;
import mamtaranipkg.pageobjects.CartPage;
import mamtaranipkg.pageobjects.CheckoutPage;
import mamtaranipkg.pageobjects.ConfirmationPage;
import mamtaranipkg.pageobjects.OrderPage;
import mamtaranipkg.pageobjects.ProductCatalogue;

public class SubmitOrderTest extends BaseTest {
	String productName = "ZARA COAT 3";

	@Test(dataProvider = "getData", groups= {"Purchase"})
	public void submitOrder(String email, String password, String productName) throws IOException, InterruptedException {

		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get(key:"email"), input.get(key:"password"));

		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(input.get(key:"product"));
		CartPage cartPage = productCatalogue.goToCartPage();

		Boolean match = cartPage.verifyProductDisplay(input.get(key:"product"));
		Assert.assertTrue(match); // VALIDATIONS CANNOT GO IN PAGE OBJECT FILES, it should be in test files
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry("india");
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();

		String confirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

	}

	@Test(dependsOnMethods = { "submitOrder" }) // this will make sure that submitOrder() is executed first, this is now
												// dependent on submitOrder()
	// it cannot be run individually
	public void OrderHistoryTest() {
		ProductCatalogue productCatalogue = landingPage.loginApplication("er.mamta25@gmail.com", "Iamqueen@00");
		OrderPage ordersPage = productCatalogue.goToOrdersPage();
		//ordersPage.verifyOrderDisplay(productName);
		Assert.assertTrue(ordersPage.verifyOrderDisplay(productName));
	}
	

//	@DataProvider
//	public Object[][] getData(){
//		return new Object[][] {{"er.mamta25@gmail.com", "Iamqueen@00", "ZARA COAT 3"}, {"shetty@gmail.com", "Iamking@000", "ADIDAS ORIGINAL"}};
//	}

	@DataProvider
	public Object[][] getData() {
//		HashMap<String, String> map = new HashMap<String, String>();
//		map.put("email", "er.mamta25@gmail.com");
//		map.put("password", "Iamqueen@00");
//		map.put("product", "ZARA COAT 3");
//
//		HashMap<String, String> map1 = new HashMap<String, String>();
//		map1.put("email", "shetty@gmail.com");
//		map1.put("password", "Iamking@00");
//		map1.put("product", "ADIDAS ORIGINAL");

		List<HashMap<String, String>> data = getJsonDataToMap(
				System.getProperty("user.dir") + "/src/test/java/mamtaranipkg/data/PurchaseOrder.json");

		return new Object[][] { { data.get(0) }, { data.get(1) } };
	}

	private List<HashMap<String, String>> getJsonDataToMap(String string) {
		// TODO Auto-generated method stub
		return null;
	}
}
