package com.tutorialsninja.qa.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.tutorialsninja.qa.utilities.BaseClass;

public class LoginPageObjects {
	
	public WebDriver driver;
	BaseClass baseclass;
	
	public LoginPageObjects(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);	
		baseclass = new BaseClass(driver);
	}
	
	//WEBELEMENT OF PAGE
	@FindBy(id = "input-email") private WebElement email;
	@FindBy(id = "input-password") private WebElement password;
	@FindBy(xpath = "//input[@type='submit']") private WebElement login;
	@FindBy(linkText = "Continue") private WebElement newCustomerContinue;
	
	public WebElement Email() {
		return email;
	}	
	public WebElement password() {
		return password;
	}	
	public AccountPageObjects Login() {
		login.click();
		return new AccountPageObjects(driver);
	}	
	public RegisterPageObjects newCustomer_Continue() {
		newCustomerContinue.click();
		return new RegisterPageObjects(driver);
	}
	
	
	//SIDE COLUMN ELEMENT
	@FindBy(linkText = "Forgotten Password") private WebElement forgetPassword;
	@FindBy(xpath = "//div[@class='list-group']//a[text()='Logout']") private WebElement LogoutSideColumn;
	
	public ForgetPasswordPageObjects forgetPassword() {
		forgetPassword.click();
		return new ForgetPasswordPageObjects(driver);
	}
	public WebElement logoutSideColumn() {
		return LogoutSideColumn;
	}
	
	public AccountPageObjects loginToAccount(String Validemail, String ValidPassword) {
		email.sendKeys(Validemail);
		password.sendKeys(ValidPassword);
		login.click();
		return new AccountPageObjects(driver);
	}
	
	public WishListPageObjects loginToAccountForWishList(String Validemail, String ValidPassword) {
		email.sendKeys(Validemail);
		password.sendKeys(ValidPassword);
		login.click();
		return new WishListPageObjects(driver);
	}
	

	//ACCOUNT DROPDOWN 
	@FindBy(linkText= "My Account") private WebElement accountDropDown;
	@FindBy(xpath = "(//a[contains(text(),'Logout')])[1]") private WebElement accountDropDownForget;
	
	public WebElement LoginPageDropDown() {
		baseclass.explicitWait(10, accountDropDown);
		accountDropDown.click();
		return accountDropDownForget;		
	}
	
	
	//HEADER PAGEOBJECT
	@FindBy(linkText = "Cameras") private WebElement camerasHeaders;
		
	public WebElement camerasHeaders() {
		return camerasHeaders;
	}
	
	//ACTUAL WARNING MESSAGES OF PAGE
	@FindBy(xpath = "//div[@class='alert alert-danger alert-dismissible']") private WebElement noMatchNoti;
	@FindBy(xpath = "//div[@class='alert alert-success alert-dismissible']") private WebElement emailConfirmationForgetPass;	
	
	public String noMatchActualWarningMessage() {
		return noMatchNoti.getText();
	}
	public String emailConfirmationForgetPassActualConfirmationMessage() {
		return emailConfirmationForgetPass.getText();
	}
	
		
		
	//EXPECTED WARNING MESSAGES OF PAGE
	public String noMatchExepectedWarningMessage() {
		return "Warning: No match for E-Mail Address and/or Password.";
	}
	
	public String emailConfirmationForgetPassExpectedConfirmationMessage() {
		return "An email with a confirmation link has been sent your email address.";
	}
	
	
	//TEXT OF PAGE FOR ASSERTION
	@FindBy(xpath = "(//div[@class='well'])[2]/h2") private WebElement returningCus;
	
	public String returningCustomerActualText() {
		return returningCus.getText();
	}
	public String returningCustomerExpectedText() {
		return "Returning Customer";
	}
	
	public String exceededLoginAttemptsExpectedWarning() {
		return "Warning: Your account has exceeded allowed number of login attempts. Please try again in 1 hour.";
	}
	
	//MOVE TO CAMERA PAGE
	@FindBy(xpath = "//div[@id='content']/h2") private WebElement cameraText;
	
	public String cameraTextActualText() {
		return cameraText.getText();
	}
	
	public String cameraTextExpectedText() {
		return "Cameras";
	}
	
	
	

	
	
		
	
	
	
	

}
