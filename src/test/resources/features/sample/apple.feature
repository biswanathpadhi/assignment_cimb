Feature: Verify apple feature
  Background:
    Given I am on apple page

  Scenario: Verify apple i12 purchase
    When i select i12
    And i liked it
    Then i should be able to buy it