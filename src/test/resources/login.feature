Feature: Login to account

  Scenario Outline: Login in Trade Coin Club and Email in two tabs
    Given I navigate to site with user <username>
    When I enter username
    And I enter password
    And I allow user to complete captha
    And I press sign in
    And I wait for google authenticator to be completed
    Then I open a new tab for email
    And I enter email username
    And I enter email password
    And I confirm I have logged in

    Examples:
    |username|
    |template|