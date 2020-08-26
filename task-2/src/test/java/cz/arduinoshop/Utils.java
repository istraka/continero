package cz.arduinoshop;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class Utils {
	public static List<WebElement> getElementsByText(WebDriver driver, By by, String text) {
		return driver.findElements(by)
				.stream()
				.filter(element -> element.getText().equals(text))
				.collect(Collectors.toList());
	}
	public static List<WebElement> getElementsStartByText(WebDriver driver, By by, String text) {
		return driver.findElements(by)
				.stream()
				.filter(element -> element.getText().startsWith(text))
				.collect(Collectors.toList());
	}

	public static int priceToInt(String price) {
		return Integer.valueOf(price.replaceAll("\\s", "").replaceAll("(\\d+)[^\\d]*", "$1"));
	}
}
