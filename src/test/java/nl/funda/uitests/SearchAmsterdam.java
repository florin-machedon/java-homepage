package nl.funda.uitests;

import java.util.Map;

import org.openqa.selenium.support.ui.Sleeper;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
import com.relevantcodes.extentreports.model.ScreenCapture;

import nl.funda.uitests.base.BaseTest;
import nl.funda.uitests.base.CsvDataProvider;
import nl.funda.uitests.pages.ResultsPage;
import nl.funda.uitests.pages.SearchPage;
import nl.funda.uitests.pages.UserAccountPage;

public class SearchAmsterdam extends BaseTest {

	@Test(dataProvider = "CsvDataProvider", dataProviderClass = CsvDataProvider.class)
	public void searchAmsterdam(Map<String, String> testData) {

		// Inject details from scr/test/resources/test_data/SearchData.csv
		String testNumber = testData.get("no");
		String description = testData.get("description");
		String filterLocation = testData.get("filterLocation");
		String filterDistance = testData.get("filterDistance");
		String fromPrice = testData.get("fromPrice");
		String toPrice = testData.get("toPrice");

		String resultsText = "*results"; //FIX THIS

		SearchPage searchPage = new SearchPage(driver);
		ResultsPage resultsPage = new ResultsPage(driver);

		logger.log(LogStatus.INFO, "Started Test No #" + testNumber + " for " + description);

		searchPage.clickForRentButton();
		searchPage.addSearchDetails(filterLocation, filterDistance, fromPrice, toPrice);
		searchPage.clickSearchButton();
		resultsPage.waitForResultsPageToLoad();

		String checkResults = resultsPage.getResultsText();
		Assert.assertTrue(checkResults.contains(resultsText),
				"This page did not returned any results: " + resultsText + "\nActual: " + "" + checkResults + ".");

		captureScreenshot(driver, "ForRentSearchResults");
	}

}
