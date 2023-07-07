package com.tutorialsninja.qa.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPageObjects {
	
	WebDriver driver;
	
	public RegisterPageObjects(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id = "input-firstname") WebElement firstName;
	@FindBy(id = "input-lastname") private WebElement lastName;
	@FindBy(id = "input-email")  private WebElement email;
	@FindBy(id = "input-telephone") private WebElement telephone;
	@FindBy(id = "input-password") private WebElement password;
	@FindBy(id = "input-confirm") private WebElement confPassword;
	@FindBy(css = "input[name=agree]") private WebElement privacyPolicy;
	@FindBy(css = "input[type=submit]") private WebElement submit;
	@FindBy(xpath = "(//div[@class='col-sm-10'])[8]/descendant::input[1]") private WebElement yes;
	@FindBy(xpath = "(//div[@class='col-sm-10'])[8]/descendant::input[2]") private WebElement no;
	
	public WebElement firstName() {
		return firstName;
	}
	public WebElement lastName() {
		return lastName;
	}	
	public WebElement email() {
		return email;
	}
	public WebElement telephone() {
		return telephone;
	}
	public WebElement password() {
		return password;
	}
	public WebElement confirmPassword() {
		return confPassword;
	}
	public WebElement privacyPolicy() {
		return privacyPolicy;
	}
	public AccountConfirmPageObjects submit() {
		submit.click();
		return new AccountConfirmPageObjects(driver);
	}
	
	public WebElement newsLetterYes() {
		return yes;
	}
	
	public WebElement newsLetterNo() {
		return no;
	}
	
	// Warning messages
	@FindBy(xpath = "//input[@id='input-firstname']/following::div[1]") private WebElement firstnameNoti;
	@FindBy(xpath = "//input[@id='input-lastname']/following::div[1]") private WebElement lastnameNoti;
	@FindBy(xpath = "//input[@id='input-email']/following::div[1]") private WebElement emailNoti;
	@FindBy(xpath = "//input[@id='input-telephone']/following::div[1]") private WebElement telephoneNoti;
	@FindBy(xpath = "//input[@id='input-password']/following::div[1]") private WebElement passwordNoti;
	@FindBy(xpath = "//input[@id='input-confirm']/following::div[1]") private WebElement confPasswordNoti;
	@FindBy(xpath = "//div[@class='alert alert-danger alert-dismissible']") private WebElement privacyPolicyNoti;
	@FindBy(xpath = "//div[@id='content']/p") private WebElement loginPageNoti;
	@FindBy(xpath = "//div[@class='alert alert-danger alert-dismissible']") private WebElement alreadyRegistered;
	//Please choose a stronger password. Try a mix of letters, numbers, and symbols.
	
	//Actual warning messages
	public String firstNameActualWarningMessage() {
		return firstnameNoti.getText();
	}
	public String lastNameActualWarningMessage() {
		return lastnameNoti.getText();
	}
	public String emailActualWarningMessage() {
		return emailNoti.getText();
	}
	public String telephoneActualWarningMessage() {
		return telephoneNoti.getText();
	}
	public String passwordActualWarningMessage() {
		return passwordNoti.getText();
	}
	public String confirmPassActualWarningMessage() {
		return confPasswordNoti.getText();
	}
	public String privacyPolicyActualWarningMessage() {
		return privacyPolicyNoti.getText();
	}
	public String loginpageActualWarningMessage() {
		return loginPageNoti.getText();
	}
	public String alreadyRegisteredActualWarningMessage() {
		return alreadyRegistered.getText();
	}
	
	
	//Expected warning messages
	public String firstNameExepectedWarningMessage() {
		return "First Name must be between 1 and 32 characters!";
	}
	public String lastNameExepectedWarningMessage() {
		return "Last Name must be between 1 and 32 characters!";
	}
	public String emailExepectedWarningMessage() {
		return "E-Mail Address does not appear to be valid!";
	}
	public String telephoneExepectedWarningMessage() {
		return "Telephone must be between 3 and 32 characters!";
	}
	public String passwordExepectedWarningMessage() {
		return "Password must be between 4 and 20 characters!";
	}
	public String confirmPassExpectedWarningMessage() {
		return "Password confirmation does not match password!";
	}
	public String privacyPolicyExepectedWarningMessage() {
		return "Warning: You must agree to the Privacy Policy!";
	}
	public String loginPageExpectedWarningMessage() {
		return "If you already have an account with us, please login at the login page.";
	}
	public String alreadyRegisteredExpectedWarningMessage() {
		return "Warning: E-Mail Address is already registered!";
	}
	public String passwordComplexityExpectedWarningMessage() {
		return "password should contain atleat one number, symbol, lower case letter and upper case letters";
	}
	
}
