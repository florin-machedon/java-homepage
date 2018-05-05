package nl.funda.uitests.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.LogStatus;

import nl.funda.uitests.base.BasePageObject;

public class UserAccountPage extends BasePageObject<UserAccountPage> {

	private By headerProfileButton = By.cssSelector(".app-header-profile-item");
	private By editEmailLink = By.cssSelector(".user-profile-edit-email-options");
	private By deleteAccountButton = By.cssSelector(".user-profile-remove .button-secondary");
	private By emailObject = By.cssSelector("#content > div > div > div > dl > dd:nth-child(2) > span:nth-child(1)");
	private By fundaLogo = By.cssSelector("body header .logo[rel=\"home\"]");

	public UserAccountPage(WebDriver driver) {
		super(driver);
	}

	// Wait for 'User Account Page' to load

	public void waitForUserAccountPageToLoad() {
		logger.log(LogStatus.INFO, "Wait for 'User Account Page' to load");
		waitForVisibilityOf(editEmailLink);
		waitForVisibilityOf(deleteAccountButton, 10);
	}

	// Click header Profile button

	public UserAccountPage clickHeaderProfileButton() {
		waitForVisibilityOf(headerProfileButton, 10);
		logger.log(LogStatus.INFO, "Click header Profile button");
		click(headerProfileButton);
		return new UserAccountPage(driver);
	}

	// Click Delete Account button
	public UserAccountPage clickDeleteAccountButton() {
		waitForVisibilityOf(deleteAccountButton, 10);
		logger.log(LogStatus.INFO, "Click Delete Account button");
		click(deleteAccountButton);
		return new UserAccountPage(driver);
	}

	// Check if the user was successfuly created
	public String getEmailText() {
		waitForVisibilityOf(emailObject, 10);
		logger.log(LogStatus.PASS, "User created successfully!");
		return getText(emailObject);
	}

	// Click Funda logo
	public UserAccountPage clickFundaLogo() {
		waitForVisibilityOf(fundaLogo, 10);
		logger.log(LogStatus.INFO, "Click Funda logo");
		click(fundaLogo);
		return new UserAccountPage(driver);
	}
}
