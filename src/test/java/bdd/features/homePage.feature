Feature: Search Weather Feature

  Scenario Outline: Search with valid city
    Given User is on homepage
    When User input '<CityName>' into search box
    And User press enter to search
    Then Search page is displayed
    And Search form is displayed with entered city
      | CityName   |
      | <CityName> |
    And Weather information is displayed
      | CityNameAndCountry      | Geo         |
      | <CityNameAndCountry> |  <Geo>    |

    Examples: Valid City
      | CityName      | CityNameAndCountry         | Geo |
      | ho chi minh   |  Thanh pho Ho Chi Minh, VN |  Geo coords [10.75, 106.6667]|
      | Osaka, JP   |  Osaka, JP |  Geo coords [34.6937, 135.5022]|


  Scenario Outline: Search with invalid city
    Given User is on homepage
    When User input '<CityName>' into search box
    And User press enter to search
    Then Search page is displayed
    And Search form is displayed with entered city
      | CityName   |
      | <CityName> |
    And Search result is not found

    Examples: Valid City
      | CityName      |
      | Osaka, JP     |