<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
  <body>
  <script type="text/javascript">
    var url = "${ctxStatic}/weixin/www/index.html";
    console.log("jsp--->html:"+url);
    localStorage.setItem("userId","${userId}");
	  window.location.href=url;
  </script>
  </body>
</html>