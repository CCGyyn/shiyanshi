<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/common/header.jsp" %>
<!DOCTYPE html>
<html>
	<head> 
	<title>消息列表</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">    
		<meta http-equiv="description" content="消息列表">
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
				<a href="javascript:openPass()" class="easyui-linkbutton" iconCls="fa fa-check fa-fw fa-lg" plain="true"></a>
				<!-- <a href="javascript:edit()" class="easyui-linkbutton" iconCls="icon-edit" plain="true"></a> -->
				<a href="javascript:failure()" class="easyui-linkbutton" iconCls="fa fa-times-circle fa-fw fa-lg" plain="true"></a>
			</div>
		</div>
		
		<!-- query module start -->
		<div data-options="region:'north',split:false," title="查询条件" style="height:100px">
			<div style="padding:15px;height:auto">
				身份证查询: 
				<input id="s_identifyCard" class="easyui-textbox" style="width:150px;height:25px">
				<a href="javascript:search()" class="easyui-linkbutton" iconCls="icon-search">Search</a>
			</div>
		</div>
		<!-- tree end -->
		
		<!-- tree start -->
		<div data-options="region:'west',title:'楼宇',split:true" style="width:150px;">
			<ul class="easyui-tree" id="tree">
		        
		    </ul>
		</div>
		<!-- tree end -->
		
		<div data-options="region:'center',title:'记录列表',iconCls:'icon-ok'">
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
		
		<div id="win" class="easyui-window" title="修改" style="width:650px;height:450px"
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
				    				<input id="newsId" class="easyui-textbox" type="text" name="newsId" required="required" style="width:100%;"/>
				    			</td>
				    		</tr>
				    		<!-- <tr>
				    			<td>管理处</td>
				    			<td><input  id="ptManagerId" class="easyui-combobox" name="ptManagerId"   
			                      	data-options="valueField:'managerId',
			                        required:true,textField:'managerName',
			                      	url:'/ezsh/build/findManager',
			                      	onSelect: function(rec){	
									var url = '/ezsh/charge/selectByManagerId?pManagerId='+rec.managerId;
									$('#pChargeItemId').combobox('reload', url);
									}
			                      	"/>	
	                      		</td>
				    		</tr> -->
				    			
				    		<tr>	
				    			<td>标题名称:</td>
				    			<td>
				    				<input id="newsTitle" class="easyui-textbox" type="text" name="newsTitle" required="required" style="width:100%;"/>
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
		
		<div id="win1" class="easyui-dialog" style="width:300px;height:250px"
			data-options="title:'请输入理由',buttons:'#bb1',modal:true,closed:true">
			<div style="margin:20px 0px 0px 20px;width:250px;height:100px">
				<input id="failureReason" class="easyui-textbox" data-options="multiline:true" value="请输入理由..." style="width:100%;height:100%;">
			</div>
			
		</div>
		<div id="bb1">
			<a href="javascript:subUnPass()" class="easyui-linkbutton">Save</a>
			<a href="javascript:close1()" class="easyui-linkbutton">Close</a>
		</div>
		
		<div id="win2" class="easyui-dialog" style="width:250px;height:200px"
			data-options="title:'请输入取出的公益基金金额',buttons:'#bb2',modal:true,closed:true">
			<div style="margin:50px 0px 0px 40px;width:150px;">
				<input id="applyMoney" class="easyui-numberbox" data-options="min:0,precision:2" required="true" style="width:100%;height:100%;">
			</div>
			
		</div>
		<div id="bb2">
			<a href="javascript:pass()" class="easyui-linkbutton">确定</a>
			<a href="javascript:close2()" class="easyui-linkbutton">关闭</a>
		</div>			
	</body>
	<script type="text/javascript">
		$(function(){
			$('#tree').tree({
			    url:'/ezsh/tree/gtTreeUserId',
			    lines:true
			});
		})
		
		var managerId = 0, managerName = null, buildId = 0, buildName = null, roomId = '0', roomNum = null, userId = 0, userName = null;
		$('#tree').tree({
			onClick: function(node){
				 // alert node text property when clicked
				if (node.id.substring(0, node.id.indexOf("-")) == 0){ //点击了管理处
					managerId = node.id.substring(node.id.indexOf("-")+1);
				}
				if (node.id.substring(0, node.id.indexOf("-")) == 2) { //点击房间节点
					var swap = node.id.substring((node.id.indexOf("-")+1)); // 管理处ID-楼宇ID-房间ID-userId
					var selected = $('#tree').tree('getSelected');
					var nodeParent = $("#tree").tree("getParent",selected.target); // 获取父节点（管理处节点）
					var nodeParent1 = $("#tree").tree("getParent",nodeParent.target); // 获取父父节点（楼宇节点）
					managerId = swap.substring(0,swap.indexOf("-")); // 管理处ID
					managerName = nodeParent1.text; // 管理处名称
					
					swap = swap.substring((swap.indexOf("-") + 1)); // 楼宇ID-房间ID-userId
					var buildId = swap.substring(0,swap.indexOf("-")); // 楼宇ID
					
					swap = swap.substring((swap.indexOf("-") + 1)); // 房间ID-userId
					var roomId = swap.substring(0,swap.indexOf("-")); // 房间ID
					userId = swap.substring((swap.indexOf("-") + 1)); //用户ID
					
					//2-管理处ID-楼宇ID-房间ID-userId
					roomNum = node.text.substring(0,node.text.indexOf("|"));
					userName = node.text.substring(node.text.indexOf("|") + 1);
					$('#formTable').datagrid({
						queryParams: {
							ptManagerId:managerId,
							ptBuildId:buildId,
							ptUserId:userId
						}
					});	
				}	
			}
		});
	</script>
    
	<script type="text/javascript">
	/**
	 * 初始化列表
	 */
	$('#formTable').datagrid({
	    url:'/ezsh/benefitApplyA/select',
	    columns:[[
			{field:'benefitApplyId',title:'记录ID',width:100,align:'center'},
			{field:'ptUserId',title:'用户ID',width:100,align:'center'},
			{field:'ptManagerId',hidden:true,title:'指向管理处ID',width:100,align:'center'},
			{field:'ptManagerName',title:'管理处名',width:100,align:'center'},
			{field:'illness',title:'重大疾病',width:150,align:'center'},			
			{field:'linkPhone',title:'联系号码',width:100,align:'center'},
			{field:'identifyCard',title:'身份证号码',width:200,align:'center'},
			{field:'accidentExplain',title:'事故说明',width:200,align:'center'},
			{field:'identificationImgs',hidden:true,title:'签定材料',width:100,align:'center'},
			{field:'checkStatus',title:'审核状态',width:100,align:'center',
				formatter: function(value,row,index){
					if (value == 0) {
						return "待审核";
					} else if (value == 1) {
						return "审核通过";
					} else if (value == 2) {
						return "审核失败";
					}		
				}
			},
			{field:'failureReason',title:'不通过原因',width:100,align:'center'}	
	    ]],
	    view: detailview,
	    detailFormatter: function(rowIndex, rowData){
	    	var str = rowData.identificationImgs;
	    	var imgArray = new Array();
	    	imgArray = str.split(",");
	    	var html ="<table><tr>";
	    	for(var i = 0; i < imgArray.length; i++){
	    		html += '<td rowspan=2 style="border:0">' + "鉴定材料"+ (i+1) + '</td>' +
				'<td rowspan=2 style="border:0"><img src="' + imgArray[i] + '" style="height:50px;"></td>' +
				'<td style="border:0">' +
				'</td>';
	    	}
	    	html +="</tr></table>";
			return html;
		}
	});

	function search(){
		if(managerId != null && managerId != 0){
			$('#formTable').datagrid({
				queryParams: {
					ptManagerId: managerId,
					identifyCard: $("#s_identifyCard").val()
				}
			});	
		} else {
			$.messager.alert('提示','请选择小区!','info');	
		}
		
	}
	
	function pass(){
		var row = $('#formTable').datagrid('getSelected');
		if (row) {
			if($("#applyMoney").val() == null || $("#applyMoney").val() == ''){
				$.messager.alert('提示', "金额不能为空", 'info');
				return;
			}
			$.messager.confirm('提示','确定通过审核?确定后将从小区的公益基金里扣除这笔费用。',function(r){
			    if (r){
					$.ajax({
					    type: "GET",
					    url:"/ezsh/benefitApplyA/check",
					    data:{benefitApplyId:row.benefitApplyId,checkStatus:1,
					    	ptManagerId:row.ptManagerId,applyMoney:$("#applyMoney").val()},
					    dataType:"json",
					    success: function(data){
						    if(data.status>0){
						    	$.messager.alert('提示', data.message, 'info');
						    }else{
						    	$.messager.alert('提示', data.message, 'info');
						    }
						    $('#win2').window('close');
						    $('#formTable').datagrid('reload');
						}
					})
			    }
			});
		} else {
			$.messager.alert('Warning','至少选择一条数据');
		}
	}
	
	function openPass(){
		var row = $('#formTable').datagrid('getSelected');
		if (row) {
			if(row.checkStatus){
				$.messager.alert('Warning','已通过审核，不能操作');
				return;
			}
			$('#win2').window('open');
		} else {
			$.messager.alert('Warning','至少选择一条数据');
		}
	}
	
	function failure(){
		var row = $('#formTable').datagrid('getSelected');
		if (row) {
			if(row.checkStatus){
				$.messager.alert('Warning','已通过审核，不能操作');
				return;
			}
			$('#win1').window('open');
		} else {
			$.messager.alert('Warning','至少选择一条数据');
		}
	}
	
	function subUnPass(){
		var row = $('#formTable').datagrid('getSelected');
		$.ajax({
		    type: "POST",
		    url:"/ezsh/benefitApplyA/check",
		    data:{benefitApplyId:row.benefitApplyId,ptManagerId:row.ptManagerId,checkStatus:2,failureReason:$('#failureReason').textbox('getValue')},
		    dataType:"json",
		    success: function(data){
			    if(data.status>0){
			    	$.messager.alert('提示', data.message, 'info');
			    }else{
			    	$.messager.alert('提示', data.message, 'info');
			    }
			    $('#formTable').datagrid('reload');
			    $('#win1').window('close');
			}
		})
	}

	function close(){
		$('#win').window('close');
	}
	function close1(){
		$('#win1').window('close');
	}
	function close2(){
		$('#win2').window('close');
	}
	</script>
</html>
