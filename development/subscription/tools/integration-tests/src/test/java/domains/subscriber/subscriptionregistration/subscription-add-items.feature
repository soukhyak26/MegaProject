@ignore
Feature: add items to a subscription

Scenario: Retrieve subscriber
Given url subscriberReadUrl
And path 'subscriber/name/' + 'Mandar' + '/' + 'Suresh' + '/' + 'Kulkarni' + '/' + 'Mr'
When method get
Then status 200

* def subscriberId = response.subscriberId

Scenario: Retrieve Product
Given url productReadUrl
And path 'product/name/' + 'Colgate 300 gms'
When method get
Then status 200
* def productId = response.productId

Scenario: add items to registered subscription
Given url platformSubscriberUrl
And path 'subscription/additem/' + subscriberId
And request read('classpath:domains/subscriber/subscriptionregistration/add-item-subscription.json')
When method put
Then status 200
