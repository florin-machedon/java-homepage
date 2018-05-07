/**
 * This class contains all the methods or variables that
 * are used for all objects found in web pages
 */

package nl.funda.uitests.base;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.LogStatus;

public class BasePageObject<T> extends BrowserFactory {
	protected WebDriver driver;
	protected WebDriverWait wait;

	/**
	 * 
	 * @param url
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected T getPage(String url) {
		driver.get(url);
		return (T) this;
	}

	/**
	 * Type into textbox (Clear text box default = true)
	 * 
	 * @param text
	 *            Text value of element to select
	 * @param element
	 *            locator of the text element
	 */
	protected void typeCSVValueInTextbox(String text, By element) {
		typeCSVValueInTextbox(text, element, true);
	}

	/**
	 * Type into textbox (Clear text box default = true)
	 * 
	 * @param text
	 *            Text value of element to select
	 * @param element
	 *            locator of the text element
	 * @param clearFirst
	 *            If true, first clear the current text
	 */
	protected void typeCSVValueInTextbox(String text, By element, boolean clearFirst) {
		WebElement domElement = findObject(element);
		if (clearFirst) {
			domElement.clear();
		}
		domElement.sendKeys(text);
	}

	/**
	 * Select option from Multi-Select based on value from csv
	 * 
	 * @param text
	 *            Text value of element to select
	 * 
	 * @param element
	 *            locator of the element to be selected
	 */
	protected void selectByVisibleTextFromMultiSelect(String text, By element) {
		selectByVisibleTextFromMultiSelect(text, element, true);
	}

	/**
	 * selectByVisibleText option from Multi-Select based on value from csv
	 * 
	 * @param text
	 *            Text value of element to select
	 * @param element
	 *            locator of the element to be selected
	 * @param clearFirst
	 *            If true, first deselect all currently selected values.
	 */
	protected void selectByVisibleTextFromMultiSelect(String text, By element, boolean clearFirst) {
		Select oSelect = new Select(driver.findElement(element));
		if (clearFirst) {
			oSelect.deselectAll();
		}
		oSelect.selectByVisibleText(text);
	}

	/**
	 * selectByVisibleText option from dropdown list based on value from csv
	 * (ATTENTION: this might differ from ENG to NLD)
	 * 
	 * @param text
	 *            Text value of element to select
	 * @param element
	 *            locator of the element to be selected
	 */
	protected void selectByVisibleTextFromDropdown(String text, By element) {
		Select oSelect = new Select(driver.findElement(element));
		oSelect.selectByVisibleText(text);
	}

	/**
	 * selectByValueFromDropdown option from dropdown list based on value from csv
	 * 
	 * @param text
	 *            Text value of element to select
	 * @param element
	 *            locator of the element to be selected
	 */
	protected void selectByValueFromDropdown(String text, By element) {
		Select oSelect = new Select(driver.findElement(element));
		oSelect.selectByValue(text);
	}

	/**
	 * click the found element
	 * 
	 * @param element
	 *            locator of the element to be clicked
	 */
	protected void click(By element) {
		findObject(element).click();
	}

	/**
	 * find element on webpage
	 * 
	 * @param element
	 *            locator of the element to be found
	 * @return
	 */
	private WebElement findObject(By element) {
		return driver.findElement(element);
	}

	/**
	 * 
	 * @param driver
	 */
	protected BasePageObject(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, 40);
	}

	/**
	 * Wait until expected condition
	 * 
	 * @param condition
	 * @param timeOutInSeconds
     * @throws TimeoutException If the timeout expires.
	 */
	private void waitFor(ExpectedCondition<WebElement> condition, Integer timeOutInSeconds) {
		timeOutInSeconds = timeOutInSeconds != null ? timeOutInSeconds : 30;
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.until(condition);
	}

	/**
	 * Wait for visibility of any given locator
	 * 
	 * @param locator
	 *            locator of the element with the select
	 * @param timeOutInSeconds, optional. Default: 30 seconds
     * @throws TimeoutException If the timeout expires.
	 */
	protected void waitForVisibilityOf(By locator, Integer... timeOutInSeconds) {
		int attempts = 0;
		while (attempts < 2) {
			try {
				waitFor(ExpectedConditions.visibilityOfElementLocated(locator),
						(timeOutInSeconds.length > 0 ? timeOutInSeconds[0] : null));
				break;
			} catch (StaleElementReferenceException e) {
			}
			attempts++;
		}
	}

	/**
	 * Get page title
	 * 
	 * @return
	 */
	public String getTitle() {
		return driver.getTitle();
	}

	/**
	 * Get text from page
	 * 
	 * @param element
	 * @return
	 */
	protected String getText(By element) {
		return findObject(element).getText();
	}

	/**
	 * Click on checkbox using JavaScript when the checkbox is not visible
	 * 
	 * @param checkBoxElement
	 * @param setTicked
	 */
	public void setCheckbox(WebElement checkBoxElement, boolean setTicked) {
		try {
			if (checkBoxElement.isSelected() == setTicked) {
				logger.log(LogStatus.INFO, "Checkbox is already " + (setTicked ? "" : "un") + "ticked!");

			} else {
				// Select the checkbox
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("arguments[0].click();", checkBoxElement);
			}
		} catch (Exception e) {
			logger.log(LogStatus.FAIL, "Unable to (un)tick the checkbox: " + checkBoxElement);
			// In case of exception - print the StackTrace
			// System.out.println(e.getMessage());
			// e.printStackTrace(System.out);
		}
	}

	/**
	 * Select all options from a MultiSelect list
	 * 
	 * @param multiSelect
	 */
	public void multiSelect(By multiSelect) {
		// Select MultiSelect box
		Select oSelect = new Select(driver.findElement(multiSelect));

		// Print and select all the options for the selected Multiple selection list.
		List<WebElement> oSize = oSelect.getOptions();
		int iListSize = oSize.size();

		// Setting up the loop to print all the options
		for (int i = 0; i < iListSize; i++) {

			// Storing the value of the option
			String sValue = oSelect.getOptions().get(i).getText();

			// Printing the stored value
			logger.log(LogStatus.INFO, "Select ALL options from MultiSelect list; Selected option:" + sValue);

			// Selecting all the elements one by one
			oSelect.selectByIndex(i);
		}
	}

	/*
	 * Send text to a textbox
	 */
	protected void sendKeysToTextbox(String text, By element) {
		findObject(element).sendKeys(text);
	}

}
