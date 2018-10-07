<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="/common/common.jspf"%>
<html>
	<head>
	<title>房间列表</title>
	<style type="text/css">
		.searchbox{
			margin:-3
		}
		</style>
	</head>

	<body class="easyui-layout">   
	    <div data-options="region:'north',title:'查询',split:true" style="height:120px;">
	        <table style="margin-left: 10px;margin-top: 10px;font-size: 10px"cellspacing="10">
			<tr>
				<td>
					<span>管理处：</span>            
				 	<input id="managerId" class="easyui-combobox" data-options="    
					valueField: 'managerId',    
					textField: 'managerName',
					 panelHeight: 80,
					url: '${proPath }/build/findManager.action',    
					onSelect: function(rec){    
						var url = '${proPath }/room/findBuild.action?managerId='+rec.managerId;    
						$('#buildId').combobox('reload', url);    
					}" />   
				</td>    
				<td>
					<span>楼宇：</span>            
					<input id="buildId" class="easyui-combobox" data-options="valueField:'buildId',textField:'buildName'" />  
				</td>   
				<td>
					<span>查询条件：</span>               
				    <select id="conditions" style="width:150px;font-size: 10px">
						<option value="">--请选择--</option>
						<!-- <option value="roomId">房间编号</option>   -->
						<option value="buildArea" >建筑面积</option> 
						<option value="roomFloor">楼层数</option> 
						<option value="roomNum" >房间号</option> 
						<option value="chargeMan" >收费服务对象</option> 
						<option value="roomUse" >房间用途</option> 
						<option value="position" >位置</option> 
						<option value="toward" >朝向</option> 
						<option value="rent" >租金</option> 
						<option value="managementFee" >管理费</option> 
						<option value="sumPrice" >总价</option> 
						<option value="singlePrice" >单价</option> 
					</select>
				</td>   
				<td>
					<span>关键字 ：</span>            
					<input id="keyword" type="text" style="width:150px"/>     
				</td>    
			</tr>
			
	        <tr>
				<td>
					<span>房间类型：</span>            
			    	<select id="roomType" style="width:150px;font-size: 12px">
						<option value="">--请选择--</option>
						<option value="0">住宅</option>  
						<option value="1" >公寓</option> 
						<option value="2" >办公</option> 
						<option value="3" >厂房</option> 
						<option value="4" >仓库</option> 
						<option value="5" >宿舍</option> 
						<option value="6" >其他</option> 
					</select>
				</td>    
				<td>             
					<span>房间状态：</span>  
					<select id="roomStatus" style="width:150px;font-size: 12px">
						<option value="" >--请选择--</option>
						<option value="0">未售</option>  
						<option value="1" >已售</option> 
						<option value="2" >未租</option> 
						<option value="3" >已租</option> 
						<option value="4" >自用</option> 
						<option value="5" >入住</option> 
						<option value="6" >空置</option> 
						<option value="7" >未交付</option> 
					</select>
				</td>  
				<td>
				     <a id="search"  href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a>  
				</td>  
	        </tr>
	        </table>   
	    </div>
	       
	    <div data-options="region:'west',iconCls:'icon-reload',title:'楼宇',split:true" style="width:130px;">
	       <ul id="tt" class="easyui-tree" data-options="url:'${proPath }/room/getBuildTree.action'"></ul> 
	    </div>
	       
	    <div data-options="region:'center',title:'房间列表'" style="padding:5px;background:#eee;">
	    	<table id="dg" ></table>
	    </div>   
	</body>
	<script type="text/javascript" src="/ezsh/js/room/list.js"></script>  
    <script type="text/javascript" src="/ezsh/js/room/search.js"></script>
</html>