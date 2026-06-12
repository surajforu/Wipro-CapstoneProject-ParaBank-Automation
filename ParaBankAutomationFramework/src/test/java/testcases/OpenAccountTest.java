package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.parabank.base.BaseTest;

import pages.LoginPage;
import pages.OpenAccountPage;
import utilities.ExcelUtils;

public class OpenAccountTest extends BaseTest {

	@Test(priority = 4)
	public void verifyOpenAccount() {

		String username = ExcelUtils.getCellData("LoginData", 1, 0);

		String password = ExcelUtils.getCellData("LoginData", 1, 1);

		LoginPage loginPage = new LoginPage(driver);

		loginPage.login(username, password);

		OpenAccountPage openAccountPage = new OpenAccountPage(driver);

		String accountNumber = openAccountPage.openSavingsAccount();

		Assert.assertFalse(accountNumber.isEmpty());
		System.out.println("✓ Account Created Successfully");
		Assert.assertFalse(accountNumber.trim().isEmpty(), "Account number not generated");
	}
}