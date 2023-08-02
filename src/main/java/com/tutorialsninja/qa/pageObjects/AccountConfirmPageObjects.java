package com.tutorialsninja.qa.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountConfirmPageObjects {
	
	public WebDriver driver;
	
	public AccountConfirmPageObjects(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//div[@id='content']/h1") private WebElement confAccount;
	@FindBy(linkText = "Continue") private WebElement continueButton;
	@FindBy(linkText = "Edit Account") private WebElement editAccount;	
	
	public String confirmAccountCreated() {
		return confAccount.getText();
	}
	
	public String conf_message() {
		return "Your Account Has Been Created!";
	}
	
	public LoginPageObjects continue_button() {
		continueButton.click();
		return new LoginPageObjects(driver);
	}
	
	public RegisterPageObjects editAccount() {
		editAccount.click();
		return new RegisterPageObjects(driver);
	}

}
