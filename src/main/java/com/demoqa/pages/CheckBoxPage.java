package com.demoqa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class CheckBoxPage
{
    private WebDriver driver;
    private WebDriverWait wait;

    public CheckBoxPage(WebDriver driver)
    {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public void executeTreeFlow() throws InterruptedException
    {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Expand Root Home folder using JS click
        WebElement rootSwitcher = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(@class,'rc-tree-switcher')]")));
        js.executeScript("arguments[0].click();", rootSwitcher);

        // Get immediate children under home (Desktop, Documents, Downloads)
        List<WebElement> homeBoxes = driver.findElements(By.xpath("//*[@class='rc-tree-switcher rc-tree-switcher_close']"));

        // 1. Expand Desktop folder using JS
        js.executeScript("arguments[0].click();", homeBoxes.get(0));

        // Use JS clicks for checkboxes
        WebElement notesCb = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("span[aria-label='Select Notes']")));
        js.executeScript("arguments[0].click();", notesCb);

        // Scroll commands check box into view just in case
        WebElement commandsCb = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("span[aria-label='Select Commands']")));
        js.executeScript("arguments[0].scrollIntoView({block:'center'});", commandsCb);
        js.executeScript("arguments[0].click();", commandsCb);

        // 2. Expand Documents folder using JS
        js.executeScript("arguments[0].click();", homeBoxes.get(1));

        // Target document sub-folders
        List<WebElement> docBoxes = wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(
                By.xpath("//span[contains(@class,'rc-tree-switcher rc-tree-switcher_close')]"), 1));

        // Expand Workspace folder using JS (Fixes line 54 error)
        js.executeScript("arguments[0].click();", docBoxes.get(0));

        WebElement angularCb = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("span[aria-label='Select Angular']")));
        js.executeScript("arguments[0].click();", angularCb);

        // Expand Office folder using JS
        js.executeScript("arguments[0].click();", docBoxes.get(1));

        WebElement publicCb = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("span[aria-label='Select Public']")));
        js.executeScript("arguments[0].click();", publicCb);

        WebElement privateCb = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("span[aria-label='Select Private']")));
        js.executeScript("arguments[0].click();", privateCb);

        WebElement generalCb = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("span[aria-label='Select General']")));
        js.executeScript("arguments[0].click();", generalCb);

        // 3. Handle Downloads expansion cleanly
        WebElement downloadToggle = driver.findElement(
                By.xpath("//span[@aria-label='Select Downloads']/preceding-sibling::span[1]"));

        js.executeScript("arguments[0].scrollIntoView({block:'center'});", downloadToggle);
        Thread.sleep(500);
        js.executeScript("arguments[0].click();", downloadToggle);

        // Click final Word Doc target using JS
        WebElement wordFileCb = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("span[aria-label='Select Word File.doc']")));
        js.executeScript("arguments[0].click();", wordFileCb);

    }
}