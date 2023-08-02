package com.tutorialsninja.qa.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.tutorialsninja.qa.utilities.BaseClass;

public class HomePageObjects {
	
	public WebDriver driver;
	BaseClass baseclass;
	
	public HomePageObjects(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		baseclass = new BaseClass(driver);
	}
	
	@FindBy(xpath = "//span[text()='My Account']") private WebElement account;
	@FindBy(linkText = "Register") private WebElement register;
	@FindBy(linkText = "Login") private WebElement login;
	
	
	//Tabs
	@FindBy(xpath = "//a[text()='Desktops']") private WebElement desktopTab;
	
	//Tabs WebElement
	@FindBy(xpath = "//a[text()='Show AllDesktops']") private WebElement allDesktopsElement;
	
	public WebElement accountDropdown() {
		return account;
	}
	
	public WebElement selectRegister() {
		return register;
	}
	
	public WebElement selectLogin() {
		return login;
	}
	
	public RegisterPageObjects accountRegister(){
		baseclass.explicitWait(10, account);
		account.click();
		register.click();
		return new RegisterPageObjects(driver);
	}
	
	public LoginPageObjects accountLogin() {
		baseclass.explicitWait(10, account);
		account.click();
		login.click();
		return new LoginPageObjects(driver);
	}
	
	
	
	//ACTUAL TEXT ON HOMEPAGE FOR ASSERTION
	/*@FindBy(linkText = "Your Store") private WebElement yourStore;
	
	public String yourStoreActualText() {		//Need to delete method and variable
		return yourStore.getText();
	}*/
	
	//EXPECTED TEXT ON HOMEPAGE FOR ASSERTION
	public String yourStoreExpectedText() {
		return "Your Store";
	}
	
	
	//Search Functionality
	@FindBy(name = "search") private WebElement searchTextFiled;
	@FindBy(xpath = "//div[@id='search']//button") private WebElement searchButtonPath;
	
	
	public WebElement searchTextBox() {
		return searchTextFiled;
	}
	
	public SearchResultPageObjects searchButton() {
		searchButtonPath.click();;
		return new SearchResultPageObjects(driver);
	}
	
	public WebElement desktopTab() {
		return desktopTab;
	}
	
	public DesktopPageObjects allDesktopsElement() {
		allDesktopsElement.click();
		return new DesktopPageObjects(driver);
	}
	
	
	
	
	
	
	
}
