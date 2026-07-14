package com.demoqa.tests;

import com.demoqa.pages.ButtonsPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ButtonsTest extends BaseTest
{

    @Test
    public void testAdvancedInteractions() throws InterruptedException
    {
        driver.get("https://demoqa.com/buttons");
        ButtonsPage buttonsPage = new ButtonsPage(driver);

        // Give the web layout a moment to settle down before trigger sequences
        Thread.sleep(500);

        buttonsPage.triggerDoubleClick();
        Assert.assertEquals(buttonsPage.getDoubleClickMessage(), "You have done a double click");

        buttonsPage.triggerRightClick();
        Assert.assertEquals(buttonsPage.getRightClickMessage(), "You have done a right click");

        buttonsPage.triggerDynamicClick();
        Assert.assertEquals(buttonsPage.getDynamicClickMessage(), "You have done a dynamic click");
    }
}