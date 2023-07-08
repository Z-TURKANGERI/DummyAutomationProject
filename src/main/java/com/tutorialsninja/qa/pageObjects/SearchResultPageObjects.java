package com.tutorialsninja.qa.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.tutorialsninja.qa.utilities.BaseClass;

public class SearchResultPageObjects {
	
	WebDriver driver;
	BaseClass baseClass;
	
	public SearchResultPageObjects(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		baseClass = new BaseClass(driver);
	}
	
	
	@FindBy(xpath = "//a[text()= 'iMac']") private WebElement itemSearched;
	@FindBy(xpath = "//p[contains(text(),'There is no product that matches the search criter')]") private WebElement messageOfNoProduct;
	
	@FindBy(id = "input-search") private WebElement searchCriteriaTextBox;
	@FindBy(id = "button-search") private WebElement searchButtonCriteria;
	@FindBy(id = "description") private WebElement productDescriptionCheckbox;
	
	public String itemSearched() {
		return itemSearched.getText();
	}
	
	public String messageOfNoProduct() {
		return messageOfNoProduct.getText();
	}
	
	public WebElement searchCriteriaTextBox() {
		return searchCriteriaTextBox;
	}
	
	public WebElement searchButtonCriteria() {
		return searchButtonCriteria;
	}
	
	public WebElement productDescriptionCheckbox() {
		return productDescriptionCheckbox;
	}
	

		
	
	
	
	
	
	
	

}
