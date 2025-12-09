package com.goibibo.Utilities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.goibibo.TestClass.BaseTest;

public class ExtentReportManagers implements ITestListener {

	public ExtentReportManagers manage;
	public ExtentSparkReporter reporter;
	public ExtentReports reports;
	public ExtentTest test;
	String reportName;

	@Override // ITestContext represent suite.xml related info
	public void onStart(ITestContext context) {
		String timeStamp = new SimpleDateFormat("HH.mm.ss.dd.MM").format(new Date());
		reportName = "Test-Report-" + timeStamp + ".html";
		reporter = new ExtentSparkReporter(".\\reports\\" + reportName);

		reporter.config().setDocumentTitle("Goibibo AutomationReport");
		reporter.config().setReportName("Goibibo Fuctional Testing");
		reporter.config().setTheme(Theme.STANDARD);

		reports = new ExtentReports();
		reports.attachReporter(reporter);
		reports.setSystemInfo("appilication", "Goibibo");
		reports.setSystemInfo("Module", "Admin");
		reports.setSystemInfo("User Name", System.getProperty("user.name"));

		String os = context.getCurrentXmlTest().getParameter("os");
		reports.setSystemInfo("Operation System", os);

		String browser = context.getCurrentXmlTest().getParameter("browser");
		reports.setSystemInfo("Operation System", browser);

		List<String> includeGroups = context.getCurrentXmlTest().getIncludedGroups();

		if (!includeGroups.isEmpty()) {
			reports.setSystemInfo("Groups", includeGroups.toString());
		}

	}

	@Override // ITestResult represent @Test related info
	public void onTestSuccess(ITestResult result) {

		test = reports.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());// to display groups
		test.log(Status.PASS, result.getName() + " got successfully executed");
	}

	@Override
	public void onTestFailure(ITestResult result) {

		test = reports.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());// to display groups
		test.log(Status.FAIL, result.getName() + " got failed");
		test.log(Status.INFO, result.getThrowable().getMessage());

 		try {
			String filePath = new BaseTest().getScreenshots(result.getName());
			System.out.println("file path at onTestFailure "+filePath);
			test.addScreenCaptureFromPath(filePath);

		} catch (Exception e) {
			e.printStackTrace(); 
			System.out.println("screenshots not atteched to reports");
		}

	}

	@Override
	public void onTestStart(ITestResult result) {

	}

	@Override
	public void onTestSkipped(ITestResult result) {

		test = reports.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());// to display groups
		test.log(Status.SKIP, result.getName() + " got skipped");
		test.log(Status.INFO, result.getThrowable().getMessage());
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {

	}

	@Override
	public void onFinish(ITestContext context) {

		reports.flush();
	}

}
