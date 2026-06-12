package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.parabank.base.BaseTest;

import pages.AccountOverviewPage;
import pages.LoginPage;
import pages.TransferFundsPage;
import utilities.ExcelUtils;

public class TransferFundsTest extends BaseTest {

	@Test(priority = 5)
	public void verifyTransferFunds() throws InterruptedException {

		String username = ExcelUtils.getCellData("LoginData", 1, 0);
		String password = ExcelUtils.getCellData("LoginData", 1, 1);

		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(username, password);

		Assert.assertTrue(driver.getPageSource().contains("Accounts Overview"), "Login Failed");

		System.out.println("✓ Login Verified");

		AccountOverviewPage accountPage = new AccountOverviewPage(driver);

		double beforeBalance = accountPage.getCurrentBalance();

		System.out.println("Balance Before Transfer : " + beforeBalance);

		double transferAmount = 10;

		TransferFundsPage transferPage = new TransferFundsPage(driver);

		transferPage.validateTransferAmount(beforeBalance, transferAmount);

		String[] accounts = transferPage.transferFunds(String.valueOf(transferAmount));

		String fromAccount = accounts[0];
		String toAccount = accounts[1];

		System.out.println("✓ Money transferred from Account " + fromAccount + " to Account " + toAccount);

		Assert.assertTrue(driver.getPageSource().contains("Transfer Complete"), "Transfer Failed");
		System.out.println("✓ Transfer Successful");

		double expectedBalance = beforeBalance - transferAmount;

		Thread.sleep(2000);

		double afterBalance = accountPage.getCurrentBalance();

		System.out.println("Balance After Transfer : " + afterBalance);

		Assert.assertEquals(afterBalance, expectedBalance, 0.01, "Balance mismatch after transfer");

		System.out.println("✓ Remaining Balance Verified");
	}
}