@ignore
Feature: introduce sleep time

Background:
* def afterScenario = function(){ karate.log('sleeping ..'); java.lang.Thread.sleep(2000); }

Scenario:
#Introduce delay
* call afterScenario
