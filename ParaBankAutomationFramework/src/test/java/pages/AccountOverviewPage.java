package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AccountOverviewPage {

	WebDriver driver;
	WebDriverWait wait;

	public AccountOverviewPage(WebDriver driver) {

		this.driver = driver;

		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	}

	By accountsOverviewLink = By.linkText("Accounts Overview");

	By balance = By.xpath("(//table[@id='accountTable']//tr[1]/td[2])[1]");

	public double getCurrentBalance() {

		wait.until(ExpectedConditions.elementToBeClickable(accountsOverviewLink)).click();

		String bal = wait.until(ExpectedConditions.visibilityOfElementLocated(balance)).getText();

		bal = bal.replace("$", "").replace(",", "").trim();

		return Double.parseDouble(bal);
	}
}