<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/common/common.jspf" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>车位收费设置</title>
<style type="text/css">
	body{
		height:500px;
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
		padding:0px
		
	}
	
	

</style>
</head>
<body>
	<div style="width:1150;margin:0 auto">
		<table id="box"></table>
	</div>
	<div id="tb" style="padding-top:0px">
		<a class="easyui-linkbutton" onclick="cost.openSetWindow()" iconCls="icon-add" plain='true'>添加</a>
		<a class="easyui-linkbutton" onclick="cost.modifyWindow()" iconCls="icon-edit" plain='true'>修改管理费</a>	
	</div>
	<div id="modify" class="easyui-window" title="修改设置" modal="true" closed="true" style="width:400px;height:400px;padding:5px;">
    	<form id="modifyCost">
    	<p><span>小区id:</span><input type="text"  name="userCommunityId" id="userCommunityId"/></p>
    	<p><span>小区名称:</span><input name="userCommunityName" type="text" id="userCommunityName" /></p>
    	<p><span>车位管理费:</span><input name="managementCost" type="text" id="managementCost" required="true"/></p>
		<a class="modify easyui-linkbutton" onclick="cost.modify()">确认修改</a>
		</form>
	</div>
	<div id="win" class="easyui-window" title="添加设置" modal="true" closed="true" style="width:400px;height:400px;padding:5px;">
		<form class="form" id="addCost">
				<div class="formField clearfix">
					<label for="firstName">小区id:</label>
					<input type="text" class="formText easyui-textbox userCommunityId" name="userCommunityId" required="true"/>
				</div>
				<div class="formField clearfix">
					<label for="lastName">小区名称:</label>
					<input type="text" class="formText easyui-textbox userCommunityName" name="userCommunityName" required="true"/>
				</div>
				<div class="formField clearfix">
					<label for="email">车位管理费:</label>
					<input type="text" class="formText easyui-textbox managementCost" name="managementCost" required="true"/>
				</div>
				<input type="button" value="提交" class="submit" onclick="cost.addCost()">
				<!--<a href="javascript:void(0)" class="easyui-linkbutton" onclick="add()">提交</a>-->
				<input type="reset" value="重置" class="reset">
			</form>
	</div>
	<div id="dd" class="easyui-dialog" title="My Dialog" style="width:400px;height:200px;"
   		 data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true">
    	
	</div>
</body>
<script type="text/javascript">
	$(function(){
		$("#box").datagrid({
			width:1150,
			url:"${proPath }" + '/plate/plateCost',
			title:'车位收费设置列表',
			columns:[[
				{field:'userCommunityId',title:'ID',width:50},
				{field:'userCommunityName',title:'小区名称',width:50},
				{field:'managementCost',title:'车位管理费',width:50}
			]],
			pagination:true,
			fitColumns:true,
			toolbar:"#tb",
		});
	});
	
	var cost = {
		//打开修改窗口
		modifyWindow:function(){
			if($(".datagrid-row-selected").size()==0){
				$.messager.alert('警告','请选择一项进行修改');
				return;
			}else if($(".datagrid-row-selected").size()>1){
				$.messager.alert('警告','请只选择一项进行修改');
				return;
			}
			$("#modify").window('open');
			$("#userCommunityId").val($(".datagrid-row-selected").find($(".datagrid-cell-c1-userCommunityId")).text());
			$("#userCommunityName").val($(".datagrid-row-selected").find($(".datagrid-cell-c1-userCommunityName")).text());
		},
		
		//打开设置框
		openSetWindow:function(){
			$('#win').window('open');
		},
		
		//添加费用设置
		addCost:function(){
			if($(".userCommunityId").val()=="" || $(".userCommunityName").val()=="" || $(".managementCost").val()==""){
			$.messager.alert('Warning','表单未填写完整');
			return;
			}
			$.ajax({
			   type: "POST",
			   url: "/ezsh/plate/platecost",
			   data: $("#addCost").serialize(),
			   success: function(data){
			   		if(data["success"] == true){
					//$.messager.alert('结果','操作成功');
					alert("操作成功");
					window.location.reload();
			   	}else{
					//$.messager.alert('结果',data["error"]);
					alert(data["error"]);
			   	}
			   }
			}
		 );	
		},
		//修改费用
		modify:function(){
			if($("#managementCost").val()==""){
				$.messager.alert('Warning','表单未填写完整');
				return;
				}
			$.ajax({
			   type: "PUT",
			   url: "/ezsh/plate/platecost",
			   data: $("#modifyCost").serialize(),
			   success: function(data){
			   		if(data["success"] == true){
					//$.messager.alert('结果','操作成功');
					alert("操作成功");
					 window.location.reload();
			   	}else{
					//$.messager.alert('结果',data["error"]);
					alert(data["error"]);
					window.location.reload();
			   	}
			   }
			}
		 );	
			},
		}		
	
	
	
	
	
	

</script>
</html>