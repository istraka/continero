package cz.arduinoshop;


import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class ArduinoShopShieldsTestCase {
	public static final String url = "https://arduino-shop.cz";
	private static FirefoxDriver driver;

	@BeforeAll
	public static void setup() {
		driver = new FirefoxDriver();

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}

	@AfterAll
	public static void tearDown() {
		driver.quit();
	}

	/**
	 * Test scenario does not check titles and so on as it should be done by other test cases
	 *
	 * <ol>
	 * <li>access arduino-shop..cz</li>
	 * <li>click on {@code Shield moduly} on left menu</li>
	 * <li>click on {@code Raspberry PI Shield} in subcategories</li>
	 * <li>sort by most expensive</li>
	 * <li>select & verify two most expensive</li>
	 * </ol>
	 */
	@Test
	public void twoMostExpensive() {
		driver.get(url);

		testClickLeftMenuBtn("Shield moduly");
		Assertions.assertEquals("Shield moduly", driver.findElement(ArduinoShopPageObjects.pageTitle).getText());
		testClickSubcategoryBtn("Raspberry PI Shield");
		Assertions.assertEquals("Raspberry PI Shield", driver.findElement(ArduinoShopPageObjects.pageTitle).getText());
		testClickQuickSrotBtn("Od nejdražšího");

		List<WebElement> elements = driver.findElements(ArduinoShopPageObjects.products);
		Assertions.assertTrue(elements.size() >= 2, "There must be at least two products");
		for (int i = 1; i < elements.size(); i++) {
			Assertions.assertTrue(getProductPrice(elements.get(i - 1)) >= getProductPrice(elements.get(i)), "Product should be ordered by price in DESC order");
		}
		String el1ProductName = elements.get(0).findElement(ArduinoShopPageObjects.productName).getText();
		String el2ProductName = elements.get(1).findElement(ArduinoShopPageObjects.productName).getText();
		testAddToCart(elements.get(0), false);
		testAddToCart(elements.get(1), true);

		List<WebElement> cashProducts = driver.findElements(ArduinoShopPageObjects.cashProducts);
		Assertions.assertEquals(2, cashProducts.size(), "Two items must be in cash cart");
		List<String> cashCartItemNames = cashProducts.stream()
				.map(WebElement::getText)
				.collect(Collectors.toList());
		Assertions.assertIterableEquals(cashCartItemNames, Arrays.asList(el1ProductName, el2ProductName));
	}

	private int getProductPrice(WebElement product) {
		WebElement price = product.findElement(ArduinoShopPageObjects.productPrice);
		Assertions.assertNotEquals("", price.getText());
		return Utils.priceToInt(price.getText());
	}

	private void testAddToCart(WebElement element, boolean proceedToCart) {
		String itemName = element.findElement(ArduinoShopPageObjects.productName).getText();

		WebElement addToCart = element.findElement(ArduinoShopPageObjects.productAddToCartBtn);
		addToCart.click();

		(new WebDriverWait(driver, 10)).until(ExpectedConditions.numberOfElementsToBe(ArduinoShopPageObjects.itemAddedInfoPanel, 1));
		WebElement infoPanel = driver.findElement(ArduinoShopPageObjects.itemAddedInfoPanel);
		Assertions.assertEquals(itemName, infoPanel.findElement(ArduinoShopPageObjects.itemAddedInfoPanelProductName).getText());
		if (!proceedToCart) {
			infoPanel.findElement(ArduinoShopPageObjects.itemAddedInfoPanelBackBtn).click();
			(new WebDriverWait(driver, 10)).until(ExpectedConditions.invisibilityOf(infoPanel));
		} else {
			infoPanel.findElement(ArduinoShopPageObjects.itemAddedInfoPanelProceedToCartBtn).click();
		}
	}

	private void testClickQuickSrotBtn(String elementText) {
		(new WebDriverWait(driver, 10)).until(ExpectedConditions.numberOfElementsToBeMoreThan(ArduinoShopPageObjects.sortingBtns, 0));
		List<WebElement> aList = Utils.getElementsByText(driver, ArduinoShopPageObjects.sortingBtns, elementText);
		Assertions.assertEquals(1, aList.size(), String.format("Exactly one element with text '%s' must be in quick sort options", elementText));
		WebElement item = aList.get(0);
		Assertions.assertTrue(item.isDisplayed(), String.format("Quick sort button '%s' must be visible", elementText));
		item.click();
	}

	private void testClickSubcategoryBtn(String elementText) {
		(new WebDriverWait(driver, 10)).until(ExpectedConditions.numberOfElementsToBeMoreThan(ArduinoShopPageObjects.subcategoryBtns, 0));
		List<WebElement> aList = Utils.getElementsByText(driver, ArduinoShopPageObjects.subcategoryBtns, elementText);
		Assertions.assertEquals(1, aList.size(), String.format("Exactly one element with text '%s' must be in subcategories", elementText));
		WebElement item = aList.get(0);
		Assertions.assertTrue(item.isDisplayed(), String.format("Subcategories item'%s' must be visible", elementText));
		item.click();
	}

	public void testClickLeftMenuBtn(String elementText) {
		(new WebDriverWait(driver, 10)).until(ExpectedConditions.numberOfElementsToBeMoreThan(ArduinoShopPageObjects.categoriesBtns, 0));
		List<WebElement> liList = Utils.getElementsByText(driver, ArduinoShopPageObjects.categoriesBtns, elementText);
		Assertions.assertEquals(1, liList.size(), String.format("Exactly one element with text '%s' must be in main menu", elementText));
		WebElement item = liList.get(0);
		Assertions.assertTrue(item.isDisplayed(), String.format("Main menu item '%s' must be visible", elementText));
		item.click();
	}

}
