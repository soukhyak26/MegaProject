@ignore
Feature: add contact details

Scenario: Retrieve subscriber
Given url subscriberReadUrl
And path 'subscriber/name/' + 'Mandar' + '/' + 'Suresh' + '/' + 'Kulkarni' + '/' + 'Mr'
When method get
Then status 200

* def subscriberId = response.subscriberId
Scenario: Use the subscriber Id retrieved and add contact details
Given url platformSubscriberUrl
And path 'subscription/addcontactdetails/' + subscriberId
And header Accept = 'application/json'
And request read('classpath:domains/subscriber/subscriptionregistration/add-contact-details.json')
When method put
Then status 200
