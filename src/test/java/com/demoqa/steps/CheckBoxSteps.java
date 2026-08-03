package com.demoqa.steps;

import com.demoqa.context.DriverManager;
import com.demoqa.pages.CheckBoxPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class CheckBoxSteps {
    private CheckBoxPage checkBoxPage = new CheckBoxPage(DriverManager.getDriver());

    @When("I execute the complex tree selection flow")
    public void i_execute_the_complex_tree_selection_flow() throws InterruptedException {
        checkBoxPage.executeTreeFlow();
    }

    @Then("the checkbox selections should be verified")
    public void the_checkbox_selections_should_be_verified() {
        WebElement resultTextPanel = DriverManager.getDriver().findElement(By.id("result"));
        Assert.assertTrue(resultTextPanel.isDisplayed());
    }
}