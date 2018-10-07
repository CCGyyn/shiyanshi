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
		<meta http-equiv="description" content="公告列表">
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
			</div>
		</div>
		
		<div data-options="region:'north',split:false," title="查询条件" style="height:100px">
			<div style="padding:15px;height:auto">
				<!-- <span>管理处:</span>
			    <input  id="ptManagerId" class="easyui-combobox" name="ptManagerId"   
                      	data-options="valueField:'managerId',
                        required:true,textField:'managerName',
                      	url:'/ezsh/build/findManager',
                      	onSelect: function(rec){	
						var url = '/ezsh/charge/selectByManagerId?pManagerId='+rec.managerId;
						$('#pChargeItemId').combobox('reload', url);
						}
                      	"/>	 -->
				标题查询: 
				<input id="noticeTitle" class="easyui-textbox" style="width:120px;height:25px">
				<a href="javascript:search()" class="easyui-linkbutton" iconCls="icon-search">Search</a>
			</div>
		</div>
		
		<!-- tree start -->
		<div data-options="region:'west',title:'楼宇',split:true" style="width:150px;">
			<ul class="easyui-tree" id="tree">
		        
		    </ul>
		</div>
		<!-- tree end -->
		
		<div data-options="region:'center',title:'公告列表',iconCls:'icon-ok'">
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
		
		<div id="win" class="easyui-window" title="公告信息修改" style="width:650px;height:450px"
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
				    				<input id="noticeId" class="easyui-textbox" type="text" name="noticeId" required="required" style="width:100%;"/>
				    			</td>
				    		</tr>
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
		</div>		
	</body>
	<script type="text/javascript" src="/ezsh/wang-Editor/wangEditor.min.js"></script>
	<script type="text/javascript" src="/ezsh/js/notice/list.js"></script>
</html>
