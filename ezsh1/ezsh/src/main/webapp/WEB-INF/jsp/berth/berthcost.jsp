<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/common/common.jspf" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>车位区域管理</title>
<style type="text/css">
	.search{
		float:right;
		margin-top:15px;
		margin-right:540px;
		width:250px;
	}
	
	#win input{
		width:120px;
		border-radius:15px;
		text-align:center;
	}
    .formField {
		overflow: auto;
		width: 100%;
		margin: 35px 50px;
		
	}
	.form{
		margin:0 auto;
	}
				
	label {
		float: left;
		display: block;
		margin-right: 5px;
		width: 110px;
		font-size:18px;
	}
				
	.formText {
		display: block;
		float: left;
	}
	.submit{
		margin-top:30px;
		margin-left:40px;
		margin-right:20px;
	}
	.reset{
		margin-top:30px;
	}
	p{
		width: 100%;
		margin: 40px 50px;
		overflow: auto;
	}
	p span{
		float:left;
		width: 110px;
		font-size:18px;
	}
	p input{
		float:left;
		width:120px;
		border-radius:15px;
		text-align:center;
	}
	.modify{
		margin-left:140px;
	}
	#box{
		margin-left:50px;
	}
	#tb{
		padding:0px;
	}
	#searchResultWin input{
		margin-left:30px;
	
	}
	#deleteWin p{
		
		text-align:center;
	}
	#deleteWin a{
		margin-left:20px;
		margin-top:40px;
	}
</style>
</head>
<body>
	<table id="box"></table>
	<div id="tb" style="padding-top:0px;width:100%">
		<a class="easyui-linkbutton" onclick="berth.openAddWindow()" iconCls="icon-add" plain='true'>添加车位</a>
		<a class="easyui-linkbutton" onclick="berth.deleteBerthWin()" iconCls="icon-cancel" plain='true'>删除车位</a>
		<div class="search">
			<span>小区id：</span><input class="id" type="text" style="width:50px;"></input>
			<span>车位号：</span><input class="berthnumber" type="text" style="width:50px"></input>
			<button class="" onclick="berth.search()"  plain='true'>搜索</button>
		</div>
	</div>
	<div id="win" class="easyui-window" title="添加车位" modal="true" closed="true" style="top:50px;width:400px;height:400px;padding:5px;">
		<form class="form" id="addCost">
				<div class="formField clearfix">
					<label for="firstName">小区id:</label>
					<input type="text" class="formText easyui-textbox" id="userCommunityId" name="userCommunityId" required="true"/>
				</div>
				<div class="formField clearfix">
					<label for="lastName">小区名称:</label>
					<input type="text" class="formText easyui-textbox" id="userCommunity" name="userCommunity" required="true"/>
				</div>
				<div class="formField clearfix">
					<label for="email">车位号:</label>
					<input type="text" class="formText easyui-textbox" id="berthNumber" name="berthNumber" required="true"/>
				</div>
				<div class="formField clearfix">
					<label for="email">车位管理费:</label>
					<input type="text" class="formText easyui-textbox" id="berthCost" name="berthCost" required="true"/>
				</div>
				<input type="button" value="提交" class="submit" onclick="berth.addBerth()">
				<!--<a href="javascript:void(0)" class="easyui-linkbutton" onclick="add()">提交</a>-->
				<input type="reset" value="重置" class="reset">
			</form>
	</div>
	<div id="deletewin" class="easyui-window" title="Window Layout" modal="true" data-options="iconCls:'icon-save'" style="top:20px;width:600px;height:700px;" closed="true">
		<p>请问要删除此车位吗？</p>
		<a class="easyui-linkbutton" data-options="iconCls:'icon-ok'"  onclick="berth.deleteBerth()">确认删除</a>
		<a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="$('#deletewin').window('close')">取消删除</a>
	</div>
</body>
<script type="text/javascript">
	$(function(){
		$("#box").datagrid({
			width:1400,
			url:"${proPath }" + '/plate/berthMessage',
			title:'车位管理列表',
			columns:[[
				{field:'id',title:'ID',width:20},
				{field:'userCommunity',title:'小区名称',width:20},
				{field:'berthNumber',title:'车位号',width:35},
				{field:'berthCost',title:'车位管理费',width:35}
			]],
			pagination:true,
			fitColumns:true,
			toolbar:"#tb",
			queryParams: {
			    	 status:'%%'
				}
		});
	});
	
	var berth = {
		//打开添加窗口
		openAddWindow:function(){
			$('#win').window('open');
		},
		//添加车位
		addBerth:function(){
			if($("#userCommunityId")=="" || $("#userCommunity")=="" || $("#berthNumber")=="" || $("#berthCost")==""){
				$.messager.alert('Warning','表单未填写完整');
				return;
			}
			$.ajax({
			   type: "POST",
			   url: "/ezsh/plate/berthmessage",
			   data: $("#addCost").serialize(),
			   success: function(data){
			   		if(data["success"] == true){
					alert("操作成功");
					window.location.reload();
			   	}else{
					alert(data["error"]);
			   	}
			   }
			});
		 },
		 
		 //删除车位窗口
		 deleteBerthWin:function(){
		 	if($(".datagrid-row-selected").size()==0){
				$.messager.alert('警告','请选择一项进行删除');
				return;
			}else if($(".datagrid-row-selected").size()>1){
				$.messager.alert('警告','请只选择一项进行删除');
				return;
			}
			//if($(".datagrid-row-selected").find($(".datagrid-cell-c1-UserPhone")).text()!=""){
				//$.messager.alert('警告','该车位已被购买，不能被删除');
				//return;
			//}
			$("#deletewin").window('open');
		 
		 },
		 //删除车位
		 deleteBerth:function(){
		 	var id  = $(".datagrid-row-selected").find($(".datagrid-cell-c1-id")).text();
			var berthNumber = $(".datagrid-row-selected").find($(".datagrid-cell-c1-berthnumber")).text();
			$.ajax({
				   type: "POST",
				   url: "/ezsh/plate/berthmessage",
				   data: {id:id,berthNumber:berthNumber,_method:"delete"},
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
		 },
		 
		 //搜索
		 search:function(){
		// $("#searchResultWin").window('open');
		 	var id = $(".id").val();
		 	var berthnumber = $(".berthnumber").val();
		 	$.ajax({
				   type: "GET",
				   url: "/ezsh/plate/berthmessage/"+berthnumber+"/"+id,
				   
				   success: function(data){
				   		if(data["success"] == true){
						$("#Id").val(data.data.userCommunityId); 
						$("#community").val(data.data.userCommunity);
						$("#number").val(data.data.berthNumber);
						$("#Name").val(data.data.userName);
						$("#Phone").val(data.data.userPhone);
						$("#handInCost").val(data.data.handInCost);
						$("#searchResultWin").window('open');
				   	}else{
						alert(data["error"]);
						
				   	}
			   }
			});	
		 
		 }
			
			
		}
</script>
</html>