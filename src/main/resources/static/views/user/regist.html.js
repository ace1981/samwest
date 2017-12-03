var app = angular.module('app', []);

app.controller('registCtrl', function($scope,$http) {
    //$scope.user={};
    $scope.registuser=function(){
    	$http({
         url: '../../user/new/',
         method: 'POST',
         data:JSON.stringify($scope.user),
    	 headers: {
      	 'Content-Type': 'application/json'
         }
        }).then(function successCallback(response) {
    		console.log(response.data);            
        }, function errorCallback(response) {
            // 请求失败执行代码
            console.log(response.data); 
    	});
    };
});