/*
 * created by github.com/marcioAlexandre
 * 19 Mar 2020
 *
 */

package com.herokuapp.higherisk.person;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {
	
	
	@Autowired
	private PersonRepository personRepository;
	
	
	/*
	 * 
	 * mapping 'place' and number of people
	 * 
	 * 
	 */
	
	@RequestMapping(method = RequestMethod.GET ,value="/countriesmap")
	public Map<String, Integer> getCountriesMapping() 
	{		
		List<Person> people = (List<Person>) this.personRepository.findAll();
				
		PersonBusiness personBusiness = new PersonBusiness();
		Map<String, Integer> countriesAndNumberOfPeopleMap = personBusiness.getCountriesAndNumberOfPeople(people);
		
		return countriesAndNumberOfPeopleMap;
		
	}	
	
	
	@RequestMapping(method = RequestMethod.POST ,value="/statesmap")
	public Map<String, Integer> getStatesMapping(@RequestBody String country) 
	{	
		
		List<Person> people = this.personRepository.findAllByCountry(country);
		
		PersonBusiness personBusiness = new PersonBusiness();
		Map<String, Integer> statesAndNumberOfPeopleMap = personBusiness.getStatesAndNumberOfPeople(people);
		
		return statesAndNumberOfPeopleMap;
	
	}
	
	
	
	@RequestMapping(method = RequestMethod.POST ,value="/citiesmap")
	public Map<String, Integer> getCitiesMapping(@RequestBody String[] data) 
	{	

		String country = data[0];
		String state = data[1];
		
		List<Person> people = this.personRepository.findAllByCountryAndState(country, state);
				
		PersonBusiness personBusiness = new PersonBusiness();
		Map<String, Integer> citiesAndNumberOfPeopleMap = personBusiness.getCitiesAndNumberOfPeople(people);
		
		return citiesAndNumberOfPeopleMap;
	}	
	
	
	@RequestMapping(method = RequestMethod.POST ,value="/neighborhoodsmap")
	public Map<String, Integer> getNeighborhoodMapping(@RequestBody String[] data) 
	{	

		String country = data[0];
		String state = data[1];
		String city = data[2];
		
		List<Person> people = this.personRepository.findAllByCountryAndStateAndCity(country, state, city);
		
		PersonBusiness personBusiness = new PersonBusiness();
		Map<String, Integer> neighborhoodsAndNumberOfPeopleMap = personBusiness.getNeiborhoodsAndNumberOfPeople(people);
		
		return neighborhoodsAndNumberOfPeopleMap;
		
	}
	
	
	/*
	 * 
	 * List<Person> from a specific 'place'
	 * 
	 * 
	 */
	
	@RequestMapping(method = RequestMethod.POST ,value="/person")
	public String insertPerson(@RequestBody Person person) 
	{		
		try 
		{
			person.setDateTime(LocalDateTime.now());
			this.personRepository.save(person);
			return "{\"msg\" : \"This person has been recorded successfully\"}";
		}catch(Exception e)
		{
			e.printStackTrace();
			return "{\"msg\" : \"Error - This person has NOT been recorded successfully\"}";
		}
	}	
	
	
	@RequestMapping (method = RequestMethod.GET, value="/people")
	public List<Person> getPeople()
	{
		List<Person> people = new ArrayList<>();
			
		people = (List<Person>) this.personRepository.findAll();
		
		return people;
		
	}
	
	@RequestMapping (method = RequestMethod.POST, value="/people-country")
	public List<Person> getPeopleByCountry(@RequestBody String[] data)
	{
		System.out.println("country: "+data[0]);
		
		String countryName = data[0].trim();
		
		System.out.println("Country: "+countryName);
		
		List<Person> people = new ArrayList<>();
		
		people = (List<Person>) this.personRepository.findAllByCountry(countryName);
		
		return people;
	}
	
	@RequestMapping (method = RequestMethod.POST, value="/people-country-state")
	public List<Person> getPeopleByCountryAndState(@RequestBody String[] data)
	{
		String countryName = data[0].trim();
		String stateName = data[1].trim();
		
		System.out.println("Country: "+countryName+", State: "+stateName);
		
		List<Person> people = new ArrayList<>();
		
		people = (List<Person>) this.personRepository.findAllByCountryAndState(countryName, stateName);
		
		return people;
	}
	
	@RequestMapping (method = RequestMethod.POST, value="/people-country-state-city")
	public List<Person> getPeopleByCountryAndStateAndCity(@RequestBody String[] data)
	{
		String countryName = data[0].trim();
		String stateName = data[1].trim();
		String cityName = data[2].trim();
		
		System.out.println("Country: "+countryName+", State: "+stateName+", City: "+cityName);
		
		List<Person> people = new ArrayList<>();
		
		people = (List<Person>) this.personRepository.findAllByCountryAndStateAndCity(countryName, stateName, cityName);
		
		return people;
	}
	
	@RequestMapping (method = RequestMethod.POST, value="/people-country-state-city-neighborhood")
	public List<Person> getPeopleByCountryAndStateAndCityAndNeighborhood(@RequestBody String[] data)
	{
		String countryName = data[0].trim();
		String stateName = data[1].trim();
		String cityName = data[2].trim();
		String neighborhoodName = data[3].trim();

		System.out.println("Country: "+countryName+", State: "+stateName+", City: "+cityName+", Neighborhood: "+neighborhoodName+".");
		
		List<Person> people = new ArrayList<>();
		
		people = (List<Person>) this.personRepository.
				findAllByCountryAndStateAndCityAndNeighborhood(countryName, stateName, cityName, neighborhoodName);
		
		System.out.println("Number of people: "+people.size());
		
		return people;
	}
	
	
	@RequestMapping (value="/people-country")
	@ResponseBody
	public List<Person> getPeopleByCountry(@RequestBody String country)
	{
		System.out.println("country is: "+country);
		return this.personRepository.findAllByCountry(country);
	}
	
	
	


	
	

	

}
