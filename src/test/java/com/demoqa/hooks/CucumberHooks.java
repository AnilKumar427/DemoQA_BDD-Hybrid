package com.demoqa.hooks;

import com.demoqa.context.DriverManager;
import com.demoqa.factory.BrowserFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import utils.ConfigReader;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public class CucumberHooks {

    private static final Logger logger = LogManager.getLogger(CucumberHooks.class);

    @Before
    public void setup(Scenario scenario) {
        // Mute the red Selenium CDP warnings
        java.util.logging.Logger.getLogger("org.openqa.selenium").setLevel(java.util.logging.Level.SEVERE);

        logger.info("========== Starting BDD Scenario: " + scenario.getName() + " ==========");

        String targetBrowser = ConfigReader.getProperty("browser");
        WebDriver driver = BrowserFactory.createDriverInstance(targetBrowser);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        DriverManager.setDriver(driver);
    }

    @After
    public void tearDown(Scenario scenario) {
        WebDriver driver = DriverManager.getDriver();

        if (scenario.isFailed() && driver != null) {
            logger.error("Scenario Failed! Taking screenshot for: " + scenario.getName());
            try {
                // 1. Attach Base64 Screenshot directly to the Extent Report
                byte[] screenshotBytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshotBytes, "image/png", "Failure_Screenshot");

                // 2. Save the Physical .png file to the BDD screenshots folder
                File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                String cleanScenarioName = scenario.getName().replaceAll("[^a-zA-Z0-9.-]", "_");
                String destPath = System.getProperty("user.dir") + "/screenshots/BDD/" + cleanScenarioName + "_" + timestamp + ".png";

                FileUtils.copyFile(srcFile, new File(destPath));
                logger.info("Physical screenshot saved at: " + destPath);

            } catch (Exception e) {
                logger.error("Failed to capture screenshot: " + e.getMessage());
            }
        }

        logger.info("Scenario Finished: " + scenario.getName() + " | Status: " + scenario.getStatus());
        logger.info("==========================================================================");

        if (driver != null) {
            driver.quit();
        }
    }
}