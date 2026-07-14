package com.demoqa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

// Imported from your QA_Utils JAR
import pages.BaseEnginePage;

public class DynamicPropertiesPage extends BaseEnginePage {
    @FindBy(id = "enableAfter")
    private WebElement interactiveDelayedBtn;

    @FindBy(id = "colorChange")
    private WebElement textDangerColorBtn;

    @FindBy(id = "visibleAfter")
    private WebElement visibleDelayedBtn;

    public DynamicPropertiesPage(WebDriver driver) {
        super(driver);
    }

    public boolean waitForButtonClickable() {
        return wait.until(ExpectedConditions.elementToBeClickable(interactiveDelayedBtn)).isEnabled();
    }

    public boolean waitForColorMutation() {
        return wait.until(ExpectedConditions.attributeContains(textDangerColorBtn, "class", "text-danger"));
    }

    public boolean waitForElementVisibility() {
        return wait.until(ExpectedConditions.visibilityOf(visibleDelayedBtn)).isDisplayed();
    }
}