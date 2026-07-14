package com.demoqa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.Set;

// Imported from your QA_Utils JAR
import pages.BaseEnginePage;

public class LinksPage extends BaseEnginePage {
    @FindBy(id = "simpleLink") private WebElement simpleTabLink;
    @FindBy(id = "linkResponse") private WebElement linkResponsePanel;
    @FindBy(id = "created") private WebElement createdLink;
    @FindBy(id = "no-content") private WebElement noContentLink;
    @FindBy(id = "moved") private WebElement movedLink;
    @FindBy(id = "bad-request") private WebElement badRequestLink;
    @FindBy(id = "unauthorized") private WebElement unauthorizedLink;
    @FindBy(id = "forbidden") private WebElement forbiddenLink;
    @FindBy(id = "invalid-url") private WebElement notFoundLink;

    public LinksPage(WebDriver driver) {
        super(driver);
    }

    public String followSimpleLinkAndGetTitle() {
        String mainWin = driver.getWindowHandle();
        simpleTabLink.click();
        wait.until(d -> d.getWindowHandles().size() > 1);

        Set<String> allWindows = driver.getWindowHandles();
        String childTitle = "";
        for (String win : allWindows) {
            if (!win.equals(mainWin)) {
                driver.switchTo().window(win);
                childTitle = driver.getTitle();
                driver.close();
            }
        }
        driver.switchTo().window(mainWin);
        return childTitle;
    }

    public void clickApiLink(String type) {
        WebElement target = switch (type.toLowerCase()) {
            case "created" -> createdLink;
            case "no-content" -> noContentLink;
            case "moved" -> movedLink;
            case "bad-request" -> badRequestLink;
            case "unauthorized" -> unauthorizedLink;
            case "forbidden" -> forbiddenLink;
            default -> notFoundLink;
        };
        scrollCenterAndClick(target);
    }

    public String getApiResponsePanelText() {
        wait.until(ExpectedConditions.visibilityOf(linkResponsePanel));
        return linkResponsePanel.getText();
    }
}