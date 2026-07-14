package com.demoqa.tests;

import com.demoqa.pages.DynamicPropertiesPage;
import org.testng.Assert;
import org.testng.annotations.Test;

// Imported from your QA_Utils JAR
import utils.ConfigReader;

public class DynamicPropsTest extends BaseTest {
    @Test
    public void testDynamicDelays() {
        driver.get(ConfigReader.getProperty("baseUrl") + "/dynamic-properties");
        DynamicPropertiesPage dynamicPage = new DynamicPropertiesPage(driver);

        Assert.assertTrue(dynamicPage.waitForElementVisibility());
        Assert.assertTrue(dynamicPage.waitForButtonClickable());
        Assert.assertTrue(dynamicPage.waitForColorMutation());
    }
}