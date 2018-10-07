<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/common/common.jspf" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>车位管理</title>
<style type="text/css">
	
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
	.search{
		float:right;
		margin-top:15px;
		margin-right:140px;
		width:250px;
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
	<div style="width:1150;margin:0 auto">
		<table id="box" ></table>
	</div>
	<div id="tb" style="padding-top:0px;width:100%">
		<a class="easyui-linkbutton" onclick="berth.openBuyWindow()" iconCls="icon-edit" plain='true'>业主购买车位</a>
		<a class="easyui-linkbutton" onclick="berth.cancelBuyBerthWin()" iconCls="icon-cancel" plain='true'>业主取消购买车位</a>	
		<a class="easyui-linkbutton" onclick="berth.getBerth(0)" iconCls="icon-search" plain='true'>查看未购买车位</a>	
		<a class="easyui-linkbutton" onclick="berth.getBerth(1)" iconCls="icon-search" plain='true'>查看已购买车位</a>	
	
		<div class="search">
			<span>小区id</span><input class="id" type="text" style="width:50px;"></input>
			<span>车位号</span><input class="berthnumber" type="text" style="width:50px"></input>
			<button class="" onclick="berth.search()"  plain='true'>搜索</button>
		</div>
	</div>
	<div id="buy" class="easyui-window" title="业主购买车位" modal="true" closed="true" style="top:10px;width:400px;height:450px;padding-top:0px;">
    	<form id="buyBerth">
    	<p><span>id:</span><input type="text"  name="id" id="id"/></p>
    	<p><span>小区名称:</span><input name="userCommunity" type="text" id="usercommunity" /></p>
    	<p><span>车位号:</span><input name="berthNumber" type="text" id="berthnumber" /></p>
    	<p><span>业主姓名:</span><input name="userName" type="text" id="userName" required="true"/></p>
    	<p><span>业主手机号:</span><input name="userPhone" type="text" id="userPhone" required="true"/></p>
    	<p><span>合同开始时间:</span><input name="contractStartTime"  type="date" id="contractStartTime" /></p>
    	<p><span>合同结束时间:</span><input name="contractEndTime" class="easyui-datetimebox" type="text" id="contractEndTime" /></p>
		<a class="modify easyui-linkbutton" onclick="berth.buyBerth()">确认提交</a>
		</form>
	</div>
	<div id="cancelbuy" class="easyui-window" title="业主取消购买车位窗口" modal="true" closed="true" style="top:10px;width:400px;height:450px;padding-top:0px;">
    	<form id="cancelbuyBerth">
    	<p><span>id:</span><input type="text"  name="id" id="id"/></p>
    	<p><span>小区名称:</span><input name="userCommunity" type="text" id="Usercommunity" /></p>
    	<p><span>车位号:</span><input name="berthNumber" type="text" id="Berthnumber" /></p>
    	<p><span>业主姓名:</span><input name="userName" type="text" id="UserName" required="true"/></p>
    	<p><span>业主手机号:</span><input name="userPhone" type="text" id="UserPhone" required="true"/></p>
		<a class="modify easyui-linkbutton" onclick="berth.cancelBuyBerth()">取消购买</a>
		</form>
	</div>
	<div id="deletewin" class="easyui-window" title="Window Layout" modal="true" data-options="iconCls:'icon-save'" style="top:40px;width:300px;height:200px;" closed="true">
		<p>请问要删除此车位吗？</p>
		<a class="easyui-linkbutton" data-options="iconCls:'icon-ok'"  onclick="berth.deleteBerth()">确认删除</a>
		<a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="$('#deletewin').window('close')">取消删除</a>
	</div>
	<div id="searchResultWin" class="easyui-window" modal="true" style="top:20px;left:200px;width:500px;height:500px;padding:5px;" closed="true">
		<p><span>小区id:</span><input type="text"  name="userCommunityId" id="Id"/></p>
    	<p><span>小区名称:</span><input name="userCommunity" type="text" id="community" /></p>
    	<p><span>车位号:</span><input name="berthNumber" type="text" id="number" /></p>
    	<p><span>业主姓名:</span><input name="userName" type="text" id="Name" /></p>
    	<p><span>业主手机号:</span><input name="userPhone" type="text" id="Phone" /></p>
    	<p><span>实交管理费:</span><input name="handInCost" type="text" id="handInCost" /></p>
	</div>
</body>
<script type="text/javascript">
	$(function(){
		$("#box").datagrid({
			width:1150,
			url:"${proPath }" + '/plate/berthMessage',
			title:'车位管理列表',
			columns:[[
				{field:'id',title:'ID',width:10},
				{field:'userCommunity',title:'小区名称',width:25},
				{field:'berthNumber',title:'车位号',width:35},
				{field:'userName',title:'购买车位用户姓名',width:35},
				{field:'userPhone',title:'购买车位用户手机号',width:35},
				{field:'contractStarttime',title:'合同开始时间',width:35},
				{field:'contractEndtime',title:'合同结束时间',width:35},
				{field:'berthCost',title:'管理费',width:35},
				{field:'handInCost',title:'用户实交管理费',width:35}
			]],
			pagination:true,
			fitColumns:true,
			toolbar:"#tb",
			queryParams: {
			    	 status:'1'
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
			if($("#userCommunityId")=="" || $("#userCommunity")=="" || $("#berthNumber")==""){
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
		 //打开购买窗口
		 openBuyWindow:function(){
		 	if($(".datagrid-row-selected").size()==0){
				$.messager.alert('警告','请选择一项进行修改');
				return;
			}else if($(".datagrid-row-selected").size()>1){
				$.messager.alert('警告','请只选择一项进行修改');
				return;
			}
			if($(".datagrid-row-selected").find($(".datagrid-cell-c1-UserPhone")).text()!=""){
				$.messager.alert('警告','该车位已被购买');
				return;
			}
			$("#buy").window('open');
			$("#id").val($(".datagrid-row-selected").find($(".datagrid-cell-c1-id")).text());
			$("#usercommunity").val($(".datagrid-row-selected").find($(".datagrid-cell-c1-usercommunity")).text());
			$("#berthnumber").val($(".datagrid-row-selected").find($(".datagrid-cell-c1-berthnumber")).text());
		},
		 	
		 //业主购买车位登记
		 buyBerth:function(){
				if($("#userName")=="" || $("#userPhone")==""){
					$.messager.alert('Warning','表单未填写完整');
				return;
				}
				$.ajax({
				   type: "PUT",
				   url: "/ezsh/plate/berthmessage",
				   data: $("#buyBerth").serialize(),
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
		 
		 //业主取消购买车位窗口
		 cancelBuyBerthWin:function(){
		 	if($(".datagrid-row-selected").size()==0){
				$.messager.alert('警告','请选择一项进行修改');
				return;
			}else if($(".datagrid-row-selected").size()>1){
				$.messager.alert('警告','请只选择一项进行修改');
				return;
			}
			$("#cancelbuy").window('open');
			$("#id").val($(".datagrid-row-selected").find($(".datagrid-cell-c1-id")).text());
			$("#Usercommunity").val($(".datagrid-row-selected").find($(".datagrid-cell-c1-usercommunity")).text());
			$("#Berthnumber").val($(".datagrid-row-selected").find($(".datagrid-cell-c1-berthnumber")).text());
		 	$("#UserName").val($(".datagrid-row-selected").find($(".datagrid-cell-c1-userName")).text());
		 	$("#UserPhone").val($(".datagrid-row-selected").find($(".datagrid-cell-c1-userPhone")).text());
		 },
		 //业主取消购买车位
		 cancelBuyBerth:function(){
		 	$.ajax({
				   type: "PUT",
				   url: "/ezsh/plate/berthMessage",
				   data: $("#cancelbuyBerth").serialize(),
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
		 
		 
		//查看未购买车位
		getBerth:function(status){
			$('#box').datagrid('load',{
					status:status
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