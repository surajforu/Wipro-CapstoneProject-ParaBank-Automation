package com.parabank.base;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;
import utilities.ConfigReader;

public class BaseTest {

    protected WebDriver driver;

    @BeforeMethod
    public void setup() {

        ChromeOptions options = new ChromeOptions();

        boolean headless = Boolean.parseBoolean(
                System.getProperty("headless", "false"));

        boolean docker = Boolean.parseBoolean(
                System.getProperty("docker", "false"));

        if (headless) {
            options.addArguments("--headless=new");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--window-size=1920,1080");
        }

        if (docker) {

            String gridUrl = System.getProperty(
                    "selenium.grid.url",
                    "http://selenium-chrome:4444");

            try {
                driver = new RemoteWebDriver(
                        new URL(gridUrl),
                        options);

            } catch (MalformedURLException e) {
                throw new RuntimeException(
                        "Invalid Selenium Grid URL: " + gridUrl,
                        e);
            }

        } else {

            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver(options);
        }
        if (!headless) {
            driver.manage().window().maximize();
        }

        driver.manage().timeouts()
                .implicitlyWait(Duration.ofSeconds(10));

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