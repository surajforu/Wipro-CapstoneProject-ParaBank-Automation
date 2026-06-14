package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegistrationPage {

	WebDriver driver;
	WebDriverWait wait;

	public RegistrationPage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	}

	// Locators
	By registerLink = By.linkText("Register");

	By firstName = By.id("customer.firstName");
	By lastName = By.id("customer.lastName");
	By address = By.id("customer.address.street");
	By city = By.id("customer.address.city");
	By state = By.id("customer.address.state");
	By zipCode = By.id("customer.address.zipCode");
	By phoneNumber = By.id("customer.phoneNumber");
	By ssn = By.id("customer.ssn");

	By username = By.id("customer.username");
	By password = By.id("customer.password");
	By confirmPassword = By.id("repeatedPassword");

	By registerButton = By.xpath("//input[@value='Register']");

	public void registerUser(String fName, String lName, String addr, String cityName, String stateName, String zip,
			String phone, String userName, String passWord) {

		wait.until(ExpectedConditions.elementToBeClickable(registerLink)).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(firstName));

		driver.findElement(firstName).clear();
		driver.findElement(firstName).sendKeys(fName);

		driver.findElement(lastName).clear();
		driver.findElement(lastName).sendKeys(lName);

		driver.findElement(address).clear();
		driver.findElement(address).sendKeys(addr);

		driver.findElement(city).clear();
		driver.findElement(city).sendKeys(cityName);

		driver.findElement(state).clear();
		driver.findElement(state).sendKeys(stateName);

		driver.findElement(zipCode).clear();
		driver.findElement(zipCode).sendKeys(zip);

		driver.findElement(phoneNumber).clear();
		driver.findElement(phoneNumber).sendKeys(phone);

		driver.findElement(ssn).clear();
		driver.findElement(ssn).sendKeys("123456");

		driver.findElement(username).clear();
		driver.findElement(username).sendKeys(userName);

		driver.findElement(password).clear();
		driver.findElement(password).sendKeys(passWord);

		driver.findElement(confirmPassword).clear();
		driver.findElement(confirmPassword).sendKeys(passWord);

		driver.findElement(registerButton).click();
	}
}