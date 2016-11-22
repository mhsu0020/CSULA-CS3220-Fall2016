package edu.calstatela;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//mapping to tell spring boot that this class is a REST Controller
@RestController
public class CountryController {

	// property is injected, we do not create this object ourselves
	@Autowired
	DatabaseBean databaseBean;

	//return all countries
	@RequestMapping("/countries")
	public List<Country> getCountries() {
		return databaseBean.getCountries();
	}
	
}
