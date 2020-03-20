/*
 * created by github.com/marcioAlexandre
 * 19 Mar 2020
 *
 */

package com.herokuapp.higherisk;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HighRiskController {
	
	@RequestMapping("/")
	public String goToIndex() 
	{	
		return "index";
	}
	
	@RequestMapping("/links")
	public String goToLinks() 
	{	
		return "links";
	}
	
	@RequestMapping("/search")
	public String goToCountries() 
	{	
		return "search";
	}
	
}
