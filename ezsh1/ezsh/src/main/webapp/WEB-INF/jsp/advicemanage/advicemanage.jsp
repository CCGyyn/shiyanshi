<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/common/common.jspf" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>用户意见管理</title>
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
	<table id="box"></table>
	<div id="tb" style="padding-top:0px">
		<a class="easyui-linkbutton" onclick="advice.auditeWin()" iconCls="icon-ok" plain='true'>审核投诉</a>	
		<a class="easyui-linkbutton" onclick="advice.deleteWin()" iconCls="icon-cancel" plain='true'>删除记录</a>	
		<a class="easyui-linkbutton" onclick="advice.getAuditedRecord()" iconCls="icon-search" plain='true'>查看已审核投诉</a>	
		<a class="easyui-linkbutton" onclick="advice.getUnauditedRecord()" iconCls="icon-search" plain='true'>查看未审核投诉</a>	
		<a class="easyui-linkbutton" onclick="advice.getDetail()" iconCls="icon-search" plain='true'>查看投诉内容</a>	
		<a class="easyui-linkbutton" onclick="advice.getAdviceImg()" iconCls="icon-search" plain='true'>查看投诉图片</a>	
	</div>
	<div id="auditeWin" class="easyui-window" title="Window Layout" modal="true" data-options="iconCls:'icon-save'" style="top:40px;width:300px;height:200px;padding:5px;" closed="true">
		<p>审核投诉</p>
		<a class="easyui-linkbutton" data-options="iconCls:'icon-ok'"  onclick="advice.auditeRecord()">确认审核</a>
		<a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="$('#auditeWin').window('close')">取消审核</a>
	</div>
	<div id="deletewin" class="easyui-window" title="Window Layout" modal="true" data-options="iconCls:'icon-save'" style="top:40px;width:300px;height:200px;padding:5px;" closed="true">
		<p>请问要删除此记录吗？</p>
		<a class="easyui-linkbutton" data-options="iconCls:'icon-ok'"  onclick="advice.deleteRecord()">确认删除</a>
		<a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="$('#deletewin').window('close')">取消删除</a>
	</div>
	<div id="descWindow" class="easyui-window" title="投诉内容" modal="true" data-options="iconCls:'icon-save'" style="top:40px;width:350px;height:300px;padding:5px;" closed="true">
		<textarea id="desc" rows="30" cols="29" style="font-size:20px">
		
		</textarea>
	</div>
	<div id="imgWindow" class="easyui-window" title="投诉图片" modal="true" data-options="iconCls:'icon-save'" style="top:40px;width:450px;height:450px;padding:5px;" closed="true">
		
	</div>
</body>
<script type="text/javascript">
	$(function(){
	$("#box").datagrid({
			width:1150,
			url:"${proPath }" + '/advice/manage',
			title:'用户投诉记录列表',
			columns:[[
				{field:'id',title:'id',width:10},
				{field:'userName',title:'用户名',width:20},
				{field:'communityId',title:'小区id',width:15},
				{field:'userPhone',title:'用户手机号',width:25},
				{field:'house',title:'房屋号',width:20},
				{field:'adviceTitle',title:'投诉标题',width:35},
				{field:'advice',title:'投诉内容',width:55},
				{field:'result',title:'审核结果',width:15},
				{field:'createtime',title:'提交时间',width:25}
			]],
			queryParams: {
			    	 result:0
				}, 
			pagination:true,
			fitColumns:true,
			toolbar:"#tb",
		});
	});
	
	var advice = {
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
		
		//打开审核窗口
		auditeWin:function(){
			if($(".datagrid-row-selected").size()==0){
				$.messager.alert('警告','请选择一项进行审核');
				return;
			}else if($(".datagrid-row-selected").size()>1){
				$.messager.alert('警告','请只选择一项进行审核');
				return;
			}
			$("#auditeWin").window('open');
		},
		
		//审核投诉记录
		auditeRecord:function(){
			var id  = $(".datagrid-row-selected").find($(".datagrid-cell-c1-id")).text();
			$.ajax({
				   type: "POST",
				   url: "/ezsh/advice/audite",
				   data: {id:id,result:1},
				   success: function(data){
				   		if(data["status"] == 1){
						alert("操作成功");
						window.location.reload();
				   	}else{
						alert(data["message"]);
						window.location.reload();
				   	}
			   }
			});	
		},
		
		//查看已审核投诉记录
		getAuditedRecord:function(){
			$('#box').datagrid('load',{
					result:1
				});
		},
		
		//查看未审核投诉记录
		getUnauditedRecord:function(){
			$('#box').datagrid('load',{
					result:0
				});
		},
		
		//查看投诉内容
		getDetail:function(){
			if($(".datagrid-row-selected").size()==0){
				$.messager.alert('警告','请选择一项进行查看');
				return;
			}else if($(".datagrid-row-selected").size()>1){
				$.messager.alert('警告','请只选择一项进行查看');
				return;
			}
			var adviceTitle = "投诉标题："+$(".datagrid-row-selected").find($(".datagrid-cell-c1-adviceTitle")).text()+'\n\n';
			var advice = "投诉内容: "+$(".datagrid-row-selected").find($(".datagrid-cell-c1-advice")).text()+'\n\n';
			var desc = adviceTitle + advice;
	    	$("#desc").val(desc);
	    	$("#descWindow").window("open");
		},
		
		//删除记录
		deleteRecord:function(){
			var id  = $(".datagrid-row-selected").find($(".datagrid-cell-c1-id")).text();
			$.ajax({
				   type: "POST",
				   url: "/ezsh/advice",
				   data: {id:id,_method:"delete"},
				   success: function(data){
				   		if(data["status"] == 1){
						alert("操作成功");
						window.location.reload();
				   	}else{
						alert(data["message"]);
						window.location.reload();
				   	}
			   }
			});	
		
		},
		
		//查看投诉图片
		getAdviceImg:function(){
			if($(".datagrid-row-selected").size()==0){
				$.messager.alert('警告','请选择一项进行查看');
				return;
			}else if($(".datagrid-row-selected").size()>1){
				$.messager.alert('警告','请只选择一项进行查看');
				return;
			}
			var id  = $(".datagrid-row-selected").find($(".datagrid-cell-c1-id")).text();
			$("#imgWindow").empty();
			$.ajax({
				   type: "GET",
				   url: "/ezsh/advice/getAdviceImg",
				   data: {id:id},
				   success: function(data){
				   		if(data["status"] == 1){
				   		for(var i=0;i<data["data"].length;i++){
				   			var img = "<img src='"+data["data"][i]+"'>";
				   			$("#imgWindow").append(img);
				   		}
				   		$("#imgWindow").window("open");
				   		}else{
						alert(data["message"]);
				   	}
			   }
			});	
		}
	
	}
</script>
</html>