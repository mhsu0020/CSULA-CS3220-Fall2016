//Using the EmployeeResource Servlet that we defined to return JSON objects
(function($) {

	//relative path
	var EMPLOYEES_RESOURCE = "employees";
	
	// Shorthand for $( document ).ready(), runs exactly once when page finishes loading
	$(function() {

	    //click handler for the button
	    document.getElementById("get-employees").onclick = function() {
	    	makeEmployeesRequest(EMPLOYEES_RESOURCE);
	    };
	    
	    //Call it once on page load
	    makeEmployeesRequest(EMPLOYEES_RESOURCE);
	    
	    initializeAddEmployeeForm();
	    
	    document.getElementById("add-employee-button").onclick = function() {
	    	
	    	var employeeObject = {};
	    	
	    	employeeObject.firstName = document.getElementById("firstName").value;
	    	employeeObject.lastName = document.getElementById("lastName").value;
	    	employeeObject.address = document.getElementById("address").value;
	    	employeeObject.country = {};
	    	
	    	var countrySelect = document.getElementById("country");
	    	var selectedCountry = countrySelect.options[countrySelect.selectedIndex];

	    	employeeObject.country.id = parseInt(selectedCountry.value, 10);
	    	employeeObject.country.name = selectedCountry.text;
	    	
	    	console.log(employeeObject);
	    	
	    	//posts to AddEmployeeResource doPost method
	    	$.ajax({
	    	    type: "POST",
	    	    url: "employee",
	    	    data: JSON.stringify(employeeObject),
	    	    contentType: "application/json; charset=utf-8",
	    	    dataType: "json",
	    	    success: function(data){
	    	    	 console.log(data);
		    		 makeEmployeesRequest(EMPLOYEES_RESOURCE);
	    	    },
	    	    failure: function(errMsg) {
	    	        alert(errMsg);
	    	    }
	    	});
	    	initializeAddEmployeeForm();
	    };
	});
	
	

    //See JQuery getJSON doc: http://api.jquery.com/jquery.getjson/
    function makeEmployeesRequest(url) {

      $.getJSON(url, function(data) {
        //JQuery parses the JSON string using JSON.parse for you.
        console.log(data);
        
        displayEmployeesTable(data);
      });
    }

    //handler when response data is received
    function displayEmployeesTable(employeesArray) {
    	
    	var employeesTableBody = document.getElementById("employees-table-body");
    	//clear table body
    	employeesTableBody.innerHTML="";
    	
    	//Create table row and append to body
    	employeesArray.forEach(function(employeeObject){
	       var tr = document.createElement('tr');
           tr.appendChild( document.createElement('td') );
           tr.appendChild( document.createElement('td') );
           tr.appendChild( document.createElement('td') );
           tr.appendChild( document.createElement('td') );

           tr.cells[0].appendChild( document.createTextNode(employeeObject.id) )
           tr.cells[1].appendChild( document.createTextNode(employeeObject.firstName+" "+employeeObject.lastName) );
           tr.cells[2].appendChild( document.createTextNode(employeeObject.address));    		
           tr.cells[3].appendChild( document.createTextNode(employeeObject.country.name));    		
           employeesTableBody.appendChild(tr);
    	});

    }
    
    //Retrieve list of countries and clears form
    function initializeAddEmployeeForm(){
       
    	//reset form
       document.getElementById("add-employee").reset();
	   $.getJSON("countries", function(countries) {
		   	var countrySelect = document.getElementById("country");
		   	countrySelect.innerHTML = "";
		   	countries.forEach(function(country){
			   	var option = document.createElement('option');
			   	option.value = country.id;
			   	option.innerHTML = country.name;
			   	countrySelect.appendChild(option);
		   	});

	      });
	
    }

})(jQuery);