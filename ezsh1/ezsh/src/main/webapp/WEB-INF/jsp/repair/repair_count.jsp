<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/common/common.jspf" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>报修数量统计</title>
</head>
<body>
	<div style="width:1150;margin:0 auto">
		<table id="box"></table>
	</div>
	<div id="tb">
		<div style="margin-top:10px;margin-bottom:10px">
			<span style="margin-right:10px">查询条件</span>  
			小区名称 ：&nbsp<input id="communityName" type="text" style="width:70px"/>
			<a id="search" style="float: right;margin-right: 820px;" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a>
		</div>
	</div>
</body>
<script type="text/javascript">
	$(function(){
		$("#box").datagrid({
			width:1150,
			url:"${proPath }" + '/repair/count',
			title:'报修数量列表',
			columns:[[
				{field:'communityName',title:'小区名',width:40},
				{field:'total',title:'总数',width:30},
				{field:'waitForWorker',title:'未派工数',width:30},
				{field:'sendWorker',title:'已派工数',width:30},
				{field:'complete',title:'完工数',width:30},
				{field:'audited',title:'已审核数',width:30},
			]],
			queryParams: {
			    	 communityName:'%%',	
				}, 
			pagination:true,
			fitColumns:true,
			toolbar:"#tb",
		});
	});

	//查询按钮的点击触发事件
	$("#search").click(function(){
		var communityName = $("#communityName").val();
		$('#box').datagrid('load',{
					communityName:'%'+ communityName+'%',
				});
	})
</script>
</html>