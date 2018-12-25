@ignore
Feature: introduce sleep time

Background:
* def afterScenario = function(){ karate.log('sleeping ..'); java.lang.Thread.sleep(1500); }

Scenario:
#Introduce delay
* call afterScenario
