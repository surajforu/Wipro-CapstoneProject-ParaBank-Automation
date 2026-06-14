package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.parabank.base.BaseTest;

import pages.LoginPage;

public class NegativeTest extends BaseTest {

	@Test(priority = 1)
	public void invalidLoginTest() {

		LoginPage loginPage = new LoginPage(driver);

		loginPage.login("wronguser123", "wrongpass123");

		String errorMessage = loginPage.getLoginErrorMessage();

		Assert.assertTrue(errorMessage.contains("could not be verified"), "Invalid login error message not displayed");

		System.out.println("✓ Invalid Login Validation Verified");
	}

	@Test(priority = 2)
	public void emptyLoginTest() {

		LoginPage loginPage = new LoginPage(driver);

		loginPage.login("", "");

		String errorMessage = loginPage.getLoginErrorMessage();

		Assert.assertTrue(errorMessage.contains("Please enter a username and password"),
				"Empty login error message not displayed");

		System.out.println("✓ Empty Login Validation Verified");
	}
}