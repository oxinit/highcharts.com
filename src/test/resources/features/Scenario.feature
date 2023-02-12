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
