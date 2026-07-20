package com.demoqa.tests;

import com.demoqa.factory.BrowserFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import java.time.Duration;

// Imported from our QA_Utils JAR
import utils.ConfigReader;

public class BaseTest
{
    protected WebDriver driver;

    @BeforeMethod
    @Parameters("browser")
    public void setUp(@org.testng.annotations.Optional String xmlBrowser)
    {
        java.util.logging.Logger.getLogger("org.openqa.selenium").setLevel(java.util.logging.Level.SEVERE);

        String targetBrowser;

        if (xmlBrowser != null && !xmlBrowser.isEmpty())
        {
            targetBrowser = xmlBrowser;
            System.out.println("[INFO] Launching browser from TestNG XML: " + targetBrowser);
        }
        else
        {
            targetBrowser = ConfigReader.getProperty("browser");
            System.out.println("[INFO] Launching browser from Config.properties: " + targetBrowser);
        }

        driver = BrowserFactory.createDriverInstance(targetBrowser);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    public WebDriver getDriver()
    {
        return this.driver;
    }

    @AfterMethod
    public void tearDown()
    {
        if (driver != null)
        {
            driver.quit();
        }
    }

}