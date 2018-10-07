<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/common/header.jsp" %>
<!DOCTYPE html>
<html>
	<head> 
	<title>门锁列表</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">    
		<meta http-equiv="description" content="门锁列表">
		<style type="text/css">
			.tableStyle{
				font-size:12px;
				padding:2% 0px 0px 5%;
			}
		</style>
	</head>
	
	<body class="easyui-layout">
		<div id="tb" style="padding:5px;height:auto">
			<div style="margin-bottom:5px">
				<a href="javascript:add()" class="easyui-linkbutton" iconCls="icon-add" plain="true"></a>
				<a href="javascript:edit()" class="easyui-linkbutton" iconCls="icon-edit" plain="true"></a>
				<a href="javascript:remove()" class="easyui-linkbutton" iconCls="icon-remove" plain="true"></a>
				<a id="changePass" href="javascript:changePass()" class="easyui-linkbutton" iconCls="icon-mini-edit" plain="true">修改设备密码</a>
			</div>
		</div>
		
		<!-- query module start -->
		<!-- <div data-options="region:'north',split:false," title="查询条件" style="height:100px">
			<div style="padding:15px;height:auto">
				<span>管理处:</span>
			    <input  id="ptManagerId" class="easyui-combobox" name="ptManagerId"   
                      	data-options="valueField:'managerId',
                        required:true,textField:'managerName',
                      	url:'/ezsh/build/findManager',
                      	onSelect: function(rec){	
						var url = '/ezsh/charge/selectByManagerId?pManagerId='+rec.managerId;
						$('#pChargeItemId').combobox('reload', url);
						}
                      	"/>	
				支付宝交易号查询: 
				<input id="tradeNum" class="easyui-textbox" style="width:100px;height:25px">
				<a href="javascript:search()" class="easyui-linkbutton" iconCls="icon-search">Search</a>
			</div>
		</div> -->
		<!-- tree end -->
		
		<!-- tree start -->
		<div data-options="region:'west',title:'楼宇',split:true" style="width:150px;">
			<ul class="easyui-tree" id="tree">
		        
		    </ul>
		</div>
		<!-- tree end -->
		
		<div data-options="region:'center',title:'门锁列表',iconCls:'icon-ok'">
			<table id="formTable" class="easyui-datagrid"
					data-options="url:'',method:'get', 
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
		
		<div id="win" class="easyui-window" title="设备锁-修改" style="width:250px;height:150px"
		    data-options="iconCls:'icon-edit',
		    modal:true,
		    closed:true,
		    collapsible:false,
		    minimizable:false,
		    maximizable:false,
		    resizable:false
		    ">
			<div class="easyui-layout" fit="true">
				<div region="center" border="false" >
					<form id="formInfo" method="post">
				    	<table class="tableStyle" cellpadding="5">
				    		<tr style="display:none">
				    			<td>
				    				<input id="lockId" class="easyui-textbox" type="text" name="lockId" required="required" style="width:100%;"/>
				    			</td>
				    		</tr>
				    		<tr style="display:none">
				    			<td>
				    				<input id="ptManagerId" class="easyui-textbox" type="text" name="ptManagerId" required="required" style="width:100%;"/>
				    			</td>
				    		</tr>
				    		<!-- <tr>
				    			<td>管理处</td>
				    			<td><input  id="ptManagerId" class="easyui-combobox" name="ptManagerId"   
			                      	data-options="valueField:'managerId',
			                        required:true,textField:'managerName',
			                      	url:'/ezsh/build/findManager',
			                      	onSelect: function(rec){	
									var url = '/ezsh/charge/selectByManagerId?pManagerId='+rec.managerId;
									$('#pChargeItemId').combobox('reload', url);
									}
			                      	"/>	
	                      		</td>
				    		</tr> -->
				    			
				    		<tr>	
				    			<td>设备ID:</td>
				    			<td>
				    				<input id="deviceId" class="easyui-textbox" type="text" name="deviceId" required="required" style="width:100%;"/>
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
			
		<div id="win1" class="easyui-window" title="门锁设备密码-修改" style="width:320px;height:200px"
		    data-options="iconCls:'icon-edit',
		    modal:true,
		    closed:true,
		    collapsible:false,
		    minimizable:false,
		    maximizable:false,
		    resizable:false
		    ">
			<div class="easyui-layout" fit="true">
				<div region="center" border="false" >
					<form id="formInfo1" method="post">
				    	<table class="tableStyle" cellpadding="5">
				    		<tr style="display:none">
				    			<td>
				    				<input id="lockId" class="easyui-textbox" type="text" name="lockId" required="required" style="width:100%;"/>
				    			</td>
				    		</tr>
				    		<!-- <tr style="display:none">
				    			<td>
				    				<input id="ptManagerId" class="easyui-textbox" type="text" name="ptManagerId" required="required" style="width:100%;"/>
				    			</td>
				    		</tr> -->
				    		<!-- <tr>
				    			<td>管理处</td>
				    			<td><input  id="ptManagerId" class="easyui-combobox" name="ptManagerId"   
			                      	data-options="valueField:'managerId',
			                        required:true,textField:'managerName',
			                      	url:'/ezsh/build/findManager',
			                      	onSelect: function(rec){	
									var url = '/ezsh/charge/selectByManagerId?pManagerId='+rec.managerId;
									$('#pChargeItemId').combobox('reload', url);
									}
			                      	"/>	
	                      		</td>
				    		</tr> -->
				    		<tr>	
				    			<td>原设备密码:</td>
				    			<td>
				    				<input id="devicePassOld" class="easyui-textbox" name="devicePassOld" type="text"  disabled="disabled" style="width:100%;"/>
				    			</td>
				    		</tr>	
				    		<tr>	
				    			<td>新设备密码:</td>
				    			<td>
				    				<input id="devicePass" class="easyui-numberbox" data-options="min:10000000,max:99999999" type="text" name="devicePass" required="required" style="width:100%;"/>
				    			</td>
				    		</tr>
				    	</table>
			    	</form>
				</div>
				<div region="south" border="true" style="text-align:right;height:50px;line-height:30px;padding:10px 15px 0px 0px;">
					<a class="easyui-linkbutton" icon="icon-ok" href="javascript:subPassChange()">确定</a>
					<a class="easyui-linkbutton" icon="icon-cancel" href="javascript:close1()">取消</a>
				</div>
			</div>
		</div>	
	</body>
	<script type="text/javascript" src="/ezsh/js/deviceLock/list.js"></script>
</html>
