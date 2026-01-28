package com.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {
	private static ExtentReports extent;

	public static ExtentReports createInstance() {
		if (extent == null) {
			ExtentSparkReporter spark = new ExtentSparkReporter("Reports/ExtentReport.html");
			extent = new ExtentReports();
			extent.attachReporter(spark);
			extent.setSystemInfo("Environment", "SIT - Banque Misr");
			extent.setSystemInfo("QA Engineer", "Khaled Mostafa Elsayed");
		}
		return extent;
	}
}