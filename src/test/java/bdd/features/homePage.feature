Feature: Search Weather Feature

  Scenario: Search valid city and select Enter on keyboard to search
    Given User is on homepage
    When User input 'ho chi minh city' into search box
    And User press enter to search
    Then Search page is displayed
#    And Search form is displayed with entered city
#    And find page header as Weather in your city is displayed
#    And forecast list is displayed

  Scenario: Search invalid city and select Enter on keyboard to search
    Given user is on homepage
    When user input '@' into search box
    And user press enter to search
    Then find page is displayed with correct page title
    And find page header as Weather in your city is displayed
    And search form is displayed with the previous city entered
    And forecast list is NOT displayed