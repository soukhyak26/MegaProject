@ignore
Feature: Caller of common date functions to define all required dates in the lifecycle
Background:
* def datefun = call read('classpath:domains/common/common-datefunctions.feature')

* def businessAccountStartDate = datefun.unformattedStartOfYearString()
* print "businessAccountStartDate: " + businessAccountStartDate

* def businessAccountEndDate = datefun.unformattedEndOfYearString()
* print "businessAccountEndDate: " + businessAccountEndDate

* def businessAccountProvisionDate = businessAccountStartDate
* print "businessAccountProvisionDate: " + businessAccountStartDate

* def businessYear = datefun.businessYear()
* print "business year: " + businessYear

* def objFirstMonthDate = datefun.firstDayOfAMonth(businessYear,0)
* def firstMonthForecastStartDate = datefun.unformattedDateString(objFirstMonthDate)
* print "firstMonthForecastStartDate: " + firstMonthForecastStartDate

* def firstMonthForecastStartDateFormatted = datefun.formattedDateString(objFirstMonthDate)
* print "firstMonthForecastStartDateFormatted: " + firstMonthForecastStartDateFormatted

* def firstMonthForecastEndDate = datefun.unformattedDateString(datefun.lastDayOfAMonth(businessYear,0))
* print "firstMonthForecastEndDate: " + firstMonthForecastEndDate

* def firstMonthForecastEndDateFormatted = datefun.formattedDateString(datefun.lastDayOfAMonth(businessYear,0))
* print "firstMonthForecastEndDateFormatted: " + firstMonthForecastEndDateFormatted

* def secondMonthForecastStartDate = datefun.unformattedDateString(datefun.firstDayOfAMonth(businessYear,1))
* print "secondMonthForecastStartDate: " + secondMonthForecastStartDate

* def secondMonthForecastStartDateFormatted = datefun.formattedDateString(datefun.firstDayOfAMonth(businessYear,1))
* print "secondMonthForecastStartDateFormatted: " + secondMonthForecastStartDateFormatted

* def secondMonthForecastEndDate = datefun.unformattedDateString(datefun.lastDayOfAMonth(businessYear,1))
* print "secondMonthForecastEndDate: " + secondMonthForecastEndDate

* def secondMonthForecastEndDateFormatted = datefun.formattedDateString(datefun.lastDayOfAMonth(businessYear,1))
* print "secondMonthForecastEndDateFormatted: " + secondMonthForecastEndDateFormatted

* def thirdMonthForecastStartDate = datefun.unformattedDateString(datefun.firstDayOfAMonth(businessYear,2))
* print "thirdMonthForecastStartDate: " + thirdMonthForecastStartDate

* def thirdMonthForecastStartDateFormatted = datefun.formattedDateString(datefun.firstDayOfAMonth(businessYear,2))
* print "thirdMonthForecastStartDateFormatted: " + thirdMonthForecastStartDateFormatted

* def thirdMonthForecastEndDate = datefun.unformattedDateString(datefun.lastDayOfAMonth(businessYear,2))
* print "thirdMonthForecastEndDate: " + thirdMonthForecastEndDate

* def thirdMonthForecastEndDateFormatted = datefun.formattedDateString(datefun.lastDayOfAMonth(businessYear,2))
* print "thirdMonthForecastEndDateFormatted: " + thirdMonthForecastEndDateFormatted

* def fourthMonthForecastStartDate = datefun.unformattedDateString(datefun.firstDayOfAMonth(businessYear,3))
* print "fourthMonthForecastStartDate: " + fourthMonthForecastStartDate

* def fourthMonthForecastStartDateFormatted = datefun.formattedDateString(datefun.firstDayOfAMonth(businessYear,3))
* print "fourthMonthForecastStartDate: " + fourthMonthForecastStartDate

* def fourthMonthForecastEndDate = datefun.unformattedDateString(datefun.lastDayOfAMonth(businessYear,3))
* print "fourthMonthForecastEndDate: " + fourthMonthForecastEndDate

* def fourthMonthForecastEndDateFormatted = datefun.formattedDateString(datefun.lastDayOfAMonth(businessYear,3))
* print "fourthMonthForecastEndDateFormatted: " + fourthMonthForecastEndDateFormatted

* def fifthMonthForecastStartDate = datefun.unformattedDateString(datefun.firstDayOfAMonth(businessYear,4))
* print "fifthMonthForecastStartDate: " + fifthMonthForecastStartDate

* def fifthMonthForecastStartDateFormatted = datefun.formattedDateString(datefun.firstDayOfAMonth(businessYear,4))
* print "fifthMonthForecastStartDateFormatted: " + fifthMonthForecastStartDateFormatted

* def fifthMonthForecastEndDate = datefun.unformattedDateString(datefun.lastDayOfAMonth(businessYear,5))
* print "fifthMonthForecastEndDate: " + fifthMonthForecastEndDate

* def fifthMonthForecastEndDateFormatted = datefun.formattedDateString(datefun.lastDayOfAMonth(businessYear,5))
* print "fifthMonthForecastEndDateFormatted: " + fifthMonthForecastEndDateFormatted

* def sixthMonthForecastStartDate = datefun.unformattedDateString(datefun.firstDayOfAMonth(businessYear,5))
* print "sixthMonthForecastStartDate: " + sixthMonthForecastStartDate

* def sixthMonthForecastStartDateFormatted = datefun.formattedDateString(datefun.firstDayOfAMonth(businessYear,5))
* print "sixthMonthForecastStartDateFormatted: " + sixthMonthForecastStartDateFormatted

* def sixthMonthForecastEndDate = datefun.unformattedDateString(datefun.lastDayOfAMonth(businessYear,5))
* print "sixthMonthForecastEndDate: " + sixthMonthForecastEndDate

* def sixthMonthForecastEndDateFormatted = datefun.formattedDateString(datefun.lastDayOfAMonth(businessYear,5))
* print "sixthMonthForecastEndDateFormatted: " + sixthMonthForecastEndDateFormatted

* def seventhMonthForecastStartDate = datefun.unformattedDateString(datefun.firstDayOfAMonth(businessYear,6))
* print "seventhMonthForecastStartDate: " + seventhMonthForecastStartDate

* def seventhMonthForecastStartDateFormatted = datefun.formattedDateString(datefun.firstDayOfAMonth(businessYear,6))
* print "seventhMonthForecastStartDateFormatted: " + seventhMonthForecastStartDateFormatted

* def seventhMonthForecastEndDate = datefun.unformattedDateString(datefun.lastDayOfAMonth(businessYear,6))
* print "seventhMonthForecastEndDate: " + seventhMonthForecastEndDate

* def seventhMonthForecastEndDateFormatted = datefun.formattedDateString(datefun.lastDayOfAMonth(businessYear,6))
* print "seventhMonthForecastEndDateFormatted: " + seventhMonthForecastEndDateFormatted

* def eighthMonthForecastStartDate = datefun.unformattedDateString(datefun.firstDayOfAMonth(businessYear,7))
* print "eighthMonthForecastStartDate: " + eighthMonthForecastStartDate

* def eighthMonthForecastStartDateFormatted = datefun.formattedDateString(datefun.firstDayOfAMonth(businessYear,7))
* print "eighthMonthForecastStartDateFormatted: " + eighthMonthForecastStartDateFormatted

* def eighthMonthForecastEndDate = datefun.unformattedDateString(datefun.lastDayOfAMonth(businessYear,7))
* print "eighthMonthForecastEndDate: " + eighthMonthForecastEndDate

* def eighthMonthForecastEndDateFormatted = datefun.formattedDateString(datefun.lastDayOfAMonth(businessYear,7))
* print "eighthMonthForecastEndDateFormatted: " + eighthMonthForecastEndDateFormatted

* def ninethMonthForecastStartDate = datefun.unformattedDateString(datefun.firstDayOfAMonth(businessYear,8))
* print "ninethMonthForecastStartDate: " + ninethMonthForecastStartDate

* def ninethMonthForecastStartDateFormatted = datefun.formattedDateString(datefun.firstDayOfAMonth(businessYear,8))
* print "ninethMonthForecastStartDateFormatted: " + ninethMonthForecastStartDateFormatted

* def ninethMonthForecastEndDate = datefun.unformattedDateString(datefun.lastDayOfAMonth(businessYear,8))
* print "ninethMonthForecastEndDate: " + ninethMonthForecastEndDate

* def ninethMonthForecastEndDateFormatted = datefun.formattedDateString(datefun.lastDayOfAMonth(businessYear,8))
* print "ninethMonthForecastEndDateFormatted: " + ninethMonthForecastEndDateFormatted

* def tenthMonthForecastStartDate = datefun.unformattedDateString(datefun.firstDayOfAMonth(businessYear,9))
* print "tenthMonthForecastStartDate: " + tenthMonthForecastStartDate

* def tenthMonthForecastStartDateFormatted = datefun.formattedDateString(datefun.firstDayOfAMonth(businessYear,9))
* print "tenthMonthForecastStartDateFormatted: " + tenthMonthForecastStartDateFormatted

* def tenthMonthForecastEndDate = datefun.unformattedDateString(datefun.lastDayOfAMonth(businessYear,9))
* print "tenthMonthForecastEndDate: " + tenthMonthForecastEndDate

* def tenthMonthForecastEndDateFormatted = datefun.formattedDateString(datefun.lastDayOfAMonth(businessYear,9))
* print "tenthMonthForecastEndDateFormatted: " + tenthMonthForecastEndDateFormatted

* def eleventhMonthForecastStartDate = datefun.unformattedDateString(datefun.firstDayOfAMonth(businessYear,10))
* print "eleventhMonthForecastStartDate: " + eleventhMonthForecastStartDate

* def eleventhMonthForecastStartDateFormatted = datefun.formattedDateString(datefun.firstDayOfAMonth(businessYear,10))
* print "eleventhMonthForecastStartDateFormatted: " + eleventhMonthForecastStartDateFormatted

* def eleventhMonthForecastEndDate = datefun.unformattedDateString(datefun.lastDayOfAMonth(businessYear,10))
* print "eleventhMonthForecastEndDate: " + eleventhMonthForecastEndDate

* def eleventhMonthForecastEndDateFormatted = datefun.formattedDateString(datefun.lastDayOfAMonth(businessYear,10))
* print "eleventhMonthForecastEndDateFormatted: " + eleventhMonthForecastEndDateFormatted

* def twelvethMonthForecastStartDate = datefun.unformattedDateString(datefun.firstDayOfAMonth(businessYear,11))
* print "twelvethMonthForecastStartDate: " + twelvethMonthForecastStartDate

* def twelvethMonthForecastStartDateFormatted = datefun.formattedDateString(datefun.firstDayOfAMonth(businessYear,11))
* print "twelvethMonthForecastStartDateFormatted: " + twelvethMonthForecastStartDateFormatted

* def twelvethMonthForecastEndDate = datefun.unformattedDateString(datefun.lastDayOfAMonth(businessYear,11))
* print "twelvethMonthForecastEndDate: " + twelvethMonthForecastEndDate

* def twelvethMonthForecastEndDateFormatted = datefun.formattedDateString(datefun.lastDayOfAMonth(businessYear,11))
* print "twelvethMonthForecastEndDateFormatted: " + twelvethMonthForecastEndDateFormatted

* def firstPaymentDate = datefun.unformattedDateString(datefun.firstDayOfAMonth(businessYear,0))
* print "firstPaymentDate: " + firstPaymentDate

* def secondPaymentDate = datefun.unformattedDateString(datefun.firstDayOfAMonth(businessYear,2))
* print "secondPaymentDate: " + secondPaymentDate

* def thirdPaymentDate = datefun.unformattedDateString(datefun.firstDayOfAMonth(businessYear,4))
* print "thirdPaymentDate: " + thirdPaymentDate


Scenario: This line is required please do not delete - or the functions cannot be called