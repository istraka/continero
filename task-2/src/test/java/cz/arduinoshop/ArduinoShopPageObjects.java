package cz.arduinoshop;


import org.openqa.selenium.By;

public class ArduinoShopPageObjects {
	public static final By categoriesBtns = By.cssSelector("#menu-kategorie>div>ul>li");
	public static final By subcategoryBtns = By.cssSelector("#kategorie-boxy>div");
	public static final By sortingBtns = By.cssSelector(".box-radit>button");

	public static final By products = By.cssSelector("div.produkt");
	public static final By productPrice = By.cssSelector("span.cena-s-dph>strong");
	public static final By productAddToCartBtn = By.cssSelector("button.produkt-buy-btn");
	public static final By productName = By.cssSelector("h2");

	public static final By itemAddedInfoPanel = By.cssSelector("div.win-kosik-vlozeno");
	public static final By itemAddedInfoPanelProductName = By.cssSelector("h1");
	public static final By itemAddedInfoPanelBackBtn = By.cssSelector("button.btn-back");
	public static final By itemAddedInfoPanelProceedToCartBtn = By.cssSelector("a.btn-primary");

	public static final By cartPriceCounter = By.cssSelector("#kosik span.user-title");

	public static final By cashProducts = By.cssSelector("#obsahKosiku tr.kosik-polozka>td:nth-child(2)");

	public static final By pageTitle = By.cssSelector("#main-content article.page-content>h1");



}
