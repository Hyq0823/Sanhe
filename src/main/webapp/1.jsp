<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>

<script type="text/javascript" src="${pageContext.request.contextPath }/static/jquery/jquery-1.8.3.js"></script>
</head>
<body>
<form id="userForm">
    <p>姓名：<input id="uname" type="text" name="userName" /></p>
    <p>头像：<input type="file" name="file" />
    <button type="button" value="保存" onclick="save()">出来把</button>
</form>

<script type="text/javascript">
    /* 保存 */
    function save() {
        //[0]表示获取原生对象
        var formData = new FormData($("#userForm")[0]);
        //还可以手动添加自定义字段，如下：
//        formData.append("name", "发达是否");
        var name = $("#uname").val();
        formData.append("name", name);
         
        $.ajax({
            url : '${pageContext.request.contextPath}/a/shApply/update',
            type : 'POST',
            data : formData,
            async : false,
            cache : false,
            contentType : false,// 告诉jQuery不要去设置Content-Type请求头
            processData : false,// 告诉jQuery不要去处理发送的数据
            success : function(data) {
                alert(data);
                //...
            },
            error : function(data) {
                alert(data);
                //...
            }
        });
    }
</script>

</body>
</html>