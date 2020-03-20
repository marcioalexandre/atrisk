/*
 * created by github.com/marcioAlexandre
 * 20 Mar 2020
 *
 */

package com.herokuapp.higherisk.person;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class PersonBusiness {
	
	protected Map<String, Integer> getCountriesAndNumberOfPeople(List<Person> people)
	{
		Map<String, Integer> countryAndNumberOfPeopleMap = new HashMap<String, Integer>();
		int count = 0;
		Set<String> countryNameSet = new HashSet<>(); //all country name with no repeat
		List<String> allCountryList = new ArrayList<String>();
				
		for (Person person : people) 
		{
			countryNameSet.add(person.getCountry());
			allCountryList.add(person.getCountry());
			count++;
		}
		
		count = 0;
		
		if (countryNameSet.size() > 0)
		{
			for (String countryName: countryNameSet) {
				for (String cname: allCountryList)
				{
					if (countryName.equalsIgnoreCase(cname))
					{
						count++;
					}
				}
				countryAndNumberOfPeopleMap.put(countryName, count);
				count = 0;
			}
		}
		
		return countryAndNumberOfPeopleMap;
	}
	
	protected Map<String, Integer> getStatesAndNumberOfPeople(List<Person> people)
	{
		Map<String, Integer> statesAndNumberOfPeopleMap = new HashMap<String, Integer>();
		
		int count = 0;
		
		Set<String> stateNameSet = new HashSet<>();
		
		List<String> allStateList = new ArrayList<String>();
				
		for (Person person : people) 
		{
			stateNameSet.add(person.getState());
			allStateList.add(person.getState());
			count++;
		}
		
		count = 0;
		
		if (stateNameSet.size() > 0)
		{
			for (String stateName: stateNameSet) 
			{
				for (String sname: allStateList)
				{
					if (stateName.equalsIgnoreCase(sname))
					{
						count++;
					}
				}
				statesAndNumberOfPeopleMap.put(stateName, count);
				count = 0;
			}
		}
		
		return statesAndNumberOfPeopleMap;

	}
	
	protected Map<String, Integer> getCitiesAndNumberOfPeople(List<Person> people)
	{
	
		Map<String, Integer> cityAndNumberOfPeopleMap = new HashMap<String, Integer>();
		
		int count = 0;
		
		Set<String> cityNameSet = new HashSet<>();
		
		List<String> allCityList = new ArrayList<String>();
				
		for (Person person : people) 
		{
			cityNameSet.add(person.getCity());
			allCityList.add(person.getCity());
			count++;
		}
		
		count = 0;
		
		if (cityNameSet.size() > 0)
		{
			for (String cityName: cityNameSet) 
			{
				for (String cname: allCityList)
				{
					if (cityName.equalsIgnoreCase(cname))
					{
						count++;
					}
				}
				cityAndNumberOfPeopleMap.put(cityName, count);
				count = 0;
			}
		}
		
		return cityAndNumberOfPeopleMap;
		
	}
	
	protected Map<String, Integer> getNeiborhoodsAndNumberOfPeople(List<Person> people)
	{
		
		Map<String, Integer> neighborhoodsAndNumberOfPeopleMap = new HashMap<String, Integer>();
		
		int count = 0;
		
		Set<String> neighborhoodNameSet = new HashSet<>();
		
		List<String> allNeighborhoodList = new ArrayList<String>();
				
		for (Person person : people) 
		{
			neighborhoodNameSet.add(person.getNeighborhood());
			allNeighborhoodList.add(person.getNeighborhood());
			count++;
		}
		
		count = 0;
		
		if (neighborhoodNameSet.size() > 0)
		{
			for (String neighborhoodName: neighborhoodNameSet) 
			{
				for (String nname: allNeighborhoodList)
				{
					if (neighborhoodName.equalsIgnoreCase(nname))
					{
						count++;
					}
				}
				neighborhoodsAndNumberOfPeopleMap.put(neighborhoodName, count);
				count = 0;
			}
		}
		
		return neighborhoodsAndNumberOfPeopleMap;

	}

}
