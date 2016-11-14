//Using the NREL Alternative Fuel Station API to find nearby alternative fuel stations.
//See https://developer.nrel.gov/docs/transportation/alt-fuel-stations-v1/ for full documentation
//Passing the JQuery global object to the function expression
(function($) {

    //click handler for the button
    document.getElementById("ajaxButton").onclick = function() {

      //get values from the input fields
      var city = document.getElementById("city").value;
      var state = document.getElementById("state").value;
      var apiKey = document.getElementById("apiKey").value;

      if (!apiKey) {
        alert("missing api key!!");
      } else {
        makeRequest('https://api.data.gov/nrel/alt-fuel-stations/v1/nearest.json?api_key=' + apiKey + '&location=' + city + '+' + state);
      }
    };

    //See JQuery getJSON doc: http://api.jquery.com/jquery.getjson/
    function makeRequest(url) {

      //Set text to loading... before request is made
      document.getElementById("fuelData").innerHTML = "<p>loading...</p>";

      //JQuery get JSON request. If you want to set headers, you must use the full ajax function via http://api.jquery.com/jquery.ajax/
      $.getJSON(url, function(data) {
        //JQuery parses the JSON string using JSON.parse for you.
        console.log(data);
        
        displayFuelHTML(data);
      });
    }

    //handler when response data is received
    function displayFuelHTML(fuelObject) {
      //Clear previous
      document.getElementById("fuelData").innerHTML = "";
      var fuelStations = fuelObject.fuel_stations;
      fuelStations.forEach(function(currentFuelStation) {
        document.getElementById("fuelData").innerHTML += "<p>" + currentFuelStation.id + " " + currentFuelStation.station_name + " " + currentFuelStation.city + "</p><hr>"
      });

    }

})(jQuery);
