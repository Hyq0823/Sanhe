<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<script src="${ctxStatic}/jquery/jquery-1.8.3.min.js" type="text/javascript"></script>
<html>
<head>
      <title>欢迎注册</title>
 	<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="${ctxStatic }/weixin/css/login.css">
    <link rel="stylesheet" href="${ctxStatic }/weixin/css/ionic.css">
</head>
<body>
	<ion-header-bar class="bar-positive">
    <div class="login_div">
        <div class="fr"><a href="login.html" style="color: #337ab7;">登录</a></div>
    </div>
</ion-header-bar>
<ion-content>
    <div class="kl_logo"><img src="${ctxStatic }/weixin/pic/logo_blue.png" alt=""></div>
    <br>
    <form id="reg_form" action="${ctx }/weixin/shWeixin/infoComplete" method="post">
    	<input type="hidden" name="openid" value="${openid }">
    	<input type="hidden" name="url" value="${url}">
    	<input id= "hiddenMobile" type="hidden" name="mobile">
        <div class="list">
            <label class="item item-input" style="border-top:none">
                <img src="${ctxStatic }/weixin/pic/iphone.png" alt="" >
                <input id="phoneNo"  maxlength="11" name="phone" style="padding-left:10px" type="text" placeholder="输入手机号">
                <span id="phone_pop" class="phone_prompt" style="margin-right: 10px;color: red;font-size: 11px;"></span>
            </label>
            <label class="item item-input">
                <img src="${ctxStatic }/weixin/pic/mima.png" alt="" >
                <input id="input_code" maxlength="4" name="validation" style="padding-left:10px" type="text" placeholder="输入验证码" >
                <span id="m_code" class="yanZ"  style="margin-right: 10px;color: red;font-size: 11px;"> </span>
                <span id ="m_time" style="color:#c0c0c0"></span>
                <span id="m_lab" class="validation" style="margin-right: 10px;color: #ff5b58;font-size: 11px;text-decoration:underline;">获取验证码</span>
            </label>
        </div>
    </form>
    <div class="row login_submit">
        <div class="col">
            <span></span>
            <button id="btn_registe" type="submit"  class="button button-block login_button" style="background-color: #337ab7">立即注册</button>
        </div>
    </div>
    
    <div class="toast" id="tips" style="display: none">
    	<span class="toast_text"></span>
    </div>
</ion-content>

</body>
<script type="text/javascript" src="${ctxStatic }/weixin/js/weixin.js"></script>
<script type="text/javascript">
    $(function(){
        //使用EL表达式传入参数
        /*{
            seckillId : ${seckill.seckillId},
            startTime : ${seckill.startTime.time},//毫秒
            endTime : ${seckill.endTime.time}
        }*/
      weixin.init();
    });
    
</script>
</html>