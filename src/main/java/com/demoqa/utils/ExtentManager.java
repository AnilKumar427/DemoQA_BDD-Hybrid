package com.demoqa.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager
{
    private static ExtentReports extent;

    public static ExtentReports getInstance()
    {
        if (extent == null)
        {
            // Define where the report will be saved
            String reportPath = System.getProperty("user.dir") + "/test-output/ExtentReport.html";
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);

            // Configure the look and feel of the report
            sparkReporter.config().setDocumentTitle("DemoQA Automation Results");
            sparkReporter.config().setReportName("Elements Category Test Execution");
            sparkReporter.config().setTheme(Theme.DARK);

            // Attach the configurations to the ExtentReports core
            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);

            // Add some system or environment info
            extent.setSystemInfo("Operating System", System.getProperty("os.name"));
            extent.setSystemInfo("Java Version", System.getProperty("java.version"));
            extent.setSystemInfo("QA Engineer", "Your Name");
        }
        return extent;
    }
}