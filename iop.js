var app=angular.module("myModule",[]);

app.config(function($httpProvider) {
 // $httpProvider.defaults.useXDomain = true;
  delete $httpProvider.defaults.headers.common['X-Requested-With'];
});

app.run(function($http) {
  $http.defaults.headers.common.Authorization = 'Basic cmFodWw6cmFodWwxMjM0';

});

app.controller("myController",function($scope,$http)
            {
				
	  $http.get('http://localhost:8080/users'
        
            )
            .success(function (data) {
                $scope.Details = data;
            });

/*
  {headers : { 
                  'Access-Control-Allow-Origin': '*'
                  }
               }   
               */

            //
            //'Authorization' :"Basic " + btoa("rahul" + ":" + "rahul1234"),
            //'Content-Type' : 'application/x-www-form-urlencoded; charset=UTF-8',
			// 'Access-Control-Allow-Methods' : 'POST, GET, PUT, DELETE, OPTIONS',
// 'Authorization' : 'Basic cmFodWw6cmFodWwxMjM0',
		
/*      console.log("inside controller");
      $http({
            method:"GET",
            url:"https://shinkeetechm.herokuapp.com/users",
            headers : { 
			'Content-Type' : 'application/x-www-form-urlencoded; charset=UTF-8',
			'Authorization' : 'Basic cmFodWw6cmFodWwxMjM0',
			'Access-Control-Allow-Origin': '*'
			}
            })
      .then(function(response)
                  {
            $scope.student=response.data;
                  },
function(error)
                  {
						console.log(error);
			})
*/            				  
				  
			
});