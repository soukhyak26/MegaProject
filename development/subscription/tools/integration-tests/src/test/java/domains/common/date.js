function() {
  return {
    unformattedStartOfYearString: function(){
      var targetDate = new Date();
      var dd = '01';
      var mm = '01';
      var yyyy = targetDate.getFullYear();
      return "["+yyyy+","+ mm + "," + dd +"]" ;
    },
    formattedStartOfYearString : function(){
     var targetDate = new Date();
     var dd = '01';
     var mm = '01';
     var yyyy = targetDate.getFullYear();
     return yyyy + "-" + mm + "-" + dd ;
    },


    businessYear : function(){
    var targetDate = new Date();
    var yyyy = targetDate.getFullYear();
    return yyyy;
    },

    firstDayOfAMonth : function(year,month){
    var targetDate = new Date(year,month,1);
    return targetDate;
    },

    lastDayOfAMonth : function(year,month){
    var targetDate = new Date(year,month+1,0);
    return targetDate;
    },

    unformattedStartOfYearDate : function(){
        var targetDate = new Date();
        var dd = 1;
        var mm = 1; // 0 is January, so we must add 1
        var yyyy = targetDate.getFullYear();
        var startOfYearDate = new Date(yyyy, (mm-1), dd);
        return startOfYearDate;
    },

    unformattedEndOfYearString : function(){
        var targetDate = new Date();
        var dd = 31;
        var mm = 12; // 0 is January, so we must add 1
        var yyyy = targetDate.getFullYear();
        return "["+yyyy+","+ mm + "," + dd +"]" ;
    },

    formattedEndOfYearString : function(){
       var targetDate = new Date();
       var dd = 31;
       var mm = 12; // 0 is January, so we must add 1
       var yyyy = targetDate.getFullYear();
       return yyyy + "-" + mm + "-" + dd ;
   },

    unformattedEndOfYearDate: function(){
        var targetDate = new Date();
        var dd = 31;
        var mm = 12;
        var yyyy = targetDate.getFullYear();
        var endOfYearDate = new Date(yyyy, (mm-1), dd);
        return endOfYearDate;
    },


    anyDateAfterBusinessSetup: function(startOfYearDate, noOfDaysAfter){
        var newDate = startOfYearDate.getDate() + noOfDaysAfter ;
        startOfYearDate.setDate(newDate);
        return startOfYearDate;
    },

    unformattedDateString:  function(date){
          var dd = date.getDate();
          var mm = date.getMonth();
          var yyyy = date.getFullYear();
          return "["+yyyy+","+ (mm +1) + "," + dd +"]" ;
    },
    formattedDateString :  function(date){
          var dd = date.getDate();
          var mm = date.getMonth();
          var yyyy = date.getFullYear();
          var mmString = "" + (mm +1);
          if(mm <= 8){
            mmString = "0" + (mm +1) ;
          }
          var ddString = "" + dd;
          if(dd <= 9){
            ddString = "0" + dd;
          }
          return yyyy + "-" + mmString + "-" + ddString ;
    }
 }
}