Feature: verify CIMB Malaysia Deals

  Background:
    Given I’m on CIMB "MY" page

  @deals @my
  Scenario: Verify Travel & Fun Deals
    When I select CIMB Deals
    And I would like to explore more for "Expedia" under "Travel & Fun" section
    Then I will be able to see its details and other similar deals