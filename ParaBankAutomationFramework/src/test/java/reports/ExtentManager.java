package reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {

	private static ExtentReports extent;

	public static ExtentReports getInstance() {

		if (extent == null) {

			ExtentSparkReporter spark = new ExtentSparkReporter("test-output/ExtentReport.html");

			spark.config().setDocumentTitle("ParaBank Automation Report");

			spark.config().setReportName("Execution Report");

			extent = new ExtentReports();
			extent.attachReporter(spark);

			extent.setSystemInfo("Tester", "Suraj Shaw");
		}

		return extent;
	}
}