package nl.funda.uitests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import nl.funda.uitests.base.BaseTest;
import nl.funda.uitests.base.CsvDataProvider;
import nl.funda.uitests.pages.ResultsPage;
import nl.funda.uitests.pages.SearchPage;

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

		String resultsText = "resultaten";
		String fiterCountNumber = "6";

		SearchPage searchPage = new SearchPage(driver);
		ResultsPage resultsPage = new ResultsPage(driver);

		logger.log(LogStatus.INFO, "Started Test No #" + testNumber + " for " + description);

		// Starting the script
		searchPage.clickForRentButton();
		searchPage.addSearchDetails(filterLocation, filterDistance, fromPrice, toPrice);
		searchPage.clickSearchButton();
		resultsPage.waitForResultsPageToLoad();

		// Check if the search returned any results
		String checkResults = resultsPage.getResultsText();
		Assert.assertTrue(checkResults.contains(resultsText), "This page did not returned any results: " + resultsText + "\nActual: " + "" + checkResults + ".");

		captureScreenshot(driver, "005-ForRentSearchResults");

		resultsPage.tickNoRentalFeeRadioButton();
		resultsPage.tickIndefiniteRadioButtonRadioButton();
		resultsPage.tickAvailableImmediatelyRadioButton();
		resultsPage.tickApartmentRadioButton();
		resultsPage.tickTwoRoomsRadioButton();
		resultsPage.tickFloorArea50sqm_RadioButton();
		resultsPage.tickNewConstructionRadioButton();
		resultsPage.tickAvailableRadioButton();
		resultsPage.scrollIntoViewLocationTextbox();

		// Check if the expected filters are actually applied on the resultsPage
		String checkFilterCount = resultsPage.getFiterCountNumber();
		Assert.assertTrue(checkFilterCount.contains(fiterCountNumber),
				"Expected number of filters to be applied : " + fiterCountNumber + "\nActual: " + "" + checkFilterCount + ".");

		captureScreenshot(driver, "006-ForRentSearchResults-Filtered");
		
		resultsPage.clickFundaLogo();
		
	}
}
