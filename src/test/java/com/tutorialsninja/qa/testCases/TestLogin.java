package com.tutorialsninja.qa.testCases;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.conftest.conftest;
import com.tutorialsninja.qa.pageObjects.AccountPageObjects;
import com.tutorialsninja.qa.pageObjects.ForgetPasswordPageObjects;
import com.tutorialsninja.qa.pageObjects.HomePageObjects;
import com.tutorialsninja.qa.pageObjects.LoginPageObjects;
import com.tutorialsninja.qa.pageObjects.LogoutPageObjects;
import com.tutorialsninja.qa.pageObjects.PasswordPageObjects;
import com.tutorialsninja.qa.pageObjects.RegisterPageObjects;
import com.tutorialsninja.qa.utilities.BaseClass;

public class TestLogin extends conftest {

	public WebDriver driver;
	BaseClass baseclass;
	LoginPageObjects loginPage;
	AccountPageObjects accountPage;
	LogoutPageObjects logoutPage;
	PasswordPageObjects changePassword;
	RegisterPageObjects registerPage;

	@BeforeMethod(alwaysRun=true)
	public void initilization() {
		driver = setup(config.getProperty("browserName"));
		baseclass = new BaseClass(driver);
	}

	@AfterMethod(alwaysRun=true)
	public void tearDown() {
		driver.close();
	}

	@Test(priority = 1,groups={"smoke"})
	public void verifyLoginToApplicationWithValidCredential() {
		HomePageObjects homepage = new HomePageObjects(driver);
		loginPage = homepage.accountLogin();
		loginPage.Email().sendKeys(config.getProperty("validEmail"));
		loginPage.password().sendKeys(config.getProperty("validPassword"));
		accountPage = loginPage.Login();

		Assert.assertEquals(accountPage.myAccountActualText(), accountPage.myAccountTextExpectedText());
	}

	@Test(priority = 2)
	public void verifyLoginToApplicationWithInvalidCredential() {
		HomePageObjects homepage = new HomePageObjects(driver);
		loginPage = homepage.accountLogin();
		loginPage.Email().sendKeys(testData.getProperty("LoginInvalidEmail"));
		loginPage.password().sendKeys(testData.getProperty("LoginInvalidPassword"));
		loginPage.Login();

		Assert.assertEquals(loginPage.noMatchActualWarningMessage(), loginPage.noMatchExepectedWarningMessage());
	}

	@Test(priority = 3)
	public void verifyLoginToApplicationWithValidPasswordAndInvalidUserName() {
		HomePageObjects homepage = new HomePageObjects(driver);
		loginPage = homepage.accountLogin();
		loginPage.Email().sendKeys(testData.getProperty("LoginInvalidEmail"));
		loginPage.password().sendKeys(config.getProperty("validPassword"));
		loginPage.Login();

		Assert.assertEquals(loginPage.noMatchActualWarningMessage(), loginPage.noMatchExepectedWarningMessage());
	}

	@Test(priority = 4)
	public void verifyLoginToApplicationWithValidUserNameInvalidPassword() {
		HomePageObjects homepage = new HomePageObjects(driver);
		loginPage = homepage.accountLogin();
		loginPage.Email().sendKeys(config.getProperty("validEmail"));
		loginPage.password().sendKeys(testData.getProperty("LoginInvalidPassword"));
		loginPage.Login();

		Assert.assertEquals(loginPage.noMatchActualWarningMessage(), loginPage.noMatchExepectedWarningMessage());
	}

	@Test(priority = 5)
	public void verifyLoginToApplicationWithoutProvidingAnyCredentials() {
		HomePageObjects homepage = new HomePageObjects(driver);
		loginPage = homepage.accountLogin();
		loginPage.Email().sendKeys("");
		loginPage.password().sendKeys("");
		loginPage.Login();

		Assert.assertEquals(loginPage.noMatchActualWarningMessage(), loginPage.noMatchExepectedWarningMessage());
	}

	@Test(priority = 6)
	public void verifyLoginToApplicationCheckForgettenPasswordLinkIsPresnetAndClickable() {
		HomePageObjects homepage = new HomePageObjects(driver);
		loginPage = homepage.accountLogin();
		Assert.assertNotNull(loginPage.forgetPassword(), "Forget Password button is not present.");
		ForgetPasswordPageObjects forgetPasswordPage = loginPage.forgetPassword();
		Assert.assertEquals(forgetPasswordPage.forgetPasstextActualText(),
				forgetPasswordPage.forgetPasstextExpectedText());
	}

	@Test(priority = 9)
	public void verifyLoginToApplicationWithValidCredentialBrowsingBackUsingBrowserBackButton() {
		HomePageObjects homepage = new HomePageObjects(driver);
		loginPage = homepage.accountLogin();
		loginPage.Email().sendKeys(config.getProperty("validEmail"));
		loginPage.password().sendKeys(config.getProperty("validPassword"));
		accountPage = loginPage.Login();

		Assert.assertEquals(accountPage.myAccountActualText(), accountPage.myAccountTextExpectedText());
		
		baseclass.browserBack();
		Assert.assertEquals(loginPage.returningCustomerActualText(), loginPage.returningCustomerExpectedText());
	}
	

	@Test(priority = 10)
	public void verifyLoginToApplicationaLoginAfterThatLogoutAndBrowserBackUsingBrowserButtton() {
		HomePageObjects homepage = new HomePageObjects(driver);
		loginPage = homepage.accountLogin();
		loginPage.Email().sendKeys(config.getProperty("validEmail"));
		loginPage.password().sendKeys(config.getProperty("validPassword"));
		accountPage = loginPage.Login();

		Assert.assertEquals(accountPage.myAccountActualText(), accountPage.myAccountTextExpectedText());
		
		accountPage.myAccountDropdownLoginPage().click();
		logoutPage = accountPage.logoutLoginPage();
		Assert.assertEquals(logoutPage.accountLogoutActualText(), logoutPage.accountLogoutExpectedText());
		String expected_title = baseclass.getTitle();
		baseclass.browserBack();
		
		Assert.assertEquals(baseclass.getTitle(), expected_title);
	}

	
	@Test(priority = 11, invocationCount = 10)
	public void verifyLoginToApplicationWithMultipleTimeUnsuccessfulLoginAttempt() {
		int invocationCount = 0;
		HomePageObjects homepage = new HomePageObjects(driver);
		loginPage = homepage.accountLogin();
		loginPage.Email().sendKeys(testData.getProperty("LoginInvalidEmail"));
		loginPage.password().sendKeys(testData.getProperty("LoginInvalidPassword"));
		loginPage.Login();
		
		invocationCount++;

	    if (invocationCount == 10) {
	        Assert.assertEquals("", loginPage.exceededLoginAttemptsExpectedWarning());
	    }	
	}

	
	@Test(priority = 12)
	public void verifyLoginToApplicationByCopyingPasswordFiledText() {
		HomePageObjects homepage = new HomePageObjects(driver);
		loginPage = homepage.accountLogin();
		loginPage.Email().sendKeys(testData.getProperty("LoginInvalidEmail"));
		loginPage.password().sendKeys(testData.getProperty("LoginInvalidPassword"));
		baseclass.keyBoardAction(loginPage.password(), Keys.CONTROL, "a", Keys.CONTROL, "c");
		
		Assert.assertNotEquals(baseclass.pasteActionUsingKeyBoardAction(), testData.getProperty("LoginInvalidPassword"));
	}
	
	
	@Test(priority = 13)
	public void verifyLoginToApplicationCheckPasswordIsNotVisibleInPageSource() {
		HomePageObjects homepage = new HomePageObjects(driver);
		loginPage = homepage.accountLogin();
		loginPage.Email().sendKeys(testData.getProperty("LoginInvalidEmail"));
		loginPage.password().sendKeys(testData.getProperty("LoginInvalidPassword"));
		Object expected = baseclass.getAttributeOfWebElement(loginPage.password(), "value");
		loginPage.Login();
		Object actual = baseclass.getAttributeOfWebElement(loginPage.password(), "value");
		Assert.assertNotEquals(expected, actual);
	}

	
	@Test(priority = 14)
	public void verifyLoginToApplicationAfterChangingPassword() {
		HomePageObjects homepage = new HomePageObjects(driver);
		loginPage = homepage.accountLogin();
		loginPage.Email().sendKeys(testData.getProperty("emailofWhichchagedpass"));
		loginPage.password().sendKeys(testData.getProperty("BeforechangePass"));
		accountPage = loginPage.Login();

		Assert.assertEquals(accountPage.myAccountActualText(), accountPage.myAccountTextExpectedText());
		
		changePassword = accountPage.changePassword();
		changePassword.password().sendKeys(testData.getProperty("changedPassword"));
		changePassword.confPassword().sendKeys(testData.getProperty("changedPassword"));
		accountPage = changePassword.continueButton();

		Assert.assertEquals(accountPage.changePasswordActualWarningMessage(),
				accountPage.changePassword_exepected_WarningMessage());

		accountPage.myAccountDropdownLoginPage().click();
		logoutPage = accountPage.logoutLoginPage();
		homepage = logoutPage.ContinueButton();

		loginPage = homepage.accountLogin();
		loginPage.Email().sendKeys(testData.getProperty("emailofWhichchagedpass"));
		loginPage.password().sendKeys(testData.getProperty("changedPassword"));
		accountPage = loginPage.Login();

		Assert.assertEquals(accountPage.myAccountActualText(), accountPage.myAccountTextExpectedText());
	}
	

	@Test(priority=16, dependsOnMethods = "verifyLoginToApplicationAfterChangingPassword")
	public void verifyLoginToApplicationAfterChangingPasswordAndChangingBackToOriginalPassword() {		
		HomePageObjects homepage = new HomePageObjects(driver);
		loginPage = homepage.accountLogin();
		loginPage.Email().sendKeys(testData.getProperty("emailofWhichchagedpass"));
		loginPage.password().sendKeys(testData.getProperty("changedPassword"));
		accountPage = loginPage.Login();
		
		Assert.assertEquals(accountPage.myAccountActualText(), accountPage.myAccountTextExpectedText());
		
		changePassword = accountPage.changePassword();
		changePassword.password().sendKeys(testData.getProperty("BeforechangePass"));
		changePassword.confPassword().sendKeys(testData.getProperty("BeforechangePass"));
		accountPage = changePassword.continueButton();
		
		Assert.assertEquals(accountPage.changePasswordActualWarningMessage(),
				accountPage.changePassword_exepected_WarningMessage());
		
		accountPage.myAccountDropdownLoginPage().click();
		logoutPage = accountPage.logoutLoginPage();
		homepage = logoutPage.ContinueButton();
		
		loginPage = homepage.accountLogin();
		loginPage.Email().sendKeys(testData.getProperty("emailofWhichchagedpass"));
		loginPage.password().sendKeys(testData.getProperty("BeforechangePass"));
		accountPage = loginPage.Login();
		
		Assert.assertEquals(accountPage.myAccountActualText(), accountPage.myAccountTextExpectedText());
	}
	
	
	@Test(priority = 15)
	public void verifyLoginToApplicationCheckUserIsAbleToNavigateToOtherPages() {
		HomePageObjects homepage = new HomePageObjects(driver);
		loginPage = homepage.accountLogin();
		registerPage = loginPage.newCustomer_Continue();
		Assert.assertEquals(registerPage.loginpageActualWarningMessage(),
				registerPage.loginPageExpectedWarningMessage());

		baseclass.browserBack();
		Assert.assertEquals(loginPage.returningCustomerActualText(), loginPage.returningCustomerExpectedText());

		loginPage.camerasHeaders().click();
		Assert.assertEquals(loginPage.cameraTextActualText(), loginPage.cameraTextExpectedText());
	}

}
