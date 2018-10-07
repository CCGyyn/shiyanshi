<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/common/common.jspf" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>报单登记</title>
</head>
<body>
	<table id="box"></table>
	<div id="tb" style="padding:5px">
		<a class="easyui-linkbutton" style="margin-top:10px;" data-options="iconCls:'icon-save'"  onclick="record.getDescription()">查看详情描述</a>
		<a class="easyui-linkbutton" style="margin-top:10px;margin-left:40px;" data-options="iconCls:'icon-save'"  onclick="record.getRepairRecordByStatus(0)">查看待派工报单</a>
		<a class="easyui-linkbutton" style="margin-top:10px;margin-left:40px" data-options="iconCls:'icon-save'"  onclick="record.getRepairRecordByStatus(1)">查看已派工报单</a>
		<a class="easyui-linkbutton" style="margin-top:10px;margin-left:40px" data-options="iconCls:'icon-save'"  onclick="record.getRepairRecordByStatus(2)">查看完工报单</a>
		<a class="easyui-linkbutton" style="margin-top:10px;margin-left:40px" data-options="iconCls:'icon-save'"  onclick="record.getRepairRecordByStatus(3)">查看审核通过报单</a>
		<div style="margin-top:10px;margin-bottom:10px">
			<span style="margin-right:10px">查询条件</span>  
			小区id ：&nbsp<input id="communityId" type="text" style="width:50px"/>
			小区名称 ：&nbsp<input id="communityName" type="text" style="width:70px"/>
			业主手机 ：&nbsp<input id="proprietorPhone" type="text" style="width:100px"/>
			房间号 ：&nbsp<input id="roomNumber" type="text" style="width:100px"/>
			<a id="search" style="float: right;margin-right: 750px;" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a>
		</div>
	</div>
	<div id="descWindow" class="easyui-window" title="详情描述" modal="true" data-options="iconCls:'icon-save'" style="top:40px;width:420px;height:400px;padding:10px;" closed="true">
		<textarea id="desc" rows="45" cols="44" style="font-size:16px">
		
		</textarea>
	</div>
</body>
<script type="text/javascript">
	$(function(){
		$("#box").datagrid({
			width:1500,
			url:"${proPath }" + '/repair/record',
			title:'报单记录列表',
			columns:[[
				{field:'communityId',title:'小区id',width:12},
				{field:'communityName',title:'小区名',width:20},
				{field:'repairNumber',title:'报单号',width:30},
				{field:'repairTime',title:'报单提交日期',width:35},
				{field:'repairDesc',title:'内容',width:40},
				{field:'roomNumber',title:'房间号',width:20},
				{field:'repairStatus',title:'状态(0:待派工；1:已派工;2:完工;3:审核通过)',width:40},
				{field:'proprietorName',title:'业主姓名',width:15},
				{field:'proprietorPhone',title:'业主电话',width:25},
				{field:'expectTime',title:'期待上门时间',width:25},
				{field:'repairUnit',title:'维修单位',width:30},
				{field:'repairStaff',title:'维修人员',width:15},
				{field:'repairSphone',title:'联系方式',width:25},
				{field:'completeStatus',title:'完工情况',width:20},
				{field:'completeTime',title:'完工时间',width:25},
				{field:'repairCost',title:'报修费用',width:10},
				{field:'feedback',title:'业主反馈',width:20}
				
			]],
			queryParams: {
			    	 communityId: '%%',
			    	 communityName:'%%',	
			    	 roomNumber:'%%',	
			    	 proprietorPhone:'%%',
			    	 repairStatus:'0'
				}, 
			pagination:true,
			fitColumns:true,
			toolbar:"#tb",
		});
	});
	
	var record = {
		//查看报单详情
		getDescription:function(){
			if($(".datagrid-row-selected").size()==0){
				$.messager.alert('警告','请选择一项进行查看');
				return;
			}else if($(".datagrid-row-selected").size()>1){
				$.messager.alert('警告','请只选择一项进行查看');
				return;
			}
			
			var communityId = "小区id："+$(".datagrid-row-selected").find($(".datagrid-cell-c1-communityId")).text()+'\n\n';
			var communityName = "小区名: "+$(".datagrid-row-selected").find($(".datagrid-cell-c1-communityName")).text()+'\n\n';
			var repairNumber = "报单号: "+$(".datagrid-row-selected").find($(".datagrid-cell-c1-repairNumber")).text()+'\n\n';
			var repairTime = "报单提交时间: "+$(".datagrid-row-selected").find($(".datagrid-cell-c1-repairTime")).text()+'\n\n';
			var repairDesc = "内容: "+$(".datagrid-row-selected").find($(".datagrid-cell-c1-repairDesc")).text()+'\n\n';
			var roomNumber = "房间号: "+$(".datagrid-row-selected").find($(".datagrid-cell-c1-roomNumber")).text()+'\n\n';
			var repairStatus = "报单状态: "+$(".datagrid-row-selected").find($(".datagrid-cell-c1-repairStatus")).text()+'\n\n';
			var proprietorName = "业主姓名: "+$(".datagrid-row-selected").find($(".datagrid-cell-c1-proprietorName")).text()+'\n\n';
			var proprietorPhone = "业主手机: "+$(".datagrid-row-selected").find($(".datagrid-cell-c1-proprietorPhone")).text()+'\n\n';
			var expectTime = "期待上门时间: "+$(".datagrid-row-selected").find($(".datagrid-cell-c1-expectTime")).text()+'\n\n';
			var repairUnit = "维修单位: "+$(".datagrid-row-selected").find($(".datagrid-cell-c1-repairUnit")).text()+'\n\n';
			var repairStaff = "维修人员: "+$(".datagrid-row-selected").find($(".datagrid-cell-c1-repairStaff")).text()+'\n\n';
			var repairSphone = "联系方式: "+$(".datagrid-row-selected").find($(".datagrid-cell-c1-repairSphone")).text()+'\n\n';
			var completeStatus = "完工情况: "+$(".datagrid-row-selected").find($(".datagrid-cell-c1-completeStatus")).text()+'\n\n';
			var completeTime = "完工时间: "+$(".datagrid-row-selected").find($(".datagrid-cell-c1-completeTime")).text()+'\n\n';
			var repairCost = "报修费用: "+$(".datagrid-row-selected").find($(".datagrid-cell-c1-repairCost")).text()+'\n\n';
			var feedback = "业主反馈: "+$(".datagrid-row-selected").find($(".datagrid-cell-c1-feedback")).text()+'\n\n';
			
			
	    	$("#desc").val(communityId+communityName+repairNumber+repairTime+repairDesc+roomNumber+repairStatus+proprietorName+proprietorPhone+expectTime+repairUnit+repairStaff+repairSphone+completeStatus+completeTime+repairCost+feedback);
	    	$("#descWindow").window("open");
		},
		
		//根据状态参数获取报单
		getRepairRecordByStatus:function(repairStatus){
			$('#box').datagrid('load',{
					communityId: '',
			    	communityName:'%%',	
			    	roomNumber:'%%',	
			    	proprietorPhone:'%%',
					repairStatus:repairStatus
				});
		},
	
	}
	//查询按钮的点击触发事件
	$("#search").click(function(){
		var communityId = $("#communityId").val();
		var communityName = $("#communityName").val();
		var proprietorPhone = $("#proprietorPhone").val();
		var roomNumber = $("#roomNumber").val();
		$('#box').datagrid('load',{
					communityId: '%'+communityId+'%',
					communityName:'%'+ communityName+'%',
					proprietorPhone:'%'+ proprietorPhone+'%',
					roomNumber:'%'+ roomNumber+'%',
					repairStatus:'0'
				});
	})
	
	
</script>
</html>