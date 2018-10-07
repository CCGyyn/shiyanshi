<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/common/common.jspf" %>
<!DOCTYPE html>
<html>
	<head>
		<title>管理处</title>
		<style type="text/css">
		.searchbox{
			margin:-3
		}
		</style>
	</head>
	<body class="easyui-layout">  
	
		<!-- search condition start --> 
		<div data-options="region:'north'," title="查询条件" style="height:100px">
			<table style="font-size: 10px; margin-top: 15px;margin-left: 15px"cellspacing="10" >
		        <tr>
		        	<td>
			        	<span>查询条件：</span>
						<select id="conditions" name="conditions" class="easyui-combobox" style="width:150px;font-size: 10px">
							<!-- <option value="managerId">管理处编号</option>   -->
							<option value="managerName" >管理处名称</option> 
							<option value="managerAddr" >管理处地址</option> 
							<option value="head" >负责人</option> 
							<option value="contact" >联系人</option> 
							<option value="contactNum" >联系电话</option>                           
						</select>
		        	</td>
		        	
		        	<td>
		        		<span>关键字 ：</span>
				        <input id="keyword" type="text" class="easyui-textbox" style="width:150px"/>                     
		        	</td>
		        	
		        	<td>
		        		 <a id="search"  href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a>      
		        	</td>
		        </tr>
		    </table>
		</div>
	    <!-- search condition end --> 
	    
	    <!-- table start-->  
	    <div data-options="region:'center',title:'管理处列表'" style="padding:5px;background:#eee;">
	    	<table id="dg" ></table>
	    </div>
	    <!-- table end-->   
	</body>
	
	<script type="text/javascript" src="/ezsh/js/management/list.js"></script>
	<script type="text/javascript" src="/ezsh/js/management/search.js"></script>
</html>