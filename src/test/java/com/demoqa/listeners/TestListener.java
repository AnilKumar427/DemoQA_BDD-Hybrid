package com.demoqa.listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.demoqa.tests.BaseTest;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

public class TestListener implements ITestListener {
    private static final Logger logger = LogManager.getLogger(TestListener.class);

    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> testThread = new ThreadLocal<>();
    private String executionType = "Default";

    @Override
    public void onStart(ITestContext context) {
        String xmlExecType = context.getCurrentXmlTest().getParameter("executionType");
        if (xmlExecType != null && !xmlExecType.isEmpty()) {
            this.executionType = xmlExecType;
        }

        System.setProperty("logRouting", this.executionType);
        org.apache.logging.log4j.core.LoggerContext ctx =
                (org.apache.logging.log4j.core.LoggerContext) org.apache.logging.log4j.LogManager.getContext(false);
        ctx.reconfigure();

        logger.info("========== SUITE EXECUTION STARTED: " + this.executionType + " ==========");

        // FIX 1: Only initialize if 'extent' is null (prevents overwriting in Hybrid runs)
        if (extent == null && !"BDD".equalsIgnoreCase(this.executionType)) {
            String reportPath = System.getProperty("user.dir") + "/reports/" + this.executionType + "_Report.html";

            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);
            extent.setSystemInfo("Automation Framework", "Hybrid Page Object Model");
            extent.setSystemInfo("Execution Type", this.executionType);
            extent.setSystemInfo("User Machine ID", System.getProperty("user.name"));
        }
    }

    @Override
    public void onTestStart(ITestResult result) {
        String testName = getTestName(result);
        logger.info("Starting Test: " + testName);

        // FIX 2: ONLY log to TestListener's Extent Report if it is NOT a Cucumber test
        if (extent != null && !(result.getInstance() instanceof AbstractTestNGCucumberTests)) {
            ExtentTest test = extent.createTest(testName);
            testThread.set(test);
        }
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        String testName = getTestName(result);
        logger.info("Test Passed: " + testName);

        if (extent != null && testThread.get() != null) {
            testThread.get().log(Status.PASS, "Test Executed and Passed Successfully.");
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {
        String testName = getTestName(result);
        logger.error("Test Failed: " + testName + " | Reason: " + result.getThrowable().getMessage());

        if (extent != null && testThread.get() != null) {
            testThread.get().log(Status.FAIL, "Test Execution Encountered a Fault: " + result.getThrowable());

            Object currentClass = result.getInstance();
            WebDriver driver = null;

            if (currentClass instanceof BaseTest) {
                driver = ((BaseTest) currentClass).getDriver();
            } else if (currentClass instanceof AbstractTestNGCucumberTests) {
                driver = com.demoqa.context.DriverManager.getDriver();
            }

            if (driver != null) {
                // 1. Save the physical .png file to the correct folder
                String screenshotPath = takeScreenshot(driver, testName);
                logger.info("Screenshot saved at: " + screenshotPath);

                // 2. Attach Base64 encoded image directly to the Extent Report to prevent broken links
                String base64Screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
                testThread.get().addScreenCaptureFromBase64String(base64Screenshot, testName + " Failure");
            }
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        String testName = getTestName(result);
        logger.warn("Test Skipped: " + testName);

        if (extent != null && testThread.get() != null) {
            testThread.get().log(Status.SKIP, "Test Execution was Skipped.");
        }
    }

    @Override
    public void onFinish(ITestContext context) {
        logger.info("========== SUITE EXECUTION FINISHED: " + this.executionType + " ==========");
        if (extent != null) {
            extent.flush();
        }
    }

    private String getTestName(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        if (result.getInstance() instanceof AbstractTestNGCucumberTests && result.getParameters().length > 0) {
            testName = result.getParameters()[0].toString().replaceAll("\"", "");
        }
        return testName;
    }

    private String takeScreenshot(WebDriver driver, String testName) {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String cleanTestName = testName.replaceAll("[^a-zA-Z0-9.-]", "_");
        String destPath = System.getProperty("user.dir") + "/screenshots/" + this.executionType + "/" + cleanTestName + "_" + timestamp + ".png";

        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(srcFile, new File(destPath));
        } catch (IOException e) {
            logger.error("Exception captured while taking framework screenshot: " + e.getMessage());
        }
        return destPath;
    }
}