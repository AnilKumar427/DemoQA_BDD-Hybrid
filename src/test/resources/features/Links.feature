Feature: Window Navigation and Mock API Links

  Scenario: Follow simple and API links
    Given I navigate to the "links" page
    When I follow the simple link
    Then a new tab should open with a valid title
    When I click the "created" API link
    Then the API response panel should contain "201"