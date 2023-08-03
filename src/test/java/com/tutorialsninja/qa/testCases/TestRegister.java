package com.tutorialsninja.qa.testCases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.conftest.conftest;
import com.tutorialsninja.qa.pageObjects.AccountConfirmPageObjects;
import com.tutorialsninja.qa.pageObjects.HomePageObjects;
import com.tutorialsninja.qa.pageObjects.RegisterPageObjects;
import com.tutorialsninja.qa.testData.testDataOfAllPages;
import com.tutorialsninja.qa.utilities.BaseClass;

public class TestRegister extends conftest {

	public WebDriver driver;
	BaseClass baseclass;
	RegisterPageObjects registerPage;
	AccountConfirmPageObjects confirmPage;

	@BeforeMethod(alwaysRun = true)
	public void initilization() {
		driver = setup(config.getProperty("browserName"));
		baseclass = new BaseClass(driver);
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		driver.close();
	}

	@Test(priority = 1, groups = { "smoke" })
	public void verifyRegisteringAnAccountByProvidingMandatoryField() {
		HomePageObjects homepage = new HomePageObjects(driver);
		registerPage = homepage.accountRegister();
		registerPage.firstName().sendKeys(testData.getProperty("firstName"));
		registerPage.lastName().sendKeys(testData.getProperty("lastName"));
		registerPage.email().sendKeys(baseclass.generateEmailWithTimeStamp());
		registerPage.telephone().sendKeys(testData.getProperty("telephone"));
		registerPage.password().sendKeys(config.getProperty("validPassword"));
		registerPage.confirmPassword().sendKeys(config.getProperty("validPassword"));
		registerPage.privacyPolicy().click();
		confirmPage = registerPage.submit();

		Assert.assertEquals(confirmPage.confirmAccountCreated(), confirmPage.conf_message());
	}

	@Test(priority = 2)
	public void verifyRegisteringAnAccountByProvidingAllField() {
		HomePageObjects homepage = new HomePageObjects(driver);
		registerPage = homepage.accountRegister();
		registerPage.firstName().sendKeys(testData.getProperty("firstName"));
		registerPage.lastName().sendKeys(testData.getProperty("lastName"));
		registerPage.email().sendKeys(baseclass.generateEmailWithTimeStamp());
		registerPage.telephone().sendKeys(testData.getProperty("telephone"));
		registerPage.password().sendKeys(config.getProperty("validPassword"));
		registerPage.confirmPassword().sendKeys(config.getProperty("validPassword"));
		registerPage.newsLetterYes().click();
		registerPage.privacyPolicy().click();
		confirmPage = registerPage.submit();

		Assert.assertEquals(confirmPage.confirmAccountCreated(), confirmPage.conf_message());
	}

	@Test(priority = 3)
	public void verifyRegisteringNotificationMessagesForMandatoryFieldBYNotProvidingFileds() {
		HomePageObjects homepage = new HomePageObjects(driver);
		registerPage = homepage.accountRegister();
		registerPage.submit();

		Assert.assertEquals(registerPage.firstNameActualWarningMessage(),
				registerPage.firstNameExepectedWarningMessage());
		Assert.assertEquals(registerPage.lastNameActualWarningMessage(),
				registerPage.lastNameExepectedWarningMessage());
		Assert.assertEquals(registerPage.emailActualWarningMessage(), registerPage.emailExepectedWarningMessage());
		Assert.assertEquals(registerPage.telephoneActualWarningMessage(),
				registerPage.telephoneExepectedWarningMessage());
		Assert.assertEquals(registerPage.passwordActualWarningMessage(),
				registerPage.passwordExepectedWarningMessage());
		Assert.assertEquals(registerPage.privacyPolicyActualWarningMessage(),
				registerPage.privacyPolicyExepectedWarningMessage());
	}

	@Test(priority = 4)
	public void verifyRegisteringAnAccountBySelectingNewsletterNO() {
		HomePageObjects homepage = new HomePageObjects(driver);
		registerPage = homepage.accountRegister();
		registerPage.firstName().sendKeys(testData.getProperty("firstName"));
		registerPage.lastName().sendKeys(testData.getProperty("lastName"));
		registerPage.email().sendKeys(baseclass.generateEmailWithTimeStamp());
		registerPage.telephone().sendKeys(testData.getProperty("telephone"));
		registerPage.password().sendKeys(config.getProperty("validPassword"));
		registerPage.confirmPassword().sendKeys(config.getProperty("validPassword"));
		registerPage.newsLetterNo().click();
		registerPage.privacyPolicy().click();
		confirmPage = registerPage.submit();

		Assert.assertEquals(confirmPage.confirmAccountCreated(), confirmPage.conf_message());
	}

	@Test(priority = 5)
	public void verifyRegisteringAnAccountBySelectingNewsletterNOYES() {
		HomePageObjects homepage = new HomePageObjects(driver);
		registerPage = homepage.accountRegister();
		registerPage.firstName().sendKeys(testData.getProperty("firstName"));
		registerPage.lastName().sendKeys(testData.getProperty("lastName"));
		registerPage.email().sendKeys(baseclass.generateEmailWithTimeStamp());
		registerPage.telephone().sendKeys(testData.getProperty("telephone"));
		registerPage.password().sendKeys(config.getProperty("validPassword"));
		registerPage.confirmPassword().sendKeys(config.getProperty("validPassword"));
		registerPage.newsLetterYes().click();
		registerPage.privacyPolicy().click();
		confirmPage = registerPage.submit();

		Assert.assertEquals(confirmPage.confirmAccountCreated(), confirmPage.conf_message());
	}

	@Test(priority = 6)
	public void verifyRegisteringAnAccountByProvidingDifferentPasswordInPasswordAndConfirmPassword() {
		HomePageObjects homepage = new HomePageObjects(driver);
		registerPage = homepage.accountRegister();
		registerPage.firstName().sendKeys(testData.getProperty("firstName"));
		registerPage.lastName().sendKeys(testData.getProperty("lastName"));
		registerPage.email().sendKeys(baseclass.generateEmailWithTimeStamp());
		registerPage.telephone().sendKeys(testData.getProperty("telephone"));
		registerPage.password().sendKeys(config.getProperty("validPassword"));
		registerPage.confirmPassword().sendKeys(testData.getProperty("invalidPassword"));
		registerPage.newsLetterYes().click();
		registerPage.privacyPolicy().click();
		registerPage.submit();

		Assert.assertEquals(registerPage.confirmPassActualWarningMessage(),
				registerPage.confirmPassExpectedWarningMessage());
		Assert.assertEquals(registerPage.loginpageActualWarningMessage(),
				registerPage.loginPageExpectedWarningMessage());
	}

	@Test(priority = 7)
	public void verifyRegisteringAnAccountByProvidingExistingAccount() {
		HomePageObjects homepage = new HomePageObjects(driver);
		registerPage = homepage.accountRegister();
		registerPage.firstName().sendKeys(testData.getProperty("firstName"));
		registerPage.lastName().sendKeys(testData.getProperty("lastName"));
		registerPage.email().sendKeys(config.getProperty("validEmail"));
		registerPage.telephone().sendKeys(testData.getProperty("telephone"));
		registerPage.password().sendKeys(config.getProperty("validPassword"));
		registerPage.confirmPassword().sendKeys(config.getProperty("validPassword"));
		registerPage.privacyPolicy().click();
		registerPage.submit();

		Assert.assertEquals(registerPage.alreadyRegisteredActualWarningMessage(),
				registerPage.alreadyRegisteredExpectedWarningMessage());
	}

	@Test(priority = 8, dataProvider = "getData")
	public void verifyRegisteringAnAccountByInvalidEmails(String email, String password) {
		HomePageObjects homepage = new HomePageObjects(driver);
		registerPage = homepage.accountRegister();
		registerPage.firstName().sendKeys(testData.getProperty("firstName"));
		registerPage.lastName().sendKeys(testData.getProperty("lastName"));
		registerPage.email().sendKeys(email);
		registerPage.telephone().sendKeys(testData.getProperty("telephone"));
		registerPage.password().sendKeys(password);
		registerPage.confirmPassword().sendKeys(password);
		registerPage.privacyPolicy().click();
		registerPage.submit();
		Assert.assertTrue(baseclass.getValidationMessage(registerPage.email()));
	}

	@DataProvider()
	public Object[][] getData() {
		Object[][] data = testDataOfAllPages.getTestDataFromExcel("Login");
		return data;
	}

	@Test(priority = 9, dataProvider = "getTelephoneData")
	public void verifyRegisteringAnAccountByInvalidPhonenumbers(String telephone) {
		HomePageObjects homepage = new HomePageObjects(driver);
		registerPage = homepage.accountRegister();
		registerPage.firstName().sendKeys(testData.getProperty("firstName"));
		registerPage.lastName().sendKeys(testData.getProperty("lastName"));
		registerPage.email().sendKeys(baseclass.generateEmailWithTimeStamp());
		registerPage.telephone().sendKeys(telephone);
		registerPage.password().sendKeys(config.getProperty("validPassword"));
		registerPage.confirmPassword().sendKeys(config.getProperty("validPassword"));
		registerPage.privacyPolicy().click();
		registerPage.submit();

		if (baseclass.getTitle().equals("Register Account")) {
			Assert.assertEquals(registerPage.telephoneActualWarningMessage(),
					registerPage.telephoneExepectedWarningMessage());
		} else {
			Assert.assertFalse(true, "Account got created with invalid phone numbers");
		}
	}

	@DataProvider()
	public Object[][] getTelephoneData() {
		Object[][] telephoneData = testDataOfAllPages.telephoneTestData();
		return telephoneData;
	}

	@Test(priority = 10)
	public void verifyRegisteringAnAccountByProvidingSpacesInTheFields() {
		HomePageObjects homepage = new HomePageObjects(driver);
		registerPage = homepage.accountRegister();
		registerPage.firstName().sendKeys(" ");
		registerPage.lastName().sendKeys(" ");
		registerPage.email().sendKeys(" ");
		registerPage.telephone().sendKeys("   ");
		registerPage.password().sendKeys("    ");
		registerPage.confirmPassword().sendKeys("    ");
		registerPage.privacyPolicy().click();
		confirmPage = registerPage.submit();

		Assert.assertEquals(registerPage.firstNameActualWarningMessage(),
				registerPage.firstNameExepectedWarningMessage());
		Assert.assertEquals(registerPage.lastNameActualWarningMessage(),
				registerPage.lastNameExepectedWarningMessage());
		Assert.assertEquals(registerPage.emailActualWarningMessage(), registerPage.emailExepectedWarningMessage());
		Assert.assertEquals(registerPage.telephoneActualWarningMessage(),
				registerPage.telephoneExepectedWarningMessage());
		Assert.assertEquals(registerPage.passwordActualWarningMessage(),
				registerPage.passwordExepectedWarningMessage());
		Assert.assertEquals(registerPage.privacyPolicyActualWarningMessage(),
				registerPage.privacyPolicyExepectedWarningMessage());
	}

	@Test(priority = 11)
	public void verifyRegisteringAnAccountToCheckPageFollowingComplexityStandardsPassword() {
		HomePageObjects homepage = new HomePageObjects(driver);
		registerPage = homepage.accountRegister();
		registerPage.firstName().sendKeys(testData.getProperty("firstName"));
		registerPage.lastName().sendKeys(testData.getProperty("lastName"));
		registerPage.email().sendKeys(baseclass.generateEmailWithTimeStamp());
		registerPage.telephone().sendKeys(testData.getProperty("telephone"));
		registerPage.password().sendKeys(testData.getProperty("NotComplexPassword"));
		registerPage.confirmPassword().sendKeys(testData.getProperty("NotComplexPassword"));
		registerPage.privacyPolicy().click();
		registerPage.submit();
		
		if(baseclass.getTitle().equals("Register Account")) {
		Assert.assertEquals(registerPage.passwordActualWarningMessage(),
				registerPage.passwordComplexityExpectedWarningMessage());
	}else {
		Assert.assertFalse(true, "Account got created without password complexity");
		}
	}

	@Test(priority = 12)
	public void verifyRegisteringAnAccountWhetherLeadingandTrailingSpacesTrimmed() {
		HomePageObjects homepage = new HomePageObjects(driver);
		registerPage = homepage.accountRegister();
		registerPage.firstName().sendKeys(testData.getProperty("firstNameWithSpaces"));
		registerPage.lastName().sendKeys(testData.getProperty("lastNameWithSpaces"));
		registerPage.email().sendKeys(baseclass.generateEmailWithTimeStamp());
		registerPage.telephone().sendKeys(testData.getProperty("telephoneWithSpaces"));
		registerPage.password().sendKeys(testData.getProperty("PasswordWithSpaces"));
		registerPage.confirmPassword().sendKeys(testData.getProperty("PasswordWithSpaces"));
		registerPage.privacyPolicy().click();
		confirmPage = registerPage.submit();
		confirmPage.editAccount();

		Assert.assertNotEquals((baseclass.getAttributeOfWebElement(registerPage.firstName(), "value")),
				testData.getProperty("firstNameWithSpaces"));
		Assert.assertNotEquals(baseclass.getAttributeOfWebElement(registerPage.lastName(), "value"),
				testData.getProperty("lastNameWithSpaces"));
		Assert.assertNotEquals((baseclass.getAttributeOfWebElement(registerPage.telephone(), "value")),
				testData.getProperty("telephoneWithSpaces"));
		Assert.assertNotEquals(baseclass.getAttributeOfWebElement(registerPage.password(), "value"),
				testData.getProperty("PasswordWithSpaces"));
		Assert.assertNotEquals(baseclass.getAttributeOfWebElement(registerPage.confirmPassword(), "value"),
				testData.getProperty("PasswordWithSpaces"));
	}

	@Test(priority = 13)
	public void verifyRegisteringAnAccountCheckPrivacyPolicyCheckboxIsNotSelectedByDefault() {
		HomePageObjects homepage = new HomePageObjects(driver);
		registerPage = homepage.accountRegister();

		Assert.assertFalse(baseclass.isSelected(registerPage.privacyPolicy()));

	}

	@Test(priority = 14)
	public void verifyRegisteringAnAccountWithoutSelectingPrivacyPolicyCheckbox() {
		HomePageObjects homepage = new HomePageObjects(driver);
		registerPage = homepage.accountRegister();
		registerPage.firstName().sendKeys(testData.getProperty("firstName"));
		registerPage.lastName().sendKeys(testData.getProperty("lastName"));
		registerPage.email().sendKeys(baseclass.generateEmailWithTimeStamp());
		registerPage.telephone().sendKeys(testData.getProperty("telephone"));
		registerPage.password().sendKeys(config.getProperty("validPassword"));
		registerPage.confirmPassword().sendKeys(config.getProperty("validPassword"));
		registerPage.privacyPolicy();
		confirmPage = registerPage.submit();

		Assert.assertEquals(registerPage.privacyPolicyActualWarningMessage(),
				registerPage.privacyPolicyExepectedWarningMessage());
	}

	@Test(priority = 15)
	public void verifyRegisteringAnAccountByFillingPasswordAndWithoutFillingConfirmPassword() {
		HomePageObjects homepage = new HomePageObjects(driver);
		registerPage = homepage.accountRegister();
		registerPage.firstName().sendKeys(testData.getProperty("firstName"));
		registerPage.lastName().sendKeys(testData.getProperty("lastName"));
		registerPage.email().sendKeys(baseclass.generateEmailWithTimeStamp());
		registerPage.telephone().sendKeys(testData.getProperty("telephone"));
		registerPage.password().sendKeys(config.getProperty("validPassword"));
		registerPage.confirmPassword().sendKeys("");
		registerPage.privacyPolicy().click();
		confirmPage = registerPage.submit();

		Assert.assertEquals(registerPage.confirmPassActualWarningMessage(),
				registerPage.confirmPassExpectedWarningMessage());
	}
}
