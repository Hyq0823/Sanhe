<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/modules/cms/front/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>报名列表1</title>
	   <link rel="stylesheet" href="${ctxStatic }/bootstrap3.3.7/css/bootstrap.min.css">  
   <script src="${ctxStatic }/bootstrap3.3.7/js/bootstrap.min.js"></script>
	<meta name="decorator" content="cms_default_${site.theme}"/>
	<meta name="description" content="JeeSite ${site.description}" />
	<meta name="keywords" content="JeeSite ${site.keywords}" />
	<script type="text/javascript">
	</script>
</head>
<body>


<div class="container">
   <div class="row" >

<c:forEach items="${list}" var="info">
			<div class="col-sm-3 col-md-4 col-lg-4">
				<div class="thumbnail">
					<h3>${info.name }</h3>
					<p>${info.description }</p>
					<div class="caption">
						<p>
							<a href="#" class="btn btn-default" role="button">点赞</a> <a
								href="${ctx }/apply/${info.id}/application" class="btn btn-primary" role="button">报名</a>
						<div class="progress">
							${info.applyNum} / ${info.limitNum }<div class="bar" style="width: ${info.applyPercent}%;"></div>
						</div>
						</p>
					</div>
				</div>
			</div>
</c:forEach>
		</div>
</div>

</body>
</html>