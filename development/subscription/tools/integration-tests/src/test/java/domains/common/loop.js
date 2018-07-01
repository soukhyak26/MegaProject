var json_array;
function useTheData(featureFile) {
    for (var i = 0; i < json_array.length; i++) {
       var res2 = karate.call(featureFile, json_array[i]);
    }
}
function(jsonfile, featurefile){
  $.getJSON(jsonfile, function (json) {
    json_array=json.data;
    useData(featureFile);
  });
}
