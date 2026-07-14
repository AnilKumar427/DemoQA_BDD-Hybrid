package com.demoqa.tests;

import com.demoqa.pages.BrokenLinksPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class BrokenTest extends BaseTest
{

    @Test
    public void testAssetsAndHyperlinks()
    {
        driver.get("https://demoqa.com/broken");
        BrokenLinksPage brokenPage = new BrokenLinksPage(driver);

        // 1. Fetch the image elements using the existing page object getters
        WebElement validImg = brokenPage.getStandardImage();
        WebElement brokenImg = brokenPage.getBrokenImageElement();

        System.out.println("\n--- Validating Images ---");

        // Use validateImageRendering from your original BrokenPage class
        boolean isValidImgRendered = brokenPage.validateImageRendering(validImg);
        boolean isBrokenImgRendered = brokenPage.validateImageRendering(brokenImg);

        System.out.println("First Web Image Element rendered successfully? " + isValidImgRendered);
        System.out.println("Second Web Image Element rendered successfully? " + isBrokenImgRendered);

        // 2. Fetch the hyperlink text strings using the existing page object getters
        String validLinkHref = brokenPage.getValidLinkHref();
        String brokenLinkHref = brokenPage.getBrokenLinkHref();

        System.out.println("\n--- Validating Links ---");

        // Use getLinkHttpStatusCode from your original BrokenPage class
        int validLinkStatus = brokenPage.getLinkHttpStatusCode(validLinkHref);
        int brokenLinkStatus = brokenPage.getLinkHttpStatusCode(brokenLinkHref);

        System.out.println("Standard Application Link returned HTTP Status: " + validLinkStatus);
        System.out.println("Intentionally Broken Redirect returned HTTP Status: " + brokenLinkStatus);
    }
}