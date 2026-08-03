package com.demoqa.steps;

import com.demoqa.context.DriverManager;
import com.demoqa.pages.DynamicPropertiesPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.testng.Assert;

public class DynamicPropertiesSteps {
    private DynamicPropertiesPage dynamicPage = new DynamicPropertiesPage(DriverManager.getDriver());

    @Then("the delayed element should become visible")
    public void the_delayed_element_should_become_visible() {
        Assert.assertTrue(dynamicPage.waitForElementVisibility());
    }

    @And("the interactive button should become clickable")
    public void the_interactive_button_should_become_clickable() {
        Assert.assertTrue(dynamicPage.waitForButtonClickable());
    }

    @And("the text color should mutate to danger")
    public void the_text_color_should_mutate_to_danger() {
        Assert.assertTrue(dynamicPage.waitForColorMutation());
    }
}