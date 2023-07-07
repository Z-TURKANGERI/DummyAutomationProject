package com.tutorialsninja.qa.testCases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.conftest.conftest;
import com.tutorialsninja.qa.pageObjects.AccountPageObjects;
import com.tutorialsninja.qa.pageObjects.HomePageObjects;
import com.tutorialsninja.qa.pageObjects.LoginPageObjects;
import com.tutorialsninja.qa.pageObjects.RegisterPageObjects;
import com.tutorialsninja.qa.pageObjects.SearchResultPageObjects;
import com.tutorialsninja.qa.utilities.BaseClass;

public class TestSearch extends conftest {

	public WebDriver driver;
	BaseClass baseClass;
	SearchResultPageObjects searchResultPage;
	LoginPageObjects loginPage;
	AccountPageObjects accountPage;
	HomePageObjects homePage;

	@BeforeMethod(alwaysRun = true)
	public void initilization() {
		driver = setup(config.getProperty("browserName"));
		baseClass = new BaseClass(driver);
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		driver.close();
	}

	@Test(priority = 1)
	public void verifySearchingWithExistingProductName() {
		HomePageObjects homePage = new HomePageObjects(driver);
		homePage.searchTextBox().sendKeys(testData.getProperty("ExistingProduct"));
		searchResultPage = homePage.searchButton();

		Assert.assertEquals(searchResultPage.itemSearched(), testData.getProperty("ExistingProduct"));
	}

	@Test(priority = 2)
	public void verifySearchingWithNonExistingProductName() {
		HomePageObjects homePage = new HomePageObjects(driver);
		homePage.searchTextBox().sendKeys(testData.getProperty("NonExistingProduct"));
		searchResultPage = homePage.searchButton();

		Assert.assertEquals(searchResultPage.messageOfNoProduct(), testData.getProperty("noProductResultMessage"));
	}

	@Test(priority = 3)
	public void verifySearchingWithotProvidingAnyProductName() {
		HomePageObjects homePage = new HomePageObjects(driver);
		searchResultPage = homePage.searchButton();

		Assert.assertEquals(searchResultPage.messageOfNoProduct(), testData.getProperty("noProductResultMessage"));
	}

	/*@Test
	public void verifySearchingForProductAfterLoginToApplication() {
		// HomePageObjects homePage = new HomePageObjects(driver);
		loginPage = homePage.accountLogin();
		loginPage.Email().sendKeys(config.getProperty("validEmail"));
		loginPage.password().sendKeys(config.getProperty("validPassword"));
		accountPage = loginPage.Login();

		Assert.assertEquals(accountPage.myAccountActualText(), accountPage.myAccountTextExpectedText());

		homePage = new HomePageObjects(driver);
		homePage.searchTextBox().sendKeys(testData.getProperty("ExistingProduct"));
		searchResultPage = homePage.searchButton();

		Assert.assertEquals(searchResultPage.itemSearched(), testData.getProperty("ExistingProduct"));

	}*/

}
