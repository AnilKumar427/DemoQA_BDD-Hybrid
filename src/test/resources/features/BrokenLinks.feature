Feature: Broken Assets and Hyperlinks Validation

  Scenario: Validate image rendering and link HTTP status codes
    Given I navigate to the "broken" page
    Then the valid image should render successfully
    And the broken image should fail to render
    And the standard application link should return HTTP status 200
    And the broken redirect link should return HTTP status 500