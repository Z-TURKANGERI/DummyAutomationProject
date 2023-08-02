package com.tutorialsninja.qa.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductComparisonPageObjects {
	
	public WebDriver driver;
	
	public ProductComparisonPageObjects(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//Header
	@FindBy(xpath = "//a[@title='Shopping Cart']") private WebElement shoppingCartButton;
	
	//WebElement
	@FindBy(xpath = "//input[@value='Add to Cart']") private WebElement addToCartButton;
	
	//Comparison component
	@FindBy(xpath = "//div[@id='content']//a/strong") private WebElement productName;
	
	public String productName() {
		return productName.getText();
	}
	
	public WebElement addToCartButton() {
		return addToCartButton;
	}
	
	public ShoppingCartPageObjects shoppingCartButton() {
		shoppingCartButton.click();
		return new ShoppingCartPageObjects(driver);
	}

}
