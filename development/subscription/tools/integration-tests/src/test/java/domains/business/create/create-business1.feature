Feature: create business account for the financial year

Background:
* url demoBaseUrl

Scenario:

Given path 'business/account'
And request read('request.json')
When method post
Then status 200


* url 'http://localhost:5060/business'
* expectedResult = read('request.json')
Given path 'businessaccount'
    When method get
    Then status 200
    And match response[*].startDate contains '2018-01-01'
    And match response[*].endDate contains '2018-12-31'
    And match response[*].merchantId contains 'merchant1'
