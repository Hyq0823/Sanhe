angular.module('starter.controllers', [])

.controller('DashCtrl', function($scope) {})
.controller('ApplyCtrl', function($scope,$http) {
	$scope.userId=localStorage.getItem("userId");
	var base_url = "http://localhost:8080/Exam/a/shApply/";
	
	var applys_url = base_url+"applys?userId="+$scope.userId;//报名
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
	 
	 
	 $scope.askToapply=function(infoId,isHandConfirm){
		 var askApplyUrl =base_url+infoId+"/ask/"+$scope.userId; 
		 $http({
		        method: 'POST',
		        url: askApplyUrl,
		        params:{'isHandConfirm':isHandConfirm},
		    }).then(function successCallback(response) {//如何在这里接受到数据后，实现toast？用指令吗？
		            console.log(response.data.errmsg);
		            alert(response.data.errcode+": "+response.data.errmsg);
		        }, function errorCallback(response) {
		            // 请求失败执行代码
		    });
	 }
 $scope.share=function(){
		 
	 }
}).directive("direcApply",function(){
	return {
		restict: "A",
		link:function(scope,elem,attrs){
			$(elem).click(function(){
				console.log("厉害了！");
			});
		}
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
