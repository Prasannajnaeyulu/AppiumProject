package com.seneca.appiumassessment.flipkart.listeners;


import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import java.io.File;
import java.io.IOException;

public class TestListener extends TestListenerAdapter {
	
	private static ExtentReports report = new ExtentReports(System.getProperty("user.dir")+"\\test-output\\FlipKartMobileAppTestResults.html");
	private static ExtentTest test;
	
	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		super.onTestStart(result);
		System.out.println("Inside Test Start Method");
		test = report.startTest(result.getMethod().getMethodName());
		test.log(LogStatus.INFO, "Name: "+result.getName());
		//test.log(LogStatus.INFO, "TestName from TestClass : "+result.getTestClass().getTestName());
		//test.log(LogStatus.INFO, "Test Name: "+result.getTestName());
		//test.log(LogStatus.INFO, "Test Class Name: "+result.getTestClass().getName());
		test.assignCategory("RegressionTests");
		test.log(LogStatus.INFO, "Started Executing Test:"+result.getMethod().getMethodName());
	}
	
	@Override
	public void onTestFailure(ITestResult tr) {
		super.onTestFailure(tr);
		System.out.println("Inside Test Failure Method");
		test.log(LogStatus.ERROR, tr.getThrowable().getMessage());
		//test.log(LogStatus.INFO, "");
		//test.addScreenCapture(System.getProperty("User.dir")+"\\Screenshots\\"+tr.getTestName()+System.currentTimeMillis()+".png");
		//takeScreenshot(getWebDriver(), tr.getTestName());
		String imagepath = test.addScreenCapture(System.getProperty("user.dir")+"\\ErrorMessage.jpg");
		test.log(LogStatus.INFO, "Screenshot of the Page when this test failed.."+imagepath);
		report.endTest(test);
	}
	
	@Override
	public void onTestSuccess(ITestResult tr) {
		// TODO Auto-generated method stub
		super.onTestSuccess(tr);
		System.out.println("Inside Test Success Method");
		test.log(LogStatus.PASS, tr.getName()+" succesfully passed");
		report.endTest(test);
	}
	
	@Override
	public void onFinish(ITestContext testContext) {
		// TODO Auto-generated method stub
		super.onFinish(testContext);
		System.out.println("Finished Running all tests");
		test.log(LogStatus.INFO, "Finished Running all tests");
		report.flush();
		report.close();
	}
	
	public void takeScreenshot(WebDriver driver, String testname){
		
		File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		File destFile = new File(System.getProperty("User.dir")+"\\Screenshots\\"+testname+System.currentTimeMillis()+".png");
		try {
			FileUtils.copyFile(srcFile, destFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static ExtentTest getTest() {
		return test;
	}
	
}
