Feature: Buttons Advanced Interactions

  Scenario: Perform double, right, and dynamic clicks
    Given I navigate to the "buttons" page
    When I trigger a double click
    Then the double click message should be "You have done a double click"
    When I trigger a right click
    Then the right click message should be "You have done a right click"
    When I trigger a dynamic click
    Then the dynamic click message should be "You have done a dynamic click"