package FirstAutomation.TestComponents;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import FirstAutomation.resources.ExtentReporterNG;

public class Listerners extends BaseTest implements ITestListener {

	ExtentTest test;
	ExtentReports extent =	ExtentReporterNG.getReportObject();
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
	
	@Override
	public void onTestStart(ITestResult result) 
	{
		test = extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test); //unique thread id (ErrorValidationTest)->test
	}
	
	@Override
	public void onTestSuccess(ITestResult result) 
	{
		test.log(Status.PASS, "Test passed");
	}
	
	@Override
	public void onTestFailure(ITestResult result) {
		
		test.fail(result.getThrowable());
		extentTest.get().fail(result.getThrowable());
		
		try {
			driver  = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		
		String filePath = null;
		try {
			filePath = getScreenshot(result.getMethod().getMethodName(), driver); 
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		test.addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
		
		}

		
	
	@Override
	public void onTestSkipped(ITestResult result) 
	{
		
	}
	
	@Override
	public void onFinish(ITestContext result) 
	{
		extent.flush();
	}
	
}
