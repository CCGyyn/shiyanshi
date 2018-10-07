<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="/common/common.jspf"%>
<html>
	<head>
	<title>楼宇列表</title>
	</head>

	<body class="easyui-layout">   
	    <div data-options="region:'north',title:'查询',split:true" style="height:100px;">
		    <table style="font-size: 10px; margin-top: 15px;margin-left: 15px"cellspacing="10" >
		        <tr>
			        <td>
				        <span>管理处：</span>
				        <input id="buildManagerId"  class="easyui-combobox" name="buildManagerId"   
							data-options="valueField:'managerId',textField:'managerName',  panelHeight: 80, url:'${proPath}/build/findManager.action'" />
			        </td>
		            <td>
			            <span>查询条件：</span>
				        <select id="conditions" class="easyui-combobox" style="width:150px;font-size: 10px">
				            <option value="buildName" >楼宇名称</option> 
				            <option value="buildAddr" >楼宇地址</option> 
				            <option value="buildType" >楼宇类型</option> 
				            <option value="remark" >备注</option> 
			      		</select>
		      		</td>
		      		<td>
		      			<span>关键字 ：</span>
		                <input id="keyword" type="text" style="width:150px"/>   
		            </td>
		            <td>                  
						<a id="search"  href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a>  
		            </td> 
		        </tr>   
		    </table>
	    </div>
	       
	    <div data-options="region:'center',title:'楼宇列表'" style="padding:5px;background:#eee;">
	           <table id="dg" ></table>
	    </div>   
	</body>
	<script type="text/javascript" src="/ezsh/js/building/list.js"></script>  
	<script type="text/javascript" src="/ezsh/js/building/search.js"></script>
</html>