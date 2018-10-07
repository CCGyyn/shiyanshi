<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/common/header.jsp" %>
<!DOCTYPE html>
<html>
	<head> 
	<title>商品列表</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">    
		<meta http-equiv="description" content="商品列表">
		<style type="text/css">
			.tableStyle{
				font-size:12px;
				padding:10% 0px 0px 10%;
			}
		</style>
	</head>
	
	<body class="easyui-layout">
		<div id="tb" style="padding:5px;height:auto">
			<div style="margin-bottom:5px">
				<a href="javascript:add()" class="easyui-linkbutton" iconCls="icon-add" plain="true"></a>
				<a href="javascript:edit()" class="easyui-linkbutton" iconCls="icon-edit" plain="true"></a>
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true"></a>
				<a href="javascript:detailsList()" ><i class="fa fa-arrow-circle-right fa-lg"  style="color:green;" aria-hidden="true"></i></a>
			</div>
		</div>
		
		<div data-options="region:'north',split:false," title="查询条件" style="height:100px">
			<div style="padding:15px;height:auto">
				起始时间: <input id="startTime" class="easyui-datebox" style="width:100px">
				终止时间: <input id="endTime" class="easyui-datebox" style="width:100px">
				展示类型: 
				<select id="goodsShowClassfy" class="easyui-combobox" panelHeight="auto" style="width:100px">
					<option value="1">普通展示</option>
					<option value="2">每日推荐</option>
					<option value="3">活动优惠</option>
				</select>
				商品名称：
				<input id="goodsName" class="easyui-textbox" style="width:8%;">
				<a href="#" id="search" class="easyui-linkbutton" iconCls="icon-search">Search</a>
			</div>
		</div>
		
		<div data-options="region:'center',title:'商品库存列表',iconCls:'icon-ok'">
			<table  id="formTable"  class="easyui-datagrid"
					data-options="url:'',method:'post', 
					border:false,
					fit:true,
					fitColumns:false,
					striped:true,
					pagination:true,
					toolbar:'#tb',
					rownumbers:true,
					singleSelect:true,
					">
			</table>
		</div>
		
	</body>
	<script type="text/javascript">
	$('#formTable').datagrid({
	    url:'/ezsh/goodsAd/gtGoodsList?goodsShowClassfy=1',
	    columns:[[
			{field:'goodsInfoId',title:'商品信息ID',width:100,align:'center'},
			{field:'pManagerId',title:'管理处ID',width:100,align:'center'},
			{field:'goodsName',title:'商品名称',width:480,align:'center'},
			{field:'goodsTotalSaleAmount',title:'总销量',width:80,align:'center'},
			{field:'goodsPrice',title:'商品单价',width:100,align:'center'},
			{field:'addtime',title:'创建时间',width:100,align:'center'},
			{field:'goodsShowClassfy',title:'展示类型',width:100,align:'center'},
	    ]]
	});
	
	$("#search").click(function(){
		var startTime=$('#startTime').datebox('getValue');
		var endTime=$('#endTime').datebox('getValue');
		var goodsShowClassfy=$('#goodsShowClassfy').combobox('getValue');
		var goodsName=$('#goodsName').textbox('getValue');
		$('#formTable').datagrid({
			queryParams: {
				startTime: startTime,
				endTime: endTime,
				goodsShowClassfy:goodsShowClassfy,
				keyWord:goodsName
			}
		});
	})
	
	function add(){
		location.href="${pageContext.request.contextPath}/base/goURL/shopping-mall/add";
	}
	
	function detailsList(){
		var row = $('#formTable').datagrid('getSelected');
		if (row){
			var goodsInfoId=row.goodsInfoId;
			location.href="${pageContext.request.contextPath}/goodsAd/jumpToDetailsView?goodsInfoId="+goodsInfoId;
		}else{
			alert("请至少选中一条数据！");
		}	
	}
	
	function edit(){
		var row = $('#formTable').datagrid('getSelected');
		if (row){
			var goodsInfoId=row.goodsInfoId;
			location.href="${pageContext.request.contextPath}/goodsAd/gtGoodsInfo?goodsInfoId="+goodsInfoId;	
		}else{
			alert("请至少选中一条数据！");
		}	
	
	}
	</script>
</html>
