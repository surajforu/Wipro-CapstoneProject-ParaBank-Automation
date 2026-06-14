package listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.parabank.base.BaseTest;

import reports.ExtentManager;
import utilities.ScreenshotUtil;

public class TestListener implements ITestListener {

    private final ExtentReports extent =
            ExtentManager.getInstance();

    private ExtentTest test;

    @Override
    public void onTestStart(ITestResult result) {

        test = extent.createTest(result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {

        test.pass("Test Passed");

        System.out.println(
                "✓ " + result.getName() + " PASSED");
    }

    @Override
    public void onTestFailure(ITestResult result) {

        test.fail(result.getThrowable());

        try {

            BaseTest base =
                    (BaseTest) result.getInstance();

            if (base.getDriver() != null) {

                String path =
                        ScreenshotUtil.captureScreenshot(
                                base.getDriver(),
                                result.getName());

                if (path != null) {
                    test.addScreenCaptureFromPath(
                            "../" + path);
                }

                System.out.println(
                        "Screenshot Captured: " + path);

            } else {

                System.out.println(
                        "Screenshot not captured because driver is null.");
            }

        } catch (Exception e) {

            System.out.println(
                    "Screenshot capture failed: "
                            + e.getMessage());
        }

        System.out.println(
                "✗ " + result.getName() + " FAILED");
    }

    @Override
    public void onFinish(ITestContext context) {

        extent.flush();
    }
}