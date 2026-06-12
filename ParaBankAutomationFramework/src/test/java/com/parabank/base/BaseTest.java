package com.parabank.base;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;
import utilities.ConfigReader;

public class BaseTest {

	protected WebDriver driver;

	@BeforeMethod
	public void setup() {

		WebDriverManager.chromedriver().setup();

		ChromeOptions options = new ChromeOptions();

		boolean headless = Boolean.parseBoolean(System.getProperty("headless", "false"));

		if (headless) {
			options.addArguments("--headless=new");
			options.addArguments("--window-size=1920,1080");
		}

		driver = new ChromeDriver(options);

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		driver.manage().window().maximize();

		driver.get(ConfigReader.getProperty("url"));
	}

	@AfterMethod
	public void tearDown() {

		if (driver != null) {
			driver.quit();
		}
	}

	public WebDriver getDriver() {
		return driver;
	}
}