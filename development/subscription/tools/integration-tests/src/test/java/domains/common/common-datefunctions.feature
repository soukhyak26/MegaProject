@ignore
Feature: Common Javascript functions
Background:
  * def unformattedStartOfYearString =
    """
    function(){
    var targetDate = new Date();
    var dd = 1;
    var mm = 1;
    var yyyy = targetDate.getFullYear();
    return "["+yyyy+","+ mm + "," + dd +"]" ;
    }
    """
  * def businessYear =
    """
    function(){
    var targetDate = new Date();
    var yyyy = targetDate.getFullYear();
    return yyyy;
    }
    """
  * def firstDayOfAMonth =
    """
    function(year,month){
    var targetDate = new Date(year,month,1);
    return targetDate;
    }
    """

  * def lastDayOfAMonth =
    """
    function(year,month){
    var targetDate = new Date(year,month+1,0);
    return targetDate;
    }
    """

  * def unformattedStartOfYearDate =
    """
    function(){
        var targetDate = new Date();
        var dd = 1;
        var mm = 1; // 0 is January, so we must add 1
        var yyyy = targetDate.getFullYear();
        var startOfYearDate = new Date(yyyy, (mm-1), dd);
        return startOfYearDate;
    }
    """
  * def unformattedEndOfYearString =
    """
    function(){
        var targetDate = new Date();
        var dd = 31;
        var mm = 12; // 0 is January, so we must add 1
        var yyyy = targetDate.getFullYear();
        return "["+yyyy+","+ mm + "," + dd +"]" ;
    }
    """
    * def unformattedEndOfYearDate =
    """
    function(){
        var targetDate = new Date();
        var dd = 31;
        var mm = 12;
        var yyyy = targetDate.getFullYear();
        var endOfYearDate = new Date(yyyy, (mm-1), dd);
        return endOfYearDate;
    }
    """
    * def anyDateAfterBusinessSetup =
    """
    function(startOfYearDate, noOfDaysAfter){
        var newDate = startOfYearDate.getDate() + noOfDaysAfter ;
        startOfYearDate.setDate(newDate);
        return startOfYearDate;
    }
    """
    * def unformattedDateString =
    """
    function(date){
          var dd = date.getDate();
          var mm = date.getMonth();
          var yyyy = date.getFullYear();
          return "["+yyyy+","+ (mm +1) + "," + dd +"]" ;
    }
    """

    * def formattedDateString =
    """
    function(date){
          var dd = date.getDate();
          var mm = date.getMonth();
          var yyyy = date.getFullYear();
          return yyyy + "-" + (mm +1) + "-" + dd ;
    }
    """

Scenario: This line is required please do not delete - or the functions cannot be called