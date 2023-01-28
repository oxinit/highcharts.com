Feature: Just open home page

  Scenario Outline: User opens home page and checks does site address correct

    Given User opens '<homePage>' page
    When User clicking button below graph with name Google search for highcharts
    And User clicking button to it with name Revenue
    Then User checks tooltip '<text>'
    Examples:
      | homePage                                       | text  |
      | https://www.highcharts.com/demo/combo-timeline | Anita |