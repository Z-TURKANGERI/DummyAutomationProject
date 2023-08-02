package com.tutorialsninja.qa.utilities;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseClass {
	private WebDriver driver;

	public BaseClass(WebDriver driver) {
		this.driver = driver;
	}

	/***
	 * This method takes screenshot
	 * 
	 * @param driver
	 * @param testName
	 * @return
	 */
	public static String captureScreenShot(WebDriver driver, String testName) {
		File srcScreenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String destinationScreenshotPath = System.getProperty("user.dir") + "\\Screenshots\\" + testName + ".png";

		try {
			FileHandler.copy(srcScreenshot, new File(destinationScreenshotPath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return destinationScreenshotPath;
	}

	/***
	 * This method is used to create and return a new email with a timestamp
	 *
	 * @return
	 */
	public String generateEmailWithTimeStamp() {
		Date date = new Date();
		String timestamp = date.toString().replace(" ", "_").replace(":", "_");
		return "zubertest0" + timestamp + "@gmail.com";
	}

	/**
	 * This method returns the title of the web page
	 *
	 * @return
	 */
	public String getTitle() {
		return driver.getTitle();
	}

	/**
	 * This method navigates back to the previous page
	 */
	public void browserBack() {
		driver.navigate().back();
	}

	/**
	 * This method returns the value of any attribute
	 *
	 * @param locator
	 * @param attribute
	 * @return
	 */
	public Object getAttributeOfWebElement(WebElement locator, String attribute) {
		return locator.getAttribute(attribute);
	}

	/**
	 * This method returns the element is selected or not
	 * 
	 * @param locator
	 * @return
	 */
	public boolean isSelected(WebElement locator) {
		return locator.isSelected();
	}

	/**
	 * This method perform keyBoardActions
	 * 
	 * @param locator is webElement
	 * @param keys    if keys returns key if it is string it return String
	 */
	public void keyBoardAction(WebElement locator, Object... keys) {
		Actions action = new Actions(driver);
		action.moveToElement(locator).click().perform();

		for (Object key : keys) {
			if (key instanceof Keys) {
				action.keyDown((Keys) key).sendKeys((Keys) key).keyUp((Keys) key);
			} else if (key instanceof String) {
				action.sendKeys((String) key);
			}
		}

		action.perform();
	}

	/**
	 * paste Action Using KeyBoard Action
	 * 
	 * @return
	 */
	public String pasteActionUsingKeyBoardAction() {
		Actions actions = new Actions(driver);
		actions.keyDown(Keys.CONTROL).sendKeys("v").keyUp(Keys.CONTROL).perform();
		return null;
	}

	/**
	 * This method is used when no HTML code for validation
	 *
	 * @param locator
	 * @return
	 */
	public boolean getValidationMessage(WebElement locator) {
		String validateMessage = locator.getAttribute("validationMessage");
		if (validateMessage != null) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * This method is used to wait until the visibility of a web element
	 *
	 * @param time
	 * @param element
	 */
	public void explicitWait(long time, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	/**
	 * This method return is element is present or not on page
	 * 
	 * @param locator
	 * @return
	 */
	public boolean elementIsDisplayed(WebElement locator) {
		try {
			return locator.isDisplayed();
		} catch (Throwable e) {
			return false;
		}
	}

	/**
	 * This method navigate to link
	 * 
	 * @param link
	 */
	public void navigateToUrl(String link) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(link);
	}
	
	public void selectDropdownByVisibleText(WebElement locator, String text) {
		Select select = new Select(locator);
		select.selectByVisibleText(text);
	}
	
	public void selectDropdownByValue(WebElement locator, String value) {
		Select select = new Select(locator);
		select.selectByValue(value);
	}
	
	public boolean isEnabled(WebElement locator) {
		return locator.isEnabled();
	}
	
	
	public void mouseHover(WebElement locator) {
		Actions action = new Actions(driver);
		action.moveToElement(locator).click().perform();
	}
	
}