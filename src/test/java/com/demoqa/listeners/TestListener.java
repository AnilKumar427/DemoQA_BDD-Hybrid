package com.demoqa.listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.demoqa.tests.BaseTest;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestListener implements ITestListener
{
    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> testThread = new ThreadLocal<>();

    @Override
    public void onStart(ITestContext context)
    {
        String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        String reportPath = System.getProperty("user.dir") + "/reports/ExtentReport_" + timestamp + ".html";

        // FIX 1: ExtentHtmlReporter is deprecated/removed in Extent Reports v5.
        // We now use ExtentSparkReporter to eliminate compilation issues.
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        extent.setSystemInfo("Automation Framework", "Page Object Model");
        extent.setSystemInfo("Environment", "QA/Production");
        extent.setSystemInfo("User Machine ID", System.getProperty("user.name"));
    }

    @Override
    public void onTestStart(ITestResult result)
    {
        ExtentTest test = extent.createTest(result.getMethod().getMethodName());
        testThread.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult result)
    {
        testThread.get().log(Status.PASS, "Test Executed and Passed Successfully.");
    }

    @Override
    public void onTestFailure(ITestResult result)
    {
        testThread.get().log(Status.FAIL, "Test Execution Encountered a Fault: " + result.getThrowable());

        // FIX 2: Safe fetching of the running test case object instance
        Object currentClass = result.getInstance();

        // FIX 3: Cast to BaseTest to fetch the active Webdriver reference cleanly
        if (currentClass instanceof BaseTest)
        {
            WebDriver driver = ((BaseTest) currentClass).getDriver();

            if (driver != null)
            {
                String screenshotPath = takeScreenshot(driver, result.getMethod().getMethodName());
                // FIX 4: Correct v5 syntax for binding the captured screenshot string path to the log thread
                testThread.get().addScreenCaptureFromPath(screenshotPath);
            }
        }
    }

    @Override
    public void onTestSkipped(ITestResult result)
    {
        testThread.get().log(Status.SKIP, "Test Execution was Skipped.");
    }

    @Override
    public void onFinish(ITestContext context)
    {
        if (extent != null) {
            extent.flush();
        }
    }

    private String takeScreenshot(WebDriver driver, String methodName)
    {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String destPath = System.getProperty("user.dir") + "/screenshots/" + methodName + "_" + timestamp + ".png";

        // FIX 5: Explicitly type-cast the active web driver to the screen-capture engine
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try
        {
            FileUtils.copyFile(srcFile, new File(destPath));
        }
        catch (IOException e)
        {
            System.out.println("Exception captured while taking framework screenshot: " + e.getMessage());
        }
        return destPath;
    }
}