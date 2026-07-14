package com.demoqa.tests;

import com.demoqa.pages.CheckBoxPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class CheckBoxTest extends BaseTest
{

    @Test
    public void testComplexTreeCheckboxes() throws InterruptedException
    {
        driver.get("https://demoqa.com/checkbox");
        CheckBoxPage checkBoxPage = new CheckBoxPage(driver);

        // Executes your exact step-by-step static tree selection logic
        checkBoxPage.executeTreeFlow();

        // Dynamically fetch and print the text results block beneath the tree elements
        try
        {
            WebElement resultTextPanel = driver.findElement(By.id("result"));
            if (resultTextPanel.isDisplayed())
            {
                System.out.println("\n--- CheckBox Selections Verified ---");
                System.out.println(resultTextPanel.getText());
            }
        }
        catch (Exception e)
        {
            System.out.println("Could not locate the selection result panel text: " + e.getMessage());
        }
    }
}