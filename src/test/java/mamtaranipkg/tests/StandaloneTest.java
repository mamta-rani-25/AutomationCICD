package mamtaranipkg.tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import mamtaranipkg.pageobjects.CartPage;
import mamtaranipkg.pageobjects.LandingPage;
import mamtaranipkg.pageobjects.ProductCatalogue;

public class StandaloneTest {

	public static void main(String[] args) throws InterruptedException {
		String productName = "ZARA COAT 3";
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		LandingPage landingPage = new LandingPage(driver);
		landingPage.goTo();
		ProductCatalogue productCatalogue = landingPage.loginApplication("er.mamta25@gmail.com", "Iamqueen@00");
		
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
		CartPage cartPage = productCatalogue.goToCartPage();
		
		Boolean match = cartPage.verifyProductDisplay(productName);
		Assert.assertTrue(match); // VALIDATIONS CANNOT GO IN PAGE OBJECT FILES, it should be in test files
		cartPage.goToCheckout();
		
		driver.findElement(By.cssSelector(".totalRow button")).click();

		
		// it's mandatory to write .build().perform(),
		// otherwise (driver.findElement(By.cssSelector("[placeholder='Select
		// Country']")), "india") will have no life

				driver.findElement(By.cssSelector(".action__submit")).click();

		String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

		driver.close();
	}

}
