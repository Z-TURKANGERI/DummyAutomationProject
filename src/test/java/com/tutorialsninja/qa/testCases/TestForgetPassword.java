package com.tutorialsninja.qa.testCases;

import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.GmailAPiLib.GMailQuickstart;
import com.tutorialsninja.qa.conftest.conftest;
import com.tutorialsninja.qa.pageObjects.AccountPageObjects;
import com.tutorialsninja.qa.pageObjects.ForgetPasswordPageObjects;
import com.tutorialsninja.qa.pageObjects.HomePageObjects;
import com.tutorialsninja.qa.pageObjects.LoginPageObjects;
import com.tutorialsninja.qa.pageObjects.LogoutPageObjects;
import com.tutorialsninja.qa.pageObjects.PasswordPageObjects;
import com.tutorialsninja.qa.pageObjects.RegisterPageObjects;
import com.tutorialsninja.qa.utilities.BaseClass;

public class TestForgetPassword extends conftest {

	public WebDriver driver;
	BaseClass baseclass;
	HomePageObjects homepage;
	LoginPageObjects loginPage;
	AccountPageObjects accountPage;
	LogoutPageObjects logoutPage;
	PasswordPageObjects changePassword;
	RegisterPageObjects registerPage;
	ForgetPasswordPageObjects forgetPassword;
	Map<String, String> gmailInfo = GMailQuickstart.getEmailInfo("Reset password");

	@BeforeMethod(alwaysRun = true)
	public void initlization() {
		driver = setup(config.getProperty("browserName"));
		baseclass = new BaseClass(driver);
		homepage = new HomePageObjects(driver);
		loginPage = homepage.accountLogin();

	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		driver.close();
	}

	@Test(priority = 1, groups = { "smoke" })
	public void verifyForgetPasswordUserAbleToRestorePassword() {
		forgetPassword = loginPage.forgetPassword();

		Assert.assertEquals(forgetPassword.forgetPasstextActualText(), forgetPassword.forgetPasstextExpectedText());

		forgetPassword.emailAddress().sendKeys(testData.getProperty("emailofWhichchagedpass"));
		loginPage = forgetPassword.continueButton();

		Assert.assertEquals(loginPage.emailConfirmationForgetPassActualConfirmationMessage(),
				loginPage.emailConfirmationForgetPassExpectedConfirmationMessage());

		String link = "";
		if (GMailQuickstart.isMailExist("Reset password")) {
			link = gmailInfo.get("body");
			System.out.println(link);
		} else {
			Assert.assertTrue(false);
		}
		baseclass.navigateToUrl(link);

		if (baseclass.getTitle().equals("Change Password")) {
			changePassword = PageFactory.initElements(driver, PasswordPageObjects.class);
			changePassword.password().sendKeys("");
			changePassword.confPassword().sendKeys("");
			changePassword.continueButton();
		} else {
			Assert.assertTrue(false);
		}
		if (baseclass.getTitle().equals("Account Login")) {
			loginPage = PageFactory.initElements(driver, LoginPageObjects.class);
			loginPage.Email().sendKeys("");
			loginPage.password().sendKeys("");
			accountPage = loginPage.Login();
			Assert.assertEquals(accountPage.myAccountActualText(), accountPage.myAccountTextExpectedText());
		} else {
			Assert.assertTrue(false);
		}

	}

	@Test(priority = 2)
	public void verifyForgetPasswordCheckLoggingIntoApplicationWithOldPasswordAfterResettingPasswordIntitiatedProcess() {
		forgetPassword = loginPage.forgetPassword();
		forgetPassword.emailAddress().sendKeys(testData.getProperty("emailofWhichchagedpass"));
		loginPage = forgetPassword.continueButton();
		loginPage.Email().sendKeys(testData.getProperty("emailofWhichchagedpass"));
		loginPage.password().sendKeys(testData.getProperty("BeforechangePass"));
		accountPage = loginPage.Login();

		Assert.assertEquals(accountPage.myAccountActualText(), accountPage.myAccountTextExpectedText());
	}

	@Test(priority = 3)
	public void verifyForgetPasswordWithNonRegisteredAccount() {
		forgetPassword = loginPage.forgetPassword();
		forgetPassword.emailAddress().sendKeys(testData.getProperty("LoginInvalidEmail"));
		loginPage = forgetPassword.continueButton();

		Assert.assertEquals(loginPage.emailConfirmationForgetPassActualConfirmationMessage(),
				loginPage.emailConfirmationForgetPassExpectedConfirmationMessage());
	}

	@Test(priority = 4)
	public void verifyForgetPasswordWithoutProvidingEmailAddress() {
		forgetPassword = loginPage.forgetPassword();
		forgetPassword.emailAddress().sendKeys("");
		forgetPassword.continueButton();

		Assert.assertTrue(false, forgetPassword.warningEmailAddressFieldExpectedText());
	}

	/*
	 * I am not including the email verification code in this script as I am unable
	 * to receive email to the register email at this time
	 */
}
