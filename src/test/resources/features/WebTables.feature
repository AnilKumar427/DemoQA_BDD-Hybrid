Feature: Web Tables Operations

  Scenario: Add and search for a table record
    Given I navigate to the "webtables" page
    When I add a new record for "Albert" "Einstein"
    And I search for the record "Kierra"
    Then the matching records should be displayed