package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.parabank.base.BaseTest;

import pages.LoginPage;
import utilities.ExcelUtils;

public class AccountOverviewTest extends BaseTest {

	@Test(priority = 7)
	public void verifyAccountOverview() {

		String username = ExcelUtils.getCellData("LoginData", 1, 0);

		String password = ExcelUtils.getCellData("LoginData", 1, 1);

		LoginPage loginPage = new LoginPage(driver);

		loginPage.login(username, password);

		Assert.assertTrue(driver.getPageSource().contains("Accounts Overview"));
	}
}