package nl.funda.uitests.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.relevantcodes.extentreports.LogStatus;

import nl.funda.uitests.base.BasePageObject;

public class ResultsPage extends BasePageObject<ResultsPage> {

	private By resultsLabel = By.cssSelector(".container.search-main > .search-output-result-count");
	private By fundaLogo = By.cssSelector("body header .logo[rel=\"home\"]");
	private By saveSearchButton = By.cssSelector(".search-save-desktop a");
	private By filterLocationTextbox = By.cssSelector("input#autocomplete-input.autocomplete-input");
	// Kosten huurder
	private By noRentalFeeRadioButton = By
			.cssSelector(".radio-group-item-label.label-text[for=\"IndGeenBemiddelingskosten-geen\"]");
	private By indefiniteRadioButton = By
			.cssSelector(".radio-group-item-label.label-text[for=\"Huurovereenkomst-geen\"]");
	private By availableImmediatelyRadioButton = By
			.cssSelector(".radio-group-item-label.label-text[for=\"DatumAanvaarding-geen\"]");
	private By apartmentRadioButton = By
			.cssSelector(".radio-group-item-label.label-text[for=\"SoortObject-Appartement\"]");
	private By twoRoomsRadioButton = By.cssSelector(".radio-group-item-label.label-text[for=\"AantalKamers-2\"]");
	private By floorArea50sqm_RadioButton = By
			.cssSelector(".radio-group-item-label.label-text[for=\"WoonOppervlakte-50\"]");
	private By newConstructionRadioButton = By
			.cssSelector(".radio-group-item-label.label-text[for=\"BouwvormId-BestaandeBouw\"]");
	private By availableRadioButton = By
			.cssSelector(".radio-group-item-label.label-text[for=\"ObjectBeschikbaarheid-Beschikbaar\"]");

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

		// Retrieve partial text from resultsLabel
		String text = driver.findElement(resultsLabel).getText();
		String[] trimmedText = text.split(":");
		for (String str : trimmedText) {
			// System.out.println(str);
			logger.log(LogStatus.INFO, str);
		}

		return getText(resultsLabel);
	}

	// Tick 'No Rental Fee' radio button
	public void tickNoRentalFeeRadioButton() {
		waitForVisibilityOf(noRentalFeeRadioButton, 10);
		WebElement checkBoxElement = driver.findElement(noRentalFeeRadioButton);
		logger.log(LogStatus.INFO, "Tick 'No Rental Fee' radio button");
		setCheckbox(checkBoxElement, true);
	}

	// Tick 'Indefinite' radio button
	public void tickIndefiniteRadioButtonRadioButton() {
		waitForVisibilityOf(indefiniteRadioButton, 10);
		WebElement checkBoxElement = driver.findElement(indefiniteRadioButton);
		logger.log(LogStatus.INFO, "Tick 'Indefinite' radio button");
		setCheckbox(checkBoxElement, true);
	}

	// Tick 'Available Immediately' radio button
	public void tickAvailableImmediatelyRadioButton() {
		waitForVisibilityOf(availableImmediatelyRadioButton, 10);
		WebElement checkBoxElement = driver.findElement(availableImmediatelyRadioButton);
		logger.log(LogStatus.INFO, "Tick 'Available Immediately' radio button");
		setCheckbox(checkBoxElement, true);
	}

	// Tick 'Apartment' radio button
	public void tickApartmentRadioButton() {
		waitForVisibilityOf(apartmentRadioButton, 10);
		WebElement checkBoxElement = driver.findElement(apartmentRadioButton);
		logger.log(LogStatus.INFO, "Tick 'Apartment' radio button");
		setCheckbox(checkBoxElement, true);
	}

	// Tick 'Two Rooms' radio button
	public void tickTwoRoomsRadioButton() {
		waitForVisibilityOf(twoRoomsRadioButton, 10);
		WebElement checkBoxElement = driver.findElement(twoRoomsRadioButton);
		logger.log(LogStatus.INFO, "Tick 'Two Rooms' radio button");
		setCheckbox(checkBoxElement, true);
	}

	// Tick 'Floor Area 50sqm' radio button
	public void tickFloorArea50sqm_RadioButton() {
		waitForVisibilityOf(floorArea50sqm_RadioButton, 10);
		WebElement checkBoxElement = driver.findElement(floorArea50sqm_RadioButton);
		logger.log(LogStatus.INFO, "Tick 'Floor Area 50sqm' radio button");
		setCheckbox(checkBoxElement, true);
	}

	// Tick 'New Construction' radio button
	public void tickNewConstructionRadioButton() {
		waitForVisibilityOf(newConstructionRadioButton, 10);
		WebElement checkBoxElement = driver.findElement(newConstructionRadioButton);
		logger.log(LogStatus.INFO, "Tick 'New Construction' radio button");
		setCheckbox(checkBoxElement, true);
	}

	// Tick 'Available' radio button
	public void tickAvailableRadioButton() {
		waitForVisibilityOf(availableRadioButton, 10);
		WebElement checkBoxElement = driver.findElement(availableRadioButton);
		logger.log(LogStatus.INFO, "Tick 'Available' radio button");
		setCheckbox(checkBoxElement, true);
	}

	public void scrollIntoViewLocationTextbox() {
		waitForVisibilityOf(filterLocationTextbox, 10);
		WebElement element = driver.findElement(filterLocationTextbox);

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", element);
	}
}
