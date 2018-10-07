<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/common/header.jsp" %>
<!DOCTYPE html>
<html>
	<head> 
	<title>房间收费项目新增</title>
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
			<div region="center" border="false" >
				<form id="formInfo" method="post">
			    	<table class="tableStyle" cellpadding="5">
			    		<tr>				    		    			
			    			<td>管理处:</td>
			    			<td>
			    				<input  id="pManagerId" class="easyui-combobox" name="pManagerId"   
                      				data-options="valueField:'managerId',
                      				required:true,textField:'managerName',
                      				editable:false,
                      				  panelHeight: 80,
                      				url:'/ezsh/build/findManager',
                      				onSelect: function(rec){	
								    var url = '/ezsh/charge/selectByManagerId?pManagerId='+rec.managerId;
								    	$('#pChargeItemId').combobox('reload', url);
								    },
								    onLoadSuccess:function(data){
									    var url = '/ezsh/charge/selectByManagerId?pManagerId='+data[0].managerId;
										$('#pChargeItemId').combobox('reload', url);
								    }
                      				"/> 
			    			</td>
			    		</tr>
			    		
			    		<tr>
			    			<td style="display:none">
			    				<input id="pBuildId"  class="easyui-textbox" type="text" name="pBuildId"/>
			    			</td>
			    		</tr>
			    		
			    		<tr>
			    			<td style="display:none">
			    				<input id="pRoomId"  class="easyui-textbox" type="text" name="pRoomId"/>
			    			</td>
			    			<td>房间编号:</td>
			    			<td>
			    				<input id="roomNum" class="easyui-textbox" readonly="readonly" type="text" name="roomNum" required="required"/>
			    			</td>
			    		</tr>
			    		
			    		<tr>
			    			<td>收费项目:</td>
			    			<td>
			    				<input id="pChargeItemId" class="easyui-combobox" name="pChargeItemId"
                        			data-options="valueField:'chargeId',
                        			editable:false,
                        			required:true,
                        			textField:'chargeName',
				                    panelHeight: 80,
                        			"
                       			 />
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
	<script type="text/javascript" src="/ezsh/js/charge/room_charge_add.js"></script>
</html>