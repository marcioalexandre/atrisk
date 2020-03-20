/*
 * created by github.com/marcioAlexandre
 * 19 Mar 2020
 *
 */

package com.herokuapp.higherisk.person;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {
	
	List<Person> findAllByCountry(String country);
	List<Person> findAllByCountryAndState(String country, String state);
	List<Person> findAllByCountryAndStateAndCity(String country, String state, String city);
	List<Person> findAllByCountryAndStateAndCityAndNeighborhood(String country, String state, String city, String neighborhood);


}
