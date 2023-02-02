Feature: Just open home page

  Scenario Outline: User opens home page and checks toltips at graph has a text

    Given User opens '<homePage>' page
    When User clicking button below graph with name Google search for highcharts
    And User clicking button next to it with name Revenue
    Then User checks tooltip
    Examples:
      | homePage                                       |
      | https://www.highcharts.com/demo/combo-timeline |
