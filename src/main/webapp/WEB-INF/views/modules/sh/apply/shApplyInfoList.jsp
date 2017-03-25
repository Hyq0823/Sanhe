<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>报名信息管理</title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/treetable.jsp" %>
	<script type="text/javascript">
	var apply={
			getHandConfirm:function(type){//手动审核:1是 2否 
				return "1"==type?"是":"否";
			},
			getStatus:function(type){//报名状态 0未开始 1进行中 2已完成
				if(type=="0"){
					return "<span class='label label-default'>未开始</span>";
				}else if(type =="1"){
					return '<span class="label label-success">进行中</span>';
				}else{
					return '<span class="label label-warning">已结束</span>';
				}
			}
	};
	//console.log(apply.getHandConfirm("1"));
	
	
		$(document).ready(function() {
			var tpl = $("#treeTableTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
			var data = ${fns:toJson(list)}, ids = [], rootIds = [];
			for (var i=0; i<data.length; i++){
				data[i].isHandConfirm=apply.getHandConfirm(data[i].isHandConfirm);//是否手动审核
				data[i].status = apply.getStatus(data[i].status);//报名状态
				ids.push(data[i].id);
			}
			ids = ',' + ids.join(',') + ',';
			for (var i=0; i<data.length; i++){
				if (ids.indexOf(','+data[i].parentId+',') == -1){
					if ((','+rootIds.join(',')+',').indexOf(','+data[i].parentId+',') == -1){
						rootIds.push(data[i].parentId);
					}
				}
			}
			for (var i=0; i<rootIds.length; i++){
				addRow("#treeTableList", tpl, data, rootIds[i], true);
			}
			$("#treeTable").treeTable({expandLevel : 5});
		});
		function addRow(list, tpl, data, pid, root){
			for (var i=0; i<data.length; i++){
				var row = data[i];
				if ((${fns:jsGetVal('row.parentId')}) == pid){
					$(list).append(Mustache.render(tpl, {
						dict: {
						blank123:0}, pid: (root?0:pid), row: row
						,
					}));
					addRow(list, tpl, data, row.id);
				}
			}
		}
	</script>
<style type="text/css">
.table th, .table td { 
text-align: center;
vertical-align: middle!important;
}	
</style>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/apply/shApplyInfo/">报名信息列表</a></li>
		<shiro:hasPermission name="apply:shApplyInfo:edit"><li><a href="${ctx}/apply/shApplyInfo/form">报名信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="shApplyInfo" action="${ctx}/apply/shApplyInfo/" method="post" class="breadcrumb form-search">
		<ul class="ul-form">
			<li><label>标题：</label>
				<form:input path="name" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="treeTable" class="table table-striped table-bordered table-condensed" style="text-align: center;">
		<thead>
			<tr>
				<th>报名信息标题</th>
				<th>开始时间</th>
				<th>结束时间</th>
				<th>是否手动审核</th>
				<th>当前状态</th>
				<shiro:hasPermission name="apply:shApplyInfo:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody id="treeTableList"></tbody>
	</table>
	<script type="text/template" id="treeTableTpl">
		<tr id="{{row.id}}" pId="{{pid}}">
			<td><a href="${ctx}/apply/shApplyInfo/form?id={{row.id}}">
				{{row.name}}
			</a></td>
			<td>
				{{row.startTime}}
			</td>
			<td>
				{{row.endTime}}
			</td>
			<td>
				{{row.isHandConfirm}}
			</td>
			<td>
				{{{row.status}}}
			</td>
			

			<shiro:hasPermission name="apply:shApplyInfo:edit"><td>
   				<a href="${ctx}/apply/shApplyInfo/form?id={{row.id}}">修改</a>
				<a href="${ctx}/apply/shApplyInfo/delete?id={{row.id}}" onclick="return confirmx('确认要删除该报名信息及所有子报名信息吗？', this.href)">删除</a>
				<a href="${ctx}/apply/shApplyInfo/form?parent.id={{row.id}}">添加下级报名信息</a> 
			</td></shiro:hasPermission>
		</tr>
	</script>
</body>
</html>