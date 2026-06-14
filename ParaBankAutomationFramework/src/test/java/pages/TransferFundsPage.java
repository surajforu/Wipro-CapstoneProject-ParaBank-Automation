package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TransferFundsPage {

	WebDriver driver;
	WebDriverWait wait;

	public TransferFundsPage(WebDriver driver) {

		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	By transferFundsLink = By.linkText("Transfer Funds");

	By amount = By.id("amount");

	By fromAccount = By.id("fromAccountId");

	By toAccount = By.id("toAccountId");

	By transferButton = By.xpath("//input[@value='Transfer']");

	By successMessage = By.xpath("//h1");

	public void validateTransferAmount(double currentBalance, double transferAmount) {

		if (transferAmount > currentBalance) {

			throw new RuntimeException("Insufficient Balance. Available Balance = " + currentBalance);
		}
	}

	public String[] transferFunds(String transferAmount) {

		wait.until(ExpectedConditions.elementToBeClickable(transferFundsLink)).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(amount));

		driver.findElement(amount).clear();

		driver.findElement(amount).sendKeys(transferAmount);

		wait.until(ExpectedConditions.visibilityOfElementLocated(fromAccount));

		Select from = new Select(driver.findElement(fromAccount));

		Select to = new Select(driver.findElement(toAccount));

		from.selectByIndex(0);

		if (to.getOptions().size() > 1) {
			to.selectByIndex(1);
		} else {
			to.selectByIndex(0);
		}

		String fromAccNo = from.getFirstSelectedOption().getText();

		String toAccNo = to.getFirstSelectedOption().getText();

		System.out.println("From Account : " + fromAccNo);

		System.out.println("To Account   : " + toAccNo);

		wait.until(ExpectedConditions.elementToBeClickable(transferButton)).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage));

		return new String[] { fromAccNo, toAccNo };
	}

	public void transferFundsToSameAccount(String transferAmount) {

		wait.until(ExpectedConditions.elementToBeClickable(transferFundsLink)).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(amount));

		driver.findElement(amount).clear();
		driver.findElement(amount).sendKeys(transferAmount);

		Select from = new Select(driver.findElement(fromAccount));
		Select to = new Select(driver.findElement(toAccount));

		from.selectByIndex(0);
		to.selectByIndex(0);

		wait.until(ExpectedConditions.elementToBeClickable(transferButton)).click();
	}
}