<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<script src="${ctxStatic}/jquery/jquery-1.8.3.min.js" type="text/javascript"></script>
<script src="${ctxStatic }/weixin/js/angular.min.js" type="text/javascript"></script>
<html ng-app="infoApp">
<head>
<style type="text/css">
</style>
      <link rel="stylesheet" href="${ctxStatic }/weixin/css/message_complete.css">
      <link rel="stylesheet" href="${ctxStatic }/weixin/css/ionic.css">
      <link rel="stylesheet" href="${ctxStatic }/weixin/css/lxtClient.min.css">
      <title>基本信息完善</title>
 	<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
</head>

<body style="width: 100%;height:100%;">
<form:form modelAttribute="shWeixin" action="${ctx }/weixin/shWeixin/save"  id="com_form"  method="post">
<form:input type="hidden" path="openid" name="openid" value="${openid }" />
<form:input id= "hiddenMobile" type="hidden" path="user.mobile" name="mobile" value="${mobile }" />

<input type="hidden" name="url" value="${url}" />
<form:input id="hidden_sex" type="text" value="1" path="user.gender" style="display: none" />
<form:input id="hidden_address" type="hideen" value="" path="user.address" style="display: none" />

<div id="main_wrap" ng-controller="infoCtl">
    <ion-content>
        <div class="list" style="margin-top:10px">
            <label class="item item-input">
                <span>姓<span style="margin-left: 28px"></span>名</span>
                <input ng-model="vo.name" onkeyup="value=value.replace(/[\d]/g,'')"  class="cuS" maxlength="5" name="user.name" type="text" placeholder="请填写你的姓名">
                <span></span>
            </label>
             <label class="item item-input">
                <span>登录名称</span>
                <input id="logName" ng-model="vo.loginName"  class="idNumber" onkeyup="value=value.replace(/[\W]/g,'')"  type="text" name="user.loginName" placeholder="请输入登录名" maxlength="10">
                <span></span>
            </label>
            
            <!-- 
            <label class="item item-input">
                <span>身份证号</span>
                <input  class="idNumber" type="text" name="idNumber" placeholder="请填写身份证号" maxlength="18">
                <span></span>
            </label>
            
            <label class="item item-input">
                  <div style="color:#337ab7;font-size: 14px;font-weight: 600;">邮<span style="margin-left:28px;"></span>箱</div>
                <input  class="idNumber" type="text" name="email" placeholder="请填写邮箱" maxlength="18">
                <span></span>
            </label>
             -->
            
            <div class="item item-toggle">
					 <div style="color:#337ab7;font-size: 14px;font-weight: 600;">性<span style="margin-left:28px;"></span>别</div>
					<label class="toggle input-toggle">
						<input type="checkbox" ng-model="vo.gender"  class="ng-untouched ng-valid ng-dirty ng-valid-parse ng-empty" value="on">
						<div class="track">
							<span>男　女</span>
							<div class="handle">
							<i style="transform:translatey(6px)" ng-class="{'fa-mars':vo.gender,'fa-venus':!vo.gender}" class="fa fa-fw fa-mars"></i>
							</div>
						</div>
					</label>
				</div>
				
				<div class="item item-toggle">
				  <span>是否具有中医执业医师资格证</span>
					<label class="toggle input-toggle">
						<input type="checkbox" ng-change="vo.medicineRightChange()" ng-model="vo.hasMedicineRight" class="ng-untouched ng-valid ng-dirty ng-valid-parse ng-not-empty" value="on" />
						<div class="track"  path="user.no"/>
							<span>是　否</span>
							<div class="handle" style="padding-top: 6px;">
								<i ng-class="{'ion-checkmark-round':vo.hasMedicineRight,'ion-close-round':!vo.hasMedicineRight}" class="ion-checkmark-round"></i>
							</div>
						</div>
					</label>
				</div>
				<ul id="mrc_ul" class="com_ul" style="display: none">
                <li>
                 <input id="doctorNum" ng-model="vo.no" class="idNumber" type="text" name="user.no" placeholder="请输入执业资格号" maxlength="18">
                </li>
            </ul>
            
            <div class="address" ng-click="vo.addressChoice()" style="position: relative">
                <div>地<span style="margin-left:28px;"></span>址</div>
                <div class="address_son wo" id="address_son" ng-model="vo.address">
                    请选择详细地址
                </div>
                <div>
                    <span style="position: absolute;right:10px;margin: 0;top:15px"></span>
                </div>
            </div>
            <label class="item item-input" style="clear: both">
                <span>密<span style="margin-left: 28px"></span>码</span>
                <form:input ng-model="vo.password" class="password" path="user.password" type="password" maxlength="20" placeholder="请输入你的密码" />
                <span></span>
            </label>
            <label class="item item-input">
                <span>再次输入</span>
                <input ng-model="vo.repassword" class="repeatPwd" name="repeatPwd" type="password" maxlength="20" placeholder="请再输一次密码">
                <span></span>
            </label>
            <div class="tishi" style=""><span>*正在提交请稍候</span></div>
        </div>

    </ion-content>
    <div ng-click="vo.submit();" class="row " style="   position: fixed;bottom: 0;height:43px;background-color: #fff;z-index: 111;">
        <div class="title messageFooter" style="" >提交</div>
    </div>

</div>
</form:form>
<div class="message_wrap" style="" >
    <nav>
        <div class="nav_first" ng-click="vo.addressBack()">
            <span class="ion-chevron-left back"></span>
            <div style="text-align: center;color:white;font-size:18px;font-weight:600;">请选择地址</div>
        </div>
        <div class="nav">
        </div>
    </nav>
    <div class="row FooterFather" style="position: fixed;bottom: 0;height:43px;background-color: #fff;">
        <div class="title Footer">确认地址</div>
    </div>
</div>

<div class="toast" id="tips" style="display: none">
    	<span class="toast_text"></span>
    </div>
    </form>
</body>
<script type="text/javascript">
var app = angular.module('infoApp', []);
app.controller("infoCtl",function($scope,$compile){
		$scope.vo={
			gender:false,//性别
			hasMedicineRight:false,//
			no:"",//执业资格号
			address:"",
			name:"",
			password:"",
			repassword:"",
			loginName:"",
			submit:function(){
				if($scope.vo.name==''){
					$scope.vo.showToast($("#tips"),$("#tips span"),"请输入姓名 ！");
					return;
				}
				if($scope.vo.loginName==''){
					$scope.vo.showToast($("#tips"),$("#tips span"),"请输入登录名！");
					return;
				}
				
				if($scope.vo.hasMedicineRight && $scope.vo.no==''){
					$scope.vo.showToast($("#tips"),$("#tips span"),"请输入执业资格号！");
					return;
				}
				
				if($scope.vo.password=='' || $scope.vo.repassword==''){
					$scope.vo.showToast($("#tips"),$("#tips span"),"请输入 密码！");
					return;
				}
				
				if($scope.vo.password!=$scope.vo.repassword){
					$scope.vo.showToast($("#tips"),$("#tips span"),"两次密码不一致！");
					return;
				}
				
				if($scope.vo.password.length<3){
					$scope.vo.showToast($("#tips"),$("#tips span"),"密码长度为3位及以上！");
					return;
				}
				console.log($scope.vo);
				if(!$scope.vo.gender){
					$("#hidden_sex").val("0");
				}
				
				$("#com_form").submit();
				
			},
			init:function(){
			},
			showToast:function(e_container,e_text,message){//提示Toast
				e_text.text(message);//设置toast的文本
				if(e_container.css('display')!='none') return;
				e_container.fadeIn(100);	
	            e_container.fadeOut(2000);
			},
			addressChoice:function(){//地址选择
			 $("#main_wrap").slideUp(500);
			  $(".message_wrap").css({"transition": "all 0.01s liner", "margin-top": "0"}).slideDown(199);
			},
			addressBack:function(){//模板的缩放
				$(".message_wrap").slideUp(500);
		        $("#main_wrap").slideDown(500);
		       // $(".nav_select").removeClass("active");
			},
			medicineRightChange:function(){//执业资格
				console.log("aaa:"+$scope.vo.hasMedicineRight);
				if($scope.vo.hasMedicineRight){
					$("#mrc_ul").next().show();
				}else{
					$("#doctorNum").val("");
				}
				 $("#mrc_ul").stop().slideToggle(200)
			},
			shen:function(parentId){
		    var url = "${ctx}/sys/area/findByParentId";
		    $.ajax({
		        type: "post",
		        url: url,
		        data: {"parentId": parentId},
		        beforeSend: function () {//省请求市请求前的处理
		            $scope.vo.insert();
		        },
		        success: function (data) {
		            $(this).prev("div.nav_second").find("span:nth-child(2)").toggleClass("active");//切换箭头方向
		            var name = $("#" + parentId).html();
		            $("#" + parentId).parent().prev().html("<span class='span' nameId='" + parentId + "'>" + name + "</span><span class='ion-chevron-right nav_select'></span>");
		            $("#" + parentId).siblings("li").css({"background-color": "#fff"});
		            $("#" + parentId).css({"background-color": "#ccc"});
		            $("#" + parentId).parent().nextAll("div.nav_second").remove();//父亲后面的div.nav_second全部删除
		            $("#" + parentId).parent().nextAll("ul").remove();//父亲后面的ul全部删除
		            if (data != "") {
		                $("#" + parentId).parent().slideUp(20);
		                $(".nav").append("" +
		                "<div class='nav_second' style='background-color: #fff'>" +
		                "<span class='span'>请选择</span>" +
		                "<span class='ion-chevron-right nav_select active'>" +
		                "</span></div>");
		                $(".nav").append("  <ul class='com_ul' id='ul_" + parentId + "' ></ul>");
		                for (var i = 0; i < data.length; i++) {
		                    var str = "<li class='ss" + data[i].id + "' id=" + data[i].id + " ng-click='vo.shen(\"" + data[i].id + "\")'>" + data[i].name + "</li>";
		                    str = $compile(str)($scope);  
		                    $("#ul_" + parentId).append(str);
		                    $( ".FooterFather").hide()
		                }
		                $scope.vo.insert();
		            }
		            else {
		                $("#" + parentId).parent().slideUp(20);
		                $(".nav").append("");
		                $(".FooterFather").slideDown(500);//当数据请求为空时他弹出地址提交按钮
		                $(".FooterFather").click(function () {
		                    $("#address_son").html("");
		                    var arr = new Array(6);
		                    for (var i = 0; i < 6; i++) {
		                        arr[i] = {name: "", id: ""};
		                        arr[i].id = $(".span").eq(i).attr("nameId");
		                    }
		                    for (var i = 0; i < arr.length; i++) {
		                        if ($("span.span").eq(i).html() == undefined || $("span.span").eq(i).html() == "" || $("span.span").eq(i).html() == null) {
		                            $("span.span").eq(i).html() == ""
		                        }
		                        else {
		                            arr[i].name = $("span.span").eq(i).html();
		                        }
		                        var arrName = arr[0].name + arr[1].name + arr[2].name + arr[3].name + arr[4].name + arr[5].name;
		                    }
		                    $("#address_son").html(arrName);//地址栏
		                    $("#hidden_address").val(arrName);
		                    $("input[name=address_input]").val(arrName);//地址栏隐藏的输入框
		                    document.onkeydown = function () {
		                        if (event.keyCode == 13) {
		                            runMessage(arr);
		                        }
		                    };
		                    $(".message_wrap").slideUp(500);
		                    $("#main_wrap").slideDown(500);
		                });
		                $scope.vo.insert();
		            }
		            $scope.vo.insert();
		        }, complete: function () {
		        	 $scope.vo.insert();
		        }
		    });
		},//shen end
		insert:function() {
		    $(".nav_second").click(function () {
		        $(this).next("ul").stop().slideToggle(500).siblings("ul").slideUp(100);
//		            下拉切换
		        $(this).find("span:nth-child(2)").toggleClass("active");//切换箭头方向
		        $(this).nextAll("div.nav_second").find("span:nth-child(2)").removeClass("active");
		    })
		}
			}//vo end	
		$scope.vo.shen("1");
		$scope.vo.init();
});//app end
</script>
</html>