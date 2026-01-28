package com.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.utils.ExtentManager;
import com.utils.ScreenshotUtils;

public class BaseTest {
	protected WebDriver driver;
// Define Report to be shared through all test suites
	public static ExtentReports extent = ExtentManager.createInstance();
	public static ExtentTest test;

	@BeforeMethod
	public void setUp(java.lang.reflect.Method method) {
		// Start each test case in the test report with Test Name
		test = extent.createTest(method.getName());

		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(
				"https://bm-inf-dev.apps.bm-dev-rosa-cl.mj5w.p1.openshiftapps.com/apps/OnlineBanking/#/AuthenticationMA/frmLogin");
	}

	@AfterMethod
	public void tearDown(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			String path = ScreenshotUtils.takeScreenshot(driver, result.getName());
			test.fail("Bug Detected: " + result.getThrowable().getMessage());
			test.addScreenCaptureFromPath(path); // ربط الصورة بالتقرير
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			test.pass("Test Passed Successfully");
		}

		if (driver != null) {
			// driver.quit();
		}
		extent.flush(); // Save Report after each test case
	}
}