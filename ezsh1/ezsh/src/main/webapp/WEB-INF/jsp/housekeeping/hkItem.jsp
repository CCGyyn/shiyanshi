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
		<a class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="hkItem.addItem()">新增服务项目</a>
	</div>
	<div id="descWindow" class="easyui-window" title="新增服务项目" modal="true" data-options="iconCls:'icon-save'" style="top:40px;width:400px;height:400px;padding:5px;" closed="true">
		<form action="${proPath}/housekeeping/addHkItem" method="post" enctype="multipart/form-data">
		<p>公司id:<input type="text" name="hkId" /></p>
		<p>服务类型:
			<select name="hkType">
			  <option value ="房屋基础维修">房屋基础维修</option>
			  <option value ="卫浴洁具维修">卫浴洁具维修</option>
			  <option value="灯具电路维修">灯具电路维修</option>
			  <option value="家用电器维修">家用电器维修</option>
			  <option value="其他维修">其他维修</option>
			</select>
		</p>
		<p>服务项目:<input type="text" name="item" /></p>
		<p>项目属性（有无配件，是否保修）:<input type="text" name="itemProperty" /></p>
		<p>单次维修价格:<input type="text" name="price" /></p>
		<p>备注:<input type="text" name="comment" /></p>
		<p>图片:<input type="file" name="imgs" multiple="multiple" /></p>
		<p><input type="submit" /></p>
		</form>
	</div>
</body>

<script type="text/javascript">
	$(function(){
		$("#box").datagrid({
			width:1150,
			url:"${proPath }" + '/housekeeping/getItemList',
			title:'服务项目管理列表',
			columns:[[
				{field:'id',title:'ID',width:20},
				{field:'hkId',title:'公司ID',width:20},
				{field:'hkName',title:'公司名称',width:30},
				{field:'hkType',title:'服务类型',width:30},
				{field:'item',title:'服务项目',width:30},
				{field:'itemProperty',title:'项目属性',width:20},
				{field:'price',title:'单次维修价格',width:40},
				{field:'comment',title:'备注',width:40},
				
			]],
			pagination:true,
			fitColumns:true,
			toolbar:"#tb"
			
		});
	});
	
	var hkItem = {
	//审核
	 addItem:function(){
	 	var desc  = $(".datagrid-row-selected").find($(".datagrid-cell-c1-hkDescribe")).text();
    	$("#desc").val(desc);
    	$("#descWindow").window("open");
	 
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
    	/*if($(".datagrid-row-selected").size()==0){
				$.messager.alert('警告','请选择一项进行查看');
				return;
			}else if($(".datagrid-row-selected").size()>1){
				$.messager.alert('警告','请只选择一项进行查看');
				return;
			}*/
		var desc  = $(".datagrid-row-selected").find($(".datagrid-cell-c1-hkDescribe")).text();
    	$("#desc").val(desc);
    	$("#descWindow").window("open");
    }
    
    }
</script>
</html>