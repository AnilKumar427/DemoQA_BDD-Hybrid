package com.demoqa.steps;

import com.demoqa.context.DriverManager;
import io.cucumber.java.en.Given;
import utils.ConfigReader;

public class CommonSteps {
    @Given("I navigate to the {string} page")
    public void i_navigate_to_the_page(String endpoint) {
        DriverManager.getDriver().get(ConfigReader.getProperty("baseUrl") + "/" + endpoint);
    }
}