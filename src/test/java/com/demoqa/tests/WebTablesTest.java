package com.demoqa.tests;

import com.demoqa.pages.WebTablesPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import java.util.List;

public class WebTablesTest extends BaseTest
{

    @Test
    public void testTableRecordOperations()
    {
        driver.get("https://demoqa.com/webtables");
        WebTablesPage webTablesPage = new WebTablesPage(driver);

        // Fill out form fields
        webTablesPage.addNewRecord("Albert", "Einstein", "alber0001@yahoo.com", "76", "26399", "Physics");

        // FIXED LINE: Changed from searchForUser to searchForRecord to match your page object method
        webTablesPage.searchForRecord("Kierra");

        // Dynamically grab rows matching search filter and extract text information cleanly
        List<WebElement> visibleRows = driver.findElements(By.className("rt-tr-group"));
        System.out.println("--- Scraped Search Records Outputs ---");
        for (WebElement row : visibleRows)
        {
            String rowText = row.getText().trim();
            if (!rowText.isEmpty())
            {
                System.out.println("Found Record Data: " + rowText);
            }
        }
    }
}