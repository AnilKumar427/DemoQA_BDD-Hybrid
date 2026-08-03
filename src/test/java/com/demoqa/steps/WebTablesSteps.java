package com.demoqa.steps;

import com.demoqa.context.DriverManager;
import com.demoqa.pages.WebTablesPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class WebTablesSteps {
    private WebTablesPage webTablesPage = new WebTablesPage(DriverManager.getDriver());

    @When("I add a new record for {string} {string}")
    public void i_add_a_new_record_for(String firstName, String lastName) {
        webTablesPage.addNewRecord(firstName, lastName, "alber0001@yahoo.com", "76", "26399", "Physics");
    }

    @And("I search for the record {string}")
    public void i_search_for_the_record(String query) {
        webTablesPage.searchForRecord(query);
    }

    @Then("the matching records should be displayed")
    public void the_matching_records_should_be_displayed() {
        // Exact logic from Non-BDD WebTablesTest.java
        List<WebElement> visibleRows = DriverManager.getDriver().findElements(By.className("rt-tr-group"));
        System.out.println("--- Scraped Search Records Outputs ---");
        for (WebElement row : visibleRows) {
            String rowText = row.getText().trim();
            if (!rowText.isEmpty()) {
                System.out.println("Found Record Data: " + rowText);
            }
        }
    }
}