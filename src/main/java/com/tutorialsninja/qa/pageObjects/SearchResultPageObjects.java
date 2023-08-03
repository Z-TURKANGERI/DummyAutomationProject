package com.tutorialsninja.qa.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.tutorialsninja.qa.utilities.BaseClass;

public class SearchResultPageObjects {
	
	public WebDriver driver;
	BaseClass baseClass;
	
	public SearchResultPageObjects(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		baseClass = new BaseClass(driver);
	}
	
	//elements of the page
	@FindBy(id = "input-search") private WebElement searchCriteriaTextBox;
	@FindBy(xpath = "//select[@name='category_id']") private WebElement allCategoryDropdown;
	@FindBy(name = "sub_category") private WebElement subCategoryCheckbox;
	@FindBy(id = "description") private WebElement productDescriptionCheckbox;	
	@FindBy(id = "button-search") private WebElement searchButtonCriteria;
	@FindBy(xpath = " //div[@id='cart']/button") private WebElement cartButtonInBlackColour;
			
	//Product showing view after search
	@FindBy(id = "list-view") private WebElement listButton;
	@FindBy(id = "grid-view") private WebElement gridButton;
	
	//Assertion elements
	@FindBy(xpath = "//div[@class='caption']//a[contains(text(),'iMac')]") private WebElement itemSearched;
	@FindBy(xpath = "//p[contains(text(),'There is no product that matches the search criter')]") private WebElement messageOfNoProduct;
	
	//product
	@FindBy(id = "compare-total") private WebElement compareProduct;
	@FindBy(xpath = "//img[@alt='iMac' and @class='img-responsive']") private WebElement productImage;
	@FindBy(linkText = "iMac") private WebElement productLink;
	@FindBy(xpath = "//div[@class='caption']//a") private WebElement hpProduct;
	@FindBy(xpath = "//span[text()='Add to Cart']/parent::button") private WebElement addToCartButton;
	@FindBy(xpath = "//button[@data-original-title='Add to Wish List']") private WebElement addToWishListButton;
	@FindBy(xpath = "//button[@data-original-title='Compare this Product']") private WebElement compareTheProduct;
	
	public String itemSearched() {
		return itemSearched.getText();
	}
	
	public String messageOfNoProduct() {
		return messageOfNoProduct.getText();
	}
	
	public WebElement searchCriteriaTextBox() {
		return searchCriteriaTextBox;
	}
	
	public WebElement searchButtonCriteria() {
		return searchButtonCriteria;
	}
	
	public WebElement productDescriptionCheckbox() {
		return productDescriptionCheckbox;
	}
	
	public WebElement allCategoryDropdown() {
		return allCategoryDropdown;
	}

	public WebElement subCategoryCheckbox() {
		return subCategoryCheckbox;
	}
	
	public WebElement viewListButton() {
		return listButton;
	}
	
	public WebElement viewGridButton() {
		return gridButton;
	}
	
	public ProductDisplayPageObjects productImage() {
		productImage.click();
		return new ProductDisplayPageObjects(driver);
	}
	
	public ProductDisplayPageObjects hpProduct() {
		hpProduct.click();
		return new ProductDisplayPageObjects(driver);
	}
	
	public ProductDisplayPageObjects productLink() {
		productLink.click();
		return new ProductDisplayPageObjects(driver);
	}
	
	public WebElement addToCartButton() {
		return addToCartButton;
	}
	
	public WebElement addToWishListButton() {
		return addToWishListButton;
	}
	
	public WebElement addToCompareTheProduct() {
		return compareTheProduct;
	}
	
	public CartPageObjects cartButtonInBlackColour() {
		cartButtonInBlackColour.click();
		return new CartPageObjects(driver);
	}
	
	public ProductComparisonPageObjects compareProduct() {
		compareProduct.click();
		return new ProductComparisonPageObjects(driver);
	}
		
	
	
	
	
	
	
	

}
