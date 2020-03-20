
var app = angular.module('app-countries', []); 

app.controller('countries-controller', ['$http','$scope', function ($http, $scope) 
{

	//$scope.host = 'http://localhost:8080';
	$scope.host = 'https://highriskpeople.herokuapp.com';
	
	/*
	 * 
	 * OpenData
	 * 
	 * 
	 */
	
	$scope.opendata = function()
	{
		
		$scope.bound = "all data"
		
		$http({ 
			method:'GET',
			url: '/people',
		}).then(function success(response){
			console.log(response);
			$scope.opendata = response.data;
		},function unsuccess(response){
			console.log("---- opendata ERROR -----");
			console.log(response);
		});
	
	}
	
	//init
	$scope.opendata();
	
	$scope.opendataByCountry = function(country)
	{
		
		$scope.bound = country;
		
		$http({ 
			method:'POST',
			url: '/people-country',
			data: '["'+country+'"]'
		}).then(function success(response){
			console.log(response);
			$scope.opendata = response.data;
		},function unsuccess(response){
			console.log("---- opendata ERROR -----");
			console.log(response);
		});
	}
	
	$scope.opendataByCountryAndState = function(country, state)
	{
		$scope.bound = country+" / "+state;
		
		$http({ 
			method:'POST',
			url: '/people-country-state',
			data: "[\""+country+"\", \""+state+"\"]"
		}).then(function success(response){
			console.log(response);
			$scope.opendata = response.data;
		},function unsuccess(response){
			console.log("---- opendata ERROR -----");
			console.log(response);
		});
	}
	
	$scope.opendataByCountryAndStateAndCity = function(country, state, city)
	{
		$scope.bound = country+" / "+state+" / "+city;
		
		$http({ 
			method:'POST',
			url: '/people-country-state-city',
			data: "[\""+country+"\", \""+state+"\", \""+city+"\"]"
		}).then(function success(response){
			console.log(response);
			$scope.opendata = response.data;
		},function unsuccess(response){
			console.log("---- opendata ERROR -----");
			console.log(response);
		});
	}

	$scope.opendataByCountryAndStateAndCityAndNeighborhood = function(country, state, city, neighborhood)
	{
		var countryTemp = country.split("(", 1);
		var stateTemp = state.split("(", 1);
		var cityTemp = city.split("(", 1);
		var neighborhoodTemp = neighborhood.split("(", 1);
		
		var countryName = countryTemp[0];
		var stateName = stateTemp[0];
		var cityName = cityTemp[0];
		var neighborhoodName = neighborhoodTemp[0];
		
		$scope.bound = countryName+" / "+stateName+" / "+cityName+" / "+neighborhoodName;
		
		$http({ 
			method:'POST',
			url: '/people-country-state-city-neighborhood',
			data: "[\""+countryName+"\", \""+stateName+"\", \""+cityName+"\", \""+neighborhoodName+"\"]"
		}).then(function success(response){
			console.log(response);
			$scope.opendata = response.data;
		},function unsuccess(response){
			console.log("---- opendata ERROR -----");
			console.log(response);
		});
	}
	
	/*
	 * 
	 * 'place' mapping
	 * 
	 * 
	*/
	
	$scope.getCountriesMapping = function()
	{
		$scope.countriespresentation = [];
		
		$http({ 
			method:'GET', 
			url: $scope.host+'/countriesmap',
		}).then(function success(response){
			//console.log(response);
			var values = response.data;
			angular.forEach(values, function(value, key) {
				$scope.countriespresentation.push(key+'(people: '+ value+')');
			});

		},function unsuccess(response){
			console.log(response);
		});
		
	}	
	
	//init
	$scope.getCountriesMapping();
	
	
	$scope.getStatesMapping = function(country) 
	{
		
		$scope.statespresentation = [];
		
		var countryTemp = country.split("(", 1);
		var countryName = countryTemp[0].trim();
			
		$http({ 
			method:'POST', 
			url: $scope.host+'/statesmap',
			data: countryName
		}).then(function success(response){
			console.log(response);
			var values = response.data;
			angular.forEach(values, function(value, key) {
					$scope.statespresentation.push(key+' (people: '+ value+')');
			});
		},function unsuccess(response){
			console.log(response);
		});
		
		$scope.opendataByCountry(countryName);
		
	}
	
	
	$scope.getCitiesMapping = function(country, state) 
	{		
		$scope.citiespresentation = [];
		
		var countryTemp = country.split("(", 1);
		var stateTemp = state.split("(", 1);
		
		var countryName = countryTemp[0].trim();
		var stateName = stateTemp[0].trim();
					
		$scope.bound = countryName+'/'+stateName;
		
		$http({ 
			method:'POST', 
			url: $scope.host+'/citiesmap',
			data: "[\""+countryName+"\", \""+stateName+"\"]"
		}).then(function success(response){
			console.log(response);
			var values = response.data;
			angular.forEach(values, function(value, key) {
					$scope.citiespresentation.push(key+' (people: '+ value+')');
			});
		},function unsuccess(response){
			console.log(response);
		});
		
		$scope.opendataByCountryAndState(countryName, stateName);
		
	}
	
	$scope.getNeighborhoodsMapping = function(country, state, city) 
	{
		
		$scope.neighborhoodspresentation = [];
		
		var countryTemp = country.split("(", 1);
		var stateTemp = state.split("(", 1);
		var cityTemp = city.split("(",1)
		
		var countryName = countryTemp[0].trim();
		var stateName = stateTemp[0].trim();
		var cityName = cityTemp[0].trim();
					
		$http({ 
			method:'POST', 
			url: $scope.host+'/neighborhoodsmap',
			data: "[\""+countryName+"\", \""+stateName+"\" , \""+cityName+"\"]"
		}).then(function success(response){
			console.log(response);
			var values = response.data;
			angular.forEach(values, function(value, key) {
					$scope.neighborhoodspresentation.push(key+' (people: '+ value+')');
			});
		},function unsuccess(response){
			console.log(response);
		});
		
		$scope.opendataByCountryAndStateAndCity(countryName, stateName, cityName);
		
	}
	
}]);