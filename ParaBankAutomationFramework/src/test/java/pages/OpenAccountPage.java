package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OpenAccountPage {

    WebDriver driver;
    WebDriverWait wait;

    public OpenAccountPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    By openNewAccountLink = By.linkText("Open New Account");
    By accountTypeDropdown = By.id("type");
    By fromAccountDropdown = By.id("fromAccountId");
    By openAccountButton = By.xpath("//input[@value='Open New Account']");
    By newAccountNumber = By.id("newAccountId");

    public String openSavingsAccount() {

        wait.until(ExpectedConditions.elementToBeClickable(openNewAccountLink)).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(accountTypeDropdown));

        Select accountType = new Select(driver.findElement(accountTypeDropdown));
        accountType.selectByVisibleText("SAVINGS");

        wait.until(ExpectedConditions.visibilityOfElementLocated(fromAccountDropdown));

        Select fromAccount = new Select(driver.findElement(fromAccountDropdown));
        fromAccount.selectByIndex(0);

        wait.until(ExpectedConditions.elementToBeClickable(openAccountButton)).click();

        WebElement accountElement =
                wait.until(ExpectedConditions.visibilityOfElementLocated(newAccountNumber));

        String accountNumber = accountElement.getText();

        System.out.println("Account Number = " + accountNumber);

        return accountNumber;
    }
}