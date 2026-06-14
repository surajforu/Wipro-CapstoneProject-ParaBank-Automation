package testcases;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.parabank.base.BaseTest;

import pages.BillPayPage;
import pages.LoginPage;
import utilities.DataProviders;
import utilities.ExcelUtils;

public class BillPayTest extends BaseTest {

	@Test(
			priority = 6, dataProvider = "billPayData", dataProviderClass = DataProviders.class)
		public void verifyBillPayment(

		        String payee,
		        String address,
		        String city,
		        String state,
		        String zip,
		        String phone,
		        String accountNo,
		        String amount) {

			String username = ExcelUtils.getCellData("LoginData", 1, 0);

			String password = ExcelUtils.getCellData("LoginData", 1, 1);

			LoginPage loginPage = new LoginPage(driver);

			loginPage.login(username, password);

			BillPayPage billPayPage = new BillPayPage(driver);

		    billPayPage.payBill(
		            payee,
		            address,
		            city,
		            state,
		            zip,
		            phone,
		            accountNo,
		            amount);

			Assert.assertTrue(billPayPage.getPaymentMessage().contains("Bill Payment"));

			System.out.println("✓ Bill Payment Successful");
		}}