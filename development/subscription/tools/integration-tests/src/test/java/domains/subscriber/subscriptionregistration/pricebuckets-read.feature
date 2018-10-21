@ignore
Feature: read pricebuckets from product domain and validate if their  new and total subscription count is updated or not

Scenario: read subscription view
Given url subscriberReadUrl
And path 'subscriber/subscription/' + id
And header Accept = 'application/json'
When method get
Then status 200

* def subscriptionItems = response.subscriptionItems
* def creator = read('classpath:domains/subscriber/subscriptionregistration/loop-subscription-items.feature')
* def result = call creator subscriptionItems
