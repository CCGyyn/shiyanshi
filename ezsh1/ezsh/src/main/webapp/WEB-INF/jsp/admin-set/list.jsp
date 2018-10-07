<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/common/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>管理员列表</title>
    <meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">  
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
	</style>
</head>

<body class="easyui-layout" >
	<div id="tb" style="padding:5px;height:auto">
		<div style="margin-bottom:5px">
			<a id="add" href="javascript:add()" class="easyui-linkbutton" iconCls="icon-add" plain="true"></a>
			<a id="edit" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true"></a>
			<shiro:hasPermission name="p124">
				<a id="changePass" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-mini-edit" plain="true">修改密码</a>
			</shiro:hasPermission>
			<!-- <div id="selectManager" style="display:inline-block;">
			选择小区:<input  id="ptManagerId" class="easyui-combobox" name="ptManagerId" style="margin-bottom:15px;"  
                     	data-options="valueField:'managerId',
                     	editable:false,
                       textField:'managerName',
                     	url:'/ezsh/build/findManager'
                     	"/>	
			</div> -->
			<span>搜索关键字:</span>
			<input id="search" class="easyui-searchbox" style="width:15%;height:22px">
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
				data-options="url:'/ezsh/ad/select',
				method:'get',
				singleSelect:true,
				fit:true,
				fitColumns:true,
				pagination:true,
				toolbar:'#tb'">
			<thead>
				<tr>
					<th data-options="field:'ck',checkbox:true,align:'center'"></th>
					<th data-options="field:'adId',width:80,align:'center'">ID</th>
					<th data-options="field:'adName',width:100,align:'center'">姓名</th>
					<th data-options="field:'adAccount',width:80,align:'center'">账号</th>
					<th data-options="field:'adTelephone',width:80,align:'center'">手机号码</th>
					<th data-options="field:'adManagerId',width:40,align:'center'">管理处编号</th>
					<th data-options="field:'adRoleId',hidden:true,width:40,align:'center'">角色ID</th>
				</tr>
			</thead>
		</table>
	</div>
	
	<!-- <div id="pp" style="background:#efefef;border:1px solid #ccc;"></div>	 -->

	<!--edit windows start -->
	<div id="win" class="easyui-window" title="修改-管理员" style="width:360px;height:280px"
   		data-options="modal:true,
   		closed:true,
   		resizable:false,
   		maximizable:false">
   		<div class="easyui-layout" fit="true">
	    	<div style="padding:10px 10px 20px 60px;" region="center" border="false">
			    <form id="formInfo" method="post">
			    	<table>
			    		<tr style="display:none;">
			    			<td><input type="text" class="easyui-textbox"  name="adId"></input></td>
			    		</tr>
			    		<tr>
			    			<td>姓名:</td>
			    			<td><input type="text" class="easyui-textbox"  name="adName" style="height:28px;" missingMessage="不能为空！" required="required"></input></td>
			    		</tr>
			    		<tr>
			    			<td>账号:</td>
			    			<td><input type="text" class="easyui-textbox"  name="adAccount" style="height:28px;"  missingMessage="不能为空！" disabled="disabled" required="required"></input></td>
			    		</tr>
			    		<tr>
			    			<td>手机号码:</td>
			    			<td>
			    				<input type="text" class="easyui-textbox"  name="adTelephone" style="height:28px;"   missingMessage="不能为空！" data-options="required:true"></input>
			    			</td>
			    		</tr>
			    		<tr>
			    			<td>管理小区:</td>
			    			<td>
			    				<input  id="adManagerId" class="easyui-combobox" name="adManagerId"   
	                      				data-options="valueField:'managerId',
	                      				required:true,
	                      				textField:'managerName',
	                      				  panelHeight: 80,
	                      				url:'/ezsh/build/findManager',
	                      				onSelect: function(rec){
	                      					$('#adRoleId').combobox('clear');	
											var url = '/ezsh/ro/getRoleList?ptManagerId='+rec.managerId;
											$('#adRoleId').combobox('reload', url);
										}
	                      				"/>
			    			</td>
			    		</tr>
			    		<tr>
			    			<td>分配角色:</td>
			    			<td>
			    				<input  id="adRoleId" class="easyui-combobox" name="adRoleId"   
	                      				data-options="valueField:'roId',
	                      				required:true,
	                      				textField:'roName',
	                      				url:'/ezsh/ro/getRoleList',
	                      				"/>
			    			</td>
			    		</tr>    		
			    	</table>
			    </form>
		    </div>
		    <div region="south" border="true" style="text-align:right;height:50px;line-height:30px;padding:10px 15px 0px 0px;">
				<a class="easyui-linkbutton" icon="icon-ok" href="javascript:sub()">确定</a>
				<a class="easyui-linkbutton" icon="icon-cancel" href="javascript:close()">取消</a>
		    </div>
	    </div>
	</div>
	<!--edit windows end -->
	
	<!--changePass windows start -->
	<div id="win1" class="easyui-window" title="密码-修改" style="width:360px;height:260px"
   		data-options="modal:true,
   		closed:true,
   		resizable:false,
   		maximizable:false">
   		<div class="easyui-layout" fit="true">
	    	<div style="padding:30px 10px 20px 60px;" region="center" border="false">
			    <form id="formInfo1" method="post">
			    	<table>
			    		<tr style="display:none;">
			    			<td><input type="text" class="easyui-textbox"  name="adId"></input></td>
			    		</tr>
			    		<tr>
			    			<td>账号:</td>
			    			<td><input type="text" class="easyui-textbox"  name="adAccount" style="height:28px;" disabled="disabled" missingMessage="不能为空！" required="required"></input></td>
			    		</tr>
			    		<tr>
			    			<td>新密码:</td>
			    			<td>
			    				<input type="password" id="changePassword" name="adPassword"  class="easyui-textbox"  style="height:28px;" required="required" missingMessage="不能为空！"></input>
			    			</td>
			    		</tr>
			    		<tr>
			    			<td>确认密码:</td>
			    			<td>
			    				<input type="password" class="easyui-textbox"  name="adPassword-again" style="height:28px;"  required="required" missingMessage="不能为空！"  validType="equals['#changePassword']"></input>
			    			</td>
			    		</tr>	    		 		
			    	</table>
			    </form>
		    </div>
		    <div region="south" border="true" style="text-align:right;height:50px;line-height:30px;padding:10px 15px 0px 0px;">
				<a class="easyui-linkbutton" icon="icon-ok" href="javascript:subNewPass()">确定</a>
				<a class="easyui-linkbutton" icon="icon-cancel" href="javascript:close1()">取消</a>
		    </div>
	    </div>
	</div>
	<!--changePass windows end -->
	
</body>
<script type="text/javascript" src="/ezsh/js/admin-set/list.js"></script>
</html>

	