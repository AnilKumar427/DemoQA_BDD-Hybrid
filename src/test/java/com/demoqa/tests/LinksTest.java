package com.demoqa.tests;

import com.demoqa.pages.LinksPage;
import org.testng.Assert;
import org.testng.annotations.Test;

// Imported from your QA_Utils JAR
import utils.ConfigReader;

public class LinksTest extends BaseTest {
    @Test
    public void testWindowNavigationAndMockApiLinks() {
        driver.get(ConfigReader.getProperty("baseUrl") + "/links");
        LinksPage linksPage = new LinksPage(driver);

        String tabTitle = linksPage.followSimpleLinkAndGetTitle();
        Assert.assertFalse(tabTitle.isEmpty());

        linksPage.clickApiLink("created");
        Assert.assertTrue(linksPage.getApiResponsePanelText().contains("201"));
    }
}