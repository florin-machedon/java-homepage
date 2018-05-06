package nl.funda.uitests.pages;

import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.relevantcodes.extentreports.LogStatus;

import nl.funda.uitests.base.BasePageObject;

public class SearchPage extends BasePageObject<SearchPage> {

	private By headerProfileButton = By.cssSelector(".app-header-profile-item");
	private By logOutLink = By.cssSelector("[href=\"/mijn/logout/\"][title=\"Log hier uit\"]");

	private By forSaleButton = By.cssSelector(".search-block__navigation-item[href$=\"/koop/\"]");
	private By forRentButton = By.cssSelector(".search-block__navigation-item[href$=\"/huur/\"]");
	private By newlyBuiltButton = By.cssSelector(".search-block__navigation-item[href$=\"/nieuwbouw/\"]");
	private By recreationButton = By.cssSelector(".search-block__navigation-item[href$=\"/recreatie/\"]");
	private By europeButton = By.cssSelector(".search-block__navigation-item[href$=\"/europe/\"]");
	private By filterLocationTextbox = By.cssSelector("input#autocomplete-input.autocomplete-input");
	private By autoCompleteFirstOption = By.cssSelector("#autocomplete-list-option0 > span:nth-child(1)");
	//	private By autoCompleteFullList = By.cssSelector("#autocomplete-list");
	private By filterDistanceDropdownlist = By.cssSelector("#Straal[name=\"filter_Straal\"]");
	private By fromPriceDropdownlist = By.cssSelector("#range-filter-selector-select-filter_huurprijsvan");
	private By toPriceDropdownlist = By.cssSelector("#range-filter-selector-select-filter_huurprijstot");

	private By searchButton = By.cssSelector(".search-block__submit > [type=\"submit\"]");
	// private By searchButton = By.cssSelector("search-block__submit");

	public SearchPage(WebDriver driver) {
		super(driver);
	}

	// Wait for 'Search Page' to load
	public void waitForSearchPageToLoad() {
		logger.log(LogStatus.INFO, "Wait for 'Search Page' to load");
		waitForVisibilityOf(headerProfileButton);
		waitForVisibilityOf(logOutLink, 10);
	}

	// Click header Profile button
	public SearchPage clickDeleteAccountButton() {
		waitForVisibilityOf(headerProfileButton, 10);
		logger.log(LogStatus.INFO, "Click header Profile button");
		click(headerProfileButton);
		return new SearchPage(driver);
	}

	// Click 'For Sale' button
	public SearchPage clickForSaleButton() {
		waitForVisibilityOf(forSaleButton, 10);
		logger.log(LogStatus.INFO, "Click 'For Sale' button");
		click(forSaleButton);
		return new SearchPage(driver);
	}

	// Click 'For Rent' button
	public SearchPage clickForRentButton() {
		waitForVisibilityOf(forRentButton, 10);
		logger.log(LogStatus.INFO, "Click 'For Rent' button");
		click(forRentButton);
		return new SearchPage(driver);
	}

	// Click Newly Built button
	public SearchPage clickNewlyBuiltButton() {
		waitForVisibilityOf(newlyBuiltButton, 10);
		logger.log(LogStatus.INFO, "Click 'Newly Built' button");
		click(newlyBuiltButton);
		return new SearchPage(driver);
	}

	// Click 'Recreation' button
	public SearchPage clickRecreationButton() {
		waitForVisibilityOf(recreationButton, 10);
		logger.log(LogStatus.INFO, "Click 'Recreation' button");
		click(recreationButton);
		return new SearchPage(driver);
	}

	// Click 'Europe' button
	public SearchPage clickEuropeButton() {
		waitForVisibilityOf(europeButton, 10);
		logger.log(LogStatus.INFO, "Click Europe button");
		click(europeButton);
		return new SearchPage(driver);
	}

	// scr/test/resources/test_data/
	public void addSearchDetails(String filterLocation, String filterDistance, String fromPrice, String toPrice) {
		logger.log(LogStatus.INFO, "Filling up Search details");

		waitForVisibilityOf(filterLocationTextbox, 10);
		typeCSVValueInTextbox(filterLocation, filterLocationTextbox);

		waitForVisibilityOf(filterDistanceDropdownlist, 10);
		selectByVisibleTextFromDropdown(filterDistance, filterDistanceDropdownlist);

		waitForVisibilityOf(fromPriceDropdownlist, 10);
		selectByValueFromDropdown(fromPrice, fromPriceDropdownlist);

		waitForVisibilityOf(toPriceDropdownlist, 10);
		selectByValueFromDropdown(toPrice, toPriceDropdownlist);

		driver.findElement(filterLocationTextbox).sendKeys(Keys.SPACE);
		// waitForVisibilityOf(autoCompleteFullList, 10);

		try {
			WebElement autoOptions = driver.findElement(autoCompleteFirstOption);
			wait.until(ExpectedConditions.visibilityOf(autoOptions));

			List<WebElement> optionsToSelect = autoOptions.findElements(By.tagName("id"));
			for (WebElement option : optionsToSelect) {
				if (option.getText().equals(filterLocation)) {
					System.out.println("Trying to select: " + filterLocation);
					option.click();
					break;
				}
			}
		} catch (NoSuchElementException e) {
			// System.out.println(e.getStackTrace());
		} catch (Exception e) {
			// System.out.println(e.getStackTrace());
		}
	}

	// Click 'Search' button
	public SearchPage clickSearchButton() {
		waitForVisibilityOf(searchButton, 10);
		logger.log(LogStatus.INFO, "Click 'Search' button");
		click(searchButton);
		return new SearchPage(driver);
	}

}
