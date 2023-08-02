package com.tutorialsninja.qa.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.tutorialsninja.qa.utilities.BaseClass;

public class ProductDisplayPageObjects {
	
	public WebDriver driver;
	BaseClass baseClass;
	
	public ProductDisplayPageObjects(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
		baseClass = new BaseClass(driver);
	}
	
	//Elements of the page
	@FindBy(id = "button-cart") private WebElement addToCartButton;
	@FindBy(xpath = "//div[@id='top-links']//span[text()='Wish List (0)']") private WebElement addToWishListButton;
	
	//header section button
	@FindBy(xpath = "//a[@title='Shopping Cart']") private WebElement shoppingCartButton;
	@FindBy(xpath = "//a[@title='Wish List (1)']//span") private WebElement wishListButton;
	
	
	//Assertion
	@FindBy(xpath = "//div[@class='alert alert-success alert-dismissible']") private WebElement successMessage;
	
	public WebElement addToCartButton() {
		return addToCartButton;
	}
	
	public WebElement addToWishListbutton() {
		return addToWishListButton;
	}
	
	public ShoppingCartPageObjects shoppingCartButton() {
		shoppingCartButton.click();
		return new ShoppingCartPageObjects(driver);
	}
	
	public LoginPageObjects addToWishListButton() {
		addToWishListButton.click();
		return new LoginPageObjects(driver);
	}
	
	public String ActualSuccessMessage() {
		return successMessage.getText();
	}
	
	public boolean ActualSuccessMessages() {
		successMessage.getText();
		return true;
	}
	
	public String expectedSuccessMessage() {
		return "Success: You have added iMac to your shopping cart!\r\n"
				+ "×";
	}
	

}
