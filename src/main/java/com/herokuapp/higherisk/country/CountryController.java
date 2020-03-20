/*
 * created by github.com/marcioAlexandre
 * 19 Mar 2020
 *
 */

package com.herokuapp.higherisk.country;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CountryController {
	
	private CountryBusiness countryBusiness; 
	
	@RequestMapping(method = RequestMethod.GET ,value="/countries")
	public List<String> getCountries() 
	{		
		countryBusiness = new CountryBusiness();
		return countryBusiness.getCountries();
	}
	
	
}
