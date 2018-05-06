/**
 * Class that contains the logIn / tearDown methods and the TestNG annotations
 * (@Parameters, @BeforeTest, @AfterMethod, @AfterTest, @AfterSuite).
 */

package nl.funda.uitests.base;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.relevantcodes.extentreports.LogStatus;

import nl.funda.uitests.pages.LogInPage;
import nl.funda.uitests.pages.SearchPage;
import nl.funda.uitests.pages.UserAccountPage;

public class BaseTest extends BrowserFactory {
	protected WebDriver driver;

	@Parameters({ "browser" })
	@BeforeTest
	protected void logInWebPage(String browser) {

		driver = getDriver(browser);

		LogInPage logInPage = new LogInPage(driver);
		UserAccountPage userAccountPage = new UserAccountPage(driver);
		SearchPage searchPage = new SearchPage(driver);

		String expectedPageTitle = "Zoek huizen en appartementen te koop in Nederland [funda]";
		String emailText = "florin.machedon@gmail.com";

		// Open login page
		logger.log(LogStatus.INFO, "Open login page");
		logInPage.openLogInPage();

		// Add credentials
		logInPage.addUserAndPassword("florin.machedon@gmail.com", "WFQU6a0j1QewqM9Aworh%!%6");

		captureScreenshot(driver, "001-LoginScreen");

		// Click login button and wait for next page to load
		logInPage = logInPage.clickLogInButton();
		searchPage.waitForSearchPageToLoad();

		// Verify the user is logged in successfully
		logger.log(LogStatus.INFO,
				"Check if 'Logout' and 'Saved Houses' options are displayed after the user logged in.");
		String actualTitle = logInPage.getTitle();
		Assert.assertTrue(expectedPageTitle.equals(actualTitle),
				"Page title is not the expected one.\nExpected title: " + expectedPageTitle + "\nActualTitle");

		userAccountPage.clickHeaderProfileButton();
		userAccountPage.waitForUserAccountPageToLoad();

		// Verify the user is logged in successfully
		logger.log(LogStatus.INFO, "Check if the correct user was logged in.");
		String checkEmail = userAccountPage.getEmailText();
		Assert.assertTrue(checkEmail.contains(emailText), "This is not the email of the user that logged in. Expected: "
				+ emailText + "\nActual: " + "" + checkEmail + ".");

		captureScreenshot(driver, "002-UserSuccessfullyLoggedIn");

		userAccountPage.clickFundaLogo();
		searchPage.waitForSearchPageToLoad();

		captureScreenshot(driver, "003-SearchHomePage");
	}

	@AfterMethod
	// if @Test fails:
	// @Test information will be stored in "result"
	protected void tearDownInCaseOfFailure(ITestResult result) {
		// Capture screenshot in case of failure
		if (result.getStatus() == ITestResult.FAILURE) {
			String screenshot_path = captureScreenshot(driver, result.getName());
			logger.log(LogStatus.FAIL, "The script failed!!!", screenshot_path);
		}
		report.endTest(logger);
		report.flush();
	}
	/*
	 * @AfterTest protected void tearDownTest() { logger.log(LogStatus.INFO,
	 * "Closing the test."); driver.close(); }
	 * 
	 * @AfterSuite protected void tearDownSuite() { logger.log(LogStatus.INFO,
	 * "Closing the test suite."); report.close(); }
	 */
}
