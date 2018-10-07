<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/common/common.jspf" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>停车记录</title>
<style type="text/css">
	#deleteWin p{
		margin-top:20px;
		text-align:center;
	}
	#deleteWin a{
		margin-left:20px;
		margin-top:40px;
	}
</style>
</head>
<body>
	<div style="width:1150;margin:0 auto">
		<table id="box"></table>
	</div>
	<div id="tb" style="padding-top:0px">
		<a class="easyui-linkbutton" onclick="record.deleteWin()" iconCls="icon-cancel" plain='true'>删除记录</a>	
	</div>
	<div id="deletewin" class="easyui-window" title="Window Layout" modal="true" data-options="iconCls:'icon-save'" style="top:40px;width:300px;height:200px;padding:5px;" closed="true">
		<p>请问要删除此记录吗？</p>
		<a class="easyui-linkbutton" data-options="iconCls:'icon-ok'"  onclick="record.deleteRecord()">确认删除</a>
		<a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="$('#deletewin').window('close')">取消删除</a>
	</div>
</body>
<script type="text/javascript">
	$(function(){
		$("#box").datagrid({
			width:1150,
			url:"${proPath }" + '/plate/parkrecord',
			title:'停车记录列表',
			columns:[[
				{field:'id',title:'id',width:20},
				{field:'plateNumber',title:'车位号',width:20},
				{field:'enterTime',title:'进入时间',width:30},
				{field:'leaveTime',title:'离开时间',width:35},
				{field:'userCommunityId',title:'小区id',width:35},
				{field:'supposeParkCost',title:'应交费用',width:35},
				{field:'handParkCost',title:'实交费用',width:35},
				{field:'payWay',title:'支付方式',width:35}
			]],
			pagination:true,
			fitColumns:true,
			toolbar:"#tb",
		});
	});
	
	var record = {
		//打开删除窗口
		deleteWin:function(){
			if($(".datagrid-row-selected").size()==0){
				$.messager.alert('警告','请选择一项进行删除');
				return;
			}else if($(".datagrid-row-selected").size()>1){
				$.messager.alert('警告','请只选择一项进行删除');
				return;
			}
			$("#deletewin").window('open');
		},
		
		//删除记录
		deleteRecord:function(){
			var id  = $(".datagrid-row-selected").find($(".datagrid-cell-c1-id")).text();
			var plateNumber = $(".datagrid-row-selected").find($(".datagrid-cell-c1-plateNumber")).text();
		 
			$.ajax({
				   type: "POST",
				   url: "/ezsh/plate/parkrecord",
				   data: {id:id,plateNumber:plateNumber,_method:"delete"},
				   success: function(data){
				   		if(data["success"] == true){
						alert("操作成功");
						window.location.reload();
				   	}else{
						alert(data["error"]);
						window.location.reload();
				   	}
			   }
			});	
		
		}
	
	}
</script>
</html>