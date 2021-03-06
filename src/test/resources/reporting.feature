Feature: To capture different types of data

  Scenario Outline: Report on commission wallet
    Given I have deleted any previous reports
    And I navigate to site with user <username>
    When I enter username
    And I enter password
    And I allow user to complete captha
    And I press sign in
    And I wait for google authenticator to be completed
    Then I capture details
    And I log out
    And close the browser

    Examples:
    |username|
    |template|
