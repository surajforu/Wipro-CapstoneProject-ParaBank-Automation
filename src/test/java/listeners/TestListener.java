package listeners;

import java.io.IOException;

import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.parabank.base.BaseTest;

import reports.ExtentManager;
import utilities.ScreenshotUtil;

public class TestListener implements ITestListener {

	ExtentReports extent = ExtentManager.getInstance();

	ExtentTest test;

	@Override
	public void onTestStart(ITestResult result) {

		test = extent.createTest(result.getName());
	}

	@Override
	public void onTestSuccess(ITestResult result) {

		test.pass("Test Passed");

		System.out.println("✓ " + result.getName() + " PASSED");
	}

	@Override
	public void onTestFailure(ITestResult result) {

		test.fail(result.getThrowable());

		try {

			BaseTest base = (BaseTest) result.getInstance();

			String path = ScreenshotUtil.captureScreenshot(base.getDriver(), result.getName());

			test.addScreenCaptureFromPath("../" + path);

			System.out.println("📸 Screenshot Captured");

		} catch (IOException e) {

			e.printStackTrace();
		}

		System.out.println("✗ " + result.getName() + " FAILED");
	}

	@Override
	public void onFinish(org.testng.ITestContext context) {

		extent.flush();
	}
}