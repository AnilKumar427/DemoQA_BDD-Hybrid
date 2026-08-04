//package com.demoqa.factory;
//
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.ChromeOptions;
//import org.openqa.selenium.edge.EdgeDriver;
//import org.openqa.selenium.edge.EdgeOptions;
//import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.firefox.FirefoxOptions;
//
//// Imported from our QA_Utils JAR
//import utils.ConfigReader;
//
//public class BrowserFactory
//{
//    public static WebDriver createDriverInstance(String browser)
//    {
//        WebDriver driver;
//        String headlessProp = ConfigReader.getProperty("headless");
//        boolean isHeadless = headlessProp != null && headlessProp.equalsIgnoreCase("true");
//
//        switch (browser.toLowerCase().trim())
//        {
//            case "chrome":
//                ChromeOptions chromeOptions = new ChromeOptions();
//                chromeOptions.addArguments("--remote-allow-origins=*");
//                if (isHeadless)
//                {
//                    chromeOptions.addArguments("--headless=new");
//                    chromeOptions.addArguments("--window-size=1920,1080");
//                }
//                driver = new ChromeDriver(chromeOptions);
//                break;
//
//            case "edge":
//                EdgeOptions edgeOptions = new EdgeOptions();
//                edgeOptions.addArguments("--remote-allow-origins=*");
//                if (isHeadless)
//                {
//                    edgeOptions.addArguments("--headless=new");
//                    edgeOptions.addArguments("--window-size=1920,1080");
//                }
//                driver = new EdgeDriver(edgeOptions);
//                break;
//
//            case "firefox":
//            case "ff":
//                FirefoxOptions firefoxOptions = new FirefoxOptions();
//                if (isHeadless)
//                {
//                    firefoxOptions.addArguments("-headless");
//                }
//                driver = new FirefoxDriver(firefoxOptions);
//                break;
//
//            default:
//                throw new IllegalArgumentException("Unsupported browser: " + browser);
//        }
//        return driver;
//    }
//}

package com.demoqa.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

// Imported from our QA_Utils JAR
import utils.ConfigReader;

public class BrowserFactory
{
    public static WebDriver createDriverInstance(String browser) {
        WebDriver driver = null;

        // Read your headless property (either from config or command line)
        String configHeadless = utils.ConfigReader.getProperty("headless");
        String cmdHeadless = System.getProperty("headless");
        boolean isHeadless = Boolean.parseBoolean(cmdHeadless != null ? cmdHeadless : configHeadless);

        if (browser.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();

            // ---> PUT IT RIGHT HERE FOR CHROME <---
            if (isHeadless) {
                options.addArguments("--headless=new");
                options.addArguments("--window-size=1920,1080");
                options.addArguments("--disable-gpu");
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-dev-shm-usage");
            }

            driver = new ChromeDriver(options); // Initialize AFTER setting options

        } else if (browser.equalsIgnoreCase("edge")) {
            EdgeOptions options = new EdgeOptions();

            // ---> PUT IT RIGHT HERE FOR EDGE <---
            if (isHeadless) {
                options.addArguments("--headless=new");
                options.addArguments("--window-size=1920,1080");
                options.addArguments("--disable-gpu");
            }

            driver = new EdgeDriver(options); // Initialize AFTER setting options
        }

        return driver;
    }
}