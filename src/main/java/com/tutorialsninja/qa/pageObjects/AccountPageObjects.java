package com.tutorialsninja.qa.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.tutorialsninja.qa.utilities.BaseClass;

public class AccountPageObjects {

	public WebDriver driver;
	BaseClass baseclass;

	public AccountPageObjects(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		baseclass = new BaseClass(driver);
	}

	// PAGE OBJECTS
	@FindBy(linkText = "Password") private WebElement password;
	@FindBy(linkText = "Logout") private WebElement logoutOnPage;

	public PasswordPageObjects changePassword() {
		password.click();
		return new PasswordPageObjects(driver);
	}

	public LogoutPageObjects logoutOnPage() {
		logoutOnPage.click();
		return new LogoutPageObjects(driver);
	}

	// ACCOUNT DROPDOWN OBJECTS
	@FindBy(xpath = "//span[text()='My Account']") private WebElement accountDropdown;
	@FindBy(linkText = "Logout") private WebElement logoutDropdown;

	public WebElement myAccountDropdownLoginPage() {
		baseclass.explicitWait(10, accountDropdown);
		return accountDropdown;
	}

	public LogoutPageObjects logoutLoginPage() {
		baseclass.explicitWait(10, accountDropdown);
		logoutDropdown.click();
		return new LogoutPageObjects(driver);
	}

	// TEXT FOR ASSERTION
	@FindBy(xpath = "//div[@id='content']/h2[1]")
	private WebElement myAccount;

	public String myAccountActualText() {
		return myAccount.getText();
	}

	public String myAccountTextExpectedText() {
		return "My Account";
	}

	// ACTUAL WARNING MESSAGES
	@FindBy(xpath = "//div[@class='alert alert-success alert-dismissible']")
	private WebElement password_war;

	public String changePasswordActualWarningMessage() {
		return password_war.getText();
	}

	// Expected warning messages
	public String changePassword_exepected_WarningMessage() {
		return "Success: Your password has been successfully updated.";
	}

}
