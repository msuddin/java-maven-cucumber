Feature: To turn on trading

  Scenario Outline: Turn on trading for all users
    Given I navigate to site with user <username>
    And I enter username
    And I enter password
    And I press sign in
    And I wait for google authenticator to be completed
    When I set trading to "Medium risk"
    And wait for trading confirmation
    And I set trading to "High risk"
    And wait for trading confirmation
    And I log out
    And close the browser
    Then I navigate to site with user <username>
    And I enter username
    And I enter password
    And I press sign in
    And I wait for google authenticator to be completed
    And I check that trading is set to "High risk"
    And I log out
    And close the browser

    Examples:
    |username|
    |template|