@ignore
Feature: Retrieve the subscriber Id given the name

Scenario: Retrieve subscriber details
Given url subscriberReadUrl
And path 'subscriber/name/' + 'Mandar' + '/' + 'Suresh' + '/' + 'Kulkarni' + '/' + 'Mr'
When method get
Then status 200

* def subscriberId = response.subscriberId

Scenario: Retrieve subscription Id given subscriber Id
Given url subscriberReadUrl
And path 'subscriber/subscription/active/subscriber/' + subscriberId
When method get
Then status 200

* def subscriptionId = response.subscriptionId

Scenario: Retrieve next approved delivery for this subscriber
Given url subscriberReadUrl
And path 'subscriber/deliveries/approved/next/subscriberid/' + subscriberId + "/subscriptionid/" + subscriptionId
When method get
Then status 200

* def deliveryId = response.deliveryId
* def deliveryDate = response.deliveryDate


Scenario: Change sys date to the date of next delivery, so as to trigger its dispatch
Given url platformSysDateUrl
And path 'sysdate'
And request read('classpath:domains/deliverydispatch/update-sysdate.json')
When method put
Then status 200

Scenario: Trigger Delivery Dispatch batch
Given url platformDeliveryDispatchUrl
And path 'batch/deliver/trigger'
And request {}
When method post
Then status 200

