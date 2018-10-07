<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/common/header.jsp" %>
<!DOCTYPE html>
<html>
	<head> 
	<title>收费项目列表</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">    
		<meta http-equiv="description" content="收费项目列表">
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
				<span>管理处:</span>
			    <input  id="pManagerId" class="easyui-combobox" name="pManagerId"   
                      	data-options="valueField:'managerId',
                        required:true,textField:'managerName',
                         panelHeight: 80,
                      	url:'/ezsh/build/findManager',
                      	onSelect: function(rec){	
						var url = '/ezsh/charge/selectByManagerId?pManagerId='+rec.managerId;
						$('#pChargeItemId').combobox('reload', url);
						}
                      	"/>			 		    		    		    						
				项目名查询: 
				<input id="chargeName" class="easyui-textbox" style="width:100px;height:25px">
				<a href="javascript:search()" class="easyui-linkbutton" iconCls="icon-search">Search</a>
			</div>
		</div>
		
		<div data-options="region:'center',title:'收费项目列表',iconCls:'icon-ok'">
			<table  id="formTable" class="easyui-datagrid"
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
				
			</table>
		</div>
		
		<div id="win" class="easyui-window" title="收费项目-修改" style="width:600px;height:400px"
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
				    		<tr style="display:none;">
				    			<td>管理处ID:</td>
				    			<td>
				    				<input id="pManagerId" class="easyui-textbox" type="text" name="pManagerId" readonly="readonly"></input>
				    			</td>
				    		</tr>
				    		<tr>				    		
				    			<td>项目代码:</td>
				    			<td>
				    				<input id="chargeId" class="easyui-textbox" type="text" name="chargeId" readonly="readonly"></input>
				    			</td>
				    			<td>项目名称:</td>
				    			<td>
				    				<input id="chargeName" class="easyui-textbox" type="text" name="chargeName"></input>
				    			</td>
				    		</tr>
				    		
				    		<tr>
				    			<td>收费方式:</td>
				    			<td>
					    			<select id="billWay" class="easyui-combobox" name="chargeWay" style="width:150px;">
					    				<option value="1">周期性</option>
					    				<option value="2">临时性</option>
					    				<option value="3">一次性</option>
					    			</select>
				    			</td>
				    			
				    			<td>收费类型:</td>
				    			<td>
				    				<select class="easyui-combobox" name="chargeClassify" style="width:150px;">
					    				<option value="1">正常</option>
					    				<option value="2">押金</option>
					    			</select>
				    			</td>
				    		</tr>
				    		
				    		<tr>
				    			<td>计费方式:</td>
				    			<td>
				    				<select class="easyui-combobox" name="chargeBillingWay" style="width:150px;">
					    				<option value="1">建面：单价*建筑面积</option>
					    				<option value="2">抄表：单价*度数</option>
					    				<option value="3">定额：金额=单价</option>
					    			</select>
				    			</td>
				    			
				    			<td>计费周期数:</td>
				    			<td>
				    				<select id="cycleCount" class="easyui-combobox"  name="chargeBillingCycleCount" style="width:150px;">
					    				<option value="0">循环</option>
					    			</select>
				    			</td>
				    		</tr>
				    		
				    		<tr>
				    			<td>计费周期单位:</td>
				    			<td>
				    				<select id="cycleUnit" class="easyui-combobox" name="chargeBillingCycleUnit" style="width:150px;">
					    				<option value="1">月</option>
					    				<!-- <option value="2">日</option> -->
					    			</select>
				    			</td>
				   
				    			<td>计费单价:</td>
				    			<td>
				    				<input id="chargeBillingUnitPrice" class="easyui-numberbox"  min="0.1"  max="10000" precision="2" required="required" name="chargeBillingUnitPrice"></input>
				    			</td>
				    		</tr>
				    		
				    		<tr>
				    			<td>滞纳金(%):</td>
				    			<td>
				    				<input id="chargeOverdueFine" class="easyui-numberbox" value="0" min="0"  max="100" required="required" type="text" name="chargeOverdueFine" ></input>
				    			</td>
				    			<td>打印次序:</td>
				    			<td>
				    				<input id="printOrder" class="easyui-numberbox" min="1"  max="20" required="required" type="text" name="printOrder"></input>
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
	<script type="text/javascript" src="/ezsh/js/charge/item_list.js"></script>
</html>
