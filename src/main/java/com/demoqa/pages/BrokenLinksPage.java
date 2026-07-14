package com.demoqa.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.net.HttpURLConnection;
import java.net.URL;

// Imported from your QA_Utils JAR
import pages.BaseEnginePage;

public class BrokenLinksPage extends BaseEnginePage {
    @FindBy(xpath = "//img[@src='/images/Toolsqa.jpg']")
    private WebElement standardImage;

    @FindBy(xpath = "//img[@src='/images/Toolsqa_1.jpg']")
    private WebElement brokenImageElement;

    @FindBy(linkText = "Click Here for Valid Link")
    private WebElement validLinkLocator;

    @FindBy(linkText = "Click Here for Broken Link")
    private WebElement brokenLinkLocator;

    public BrokenLinksPage(WebDriver driver) {
        super(driver);
    }

    public boolean validateImageRendering(WebElement img) {
        return (Boolean) ((JavascriptExecutor) driver).executeScript("return arguments[0].naturalWidth > 0;", img);
    }

    public int getLinkHttpStatusCode(String urlString) {
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("HEAD");
            connection.connect();
            return connection.getResponseCode();
        } catch (Exception e) {
            return 500;
        }
    }

    public WebElement getStandardImage() {
        return standardImage;
    }

    public WebElement getBrokenImageElement() {
        return brokenImageElement;
    }

    public String getValidLinkHref() {
        return validLinkLocator.getAttribute("href");
    }

    public String getBrokenLinkHref() {
        return brokenLinkLocator.getAttribute("href");
    }
}