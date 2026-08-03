Feature: Dynamic Delays and Properties

  Scenario: Wait for dynamic element properties
    Given I navigate to the "dynamic-properties" page
    Then the delayed element should become visible
    And the interactive button should become clickable
    And the text color should mutate to danger