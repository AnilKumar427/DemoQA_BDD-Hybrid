package com.demoqa.tests;

import com.demoqa.pages.TextBoxPage;
import org.testng.Assert;
import org.testng.annotations.Test;

// Imported from our QA_Utils JAR
import utils.ConfigReader;

public class TextBoxTest extends BaseTest
{
    @Test
    public void testTextBoxSubmission()
    {
        driver.get(ConfigReader.getProperty("baseUrl") + "/text-box");
        TextBoxPage textBoxPage = new TextBoxPage(driver);

        textBoxPage.fillForm("Albert Einstein", "albert001@gmail.com",
                "112 Mercer Street, Princeton, NJ", "112 Mercer Street, Princeton");

        Assert.assertTrue(textBoxPage.isOutputDisplayed());
    }
}