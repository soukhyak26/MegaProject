@ignore
Feature: create business account for the financial year

Scenario: VOID SCENARIO

* print "businessAccountStartDateFormatted:" + businessAccountStartDateFormatted
* print "businessAccountEndDateFormatted" + businessAccountEndDateFormatted

Scenario:
Given url platformBusinessUrl
And path 'business/account'
And request read('classpath:domains/business/create/createrequest.json')
When method post
Then status 201

