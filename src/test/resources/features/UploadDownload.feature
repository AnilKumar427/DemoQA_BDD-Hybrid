Feature: File Transfer Modules

  Scenario: Download and upload a sample file
    Given I navigate to the "upload-download" page
    When I trigger the file download
    And I upload a sample file named "frameworkSample.txt"
    Then the upload confirmation should contain "frameworkSample.txt"