package com.tutorialsninja.qa.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WishListPageObjects {
	
	public WebDriver driver;
	
	public WishListPageObjects(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//div[@id='content']//td//a[text()='iMac']") private WebElement myWishListProduct;
	
	public String myWishListProduct() {
		return myWishListProduct.getText();
	}

}
