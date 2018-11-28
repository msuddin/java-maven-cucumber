Feature: Withdraw Request

  Scenario Outline: Request to withdraw
    Given I navigate to site with user <username>
    When I enter username
    And I enter password
    And I allow user to complete captha
    And I press sign in
    And I wait for google authenticator to be completed
    Then I navigate to withdraw
    And I set withdraw request
    And I press request button
    And I press token button
    And I wait for token confirmation
    And I set financial password
    And I set google authenticator value
    And I open a new tab for email
    And I enter email username
    And I enter email password
    And I confirm I have logged in

    Examples:
    |username|
    |template|