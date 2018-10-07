<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/common/header.jsp" %>
<!DOCTYPE html>
<html>
	<head> 
	<title>表计类别列表</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">    
		<meta http-equiv="description" content="表计类别列表">
		<style type="text/css">
			.tableStyle{
				font-size:12px;
				padding:10% 0px 0px 10%;
			}
		</style>
	</head>
	
	<body>
		<div class="easyui-layout" fit="true">
			<div data-options="region:'west',split:false," title="选择房间" style="width:180px">
				<ul id="tree"></ul>
			</div>
			<div region="center" border="false" >
				<form id="formInfo" method="post">
			    	<table class="tableStyle" cellpadding="5">
			    		<tr>
			    			<td>管理处:</td>
			    			<td>
			    				<input  id="pManagerId" class="easyui-combobox" name="pManagerId"   
                       				data-options="valueField:'managerId',
                       				required:true,textField:'managerName',
                       				 panelHeight: 80,
                       				url:'/ezsh/build/findManager',
                       			"/> 
                         	</td>
			    		</tr>
			    		<tr>
			    			<td style="display:none;">
			    				<input id="pBuildId" class="easyui-textbox" type="text" name="pBuildId"></input>
			    			</td>
			    			<td style="display:none;">
			    				<input id="buildName" class="easyui-textbox" type="text" name="buildName"></input>
			    			</td>
			    		</tr>
			    			
			    		<tr>	
			    			<td>表计类别:</td>
			    			<td>
		    				<input  id="pChargeItemId" class="easyui-combobox" name="pChargeItemId"   
                         			data-options="valueField:'chargeId',
                         			required:true,textField:'chargeName'
                         			 panelHeight: 80,
                        			" />
                        	</td>
			    		</tr>
			    		
			    		<tr>
			    			<td style="display:none;">
			    				<input id="pRoomId" class="easyui-textbox" type="text" name="pRoomId"></input>
			    			</td>	
			    			<td>房间编号:</td>
			    			<td><input id="roomNum" class="easyui-textbox" type="text" name="roomNum"></input></td>
			    		</tr>
						<tr>
			    			<td>用量:</td>
			    			<td><input id="dosage" class="easyui-textbox" type="text" name="dosage"></input></td>
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
	<script type="text/javascript" src="/ezsh/js/grid/recordAdd.js"></script>
</html>
