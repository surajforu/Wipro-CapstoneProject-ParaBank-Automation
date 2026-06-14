package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.parabank.base.BaseTest;

import pages.LoginPage;
import utilities.ExcelUtils;

public class LoginTest extends BaseTest {

    @Test(priority = 2)
    public void verifyValidLogin() {

        String username =
                ExcelUtils.getCellData("LoginData", 1, 0).trim();

        String password =
                ExcelUtils.getCellData("LoginData", 1, 1).trim();

        System.out.println("Username Read From Excel: " + username);
        System.out.println("Password Read From Excel: " + password);

        Assert.assertFalse(
                username.isEmpty(),
                "Username is empty in LoginData sheet");

        Assert.assertFalse(
                password.isEmpty(),
                "Password is empty in LoginData sheet");

        LoginPage loginPage = new LoginPage(driver);

        loginPage.login(username, password);

        Assert.assertTrue(
                loginPage.isLoginSuccessful(),
                "Valid login failed. Application message: "
                        + loginPage.getLoginErrorMessage());

        System.out.println("✓ Login Successful");
    }
}