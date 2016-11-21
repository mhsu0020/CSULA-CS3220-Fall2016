//Using the EmployeeResource Servlet that we defined to return JSON objects
(function($) {

	var EMPLOYEES_RESOURCE = "http://localhost:8080/employee-project-service-barebone/employees";
	
	// Shorthand for $( document ).ready(), runs exactly once when page finishes loading
	$(function() {

	    //click handler for the button
	    document.getElementById("get-employees").onclick = function() {
	    	makeEmployeesRequest(EMPLOYEES_RESOURCE);
	    };
	    
	    //Call it once on page load
	    makeEmployeesRequest(EMPLOYEES_RESOURCE);
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
    function displayEmployeesTable(employeesObject) {
    	
    	var employeesArray = employeesObject.employees;
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

})(jQuery);