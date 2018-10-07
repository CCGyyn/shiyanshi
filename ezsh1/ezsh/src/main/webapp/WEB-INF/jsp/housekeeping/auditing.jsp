<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/common/common.jspf" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table id="box"></table>
	<div id="tb" style="padding:5px">
		<a class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="housekeeping.pass(1)">通过</a>
		<a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="housekeeping.reject(2)">拒绝</a>	
		<a class="easyui-linkbutton" data-options="iconCls:'icon-search'"  onclick="housekeeping.getdescription()">查看服务详情描述</a>
	</div>
	<div id="descWindow" class="easyui-window" title="服务详情描述" modal="true" data-options="iconCls:'icon-save'" style="top:40px;width:285px;height:300px;padding:5px;" closed="true">
		<textarea id="desc" rows="20" cols="32">
		
		</textarea>
	</div>
</body>

<script type="text/javascript">
	$(function(){
		$("#box").datagrid({
			width:1150,
			url:"${proPath }" + '/housekeeping/getList',
			title:'家政服务公司注册',
			columns:[[
				{field:'hkId',title:'ID',width:20},
				{field:'communityId',title:'小区编号ID',width:20},
				{field:'hkName',title:'公司名称',width:60},
				{field:'hkLeader',title:'负责人',width:30},
				{field:'hkPhone',title:'服务热线',width:30},
				{field:'hkStaff',title:'员工人数',width:20},
				{field:'introduction',title:'公司介绍',width:40},
				
				
			]],
			pagination:true,
			fitColumns:true,
			toolbar:"#tb"
			
		});
	});
	
	var housekeeping = {
	//审核
	 audite:function(result,id){
		var url = "${proPath }" + '/housekeeping/audite';
		$.post(url,{result:result,id:id},function(result){
			if(result==1){
				alert("操作成功");
				history.go(0);
			}
			if(result==0){
				alert("操作失败");
				history.go(0);
			}
			
		});
	
	},
	//审核通过
	pass:function(result){
		$(".datagrid-row-selected").each(function(){
			var id = $(this).find("td:first-child").text();
			if(id != null){
			housekeeping.audite(result, id);
			}
		});	
    	},
    //拒绝通过	
    reject:function(result){
    	$(".datagrid-row-selected").each(function(){
			var id = $(this).find("td:first-child").text();
			if(id != null){
			housekeeping.audite(result, id);
			}
		});	
    },
    //查看服务详情描述
    getdescription:function(){
    	if($(".datagrid-row-selected").size()==0){
				$.messager.alert('警告','请选择一项进行查看');
				return;
			}else if($(".datagrid-row-selected").size()>1){
				$.messager.alert('警告','请只选择一项进行查看');
				return;
			}
		var desc  = $(".datagrid-row-selected").find($(".datagrid-cell-c1-hkDescribe")).text();
    	$("#desc").val(desc);
    	$("#descWindow").window("open");
    }
    
    }
</script>
</html>