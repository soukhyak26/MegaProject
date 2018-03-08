Feature: create business account for the financial year

Background:
* url demoBaseUrl

Scenario:

Given path 'business/account'
And request read('request.json')
When method post
Then status 200


