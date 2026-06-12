package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BillPayPage {

	WebDriver driver;
	WebDriverWait wait;

	public BillPayPage(WebDriver driver) {

		this.driver = driver;

		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	}

	// Locators
	By billPayLink = By.linkText("Bill Pay");

	By payeeName = By.name("payee.name");

	By address = By.name("payee.address.street");

	By city = By.name("payee.address.city");

	By state = By.name("payee.address.state");

	By zipCode = By.name("payee.address.zipCode");

	By phone = By.name("payee.phoneNumber");

	By accountNumber = By.name("payee.accountNumber");

	By verifyAccount = By.name("verifyAccount");

	By amount = By.name("amount");

	By sendPaymentButton = By.xpath("//input[@value='Send Payment']");

	By successMessage = By.tagName("h1");

	public void payBill(String payee, String street, String cityName, String stateName, String zip, String mobile,
			String accNo, String billAmount) {

		wait.until(ExpectedConditions.elementToBeClickable(billPayLink)).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(payeeName)).sendKeys(payee);

		driver.findElement(address).sendKeys(street);
		driver.findElement(city).sendKeys(cityName);
		driver.findElement(state).sendKeys(stateName);
		driver.findElement(zipCode).sendKeys(zip);
		driver.findElement(phone).sendKeys(mobile);

		driver.findElement(accountNumber).sendKeys(accNo);
		driver.findElement(verifyAccount).sendKeys(accNo);

		driver.findElement(amount).sendKeys(billAmount);

		wait.until(ExpectedConditions.elementToBeClickable(sendPaymentButton)).click();
	}

	public String getPaymentMessage() {

		return wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage)).getText();
	}
}