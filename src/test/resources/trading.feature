Feature: To turn on trading

  Scenario Outline: Turn on trading for all users
    Given I navigate to site with user <username>
    When I enter username
    And I enter password
    And I press sign in
    And I wait for google authenticator to be completed
    #Then I set trading to "some risk"
    #And wait for trading confirmation
    And I log out

    Examples:
      |username|
      |template|