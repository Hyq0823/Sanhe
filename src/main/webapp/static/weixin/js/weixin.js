var weixin={
		init:function(){//注册按钮点击事件
			$("#btn_registe").click(function(){
				console.log("注册按钮！click");
				var phone = $("#phoneNo").val();
				if(!weixin.validatePhone(phone)){//校验手机号码
					weixin.showToast($("#tips"),$("#tips span"),"手机号码不正确！");
					return;
				}
				
				var code = $("#input_code").val();
				if(!weixin.validateMsgCode(code)){
					weixin.showToast($("#tips"),$("#tips span"),"验证码不正确！");
					return;
				}
				
				$("#btn_registe").attr("disabled",true);
				console.log("get right phone and code: "+phone+" -->"+code);
				$("#hiddenMobile").val(phone);
				$("#reg_form").submit();
			});
			
			//注册点击获取验证码事件
			$("#m_lab").click(function(){
				var phone = $("#phoneNo").val();
				if(weixin.validatePhone(phone)){//校验手机号码
					code = weixin.createValidateCode();
					$("#m_code").text(code);
					localStorage.setItem("msgcode",code);
					weixin.timeDown();
				}else{
					weixin.showToast($("#tips"),$("#tips span"),"请输入正确的手机号！");
				}
			});
			
			$("#phoneNo").keyup(function(){
				var mobile = $("#phoneNo").val();
				if(mobile && mobile.length==11){
					if(!weixin.validatePhone(mobile)){
						weixin.showToast($("#tips"),$("#tips span"),"手机号码格式不正确！");
					}
				}
			});
			
			//手机号码输出框blur事件绑定
			$("#phoneNo").blur(function(){
				var mobile = $("#phoneNo").val();
				if(mobile!=null && mobile.length == 11){
					if(!weixin.validatePhone(mobile)){
						weixin.showToast($("#tips"),$("#tips span"),"手机号码格式不正确！");
						return;
					}
					$.ajax({
						url:'validateMobile',
						data:{'mobile':mobile},
						type:'post',
						dataType:'json',
						success:function(r){
							console.log("validate phone result:"+r);
							if(r["errcode"] == "1000"){
								weixin.showToast($("#tips"),$("#tips span"),"此手机号码已被注册！");
							}
						}
					});
				}
			});
		},
		validatePhone:function(phone){//简单校验手机号码
			if(phone && phone.length==11 && !isNaN(phone) && (/^1[34578]\d{9}$/.test(phone))){
				return true;
			}
			return false;
		},
		validateMsgCode:function(code){//校验验证码
			if(code && code.length==4 && !isNaN(code) && code==localStorage.getItem("msgcode")){
				return true;
			}
			return false;
		},
		showToast:function(e_container,e_text,message){//提示Toast
			e_text.text(message);//设置toast的文本
			if(e_container.css('display')!='none') return;
			e_container.fadeIn(100);	
            e_container.fadeOut(2000);
		},
		createValidateCode:function(){//创建验证码
			var code = "";
			for(var i=0;i<4;i++){
				code+=Math.floor(Math.random()*10)
			}
			return code;
		},
		timeDown:function(){
			var time = 60;
			$("#m_lab").hide();
			var task = window.setInterval(function(){
				if(task==null){return;}
				if(time==0){
					$("#m_time").text(" ");
					$("#m_lab").show();
					$("#m_code").hide();
					window.clearInterval(task);
					task = null;
					return;
				}
				console.log(time);
				$("#m_time").text("("+time+")");
				time--;
			},1000);
		}
}

