<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/header.jsp" %>
<!DOCTYPE html>
<html>
	<head>
		<title>添加角色</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">    
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<style type="text/css">
			.datagrid-header td{
				border-width: 0px 0px 1px 0px;
			}
		</style>
	</head>
  
	<body>
	    <div class="easyui-layout" style="width:100%;height:400px;">
			<div data-options="region:'east',split:true,title:'分配权限',collapsible:false" style="width:80%;">
				<table class="easyui-datagrid">
				    <thead>
						<tr>
							<th data-options="field:'角色模块'">超级权限</th>
							<!-- <th data-options="field:'角色模块-1'"></th>
							<th data-options="field:'角色模块-2'"></th> -->
						</tr>
				    </thead>
				    <tbody>
						<tr>
							<td class="permission-list1" style="vertical-align:middle;">
				            	<small><input type="checkbox" class="p101" name="assgin-power" value="p101">查看所有小区&nbsp;&nbsp;</small>
				            </td>    
						</tr>
					</tbody>
				</table>
				
				<table class="easyui-datagrid">
				    <thead>
						<tr>
							<th data-options="field:'角色模块'">角色模块</th>
							<th data-options="field:'角色模块-1'"></th>
							<th data-options="field:'角色模块-2'"></th>
						</tr>
				    </thead>
				    <tbody>
						<tr>
							<td class="permission-list1" style="vertical-align:middle;">
				            	<small><input type="checkbox" class="p111" name="assgin-power" value="p111">添加角色&nbsp;&nbsp;&nbsp;</small>
				            </td>
				            <td class="permission-list1" style="vertical-align:middle;">
				            	<small><input type="checkbox" class="p112" name="assgin-power" value="p112">修改角色&nbsp;&nbsp;&nbsp;</small>
				            </td>
				            <td class="permission-list1" style="vertical-align:middle;">
				            	<small><input type="checkbox" class="p113" name="assgin-power" value="p113">角色授权&nbsp;&nbsp;&nbsp;</small>
				            </td>
						</tr>
					</tbody>
				</table>
				
				<table class="easyui-datagrid">
				    <thead>
						<tr>
							<th data-options="field:'管理员模块'">管理员模块</th>
							<th data-options="field:'管理员模块-1'"></th>
							<th data-options="field:'管理员模块-2'"></th>
							<th data-options="field:'管理员模块-3'"></th>
						</tr>
				    </thead>
				    <tbody>
						<tr>
							<td class="permission-list1">
				            	<small><input type="checkbox" class="p121" name="assgin-power" value="p121">添加管理员</small>
				            </td>		         
				            <td class="permission-list1">
				            	<small><input type="checkbox" class="p123" name="assgin-power" value="p123">修改管理员</small>
				            </td>
				            <td class="permission-list1">
				            	<small><input type="checkbox" class="p124" name="assgin-power" value="p124">删除管理员</small>
				            </td>
				            <td class="permission-list1">
				            	<small><input type="checkbox" class="p122" name="assgin-power" value="p122">分配角色</small>
				            </td>
						</tr>
					</tbody>
				</table>
			</div>
			
			<!-- add role start -->
			<div data-options="region:'center',title:'添加角色',iconCls:'icon-ok',href:''" style="padding:10px">
				<div style="padding:10px 10px 20px 10px;">
				    <form id="add-admin" method="post">
				    	<div>
					    	<div>选择小区:</div>
					    	<input  id="ptManagerId" class="easyui-combobox" style="width:100%;" name="ptManagerId"   
	                      				data-options="valueField:'managerId',
	                      				required:true,
	                      				editable:false,
	                      				  panelHeight: 80,
	                      				textField:'managerName',
	                      				url:'/ezsh/build/findManager'
	                      				"/> 
				    	</div>
				    	<div style="margin-bottom:20px">
							<div>角色名称:</div>
							<input id="role-name" class="easyui-textbox" required="required" data-options="prompt:'输入角色名称....'" style="width:100%;height:32px">
						</div>
						<div style="margin-bottom:20px">
							<div>角色描述:</div>
							<input id="role-desc" class="easyui-textbox" style="width:100%;height:100px" data-options="prompt:'说点什么...',multiline:true">
						</div>
						<div>
							<a href="javascript:void(0)" id="sub-power" class="easyui-linkbutton" iconCls="icon-ok" style="width:100%;height:32px">添加</a>
						</div>
				    </form>
			    </div>
			</div>
			<!-- add role end -->
		</div>
	</body>
	<script type="text/javascript" src="/ezsh/js/admin-set/role/add.js"></script>
</html>