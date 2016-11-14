//Using the NREL Alternative Fuel Station API to find nearby alternative fuel stations.
//See https://developer.nrel.gov/docs/transportation/alt-fuel-stations-v1/ for full documentation

(function() {
  var httpRequest;

  //click handler for the button
  document.getElementById("ajaxButton").onclick = function() {

    //get values from the input fields
    var city = document.getElementById("city").value;
    var state = document.getElementById("state").value;
    var apiKey = document.getElementById("apiKey").value;

    if(!apiKey){
      alert("missing api key!!");
    } else{
      makeRequest('https://api.data.gov/nrel/alt-fuel-stations/v1/nearest.json?api_key='+apiKey+'&location='+city+'+'+state);
    }
   };


  function makeRequest(url) {
    httpRequest = new XMLHttpRequest();

    if (!httpRequest) {
      alert('Giving up :( Cannot create an XMLHTTP instance');
      return false;
    }

    //Set text to loading... before request is made
    document.getElementById("fuelData").innerHTML="<p>loading...</p>";

    httpRequest.onreadystatechange = displayHTML;

    //make ajax request
    httpRequest.open('GET', url);
    httpRequest.send();
  }

  //handler when response data is received
  function displayHTML() {
    if (httpRequest.readyState === XMLHttpRequest.DONE) {
      //code is 200, nothing is wrong
      if (httpRequest.status === 200) {

        //Clear previous
        document.getElementById("fuelData").innerHTML="";

          //JSON.parse converts a JSON string to a JavaScript object
          var fuelObject = JSON.parse(httpRequest.responseText);
          var fuelStations = fuelObject.fuel_stations;
          fuelStations.forEach(function(currentFuelStation){
            document.getElementById("fuelData").innerHTML+="<p>"+currentFuelStation.id+" "+currentFuelStation.station_name+" "+currentFuelStation.city+"</p><hr>"
          });

      } else {
        alert('There was a problem with the request.');
      }
    }
  }
})();
