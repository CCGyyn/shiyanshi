<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/common/header.jsp" %>
<!DOCTYPE html>
<html>
	<head> 
	<title>收费项目新增</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">    
		<meta http-equiv="description" content="表计类别列表">
		<style type="text/css">
			.tableStyle{
				font-size:12px;
				padding:5% 0px 0px 5%;
			}
		</style>
	</head>
	<body>
		<div class="easyui-layout" fit="true">
			<div region="center" border="false" >
				<form id="formInfo" method="post">
					<table class="tableStyle" cellpadding="5">
			    		<tr>				    		
			    			<td>管理处:</td>
			    			<td>
			    				<input  id="pManagerId" class="easyui-combobox" name="pManagerId"   
                      				data-options="valueField:'managerId',
                      				required:true,textField:'managerName',
                      				url:'/ezsh/build/findManager',
                      				editable:false,
                      				 panelHeight: 80,
                      				onSelect: function(rec){	
								    var url = '/ezsh/charge/selectByManagerId?pManagerId='+rec.managerId;
								    $('#pChargeItemId').combobox('reload', url);
								    }
                      				"/> 
			    			</td>
			    			<td>项目名称:</td>
			    			<td>
			    			<input id="chargeName" class="easyui-textbox" required="required" type="text" name="chargeName"></input>
			    			</td>
			    		</tr>
			    		
			    		<tr>
			    			<td>收费方式:</td>
			    			<td>
				    			<select id="billWay" data-options="editable:false" class="easyui-combobox" name="chargeWay" style="width:150px;">
				    				<option value="1">周期性</option>
				    				<option value="2">临时性</option>
				    				<option value="3">一次性</option>
				    			</select>
			    			</td>
			    			
			    			<td>收费类型:</td>
			    			<td>
			    				<select data-options="editable:false" class="easyui-combobox" name="chargeClassify" style="width:150px;">
				    				<option value="1">正常</option>
				    				<!--<option value="2">押金</option> -->
				    			</select>
			    			</td>
			    		</tr>
			    		
			    		<tr>
			    			<td>计费方式:</td>
			    			<td>
			    				<select data-options="editable:false" class="easyui-combobox" name="chargeBillingWay" style="width:150px;">
				    				<option value="1">建面：单价*建筑面积</option>
				    				<option value="2">抄表：单价*度数</option>
				    				<option value="3">定额：金额=单价</option>
				    			</select>
			    			</td>
			    			
			    			<td>计费周期数:</td>
			    			<td>
			    				<select data-options="editable:false" id="cycleCount" class="easyui-combobox"  name="chargeBillingCycleCount" style="width:150px;">
				    				<option value="0">循环</option>
				    			</select>
			    			</td>
			    		</tr>
			    		
			    		<tr>
			    			<td>计费周期单位:</td>
			    			<td>
			    				<select data-options="editable:false" id="cycleUnit" class="easyui-combobox" name="chargeBillingCycleUnit" style="width:150px;">
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
	</body>
	
	<script type="text/javascript" src="/ezsh/js/charge/itemAdd.js"></script>
</html>
