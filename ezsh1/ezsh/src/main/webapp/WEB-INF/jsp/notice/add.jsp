<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/common/header.jsp" %>
<!DOCTYPE html>
<html>
	<head> 
	<title>添加公告</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">    
		<meta http-equiv="description" content="添加公告">
		<style type="text/css">
			.tableStyle{
				font-size:12px;
				padding:2% 0px 0px 10%;
			}
		</style>
	</head>
	
	<body>
		<div class="easyui-layout" fit="true">
			<div region="center" border="false" >
				<form id="formInfo" method="post">
			    	<table class="tableStyle" cellpadding="5">
			    		<tr>
			    			<td>管理处</td>
			    			<td><input  id="ptManagerId" class="easyui-combobox" name="ptManagerId"   
		                      	data-options="valueField:'managerId',
		                        required:true,textField:'managerName',
		                         panelHeight: 80,
		                      	url:'/ezsh/build/findManager',
		                      	onSelect: function(rec){	
									var url = '/ezsh/charge/selectByManagerId?pManagerId='+rec.managerId;
									$('#pChargeItemId').combobox('reload', url);
								}
		                      	"/>	
                      		</td>
			    		</tr>
			    			
			    		<tr>	
			    			<td>标题名称:</td>
			    			<td>
			    				<input id="noticeTitle" class="easyui-textbox" type="text" name="noticeTitle" required="required" style="width:100%;"/>
			    			</td>
			    		</tr>
	
			    		<tr>
			    			<td>公告内容:</td>
			    			<td>
			    				<div id="editorImgText" style="width:500px;">
							        <p>在此处编辑公告内容！</p>
							    </div>
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
	<script type="text/javascript" src="/ezsh/wang-Editor/wangEditor.min.js"></script>
	<script type="text/javascript" src="/ezsh/js/notice/add.js"></script>
</html>
