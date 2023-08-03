package com.tutorialsninja.qa.testCases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.conftest.conftest;
import com.tutorialsninja.qa.pageObjects.AccountPageObjects;
import com.tutorialsninja.qa.pageObjects.HomePageObjects;
import com.tutorialsninja.qa.pageObjects.LoginPageObjects;
import com.tutorialsninja.qa.pageObjects.LogoutPageObjects;
import com.tutorialsninja.qa.utilities.BaseClass;

public class TestLogout extends conftest {

	public WebDriver driver;
	BaseClass baseclass;
	HomePageObjects homepage;
	LoginPageObjects loginPage;
	AccountPageObjects accountPage;
	LogoutPageObjects logoutPage;
	

	@BeforeMethod(alwaysRun=true)
	public void initilization() {
		driver = setup(config.getProperty("browserName"));
		baseclass = new BaseClass(driver);
		homepage = new HomePageObjects(driver);
		loginPage = homepage.accountLogin();
		
	}

	@AfterMethod(alwaysRun=true)
	public void tearDown() {
		driver.close();
	}

	@Test(priority = 1, groups= {"smoke"})
	public void verifyLoggingOutFromApplicationFromMyAccountDropdown() {
		accountPage = loginPage.loginToAccount(config.getProperty("validEmail"), config.getProperty("validPassword"));
		accountPage.myAccountDropdownLoginPage();
		logoutPage = accountPage.logoutLoginPage();
		Assert.assertEquals(logoutPage.accountLogoutActualText(), logoutPage.accountLogoutExpectedText());
		homepage = logoutPage.ContinueButton();
		
		Assert.assertEquals(baseclass.getTitle(), homepage.yourStoreExpectedText());
	}

	@Test(priority = 2)
	public void verifyLoggingOutFromApplicationFromLogoutOptionOnPage() {
		accountPage = loginPage.loginToAccount(config.getProperty("validEmail"), config.getProperty("validPassword"));
		accountPage.myAccountDropdownLoginPage();
		logoutPage = accountPage.logoutOnPage();
		Assert.assertEquals(logoutPage.accountLogoutActualText(), logoutPage.accountLogoutExpectedText());
		homepage = logoutPage.ContinueButton();
		Assert.assertEquals(baseclass.getTitle(), homepage.yourStoreExpectedText());
	}

	@Test(priority = 3)
	public void verifyLoggingOutFromApplicationAndBrowsingBack() {
		accountPage = loginPage.loginToAccount(config.getProperty("validEmail"), config.getProperty("validPassword"));
		accountPage.myAccountDropdownLoginPage();
		logoutPage = accountPage.logoutOnPage();
		Assert.assertEquals(logoutPage.accountLogoutActualText(), logoutPage.accountLogoutExpectedText());
		logoutPage.ContinueButton();
		baseclass.browserBack();
		baseclass.browserBack();
		
		if(baseclass.getTitle().equals("My Account")) {
			Assert.assertNotEquals(baseclass.getTitle(), "My Account");
		}else {
			Assert.assertFalse(false, "Logging Out From Application And Browsing Back user should not logged in");
		}
	}

	@Test(priority = 4)
	public void verifyLoggingOutFromApplicationLogoutOptionShouldNotDisplayedBeforeLoginToAccountInAccountDrodown() {
		Assert.assertFalse(baseclass.elementIsDisplayed(loginPage.LoginPageDropDown()));
	}

	@Test(priority = 5)
	public void verifyLoggingOutFromApplicationLogoutOptionShouldNotDisplayBeforeLoginUnderRightColumn() {
		Assert.assertFalse(baseclass.elementIsDisplayed(loginPage.logoutSideColumn()));
	}

	@Test(priority = 6)
	public void verifyLoggingOutFromApplicationAfterLogoutFromOneAccountImmediatelyLoginFromAnotherAccount() {
		accountPage = loginPage.loginToAccount(config.getProperty("validEmail"), config.getProperty("validPassword"));
		accountPage.myAccountDropdownLoginPage();
		logoutPage = accountPage.logoutOnPage();
		Assert.assertEquals(logoutPage.accountLogoutActualText(), logoutPage.accountLogoutExpectedText());
		loginPage = logoutPage.accountLogout();
		accountPage = loginPage.loginToAccount(config.getProperty("validEmail"), config.getProperty("validPassword"));
		accountPage.myAccountDropdownLoginPage();
		Assert.assertEquals(accountPage.myAccountActualText(), accountPage.myAccountTextExpectedText());
	}
}
