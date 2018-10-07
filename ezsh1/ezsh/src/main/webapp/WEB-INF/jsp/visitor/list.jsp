<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/common/header.jsp" %>
<!DOCTYPE html>
<html>
	<head> 
	<title>访客记录列表</title>
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
			<!-- <div style="margin-bottom:5px">
				<a href="javascript:add()" class="easyui-linkbutton" iconCls="icon-add" plain="true"></a>
				<a href="javascript:edit()" class="easyui-linkbutton" iconCls="icon-edit" plain="true"></a>
				<a href="javascript:remove()" class="easyui-linkbutton" iconCls="icon-remove" plain="true"></a>
			</div> -->
		</div>
		
		<!-- query module start -->
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
				姓名查询: 
				<input id="visitorName" class="easyui-textbox" style="width:100px;height:25px">
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
		
		
		<!-- <div id="win" class="easyui-window" title="修改" style="width:650px;height:450px"
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
				    		<tr>
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
				    		</tr>
				    			
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
		</div>	 -->
			
	</body>
	<script type="text/javascript">
		$(function(){
			$('#tree').tree({
			    url:'/ezsh/tree/gtTreeUserId',
			    lines:true
			});
		})

	var managerId = 0, managerName = null, roomId = '0', roomNum = null, userId = 0, userName = null;
	$('#tree').tree({
		onClick : function(node) {
			// alert node text property when clicked
			if (node.id.substring(0, node.id.indexOf("-")) == 0) { // 点击了管理处
				var temp = node.id.substring(node.id.indexOf("-") + 1);
				managerId = temp.substring(temp.indexOf("-") + 1);
				$('#formTable').datagrid({
					queryParams : {
						ptManagerId : managerId
					}
				});
			}
			if (node.id.substring(0, node.id.indexOf("-")) == 1) { // 点击了楼宇
				var temp = node.id.substring(node.id.indexOf("-") + 1);
				managerId = temp.substring(0, temp.indexOf("-"));
				temp = temp.substring(temp.indexOf("-") + 1);
				buildId = temp;
				$('#formTable').datagrid({
					queryParams : {
						ptManagerId : managerId,
						ptBuildId : buildId,
						visitorName:$("#visitorName").val()
					}
				});
			}
			if (node.id.substring(0, node.id.indexOf("-")) == 2) {// 房间ID
				var swap = node.id.substring((node.id.indexOf("-") + 1));// 管理处ID-楼宇ID-房间ID-userId
				var selected = $('#tree').tree('getSelected');
				var nodeParent = $("#tree").tree("getParent", selected.target);// 获取父节点
				var nodeParent1 = $("#tree").tree("getParent", nodeParent.target);// 获取父父节点
				managerId = swap.substring(0, swap.indexOf("-"));
				managerName = nodeParent1.text;
				swap = swap.substring((swap.indexOf("-") + 1));// 楼宇ID-房间ID-userId
				swap = swap.substring((swap.indexOf("-") + 1));// 房间ID-userId
				var roomId = swap.substring(0, swap.indexOf("-"));
				userId = swap.substring((swap.indexOf("-") + 1));
	
				// 2-管理处ID-楼宇ID-房间ID-userId
				roomNum = node.text.substring(0, node.text.indexOf("|"));
				userName = node.text.substring(node.text.indexOf("|") + 1);
				$('#formTable').datagrid({
					queryParams : {
						ptManagerId : managerId,
						ptBuildId : buildId,
						ptRoomId:roomId,
						visitorName:$("#visitorName").val()
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
	    url:'/ezsh/visitRecordA/select',
	    columns:[[     
			{field:'visitorRecordId',title:'记录ID',width:100,align:'center'},
			{field:'visitoreTime',title:'实际访问时间',width:150,align:'center'},
			{field:'ptCodeId',hidden:'true',title:'指访问码记录ID',width:100,align:'center'},
			{field:'ptManagerId',hidden:'true',title:'指向管理处ID',width:100,align:'center',
				formatter: function(value,row,index){
					if(row.visitorCode != null){
						return row.visitorCode.ptManagerId;
					} 
				}
			},
			{field:'managerName',title:'管理处名',width:100,align:'center',
				formatter: function(value,row,index){
					if(row.visitorCode != null){
						return row.visitorCode.managerName;
					} 
				}
			},
			{field:'ptBuildId',hidden:'true',title:'指向楼宇ID',width:100,align:'center',
				formatter: function(value,row,index){
					if(row.visitorCode != null){
						return row.visitorCode.ptBuildId;
					} 
				}
			},
			{field:'buildName',title:'楼宇名',width:100,align:'center',
				formatter: function(value,row,index){
					if(row.visitorCode != null){
						return row.visitorCode.buildName;
					} 
				}
			},
			{field:'ptRoomId',hidden:'true',title:'指向房间ID',width:100,align:'center',
				formatter: function(value,row,index){
					if(row.visitorCode != null){
						return row.visitorCode.ptRoomId;
					} 
				}
			},
			{field:'roomNum',title:'房间号',width:100,align:'center',
				formatter: function(value,row,index){
					if(row.visitorCode != null){
						return row.visitorCode.roomNum;
					} 
				}
			},
			{field:'ptUserId',hidden:'true',title:'指向用户ID',width:100,align:'center',
				formatter: function(value,row,index){
					if(row.visitorCode != null){
						return row.visitorCode.ptUserId;
					} 
				}
			},
			{field:'ptLockId',hidden:'true',title:'指向锁ID',width:100,align:'center',
				formatter: function(value,row,index){
					if(row.visitorCode != null){
						return row.visitorCode.ptLockId;
					} 
				}
			},
			{field:'visitorName',title:'访客名称',width:100,align:'center',
				formatter: function(value,row,index){
					if(row.visitorCode != null){
						return row.visitorCode.visitorName;
					} 
				}
			},
			{field:'visitorTelephone',title:'访客联系电话',width:200,align:'center',
				formatter: function(value,row,index){
					if(row.visitorCode != null){
						return row.visitorCode.visitorTelephone;
					} 
				}
			}
	    ]]
	});

	function search(){
		var selected = $('#tree').tree('getSelected');
		if(selected){
			if (selected.id.substring(0, selected.id.indexOf("-")) == 0) { // 点击了管理处
				var temp = selected.id.substring(selected.id.indexOf("-") + 1);
				managerId = temp.substring(temp.indexOf("-") + 1);
				$('#formTable').datagrid({
					queryParams : {
						ptManagerId : managerId,
						visitorName:$("#visitorName").val()
					}
				});
			}
			if (selected.id.substring(0, selected.id.indexOf("-")) == 1) { // 点击了楼宇
				var temp = selected.id.substring(selected.id.indexOf("-") + 1);
				managerId = temp.substring(0, temp.indexOf("-"));
				temp = temp.substring(temp.indexOf("-") + 1);
				buildId = temp;
				$('#formTable').datagrid({
					queryParams : {
						ptManagerId : managerId,
						ptBuildId : buildId,
						visitorName:$("#visitorName").val()
					}
				});
			}
			if (selected.id.substring(0, selected.id.indexOf("-")) == 2) {// 房间ID
				var swap = selected.id.substring((selected.id.indexOf("-") + 1));// 管理处ID-楼宇ID-房间ID-userId
				var selected = $('#tree').tree('getSelected');
				var nodeParent = $("#tree").tree("getParent", selected.target);// 获取父节点
				var nodeParent1 = $("#tree").tree("getParent", nodeParent.target);// 获取父父节点
				managerId = swap.substring(0, swap.indexOf("-"));
				managerName = nodeParent1.text;
				swap = swap.substring((swap.indexOf("-") + 1));// 楼宇ID-房间ID-userId
				swap = swap.substring((swap.indexOf("-") + 1));// 房间ID-userId
				var roomId = swap.substring(0, swap.indexOf("-"));
				userId = swap.substring((swap.indexOf("-") + 1));
	
				// 2-管理处ID-楼宇ID-房间ID-userId
				roomNum = selected.text.substring(0, selected.text.indexOf("|"));
				userName = selected.text.substring(selected.text.indexOf("|") + 1);
				$('#formTable').datagrid({
					queryParams : {
						ptManagerId : managerId,
						ptBuildId : buildId,
						ptRoomId:roomId,
						visitorName:$("#visitorName").val()
					}
				});
			}
		} else {
			alert("请选择小区！");
		}
	}
	
	function close(){
		$('#win').window('close');
	}
	</script>
</html>
