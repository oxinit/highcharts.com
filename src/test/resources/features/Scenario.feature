Feature: Tests for highcharts.com

  Scenario Outline: User open home page and checks tooltips at graph has a text

    Given User open '<homePage>' page
    When User click button below graph with name Google search for highcharts
    And User click button next to it with name Revenue
    Then User check tooltips
    Examples:
      | homePage                                       |
      | https://www.highcharts.com/demo/combo-timeline |
