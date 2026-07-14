package com.demoqa.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener
{
    private static ExtentReports extent = ExtentManager.getInstance();
    // ThreadLocal ensures logs don't mix up during parallel execution
    private static ThreadLocal<ExtentTest> testNode = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result)
    {
        // Creates a new entry in the HTML report using the test method's name
        ExtentTest test = extent.createTest(result.getMethod().getMethodName());
        testNode.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult result)
    {
        testNode.get().log(Status.PASS, "Test Passed successfully");
    }

    @Override
    public void onTestFailure(ITestResult result)
    {
        testNode.get().log(Status.FAIL, "Test Failed");
        // Captures and prints the exact console error/exception to the report
        testNode.get().fail(result.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult result)
    {
        testNode.get().log(Status.SKIP, "Test Skipped");
        testNode.get().skip(result.getThrowable());
    }

    @Override
    public void onFinish(ITestContext context)
    {
        // REQUIRED: Writes everything to the actual HTML file at the very end
        extent.flush();
    }
}