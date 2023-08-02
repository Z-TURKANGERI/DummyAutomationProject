package com.tutorialsninja.qa.testCases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.conftest.conftest;
import com.tutorialsninja.qa.pageObjects.CartPageObjects;
import com.tutorialsninja.qa.pageObjects.DesktopPageObjects;
import com.tutorialsninja.qa.pageObjects.HomePageObjects;
import com.tutorialsninja.qa.pageObjects.LoginPageObjects;
import com.tutorialsninja.qa.pageObjects.ProductComparisonPageObjects;
import com.tutorialsninja.qa.pageObjects.ProductDisplayPageObjects;
import com.tutorialsninja.qa.pageObjects.SearchResultPageObjects;
import com.tutorialsninja.qa.pageObjects.ShoppingCartPageObjects;
import com.tutorialsninja.qa.pageObjects.WishListPageObjects;
import com.tutorialsninja.qa.utilities.BaseClass;

public class TestAddToCart extends conftest{
	
	public WebDriver driver;
	BaseClass baseClass;
	HomePageObjects homePage;
	SearchResultPageObjects searchResultPage;
	ProductDisplayPageObjects productDisplayPage;
	ShoppingCartPageObjects shoppingCartPage;
	LoginPageObjects loginPage;
	WishListPageObjects wishListPage;
	CartPageObjects cartPage;
	DesktopPageObjects desktopPage;
	ProductComparisonPageObjects productComparisonPage;
	
	
	@BeforeMethod(alwaysRun = true)
	public void initilization() {
		driver = setup(config.getProperty("browserName"));
		baseClass = new BaseClass(driver);
		
	}
	
	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		driver.close();
	}
	
	
	@Test(priority=1)
	public void verifyAddToCartByAddingProductToCartFromProductDisplayPage() {
		homePage = new HomePageObjects(driver);
		homePage.searchTextBox().sendKeys(testData.getProperty("ExistingProduct"));
		searchResultPage = homePage.searchButton();
		productDisplayPage = searchResultPage.productLink();
		productDisplayPage.addToCartButton().click();
		
		Assert.assertTrue(productDisplayPage.ActualSuccessMessages());
		shoppingCartPage = productDisplayPage.shoppingCartButton();
		
		Assert.assertEquals(shoppingCartPage.productVerification(), testData.getProperty("ExistingProduct"));
	}
	
	@Test(priority=2)
	public void VerifyAddToCartAddingProductFromWishList() {
		homePage = new HomePageObjects(driver);
		homePage.searchTextBox().sendKeys(testData.getProperty("ExistingProduct"));
		searchResultPage = homePage.searchButton();
		productDisplayPage = searchResultPage.productLink();
		loginPage = productDisplayPage.addToWishListButton();
		wishListPage = loginPage.loginToAccountForWishList(config.getProperty("validEmail"), config.getProperty("validPassword"));
		
		Assert.assertEquals(wishListPage.myWishListProduct(), testData.getProperty("ExistingProduct"));
	}
	
	@Test(priority=3)
	public void verifyAddToCartAddingProductToCartFromSearchPage() {
		homePage = new HomePageObjects(driver);
		homePage.searchTextBox().sendKeys(testData.getProperty("ExistingProduct"));
		searchResultPage = homePage.searchButton();
		searchResultPage.addToCartButton().click();
		cartPage = searchResultPage.cartButtonInBlackColour();
		
		Assert.assertEquals(cartPage.productImage(), testData.getProperty("ExistingProduct"));		
	}
	
	@Test(priority=4)
	public void verifyAddToCartProductToCartFromRelatedProductsSectionOfProductDisplayPage() {
		homePage = new HomePageObjects(driver);
		homePage.searchTextBox().sendKeys(testData.getProperty("hpProduct"));
		searchResultPage = homePage.searchButton();
		productDisplayPage = searchResultPage.hpProduct();
		productDisplayPage.addToCartButton().click();
		shoppingCartPage = productDisplayPage.shoppingCartButton();
		
		Assert.assertEquals(shoppingCartPage.hpProductVerification(), testData.getProperty("hpProduct"));
	}
	
	@Test(priority=5)
	public void verifyAddToCartAddingProductToCartFromTheProductsDisplayedInCategoryOrSubCategory() {
		homePage = new HomePageObjects(driver);
		baseClass.mouseHover(homePage.desktopTab());
		desktopPage = homePage.allDesktopsElement();
		desktopPage.macSubCategory().click();
		desktopPage.addToCartButton().click();
		shoppingCartPage = desktopPage.shoppingCart();
		Assert.assertEquals(shoppingCartPage.productVerification(), testData.getProperty("ExistingProduct"));
	}
	
	@Test(priority=6)
	public void verifyAddtoCartByAddingProductToCartFromProductComparisonPage() {
		homePage = new HomePageObjects(driver);
		homePage.searchTextBox().sendKeys(testData.getProperty("ExistingProduct"));
		searchResultPage = homePage.searchButton();
		searchResultPage.addToCompareTheProduct().click();
		productComparisonPage = searchResultPage.compareProduct();
		
		Assert.assertEquals(productComparisonPage.productName(), testData.getProperty("ExistingProduct"));
		
		productComparisonPage.addToCartButton().click();
		shoppingCartPage = productComparisonPage.shoppingCartButton();
		
		Assert.assertEquals(shoppingCartPage.productVerification(), testData.getProperty("ExistingProduct"));	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
