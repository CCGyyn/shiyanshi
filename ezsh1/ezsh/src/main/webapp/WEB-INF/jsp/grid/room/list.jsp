<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/common/header.jsp" %>
<!DOCTYPE html>
<html>
	<head> 
	<title>房间表计设置</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">    
		<meta http-equiv="description" content="房间表计类型列表">
		<style type="text/css">
			.tableStyle{
				font-size:12px;
				padding:10% 0px 0px 10%;
			}
		</style>
	</head>
	
	<body class="easyui-layout">
		<!-- toolbar start -->
		<div id="tb" style="padding:5px;height:auto">
			<div style="margin-bottom:5px">
				<a href="javascript:add()" class="easyui-linkbutton" iconCls="icon-add" plain="true"></a>
				<a href="javascript:edit()" class="easyui-linkbutton" iconCls="icon-edit" plain="true"></a>
				<a href="javascript:del()" class="easyui-linkbutton" iconCls="icon-remove" plain="true"></a>
			</div>
		</div>
		<!-- toolbar end -->
		
		<!-- query module start -->
		<div data-options="region:'north',split:false," title="查询条件" style="height:100px">
			<div style="padding:15px;height:auto">
				<!-- 起始时间: <input class="easyui-datebox" style="width:100px">
				终止时间: <input class="easyui-datebox" style="width:100px"> -->
				表计名查询: 
				<input class="easyui-textbox" id="gridName" style="width:100px;height:25px">
				<a href="javascript:search()" class="easyui-linkbutton" iconCls="icon-search">Search</a>
			</div>
		</div>
		<!-- query module end -->
		
		<!-- tree start -->
		<div data-options="region:'west',title:'楼宇',split:true" style="width:150px;">
			<ul class="easyui-tree" id="tree">
		        
		    </ul>
		</div>
		<!-- tree end -->
		
		<!-- datagrid start -->
		<div data-options="region:'center',title:'房间表计类别列表',iconCls:'icon-ok'">
			<table id="formTable" class="easyui-datagrid"
					data-options="url:'',method:'get', 
					border:false,
					fit:true,
					fitColumns:true,
					striped:true,
					pagination:true,
					toolbar:'#tb',
					rownumbers:true,
					singleSelect:true,
					">
			</table>
		</div>
		<!-- datagrid end -->
		
		<!-- edit window start -->
		<div id="win" class="easyui-window" title="房间表计类别-修改" style="width:300px;height:300px"
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
				    		<tr>				    		    			
				    			<td>管理处:</td>
				    			<td>
				    				<input  id="ptManagerId" class="easyui-combobox" name="ptManagerId"   
	                      				data-options="valueField:'managerId',
	                      				required:true,
	                      				textField:'managerName',
	                      				 panelHeight: 80,
	                      				url:'/ezsh/build/findManager',
	                      				onSelect: function(rec){	
									    var url = '/ezsh/grid/selectSimple?pManagerId='+rec.managerId;
									    	$('#ptGridId').combobox('reload', url);
									    },
									    onLoadSuccess:function(data){
										    var url = '/ezsh/grid/selectSimple?pManagerId='+data[0].managerId;
											$('#ptGridId').combobox('reload', url);
									    }
	                      				"/> 
				    			</td>
				    		</tr>
				    		
				    		<tr>
				    			<td style="display:none">
				    				<input id="ptBuildId"  class="easyui-textbox" type="text" name="ptBuildId"/>
				    			</td>
				    		</tr>
				    		
				    		<tr>
				    			<td style="display:none">
				    				<input id="ptRoomId"  class="easyui-textbox" type="text" name="ptRoomId"/>
				    			</td>
				    			<td>房间编号:</td>
				    			<td>
				    				<input id="roomNum" class="easyui-textbox" type="text" name="roomNum" required="required"/>
				    			</td>
				    		</tr>
				    		
				    		<tr>
				    			<td>表计类别:</td>
				    			<td>
				    				<input  id="ptGridId" class="easyui-combobox" name="ptGridId"   
	                        			data-options="valueField:'gridId',
	                        			required:true,
	                        			textField:'gridName'	
	                       			" />
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
		<!-- edit window end -->

	</body>

	<script type="text/javascript">
		/* 构建管理处树 */
		$(function(){
			$('#tree').tree({
			    url:'/ezsh/tree/gtTree',
			    lines:true
			});
		})
		
		var managerId,managerName,roomId,roomNum;
		$('#tree').tree({
			onClick: function(node){
				 // alert node text property when clicked
				if(node.id.substring(0,node.id.indexOf("-"))==2){//房间ID
					
					var swap=node.id.substring((node.id.indexOf("-")+1));
					var selected = $('#tree').tree('getSelected');
					var nodeParent = $("#tree").tree("getParent",selected.target);//父节点
					var nodeParent1=$("#tree").tree("getParent",nodeParent.target);//父父节点
					managerId=swap.substring(0,swap.indexOf("-"));
					managerName=nodeParent1.text;
					swap=swap.substring((swap.indexOf("-")+1));
					var roomId=swap.substring((swap.indexOf("-")+1));
					roomNum=node.text.substring(0,node.text.indexOf("|"));
					$('#formTable').datagrid({
						queryParams: {
							roomId:roomId
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
	    url:'/ezsh/gridRoom/select',
	    method: 'POST',
	    columns:[[
			{field:'ptManagerId',hidden:'true',title:'管理处ID',width:100,align:'center'},
			{field:'ptBuildId',hidden:'true',title:'楼宇ID',width:100,align:'center'},
			{field:'ptRoomId',hidden:'true',title:'房间ID',width:100,align:'center'},
			{field:'ptGridId',hidden:'true',title:'表计类别ID',width:100,align:'center'},
	    	{field:'roomGridItemId',title:'房间表计类别ID',width:100,align:'center'},
			{field:'roomGridName',title:'表计类别名',width:100,align:'center'},
			{field:'roomNum',title:'房间编号',width:100,align:'center'}
	    ]]
	});
	</script>

	<script type="text/javascript">
		var selected;
		function edit(){
			var row = $('#formTable').datagrid('getSelected');
			if (row){	
				$('#win').window('open');
				$('#formInfo').form('load',{
					ptManagerId:row.ptManagerId,	
					ptBuildId:row.ptBuildId,
					ptRoomId:row.ptRoomId,
					ptGridId:row.ptGridId,
					roomGridItemId:row.roomGridItemId,
					roomGridName:row.roomGridName,
					roomNum:row.roomNum
				});
			}else{
				alert("请至少选中一条数据！");
			}
	    }
		
		function add(){
			selected = $('#tree').tree('getSelected');
			if (selected!=null&&selected.id.substring(0,selected.id.indexOf("-"))==2){
				parent.$('#win').window({    
					title :'房间表计类别-新增',						
				    width:350,    
				    height:300,    
				    modal:true,
				    maximizable:false,
				    minimizable:false,
				    resizable:false,
				    content:"<iframe src='/ezsh/base/goURLT/grid/room/add' height='100%' width='100%' frameborder='0px' ></iframe>"  
				}); 
			}else{
				alert("请选择要添加的房间！");
			}
	    }
		
	    function sub(){
	    	$.messager.progress();	
	    	$('#formInfo').form('submit', {
	    		url: "/ezsh/gridRoom/update",
	    		onSubmit: function(param){

	    		},
	    		success: function(data){
	    			$.messager.progress('close');
	    			var data = eval('(' + data + ')'); 
	    			if(data.status==1){
	    				$.messager.alert('提示','修改成功!','info');
	    			}else{
	    				$.messager.alert('提示','修改失败!',function(){	
	    				});
	    			}
	    		}
	    	});
	    }
	    
	    function del(){
	    	var row = $('#formTable').datagrid('getSelected');
	    	if(row!=null){
	    		$.ajax({
				    type: "GET",
				    url:"/ezsh/gridRoom/delete",
				    data:{"roomGridItemId":row.roomGridItemId},
				    dataType:"json",
				    success: function(data){
					    if(data.status>0){
					    	$.messager.alert('提示','删除成功!','info');
					    }else{
					    	$.messager.alert('提示','删除失败!','info');
					    }
					    $('#formTable').datagrid('reload');
				   }
				})
	    	}else{
	    		alert("至少选择一条数据！");
	    	}
	    	
	    }
	    
	    function search(){
	    	$('#formTable').datagrid({
	    		queryParams: {
	    			gridName: $("#gridName").val()
	    		}
	    	});	
	    }
	    
	    function close(){
	    	$('#win').window('close');
	    }

	</script>
</html>
