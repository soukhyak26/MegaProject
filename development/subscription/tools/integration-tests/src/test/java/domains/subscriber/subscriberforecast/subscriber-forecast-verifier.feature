@ignore
Feature: create product  forecast for the financial year and verify if it  correctly created

Scenario: create product
* call read('classpath:domains/subscriber/subscriberforecast/subscriber-forecast-create.feature')

Scenario: verify product creation
* call read('classpath:domains/subscriber/subscriberforecast/subscriber-forecast-read.feature')
