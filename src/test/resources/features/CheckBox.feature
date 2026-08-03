Feature: Check Box Interactions

  Scenario: Execute complex tree checkbox selections
    Given I navigate to the "checkbox" page
    When I execute the complex tree selection flow
    Then the checkbox selections should be verified