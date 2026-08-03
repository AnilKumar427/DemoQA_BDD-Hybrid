Feature: Radio Button Interactions

  Scenario: Select radio buttons and verify states
    Given I navigate to the "radio-button" page
    When I select the Yes radio button
    Then the radio confirmation text should contain "Yes"
    When I select the Impressive radio button
    Then the radio confirmation text should contain "Impressive"
    And the No radio button should be disabled