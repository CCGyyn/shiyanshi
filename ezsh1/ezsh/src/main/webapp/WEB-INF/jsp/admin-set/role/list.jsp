<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/common/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>用户中心</title>
	<style type="text/css">
	body{
		margin:-15px;
	}
	.textbox.textbox-invalid{
		height:24px;
	}
	#add-admin input{
		height:22px;
		margin-bottom:10px;
	}
	
	/* span.textbox.combo{
		margin-top:10px;
	} */
	
	#ptManagerId span.textbox.combo{
		margin-top:0px;
	}
	</style>
</head>
<body class="easyui-layout">
	<div id="tb" style="padding:5px;height:auto">
		<div class="search" style="margin-bottom:5px">
			<shiro:hasPermission name="p111">
			<a id="add-role" href="javascript:void(0)" class="easyui-linkbutton"  iconCls="icon-add" plain="true"></a>
			</shiro:hasPermission>
			<a href="javascript:edit()" class="easyui-linkbutton" iconCls="icon-edit" plain="true"></a>
			<!-- <div id="selectManager" style="display:inline-block;">
				选择小区:<input  id="ptManagerId" class="easyui-combobox" name="ptManagerId" style="margin-bottom:15px;"  
                      	data-options="valueField:'managerId',
                      	editable:false,
                        textField:'managerName',
                      	url:'/ezsh/build/findManager'
                      	"/>	
			</div> -->
			<span>搜索关键字:</span>
			<input id="search" class="easyui-searchbox" style="width:15%;height:22px;">
		</div>
	</div>
	
	<!-- tree start -->
	<div data-options="region:'west',title:'楼宇',split:true" style="width:150px;">
		<ul class="easyui-tree" id="tree">
	        
	    </ul>
	</div>
	<!-- tree end -->
	
	<div data-options="region:'center',title:'管理员列表',iconCls:'icon-ok'">
		<table id="formTable" class="easyui-datagrid" 
			data-options="url:'',method:'get', 
			border:false,
			fit:true,
			fitColumns:true,
			striped:true,
			toolbar:'#tb',
			pagination:true,
			rownumbers:true,
			singleSelect:true,
			">
		</table>
	</div>
	
</body>
<script type="text/javascript">
/**
 * 初始化列表
 */
$('#formTable').datagrid({
    url:'/ezsh/ro/roList',
    columns:[[
    	{field:'roId',title:'角色ID',width:50,align:'center'},
		{field:'roDescr',title:'角色描述',width:200,align:'center'},
		{field:'roName',title:'角色名称',width:100,align:'center'}
    ]]
});
function edit(){
	var row = $('#formTable').datagrid('getSelected');
	if(row==null){
		alert("至少选一条数据");
	}else{
		var roleId = row.roId;
		self.location.href="/ezsh/ro/jumpToUpdateRole?roId="+roleId;
	}
	
}
</script>
<script type="text/javascript" src="/ezsh/js/admin-set/role/list.js"></script>
</html>

	