package com.demoqa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

// Imported from your QA_Utils JAR
import pages.BaseEnginePage;

public class RadioButtonPage extends BaseEnginePage {
    @FindBy(css = "label[for='yesRadio']")
    private WebElement yesRadioLabel;

    @FindBy(css = "label[for='impressiveRadio']")
    private WebElement impressiveRadioLabel;

    @FindBy(id = "noRadio")
    private WebElement noRadioInput;

    @FindBy(css = "p.mt-3")
    private WebElement successText;

    public RadioButtonPage(WebDriver driver) {
        super(driver);
    }

    public void clickYesRadio() {
        yesRadioLabel.click();
    }

    public void clickImpressiveRadio() {
        impressiveRadioLabel.click();
    }

    public boolean isNoRadioDisabled() {
        return !noRadioInput.isEnabled();
    }

    public String getConfirmationText() {
        return successText.getText();
    }
}