<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/common/header.jsp" %>
<!DOCTYPE html>
<html>
	<head> 
	<title>抄表记录列表</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">    
		<meta http-equiv="description" content="抄表记录列表">
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
				<a href="javascript:remove()" class="easyui-linkbutton" iconCls="icon-remove" plain="true"></a>
			</div>
		</div>
		
		<div data-options="region:'north',split:false," title="查询条件" style="height:100px">
			<div style="padding:15px;height:auto">
				<!-- 起始时间: <input class="easyui-datebox" style="width:100px">
				终止时间: <input class="easyui-datebox" style="width:100px"> -->
				管理处：
				<input id="pManagerId" class="easyui-combobox" name="pManagerId" style="width:100px"
    				data-options="valueField:'managerId',
    				textField:'managerName',
    				required:true,
    				url:'/ezsh/build/findManager',
    				onSelect: function(rec){
				    var url = '/ezsh/build/gtBuildListById?buildManagerId='+rec.managerId;
				    $('#pBuildId').combobox('reload', url);
				    var urlOne='/ezsh/grid/selectSimple?pManagerId='+rec.managerId;
				    $('#pGridId').combobox('reload', urlOne);
				    }
    				">
    			楼栋：
   				<input id="pBuildId" class="easyui-combobox" name="pBuildId" style="width:100px"
	   				data-options="valueField:'buildId',
	   				textField:'buildName',
	   				required:true,
	   				">
				表计类别：
	   			<input id="pGridId" class="easyui-combobox" name="pGridId" style="width:100px"
    				data-options="valueField:'gridId',
    				textField:'gridName',
    				required:true,
    				">
				类别名查询: 
				<input class="easyui-textbox" style="width:100px;height:25px">
				<a href="javascript:search()" class="easyui-linkbutton" iconCls="icon-search">Search</a>
			</div>
		</div>
		
		<div data-options="region:'center',title:'抄表记录列表',iconCls:'icon-ok'">
			<table id="formTable" class="easyui-datagrid"
					data-options="url:'',method:'get', 
					border:false,
					fit:true,
					fitColumns:true,
					striped:true,
					pagination:true,
					toolbar:'#tb',
					rownumbers:true,
					singleSelect:true,
					">
				<thead>
					<tr>
						<th data-options="field:'ck',checkbox:true"></th>
						<th data-options="field:'pManagerId',hidden:true,align:'center'">管理处ID</th>
						<th data-options="field:'managerName',align:'center'" width="80">管理处</th>
						<th data-options="field:'pBuildId',hidden:true,align:'center'">楼宇ID</th>
						<th data-options="field:'buildName',align:'center'" width="80">楼宇名称 </th>
						<th data-options="field:'pRoomId',hidden:true,align:'center'">房间ID</th>
						<th data-options="field:'roomNum',align:'center'" width="80">房间编号</th>
						<th data-options="field:'pGridId',hidden:true,align:'center'">表计类别ID</th>
						<th data-options="field:'gridName',align:'center'" width="100">表计类别</th>
						<th data-options="field:'beginDosage',align:'center'" width="80">起数</th>
						<th data-options="field:'dosage',align:'center'" width="80">用量</th>
						<th data-options="field:'enteringTime',align:'center'" width="80">录入时间</th>
						<th data-options="field:'gridReadTime',align:'center'" width="80">抄表时间</th>
						<th data-options="field:'gridReadType',align:'center'" width="80">抄表方式</th>
						<th data-options="field:'gridReadPeople',align:'center'" width="80">抄表人员</th>
					</tr>
				</thead>
			</table>
		</div>
		
		<div id="win" class="easyui-window" title="抄表记录-修改" style="width:350px;height:300px"
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
				    		<tr>				    		
				    			<!-- <td style="display:none;">
				    				<input id="hidden-managerId" class="easyui-textbox" type="text" name="hidden-managerId"></input>
				    			</td>  -->  		
				    			<td>类别ID:</td>
				    			<td><input id="gridId" class="easyui-textbox" type="text" name="gridId" disabled="disabled"></input></td>
				    		</tr>
				    		
				    		<tr>	
				    			<td>类别名称:</td>
				    			<td><input id="gridName" class="easyui-textbox" type="text" name="gridName"></input></td>
				    		</tr>
				    		
				    		<tr>
				    			<td>管理处:</td>
				    			<td>
				    				<input  id="pManagerId" class="easyui-combobox" disabled="disabled" name="pManagerId"   
                          				data-options="valueField:'managerId',
                          				required:true,textField:'managerName',
                          				 panelHeight: 80,
                          				url:'/ezsh/build/findManager',
                          				onSelect: function(rec){
                          					$('#pChargeItemId').combobox('clear');	
										    var url = '/ezsh/charge/selectByManagerId?pManagerId='+rec.managerId;
										    $('#pChargeItemId').combobox('reload', url);
									    }"/> 
                          		</td>
				    		</tr>
				    		
				    		<tr>	
				    			<td>收费项目:</td>
				    			<td>
				    				<input  id="pChargeItemId" class="easyui-combobox" name="pChargeItemId"   
	                          			data-options="valueField:'chargeId',
	                          			required:true,textField:'chargeName'
	                          			 panelHeight: 80,
                          			" />
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
		
	</body>
	
	<script type="text/javascript" src="/ezsh/js/grid/recordUpdate.js"></script>
</html>
