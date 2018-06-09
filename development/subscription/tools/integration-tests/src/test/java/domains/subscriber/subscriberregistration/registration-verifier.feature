@ignore
Feature: Subscriber creation and verification

Scenario: Subscriber creation
* def subscriberRegistrationResult = call read('classpath:domains/subscriber/subscriberregistration/registration-create.feature')

Scenario: Subscriber password setting
* call read('classpath:domains/subscriber/subscriberregistration/subscriber-password-set.feature') subscriberRegistrationResult.response

Scenario: Subscriber validation
* call read('classpath:domains/subscriber/subscriberregistration/registration-read.feature') subscriberRegistrationResult.response
