package com.demoqa.steps;

import com.demoqa.context.DriverManager;
import com.demoqa.pages.ButtonsPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class ButtonsSteps {
    private ButtonsPage buttonsPage = new ButtonsPage(DriverManager.getDriver());

    @When("I trigger a double click")
    public void i_trigger_a_double_click() throws InterruptedException {
        Thread.sleep(500);
        buttonsPage.triggerDoubleClick();
    }

    @Then("the double click message should be {string}")
    public void the_double_click_message_should_be(String expected) {
        Assert.assertEquals(buttonsPage.getDoubleClickMessage(), expected);
    }

    @When("I trigger a right click")
    public void i_trigger_a_right_click() {
        buttonsPage.triggerRightClick();
    }

    @Then("the right click message should be {string}")
    public void the_right_click_message_should_be(String expected) {
        Assert.assertEquals(buttonsPage.getRightClickMessage(), expected);
    }

    @When("I trigger a dynamic click")
    public void i_trigger_a_dynamic_click() {
        buttonsPage.triggerDynamicClick();
    }

    @Then("the dynamic click message should be {string}")
    public void the_dynamic_click_message_should_be(String expected) {
        Assert.assertEquals(buttonsPage.getDynamicClickMessage(), expected);
    }
}