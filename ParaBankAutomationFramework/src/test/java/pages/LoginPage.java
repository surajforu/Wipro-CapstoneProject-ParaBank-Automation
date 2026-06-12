package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

    private WebDriver driver;

    private By usernameField = By.name("username");
    private By passwordField = By.name("password");
    private By loginButton =
            By.cssSelector("input[value='Log In']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void login(String username, String password) {

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(15));

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        usernameField))
                .sendKeys(username);

        driver.findElement(passwordField).sendKeys(password);

        driver.findElement(loginButton).click();
    }

    public boolean isLoginSuccessful() {

        try {
            WebDriverWait wait =
                    new WebDriverWait(driver, Duration.ofSeconds(15));

            wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath(
                                    "//h1[normalize-space()='Accounts Overview']")));

            return true;

        } catch (TimeoutException e) {
            return false;
        }
    }

    public String getLoginErrorMessage() {

        try {
            WebDriverWait wait =
                    new WebDriverWait(driver, Duration.ofSeconds(5));

            return wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.cssSelector("#rightPanel .error")))
                    .getText()
                    .trim();

        } catch (TimeoutException e) {
            return "No error message displayed";
        }
    }
}