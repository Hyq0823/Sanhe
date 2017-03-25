<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
 <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>欢迎注册</title>
    <link rel="stylesheet" href="${ctxStatic }/weixin/css/login.css">
    <link rel="stylesheet" href="${ctxStatic }/weixin/css/ionic.css">
    <link rel="shortcut icon" href="${ctxStatic }/weixin/img/pic/logo_blue.ico">
    <link rel="Bookmark" href="${ctxStatic }/weixin/img/pic/logo_blue.ico">
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
    <form action="">
        <div class="list">
            <label class="item item-input" style="border-top:none">
                <img src="${ctxStatic }/weixin/pic/iphone.png" alt="" >
                <input maxlength="11" name="phone" style="padding-left:10px" type="text" placeholder="输入手机号">
                <span class="phone_prompt" style="margin-right: 10px;color: red;font-size: 11px;"></span>
            </label>
            <label class="item item-input">
                <img src="${ctxStatic }/weixin/pic/mima.png" alt="" >
                <input maxlength="6" name="validation" style="padding-left:10px" type="text" placeholder="输入验证码" >
                <span class="yanZ"  style="margin-right: 10px;color: red;font-size: 11px;"> </span>
                <span class="validation" style="margin-right: 10px;color: #ff5b58;font-size: 11px;text-decoration:underline;">获取验证码</span>
            </label>
        </div>
    </form>
    <div class="row login_submit">
        <div class="col">
            <span></span>
            <button type="submit" class="button button-block login_button" style="background-color: #337ab7">立即注册</button>
        </div>
    </div>
</ion-content>
</body>
</html>