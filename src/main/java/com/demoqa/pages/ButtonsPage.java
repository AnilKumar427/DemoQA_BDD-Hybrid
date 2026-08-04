package com.demoqa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

// Imported from your QA_Utils JAR
import pages.BaseEnginePage;

public class ButtonsPage extends BaseEnginePage {
    private Actions actions;
    private WebDriverWait wait;

    @FindBy(id = "doubleClickBtn") private WebElement doubleClickBtn;
    @FindBy(id = "rightClickBtn") private WebElement rightClickBtn;
    @FindBy(xpath = "//button[text()='Click Me']") private WebElement dynamicClickBtn;
    @FindBy(id = "doubleClickMessage") private WebElement doubleMessage;
    @FindBy(id = "rightClickMessage") private WebElement rightMessage;
    @FindBy(id = "dynamicClickMessage") private WebElement dynamicMessage;

    public ButtonsPage(WebDriver driver) {
        super(driver);
        this.actions = new Actions(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public void triggerDoubleClick() {
        actions.doubleClick(doubleClickBtn).perform();
    }

    public void triggerRightClick() {

        actions.contextClick(rightClickBtn).perform();
    }

    public void triggerDynamicClick() {
        scrollCenterAndClick(dynamicClickBtn);
    }

    public String getDoubleClickMessage() {
        return wait.until(ExpectedConditions.visibilityOf(doubleMessage)).getText();
    }

    public String getRightClickMessage() {
        // Wait up to 10 seconds for the message to physically appear in the DOM
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("rightClickMessage")));

        return message.getText();
    }

    public String getDynamicClickMessage() {
        return wait.until(ExpectedConditions.visibilityOf(dynamicMessage)).getText();
    }
}