package com.demoqa.steps;

import com.demoqa.context.DriverManager;
import com.demoqa.pages.TextBoxPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class TextBoxSteps {
    private TextBoxPage textBoxPage = new TextBoxPage(DriverManager.getDriver());

    @When("I fill the text box form with valid details")
    public void i_fill_the_text_box_form_with_valid_details() {
        textBoxPage.fillForm("Albert Einstein", "albert001@gmail.com",
                "112 Mercer Street, Princeton, NJ", "112 Mercer Street, Princeton");
    }

    @Then("the output panel should be displayed")
    public void the_output_panel_should_be_displayed() {
        Assert.assertTrue(textBoxPage.isOutputDisplayed());
    }
}

//Einstein