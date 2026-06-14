package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.parabank.base.BaseTest;

import pages.RegistrationPage;
import utilities.ExcelUtils;

public class RegistrationTest extends BaseTest {

	@Test
	public void verifyRegistration() {

		String firstName = ExcelUtils.getCellData("Registration", 1, 0);

		String lastName = ExcelUtils.getCellData("Registration", 1, 1);

		String address = ExcelUtils.getCellData("Registration", 1, 2);

		String city = ExcelUtils.getCellData("Registration", 1, 3);

		String state = ExcelUtils.getCellData("Registration", 1, 4);

		String zipCode = ExcelUtils.getCellData("Registration", 1, 5);

		String phone = ExcelUtils.getCellData("Registration", 1, 6);

		String username = "suraj" + System.currentTimeMillis();

		String password = "Test@123";

		RegistrationPage registrationPage = new RegistrationPage(driver);

		registrationPage.registerUser(firstName, lastName, address, city, state, zipCode, phone, username, password);

		Assert.assertTrue(driver.getPageSource().contains("Your account was created successfully"));

		System.out.println("✓ Registration Successful");
		ExcelUtils.setCellData("LoginData", 1, 0, username);

		ExcelUtils.setCellData("LoginData", 1, 1, password);

		System.out.println("Username Saved : " + username);
	}
}