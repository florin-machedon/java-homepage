package nl.funda.uitests.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.LogStatus;

import nl.funda.uitests.base.BasePageObject;

public class LogInPage extends BasePageObject<LogInPage> {

	private static final String URL = "https://www.funda.nl/mijn/login/";
	private By logInHeaderButton = By.cssSelector("#app-header-mainmenu > li:nth-child(1) a");
	private By userTextbox = By.cssSelector("#Username");
	private By passwordTextbox = By.cssSelector("#Password");
	private By logInFacebookButton = By.cssSelector("button.social-login-facebook");
	private By logInButton = By.cssSelector("button.button-primary");

	public LogInPage(WebDriver driver) {
		super(driver);
	}

	/**
	 * Get he page URL
	 */
	public void openLogInPage() {
		getPage(URL);
	}

	/**
	 * Wait for Login Page to load
	 */
	public void waitLogInPageToLoad() {
		logger.log(LogStatus.INFO, "Wait for Login Page to load");
		waitForVisibilityOf(logInButton);
		waitForVisibilityOf(logInFacebookButton, 10);
	}

	/**
	 * Click the Login header-button
	 */
	public LogInPage clickLogInHeaderButton() {
		logger.log(LogStatus.INFO, "Click the Login header-button");
		click(logInHeaderButton);
		return new LogInPage(driver);
	}

	/**
	 * Filling up user and password
	 * 
	 * @param user
	 * @param password
	 */
	// Filling up user and password
	public void addUserAndPassword(String user, String password) {
		logger.log(LogStatus.INFO, "Filling up user and password");
		typeCSVValueInTextbox(user, userTextbox);
		typeCSVValueInTextbox(password, passwordTextbox);
	}

	/**
	 * Click the Login in button
	 */
	public LogInPage clickLogInButton() {
		logger.log(LogStatus.INFO, "Click the Login in button");
		click(logInButton);
		return new LogInPage(driver);
	}

}
