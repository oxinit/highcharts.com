Feature: Add new item to ToDO list

  Scenario Outline: Lambdatest ToDO Scenario

    Given User opens '<homePage>' page
    When User clicking button below graph with name Google search for highcharts
    Then User checks query generated '<Search text>'
    Examples:
      | homePage               | Search text               |
      | https://www.highcharts.com/demo/combo-timeline | false |