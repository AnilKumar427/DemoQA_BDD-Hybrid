package com.demoqa.steps;

import com.demoqa.context.DriverManager;
import com.demoqa.pages.LinksPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class LinksSteps {
    private LinksPage linksPage = new LinksPage(DriverManager.getDriver());

    @When("I follow the simple link")
    public void i_follow_the_simple_link() {
        // Implementation handled within the page object during the assertion step
    }

    @Then("a new tab should open with a valid title")
    public void a_new_tab_should_open_with_a_valid_title() {
        String tabTitle = linksPage.followSimpleLinkAndGetTitle();
        Assert.assertFalse(tabTitle.isEmpty());
    }

    @When("I click the {string} API link")
    public void i_click_the_api_link(String linkType) {
        linksPage.clickApiLink(linkType);
    }

    @Then("the API response panel should contain {string}")
    public void the_api_response_panel_should_contain(String expectedStatus) {
        Assert.assertTrue(linksPage.getApiResponsePanelText().contains(expectedStatus));
    }
}