package mamtaranipkg.resources;

import mamtaranipkg.ExtentReports.ExtentReports;
import mamtaranipkg.ExtentReports.ExtentSparkReporter;

public class ExtentReporterNG {
	public static ExtentReports getReportObject() {
		String path = System.getProperty("user.dir") + "\\reports\\index.html";

		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Web Automation Results");
		reporter.config().setDocumentTitle("Test Results");

		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Mamta Rani");
		return extent;
	}

}
