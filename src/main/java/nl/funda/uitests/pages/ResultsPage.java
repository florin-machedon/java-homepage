package nl.funda.uitests.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.LogStatus;

import nl.funda.uitests.base.BasePageObject;

public class ResultsPage extends BasePageObject<ResultsPage> {

	private By resultsLabel = By.cssSelector(".container.search-main > .search-output-result-count");
	private By fundaLogo = By.cssSelector("body header .logo[rel=\"home\"]");
	private By saveSearchButton = By.cssSelector(".search-save-desktop a");

	public ResultsPage(WebDriver driver) {
		super(driver);
	}

	// Wait for 'Results Page' to load

	public void waitForResultsPageToLoad() {
		logger.log(LogStatus.INFO, "Wait for 'Results Page' to load");
		waitForVisibilityOf(resultsLabel);
		waitForVisibilityOf(saveSearchButton, 10);
	}

	// Click Funda logo
	public UserAccountPage clickFundaLogo() {
		waitForVisibilityOf(fundaLogo, 10);
		logger.log(LogStatus.INFO, "Click Funda logo");
		click(fundaLogo);
		return new UserAccountPage(driver);
	}

	// Page has returned results
	public String getResultsText() {
		waitForVisibilityOf(resultsLabel, 10);
		logger.log(LogStatus.PASS, "Page has returned results");
		return getText(resultsLabel);
	}
}
