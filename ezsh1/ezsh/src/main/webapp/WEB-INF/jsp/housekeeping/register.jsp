<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/common/common.jspf" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table id="box"></table>
	<div id="tb" style="padding:5px">
		<a class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="housekeeping.registerWin()">注册</a>
		<a class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="housekeeping.addHkTypeWin()">添加服务类型</a>	
		<a class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="housekeeping.addHkItemWin()">添加服务项目</a>	
		<!-- <a class="easyui-linkbutton" data-options="iconCls:'icon-search'"  onclick="housekeeping.getdescription()">查看服务详情描述</a> -->
	</div>
	<div id="descWindow" class="easyui-window" title="服务详情描述" modal="true" data-options="iconCls:'icon-save'" style="top:40px;width:285px;height:300px;padding:5px;" closed="true">
		<textarea id="desc" rows="20" cols="32">
		
		</textarea>
	</div>
	
	<div id="hkTypeWin" class="easyui-window" title="服务类型" modal="true" data-options="iconCls:'icon-save'" style="top:20px;width:520px;height:320px;padding-top:20px;" closed="true">
		<form enctype="multipart/form-data" id="form1">
		 <table align="center" width="400" border="0" cellspacing="20" >
		 	<tr>
		 		<th align="right"><span style="font-size:16px">服务类型名称:</span></th>
		 		<td><input  style="height:30px;width:200px;font-size:20px" type="text" name="hkType" id="hkType" /></td>
		 	</tr>
		
		 </table>
		<div style="margin-top:50px;margin-bottom:50px">
			<a class="easyui-linkbutton" data-options="iconCls:'icon-ok'" style="margin-right:100px;margin-left:100px" onclick="housekeeping.addHkType()">确定</a>
			<a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="$('#hkTypeWin').window('close')">取消</a>
		</div> 
		</form>
	</div> 
	
	<div id="hkItemWin" class="easyui-window" title="服务项目" modal="true" data-options="iconCls:'icon-save'" style="top:20px;width:520px;height:320px;padding-top:20px;" closed="true">
		<form enctype="multipart/form-data" id="form1">
		 <table align="center" width="400" border="0" cellspacing="20" >
		 	<tr>
		 		<th align="right"><span style="font-size:16px">服务类型名称:</span></th>
		 		<td><input class="easyui-combobox" name="language" id="selectItem" data-options="
					url:'http://localhost:8888/ezsh/housekeeping/getTypeList?hkId=1',
					method:'get',
					valueField:'typeId',
					textField:'hkType',
					 panelHeight: 80,
					onSelect:function(){  
                       selectType();  
                    }  
					"></td>
		 	</tr>
		 	<tr>
		 		<th align="right"><span style="font-size:16px">服务项目名称:</span></th>
		 		<td><input  style="height:30px;width:200px;font-size:20px" type="text" name="item" id="item" /></td>
		 	</tr>
			
			<tr>
		 		<th align="right"><span style="font-size:16px">服务费用(元/次):</span></th>
		 		<td><input  style="height:30px;width:200px;font-size:20px" type="number" name="price" id="price" /></td>
		 	</tr>
		 	
		 </table>
		<div style="margin-top:50px;margin-bottom:50px">
			<a class="easyui-linkbutton" data-options="iconCls:'icon-ok'" style="margin-right:100px;margin-left:100px" onclick="housekeeping.addHkItem()">确定</a>
			<a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="$('#hkItemWin').window('close')">取消</a>
		</div> 
		</form>
	</div> 
	
	<div id="registerWin" class="easyui-window" title="注册窗口" modal="true" data-options="iconCls:'icon-save'" style="top:20px;width:520px;height:520px;padding-top:20px;" closed="true">
		<form enctype="multipart/form-data" id="form1">
		 <table align="center" width="400" border="0" cellspacing="20" >
		 	<tr>
		 		<th align="right"><span style="font-size:16px">家政服务公司名称:</span></th>
		 		<td><input  style="height:30px;width:200px;font-size:20px" type="text" name="hkName" id="hkName" /></td>
		 	</tr>
		 	<tr>
		 		<th align="right"><span style="font-size:16px">家政服务公司负责人:</span></th>
		 		<td><input  style="height:30px;width:200px;font-size:20px" type="text" name="hkLeader" id="hkName" /></td>
		 	</tr>
		 	<tr>
		 		<th align="right"><span style="font-size:16px">服务热线:</span></th>
		 		<td><input  style="height:30px;width:200px;font-size:20px" type="text" name="hkPhone" id="hkPhone" /></td>
		 	</tr>
		 	<tr>
		 		<th align="right"><span style="font-size:16px">员工人数:</span></th>
		 		<td><input  style="height:30px;width:200px;font-size:20px" type="text" name="hkStaff" id="hkStaff" /></td>
		 	</tr>
		 	<tr>
		 		<th align="right"><span style="font-size:16px">介绍:</span></th>
		 		<td><input  style="height:30px;width:200px;font-size:20px" type="text" name="introduction" id="introduction" /></td>
		 	</tr>
		 	<tr>
		 		<th align="right"><span style="font-size:16px">公司图标:</span></th>
		 		<td><input  style="height:30px;width:200px;font-size:20px" type="file" name="imgs" id="imgs" /></td>
		 	</tr>
		 </table>
		<div style="margin-top:50px;margin-bottom:50px">
			<a class="easyui-linkbutton" data-options="iconCls:'icon-ok'" style="margin-right:100px;margin-left:100px" onclick="housekeeping.register()">确定</a>
			<a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="$('#registerWin').window('close')">取消</a>
		</div> 
		</form>
	</div> 
</body>

<script type="text/javascript">
	var typeId = 0;
	$(function(){
		$("#box").datagrid({
			width:1150,
			url:"${proPath }" + '/housekeeping/getList',
			title:'家政服务公司列表',
			columns:[[
				{field:'hkId',title:'ID',width:20},
				{field:'communityId',title:'小区编号ID',width:20},
				{field:'hkName',title:'公司名称',width:30},
				{field:'hkLeader',title:'负责人',width:30},
				{field:'hkPhone',title:'服务热线',width:30},
				{field:'hkStaff',title:'员工人数',width:20},
				{field:'introduction',title:'公司介绍',width:60},
				
				
			]],
			pagination:true,
			fitColumns:true,
			toolbar:"#tb"
			
		});
	});
	
	function selectType(){
		//获取选择的服务类型id 
    	typeId = $('#selectItem').combobox('getValue'); 
    	alert(typeId);
    	 
	}
	
	var housekeeping = {
	//注册窗口
	 registerWin:function(){
		$("#registerWin").window('open');
		
	},
	
	//添加服务类型窗口
	 addHkTypeWin:function(){
	 	if($(".datagrid-row-selected").size()==0){
				$.messager.alert('警告','请选择一项进行操作');
				return;
			}else if($(".datagrid-row-selected").size()>1){
				$.messager.alert('警告','请只选择一项进行操作');
				return;
			}
		$("#hkTypeWin").window('open');
	
	},
	
	//添加服务类型
	addHkType:function(){
		var hkId  = $(".datagrid-row-selected").find($(".datagrid-cell-c1-hkId")).text();
		var hkType = $("#hkType").val();
		if(hkType == ""){
			alert("请补充完整所需资料");
			return;
		}
		$.ajax({
				   type: "POST",
				   url: "/ezsh/housekeeping/addHkType",
				   data: {
				   	hkId:hkId,
				   	hkType:hkType
				   },
				   success: function(data){
				   		if(data["status"] == 1){
						alert("操作成功");
						window.location.reload();
				   	}else{
						alert(data["message"]);
						window.location.reload();
				   	}
			   }
			});	
	},
	
	//添加服务项目窗口
	 addHkItemWin:function(){
	 	if($(".datagrid-row-selected").size()==0){
				$.messager.alert('警告','请选择一项进行操作');
				return;
			}else if($(".datagrid-row-selected").size()>1){
				$.messager.alert('警告','请只选择一项进行操作');
				return;
			}
		$("#hkItemWin").window('open');
	
	},
	
	//添加服务项目
	addHkItem:function(){
		if ($("#item").val() == '') {
					alert("请补充完整所需资料");
					return;
		}
		if ($("#price").val() == '') {
					alert("请补充完整所需资料");
					return;
		}
		if (typeId == 0) {
					alert("请先添加服务类型");
					return;
		}
		var price = $("#price").val();
		var item = $("#item").val();
		var hkId = $(".datagrid-row-selected").find($(".datagrid-cell-c1-hkId")).text();
		$.ajax({
				   type: "POST",
				   url: "/ezsh/housekeeping/addHkItem",
				   data: {
				   		typeId:typeId,
				   		price:price,
				   		item:item,
				   		hkId:hkId
				   },
				   success: function(data){
				   		if(data["status"] == 1){
						alert("操作成功");
						
						//window.location.reload();
				   	}else{
						alert(data["message"]);
						window.location.reload();
				   	}
			   }
			});	
		
	},
	
	//注册
	register:function(){
			if ($("#hkName").val() == ''
						|| $("#hkLeader").val() == ''
						|| $("#hkPhone").val() == ''
						|| $("#hkStaff").val() == ''
						|| $("#hkName").val() == ''
						|| $("#introduction").val() == ''
						|| $("#imgs").val() == ''
						) {
					alert("请补充完整所需资料");
					return;
				}
				
				if ($("#hkName").val() != ''
						&& $("#hkLeader").val() != ''
						&& $("#hkPhone").val() != ''
						&& $("#hkStaff").val() != ''
						&& $("#hkName").val() != ''
						&& $("#introduction").val() != ''
						&& $("#imgs").val() != ''
					) {
					var fd = new FormData($("#form1")[0]);
					
			$.ajax({
				   type: "POST",
				   url: "/ezsh/housekeeping/register",
				   data: fd,
				   processData : false, // 告诉jQuery不要去处理发送的数据
				   contentType : false, // 告诉jQuery不要去设置Content-Type请求头
				   success: function(data){
				   		if(data["status"] == 1){
						alert("操作成功");
						window.location.reload();
				   	}else{
						alert(data["message"]);
						window.location.reload();
				   	}
			   }
			});	
			}
    	},
    
    //查看服务详情描述
    getdescription:function(){
    	if($(".datagrid-row-selected").size()==0){
				$.messager.alert('警告','请选择一项进行查看');
				return;
			}else if($(".datagrid-row-selected").size()>1){
				$.messager.alert('警告','请只选择一项进行查看');
				return;
			}
		var desc  = $(".datagrid-row-selected").find($(".datagrid-cell-c1-hkDescribe")).text();
    	$("#desc").val(desc);
    	$("#descWindow").window("open");
    }
    
    }
</script>
</html>