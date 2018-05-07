@ignore
Feature: create subscription forecast for the financial year and verify if it  correctly created

Scenario: create subscription forecast
* call read('classpath:domains/subscriber/subscriptionforecast/subscription-forecast-create.feature')

Scenario: verify subscription forecast creation
* call read('classpath:domains/subscriber/subscriptionforecast/subscription-forecast-read.feature')
