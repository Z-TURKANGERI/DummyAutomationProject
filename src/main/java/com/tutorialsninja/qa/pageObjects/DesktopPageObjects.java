package com.tutorialsninja.qa.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DesktopPageObjects {
	
	public WebDriver driver;
	
	public DesktopPageObjects(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//a[contains(text(),'-Mac (1)')]") private WebElement macSubCategory;
	@FindBy(xpath = "(//div[@class='button-group']/button)[1]") WebElement addToCartButton;
	
	//Header
	@FindBy(xpath = "//span[text()='Shopping Cart']") WebElement shoppingCart;
	
	public WebElement macSubCategory() {
		return macSubCategory;
	}
	
	public WebElement addToCartButton() {
		return addToCartButton;
	}
	
	public ShoppingCartPageObjects shoppingCart() {
		shoppingCart.click();
		return new ShoppingCartPageObjects(driver);
	}

}
