Feature: To turn on trading

  Scenario Outline: Turn on trading for all users
    Given I navigate to site
    When I enter username <username>
    And I enter password <password>
    And I press sign in
    And I wait for google authenticator to be completed
    Then I turn on trading
    And wait for the confirmation

    Examples:
    |username|password|
    |missatwo|0Htw3$A%qE0s|
    |missatwo|0Htw3$A%qE0s|
