Feature: Test banking system statements

  @Smoke
  Scenario Outline: user search non existing account id
    Given user goes to website "http://localhost:9999/statements"
    When user inputs <account_id> on account id field
    When user clicks search button
    Then no statements should be displayed

    Examples: 
      | account_id |
      | pj         |

  @Smoke
  Scenario Outline: test search by account id
    Given user goes to website "http://localhost:9999/statements"
    When user inputs <account_id> on account id field
    And user clicks search button
    Then only <account_id> and no <other_account> statements should be displayed

    Examples: 
      | account_id | other_account |
      | paul       | jarett        |
      | jarett     | paul          |

  @Smoke
  Scenario Outline: test From Date is later than To Date
    Given user goes to website "http://localhost:9999/statements"
    When user inputs <account_id> on account id field
    When user inputs "2020-12-16 00:00:00" on from date
    And user inputs "2020-12-15 00:00:00" on to date
    And user clicks search button
    Then no statements should be displayed

    Examples: 
      | account_id |
      | paul       |
      | jarett     |

  @Smoke
  Scenario Outline: test for statements date coverage
    Given user goes to website "http://localhost:9999/statements"
    When user inputs <account_id> on account id field
    When user inputs "2020-12-20 00:00:00" on from date
    And user inputs "2020-12-22 23:55:55" on to date
    And user clicks search button
    Then statement for other dates$ should not be displayed
      | 2020-12-19 00:00:00 |
      | 2020-12-23 00:00:00 |

    Examples: 
      | account_id |
      | jarett     |
      | paul       |

  @Smoke
  Scenario: test for checking before balance all accounts
    Given user goes to website "http://localhost:9999/statements"
    Then before balance should be "60.00"

  @Smoke
  Scenario Outline: test for checking before balance for a specific account
    Given user goes to website "http://localhost:9999/statements"
    When user inputs <account_id> on account id field
    And user clicks search button
    Then correct before balance "<before_balance>" should be displayed

    Examples: 
      | account_id | before_balance |
      | paul       |          20.00 |
      | jarett     |          30.00 |

  @Smoke
  Scenario: test for checking balance after for all accounts
    Given user goes to website "http://localhost:9999/statements"
    Then balance after should be "70.00"

  @Smoke
  Scenario Outline: test for checking after balance for a specific account
    Given user goes to website "http://localhost:9999/statements"
    When user inputs <account_id> on account id field
    And user clicks search button
    Then correct balance after "<after_balance>" should be displayed

    Examples: 
      | account_id | after_balance |
      | paul       |         30.00 |
      | jarett     |         40.00 |
