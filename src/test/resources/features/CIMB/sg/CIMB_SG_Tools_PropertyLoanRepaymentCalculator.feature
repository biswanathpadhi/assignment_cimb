Feature: verify CIMB Singapore Tools

  Scenario Outline: Verify Property Loan Repayment Calculator
    Given I’m on CIMB "<countryName>" page
    And I navigate to Tools page from menu
    When I access to Property Loan Repayment Calculator from menu
    And I have inputted all necessary values
    Then I will be able to see the Effective Interest Rate, Total Interest Payable and Total Amount Payable
    And I will be able to see the loan repayment table with total loan tenure that I’ve entered
    And different interest rates for different year based on my input
    Examples:
      | countryName |
      | SG          |