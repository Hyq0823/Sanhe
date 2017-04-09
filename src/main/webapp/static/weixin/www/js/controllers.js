angular.module('starter.controllers', [])

.controller('DashCtrl', function($scope) {})
.controller('ApplyCtrl', function($scope,$http) {
	$scope.userId=localStorage.getItem("userId");
	var applys_url = "http://localhost:8080/Exam/a/shApply/applys?userId="+$scope.userId;//报名
	console.log("我的报名url: "+applys_url);
	 $http({
	        method: 'POST',
	        url: applys_url
	    }).then(function successCallback(response) {
	            console.log(response.data);
	            $scope.applys=response.data;//报名列表
	           // $scope.$apply();//直接在$.post的回调函数的最后加上一句$scope.$apply()，把改变同步绑定到视图上
	            console.log("scope: "+$scope.applys);
	        }, function errorCallback(response) {
	            // 请求失败执行代码
	    });
	 
	 
	 $scope.share=function(){
		 
	 }
})

.controller('ChatsCtrl', function($scope, Chats) {
  // With the new view caching in Ionic, Controllers are only called
  // when they are recreated or on app start, instead of every page change.
  // To listen for when this page is active (for example, to refresh data),
  // listen for the $ionicView.enter event:
  //
  //$scope.$on('$ionicView.enter', function(e) {
  //});

  $scope.chats = Chats.all();
  $scope.remove = function(chat) {
    Chats.remove(chat);
  };
})

.controller('ChatDetailCtrl', function($scope, $stateParams, Chats) {
  $scope.chat = Chats.get($stateParams.chatId);
})

.controller('AccountCtrl', function($scope) {
  $scope.settings = {
    enableFriends: true
  };
});
