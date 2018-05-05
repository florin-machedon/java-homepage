package nl.funda.uitests.base;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class BrowserFactory {

	/**
	 * Initialization of ExtentReports (creates interactive reports, a dashboard
	 * view, and emailable reports for automated testing)
	 */

	protected static ExtentReports report = new ExtentReports("./log/automation-report.html");
	protected static ExtentTest logger = report.startTest("Automation Report");

	/**
	 * Building the webdriver based on the Operating System Switch that starts:
	 * ChromDriver and GeckoDriver
	 * 
	 * @param browser
	 */
	public WebDriver getDriver(String browser) {

		WebDriver driver;

		String suffix;
		String os = System.getProperty("os.name").toLowerCase();
		if (os.contains("mac")) {
			suffix = "";
		} else {
			suffix = ".exe";
		}

		logger.log(LogStatus.INFO, "Starting " + browser + suffix + " driver for OS: " + os);
		// System.out.println("Starting " + browser + suffix + " driver for os: " + os);

		switch (browser) {
		case "chrome":
			System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver" + suffix);
			logger.log(LogStatus.INFO, "Open Chrome Browser");
			driver = new ChromeDriver();
			break;
		case "firefox":
			System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver" + suffix);
			logger.log(LogStatus.INFO, "Open Firefox Browser");
			driver = new FirefoxDriver();
			break;
		default:
			System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver" + suffix);
			logger.log(LogStatus.INFO, "Default: Open Chrome Browser");
			driver = new ChromeDriver();
			break;
		}
		driver.manage().window().maximize();
		logger.log(LogStatus.INFO, "Web driver for " + browser + " created.");
		return driver;
	}

	/**
	 * Capture screenshot method:
	 * 
	 * @param driver
	 * @param screenshotName
	 */
	public String captureScreenshot(WebDriver driver, String screenshotName) {

		try {
			TakesScreenshot ts = (TakesScreenshot) driver;
			File source = ts.getScreenshotAs(OutputType.FILE);
			String dest = "./log/Screenshots/" + screenshotName + ".png";
			File destination = new File(dest);
			FileUtils.copyFile(source, destination);
			logger.log(LogStatus.INFO, "Screenshot " + screenshotName + ".png" + " taken!!");
			return dest;

		} catch (Exception e) {
			logger.log(LogStatus.FAIL, "Exception while taking screenshot: " + e.getMessage());
			return e.getMessage();
		}
	}

}
