Feature: Tests for highcharts.com
  As a User
  I want navigate highcharts graphs
  and look at information it provides

  Scenario Outline: User open home page and checks tooltips at graph has a text

    Given User open '<homePage>' page
    When User click button below graph with name Google search for highcharts
    And User click button with name Revenue
    Then User check tooltips
    Examples:
      | homePage                                       |
      | https://www.highcharts.com/demo/combo-timeline |

  Scenario Outline: User open home page downloading CSV file for chosen graph and chek its content

    Given User open '<homePage>' page
    When User click button below graph with name Google search for highcharts
    And User click button with name Revenue
    And User click on graph menu button
    And User click download as csv file
    Then User check does downloaded csv has expected values
    Examples:
      | homePage                                       |
      | https://www.highcharts.com/demo/combo-timeline |