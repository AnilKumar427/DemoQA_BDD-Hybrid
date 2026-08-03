Feature: Text Box Interactions

  Scenario: Successfully submit the text box form
    Given I navigate to the "text-box" page
    When I fill the text box form with valid details
    Then the output panel should be displayed