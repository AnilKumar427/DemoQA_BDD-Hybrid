package com.demoqa.steps;

import com.demoqa.context.DriverManager;
import com.demoqa.pages.RadioButtonPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class RadioButtonSteps {
    private RadioButtonPage radioPage = new RadioButtonPage(DriverManager.getDriver());

    @When("I select the Yes radio button")
    public void i_select_the_yes_radio_button() {
        radioPage.clickYesRadio();
    }

    @When("I select the Impressive radio button")
    public void i_select_the_impressive_radio_button() {
        radioPage.clickImpressiveRadio();
    }

    @Then("the radio confirmation text should contain {string}")
    public void the_radio_confirmation_text_should_contain(String expectedText) {
        Assert.assertTrue(radioPage.getConfirmationText().contains(expectedText));
    }

    @And("the No radio button should be disabled")
    public void the_no_radio_button_should_be_disabled() {
        Assert.assertTrue(radioPage.isNoRadioDisabled());
    }
}