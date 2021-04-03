Feature: Verify CIMB Singapore Tools

  @tools @sg
  Scenario Outline: Verify Property Loan Repayment Calculator
    Given I’m on CIMB Singapore "<countryName>" page
    And I navigate to Tools page from menu
    When I access to Property Loan Repayment Calculator from menu
    And I have inputted "<propertyLoanAmount>", "<propertyLoanTenure>", "<year1Interest>", "<year2Interest>", "<year3Interest>", "<year4Interest>", "<year5Interest>" values
    Then I will be able to see the Effective Interest Rate, Total Interest Payable and Total Amount Payable
    And I will be able to see the loan repayment table with "<propertyLoanTenure>" that I’ve entered
    And different interest rates for different year based on my input
    Examples:
      | countryName | propertyLoanAmount | propertyLoanTenure | year1Interest | year2Interest | year3Interest | year4Interest | year5Interest |
      | SG          | 10000              | 21                 | 1.50          | 2.50          | 3.50          | 4.50          | 5.50          |