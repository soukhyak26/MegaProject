@ignore
Feature: create subscriber forecast for the financial year and verify if it  correctly created

Scenario: create subscriber forecast
* call read('classpath:domains/subscriber/subscriberforecast/subscriber-forecast-create.feature')

Scenario: verify subscriber forecast creation
* call read('classpath:domains/subscriber/subscriberforecast/subscriber-forecast-read.feature')
