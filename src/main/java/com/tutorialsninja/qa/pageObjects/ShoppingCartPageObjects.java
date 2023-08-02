package com.tutorialsninja.qa.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ShoppingCartPageObjects {
	
	public WebDriver driver;
	
	public ShoppingCartPageObjects(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//div[@id='content']//img") private WebElement productVerification;
	@FindBy(xpath = "(//div[@id=\"content\"]//a)[2]") private WebElement hpProductVerification;
	
	public String productVerification() {
		return productVerification.getAttribute("title");
	}
	
	public String hpProductVerification() {
		return hpProductVerification.getText();
	}
	
	

}
