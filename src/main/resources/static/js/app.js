
var app = angular.module('app-index', []); 

app.controller('index-controller', ['$http','$scope', function ($http, $scope) 
{
	//$scope.host = 'http://localhost:8080';
	$scope.host = 'https://highriskpeople.herokuapp.com';
	$scope.item = {};
	$scope.countriespresentation = [];
	
	$scope.getCountries = function(){
		  
		$http({ 
			method:'GET', 
			url: $scope.host+'/countries',
		}).then(function success(response){
		   	$scope.countriesname = response.data;
		},function unsuccess(response){ 
			console.log(response); 
		});
		
	}
	
	$scope.getCountries();
	
	$scope.getCountriesMapping = function()
	{
		$http({ 
			method:'GET', 
			url: $scope.host+'/countriesmap',
		}).then(function success(response){
			console.log(response);
			var values = response.data;
			count = 0;
			angular.forEach(values, function(value, key) {
				count++;
				if (count <= 18)
				{
					$scope.countriespresentation.push(key+' ('+ value+')');
				}
			});

		},function unsuccess(response){
			console.log(response);
		});
		
		
	}	
	
	$scope.getCountriesMapping();
	
	
	$scope.add = function(person)
	{
		
		if (!person || !person.country || !person.state || !person.city || !person.neighborhood)
		{
			alert("Please, to provide an useful Open Data to worldwide communities, at least Country, State/Area/Region, City and Neighborhood are mandatory data.");

		}
		else
		{
			$scope.person = angular.copy(person);
			
			$http({ 
				method:'POST', 
				url: $scope.host+'/person',
				data: $scope.person
			}).then(function success(response){
				location.reload();
			},function unsuccess(response){ 
				console.log(response); 
			});
			
		}
		
	   	$scope.getPeople();
	}
	
	$scope.getLocation = function()
	{
		location.href = "/search";
	}


}]);