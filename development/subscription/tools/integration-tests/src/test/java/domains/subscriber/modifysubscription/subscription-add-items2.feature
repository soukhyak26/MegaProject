@ignore
Feature: add items to a subscription after first delivery

Scenario: Retrieve subscriber
Given url subscriberReadUrl
And path 'subscriber/name/' + 'Mandar' + '/' + 'Suresh' + '/' + 'Kulkarni' + '/' + 'Mr'
When method get
Then status 200

* def subscriberId = response.subscriberId

Scenario: Retrieve Product
Given url productReadUrl
And path 'product/name/' + 'Lux 200 gms'
When method get
Then status 200
* def productId2 = response[0].productId

Scenario: Retrieve latest offer price of the Product
Given url productReadUrl
And path 'product/pricebuckets/latest/' + productId2
When method get
Then status 200
* def priceBucketId = response.productwisePriceBucketId.priceBucketId
* def offeredPrice = response.offeredPriceOrPercentDiscountPerUnit

Scenario: Retrieve subscription Id given subscriber Id
Given url subscriberReadUrl
And path 'subscriber/subscription/active/subscriber/' + subscriberId
When method get
Then status 200

* def subscriptionId = response.subscriptionId

Scenario: Retrieve delivery date of latest delivered delivery
Given url subscriberReadUrl
And path 'subscriber/deliveries/delivered/latest/' + subscriberId +'/' + subscriptionId  + '/3'
When method get
Then status 200

* def deliveryId = response.deliveryId
* def deliveryDate = response.deliveryDate
* print deliveryDate


Scenario: Change sys date to the date after first delivery,to simulate subscription modification post first delivery
Given url platformSysDateUrl
And path 'sysdate/reset/next/days'
And request read('classpath:domains/subscriber/modifysubscription/update-sysdate2.json')
When method put
Then status 200

Scenario: read the latest current date
Given url sysDateReadUrl
And path 'sysdate/date'
When method get
Then status 200
* def currentDate = response.currentDate

Scenario: Retrieve undelivered  deliveries of a subscription
Given url subscriberReadUrl
And path 'subscriber/deliveries/undelivered/' + subscriberId +'/' + subscriptionId  + '/3'
When method get
Then status 200

* def deliveries = response.deliveryIds



Scenario: Loop through each undelivered delivery and add a new product to each

Given url platformSubscriberUrl
And path 'delivery/update/' + subscriberId + '/' + subscriptionId + '/' + response[0].deliveryId.deliveryId
And header Accept = 'application/json'
And request read('classpath:domains/subscriber/modifysubscription/add-item-subscription2.json')
When method put
Then status 200

Scenario: Loop through each undelivered delivery and add a new product to each

Given url platformSubscriberUrl
And path 'delivery/update/' + subscriberId + '/' + subscriptionId + '/' + response[1].deliveryId.deliveryId
And header Accept = 'application/json'
And request read('classpath:domains/subscriber/modifysubscription/add-item-subscription2.json')
When method put
Then status 200

Scenario: Loop through each undelivered delivery and add a new product to each

Given url platformSubscriberUrl
And path 'delivery/update/' + subscriberId + '/' + subscriptionId + '/' + response[2].deliveryId.deliveryId
And header Accept = 'application/json'
And request read('classpath:domains/subscriber/modifysubscription/add-item-subscription2.json')
When method put
Then status 200

Scenario: Loop through each undelivered delivery and add a new product to each

Given url platformSubscriberUrl
And path 'delivery/update/' + subscriberId + '/' + subscriptionId + '/' + response[3].deliveryId.deliveryId
And header Accept = 'application/json'
And request read('classpath:domains/subscriber/modifysubscription/add-item-subscription2.json')
When method put
Then status 200

Scenario: Loop through each undelivered delivery and add a new product to each

Given url platformSubscriberUrl
And path 'delivery/update/' + subscriberId + '/' + subscriptionId + '/' + response[4].deliveryId.deliveryId
And header Accept = 'application/json'
And request read('classpath:domains/subscriber/modifysubscription/add-item-subscription2.json')
When method put
Then status 200

Scenario: Loop through each undelivered delivery and add a new product to each

Given url platformSubscriberUrl
And path 'delivery/update/' + subscriberId + '/' + subscriptionId + '/' + response[5].deliveryId.deliveryId
And header Accept = 'application/json'
And request read('classpath:domains/subscriber/modifysubscription/add-item-subscription2.json')
When method put
Then status 200

Scenario: Loop through each undelivered delivery and add a new product to each

Given url platformSubscriberUrl
And path 'delivery/update/' + subscriberId + '/' + subscriptionId + '/' + response[6].deliveryId.deliveryId
And header Accept = 'application/json'
And request read('classpath:domains/subscriber/modifysubscription/add-item-subscription2.json')
When method put
Then status 200

Scenario: Loop through each undelivered delivery and add a new product to each

Given url platformSubscriberUrl
And path 'delivery/update/' + subscriberId + '/' + subscriptionId + '/' + response[7].deliveryId.deliveryId
And header Accept = 'application/json'
And request read('classpath:domains/subscriber/modifysubscription/add-item-subscription2.json')
When method put
Then status 200

Scenario: Loop through each undelivered delivery and add a new product to each

Given url platformSubscriberUrl
And path 'delivery/update/' + subscriberId + '/' + subscriptionId + '/' + response[8].deliveryId.deliveryId
And header Accept = 'application/json'
And request read('classpath:domains/subscriber/modifysubscription/add-item-subscription2.json')
When method put
Then status 200

Scenario: Loop through each undelivered delivery and add a new product to each

Given url platformSubscriberUrl
And path 'delivery/update/' + subscriberId + '/' + subscriptionId + '/' + response[9].deliveryId.deliveryId
And header Accept = 'application/json'
And request read('classpath:domains/subscriber/modifysubscription/add-item-subscription2.json')
When method put
Then status 200

Scenario: Loop through each undelivered delivery and add a new product to each

Given url platformSubscriberUrl
And path 'delivery/update/' + subscriberId + '/' + subscriptionId + '/' + response[10].deliveryId.deliveryId
And header Accept = 'application/json'
And request read('classpath:domains/subscriber/modifysubscription/add-item-subscription2.json')
When method put
Then status 200
