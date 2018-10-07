<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/common/header.jsp" %>
<!DOCTYPE html>
<html>
	<head> 
	<title>出租记录新增</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">    
		<meta http-equiv="description" content="出租记录列表">
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
			    			<td style="display:none">
			    				<input id="managerName"  class="easyui-textbox" type="text" name="managerName"/>
			    			</td>				    		    			
			    			<td>管理处:</td>
			    			<td>
			    				<input  id="ptManagerId" class="easyui-combobox" readonly="readonly" style="width:200px;" name="ptManagerId"   
                      				data-options="valueField:'managerId',
                      				required:true,
                      				textField:'managerName',
                      				 panelHeight: 80,
                      				url:'/ezsh/build/findManager',
                      				onSelect: function(rec){	
								    	
								    }
                      				"/> 
			    			</td>
			    		</tr>
			    		
			    		<tr>
			    			<td style="display:none">
			    				<input id="buildName"  class="easyui-textbox" type="text" name="buildName"/>
			    			</td>
			    			<td style="display:none">
			    				<input id="ptBuildId"  class="easyui-textbox" type="text" name="ptBuildId"/>
			    			</td>
			    		</tr>
			    		
			    		<tr>
			    			<td>出租人姓名:</td>
			    			<td>
			    				<input id="rentName"  class="easyui-textbox" style="width:200px;" type="text" name="rentName"/>
			    			</td>
			    		</tr>
			    		
			    		<tr>
			    			<td>联系方式:</td>
			    			<td>
			    				<input id="rentTelephone"  class="easyui-textbox" style="width:200px;" type="text" name="rentTelephone"/>
			    			</td>
			    		</tr>
			    		
			    		<tr>
			    			<td style="display:none">
			    				<input id="ptRoomId"  class="easyui-textbox" type="text" name="ptRoomId"/>
			    			</td>
			    			<td>房间编号:</td>
			    			<td>
			    				<input id="roomNum" class="easyui-textbox" style="width:200px;" type="text" readonly="readonly" name="roomNum" required="required"/>
			    			</td>
			    		</tr>
			    		
			    		<tr>
			    			<td>出租类型:</td>
			    			<td>
			    				<select id="rentClassify" class="easyui-combobox" data-options="editable:false" name="rentClassify"  style="width:200px;">
								    <option value="房屋">房屋</option>
								    <option value="车位">车位</option>
								    <option value="商铺">商铺</option>
								</select>
                       		</td>			    			
			    		</tr>
		    			
		    			<tr>
			    			<td>详细描述:</td>
			    			<td>
			    				<input class="easyui-textbox" data-options="multiline:true" value="" name="rentDescr"  style="width:200px;height:140px">
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
	
	<script type="text/javascript" src="/ezsh/js/rent/add.js"></script>
</html>
