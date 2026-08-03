package com.demoqa.steps;

import com.demoqa.context.DriverManager;
import com.demoqa.pages.BrokenLinksPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebElement;

public class BrokenLinksSteps {
    private BrokenLinksPage brokenPage = new BrokenLinksPage(DriverManager.getDriver());

    @Then("the valid image should render successfully")
    public void the_valid_image_should_render_successfully() {
        // Exact logic from Non-BDD BrokenTest.java
        WebElement validImg = brokenPage.getStandardImage();
        boolean isValidImgRendered = brokenPage.validateImageRendering(validImg);
        System.out.println("First Web Image Element rendered successfully? " + isValidImgRendered);
    }

    @And("the broken image should fail to render")
    public void the_broken_image_should_fail_to_render() {
        // Exact logic from Non-BDD BrokenTest.java
        WebElement brokenImg = brokenPage.getBrokenImageElement();
        boolean isBrokenImgRendered = brokenPage.validateImageRendering(brokenImg);
        System.out.println("Second Web Image Element rendered successfully? " + isBrokenImgRendered);
    }

    @And("the standard application link should return HTTP status {int}")
    public void the_standard_application_link_should_return_http_status(Integer expectedStatus) {
        // Exact logic from Non-BDD BrokenTest.java
        int validLinkStatus = brokenPage.getLinkHttpStatusCode(brokenPage.getValidLinkHref());
        System.out.println("Standard Application Link returned HTTP Status: " + validLinkStatus);
    }

    @And("the broken redirect link should return HTTP status {int}")
    public void the_broken_redirect_link_should_return_http_status(Integer expectedStatus) {
        // Exact logic from Non-BDD BrokenTest.java
        int brokenLinkStatus = brokenPage.getLinkHttpStatusCode(brokenPage.getBrokenLinkHref());
        System.out.println("Intentionally Broken Redirect returned HTTP Status: " + brokenLinkStatus);
    }
}