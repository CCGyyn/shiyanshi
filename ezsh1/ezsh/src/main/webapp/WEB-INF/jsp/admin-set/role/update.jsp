<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/common/header.jsp" %>
<!DOCTYPE html>
<html>
	<head>
		<%-- <base href="<%=basePath%>"> --%>
		<title>修改角色</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">    
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<style type="text/css">
			.datagrid-header td{
				border-width: 0px 0px 1px 0px;
			}
		</style>
	</head>
  
	<body>
	    <div class="easyui-layout" style="width:100%;height:80%;">
	    	<shiro:hasPermission name="p113">
			<div data-options="region:'east',split:true,title:'分配权限',collapsible:false" style="width:80%;">
				<table class="easyui-datagrid">
				    <thead>
						<tr>
							<th data-options="field:'角色模块'">超级权限</th>
							<!-- <th data-options="field:'角色模块-1'"></th>
							<th data-options="field:'角色模块-2'"></th> -->
						</tr>
				    </thead>
				    <tbody>
						<tr>
							<td class="permission-list1" style="vertical-align:middle;">
				            	<small><input type="checkbox" class="p101" name="assgin-power" value="p101">查看所有小区&nbsp;&nbsp;</small>
				            </td>    
						</tr>
					</tbody>
				</table>
				
				<table class="easyui-datagrid">
				    <thead>
						<tr>
							<th data-options="field:'角色模块'">角色模块</th>
							<th data-options="field:'角色模块-1'"></th>
							<th data-options="field:'角色模块-2'"></th>
						</tr>
				    </thead>
				    <tbody>
						<tr>
							<td class="permission-list1">
				            	<small><input type="checkbox" class="p111" name="assgin-power" value="p111">添加角色&nbsp;&nbsp;&nbsp;</small>
				            </td>
				            <td class="permission-list1">
				            	<small><input type="checkbox" class="p112" name="assgin-power" value="p112">修改角色&nbsp;&nbsp;&nbsp;</small>
				            </td>
				            <td class="permission-list1">
				            	<small><input type="checkbox" class="p113" name="assgin-power" value="p113">角色授权&nbsp;&nbsp;&nbsp;</small>
				            </td>
						</tr>
					</tbody>
				</table>
				
				<table class="easyui-datagrid">
				    <thead>
						<tr>
							<th data-options="field:'管理员模块'">管理员模块</th>
							<th data-options="field:'管理员模块-1'"></th>
							<th data-options="field:'管理员模块-2'"></th>
							<th data-options="field:'管理员模块-3'"></th>
						</tr>
				    </thead>
				    <tbody>
						<tr>
							<td class="permission-list1">
				            	<small><input type="checkbox" class="p121" name="assgin-power" value="p121">添加管理员</small>
				            </td>		         
				            <td class="permission-list1">
				            	<small><input type="checkbox" class="p123" name="assgin-power" value="p123">修改管理员</small>
				            </td>
				            <td class="permission-list1" >
				            	<small><input type="checkbox" class="p124" name="assgin-power" value="p124">删除管理员</small>
				            </td>
				            <td class="permission-list1">
				            	<small><input type="checkbox" class="p122" name="assgin-power" value="p122">分配角色</small>
				            </td>
						</tr>
					</tbody>
				</table>
			</div>
			</shiro:hasPermission>
			
			<!-- add role start -->
			<div data-options="region:'center',title:'修改角色',iconCls:'icon-ok',href:''" style="padding:10px;width:20%;">
				<shiro:hasPermission name="p112">
				<div style="padding:10px 10px 20px 10px;">
				    <form id="add-admin" method="post">
				    	<div style="margin-bottom:20px;display:none;">
							<div>管理处ID:</div>
							<input id="ptManagerId" class="easyui-textbox" value="${roleInfo.ptManagerId}" data-options="prompt:'输入角色名称....'" style="width:100%;height:32px">
						</div>
				    	<div style="margin-bottom:20px">
							<div>角色名称:</div>
							<input id="role-name" class="easyui-textbox" value="${roleInfo.roName}" data-options="prompt:'输入角色名称....'" style="width:100%;height:32px">
						</div>
						<div style="margin-bottom:20px">
							<div>角色描述:</div>
							<input id="role-desc" class="easyui-textbox" value="${roleInfo.roDescr} "style="width:100%;height:100px" data-options="prompt:'说点什么...',multiline:true">
						</div>
						<div>
							<a href="javascript:void(0)" id="alert-power" class="easyui-linkbutton" iconCls="icon-ok" style="width:100%;height:32px">提交修改</a>
						</div>
				    </form>
			    </div>
			    </shiro:hasPermission>
			</div>
			<!-- add role end -->
		</div>
		
		<input type="hidden" id="hidden-role-id" value="${roleInfo.roId}">
		<input type="hidden" id="hidden-role-powers" value="${roleInfo.roPrevNames}">
	</body>
	<script type="text/javascript">
		//获取选中的权限
		var powerList=''; 
		function getCompetenceArrayId(){
			if(powerList!=null||powerList!=""){
				powerList='';
			}
			var obj=document.getElementsByName('assgin-power'); 
			for(var i=0; i<obj.length;i++){ 
				if(obj[i].checked==true&&obj[i].value!=null&&obj[i].value!=""){
					if(powerList.indexOf(obj[i].value+",")<0){
						powerList+=obj[i].value+',';
					}
				} 
			};
		}
		
		$(document).ready(function(){
			//权限展示
			if($("#hidden-role-powers").val()!=null&&$("#hidden-role-powers").val()!=""){
				var powerStrings=$("#hidden-role-powers").val();
				var powerArray= powerStrings.split(",");
				for(var power in powerArray){
					if(powerArray[power]!=null&&powerArray[power]!=""){
						$("."+powerArray[power]).attr("checked","checked");
					}
				}
			}
			
			//更新角色
			$("#alert-power").click(function(){
				getCompetenceArrayId();
				if($('#role-name').val()==null||$('#role-name').val()==""){
					$.messager.alert('提示','角色名不能为空!','warning');
				}else if($("#role-desc").val()==null||$("#role-desc").val()==""){
					$.messager.alert('提示','描述不能为空!','warning');
				}else if(powerList!=null&&powerList!=""){
					$.ajax({
						url:"/ezsh/ro/updateRole",
						type:'post',
						data:{roId:$('#hidden-role-id').val(),ptManagerId:$('#ptManagerId').val(),
							roName:$('#role-name').val(),roDescr:$("#role-desc").val(),roPrevNames:powerList},
						dataType:'json',
						success:function(data){
							if(data.status==1){
								$.messager.alert('提示',data.message,'info',function(e){
									location.reload();}
								);
							}else if(data.status==-1){
								$.messager.alert('提示',data.message,'info',function(e){
									location.reload();}
								);
							}else{
								$.messager.alert('提示',data.message,'error',function(e){
									location.reload();}
								);
							} 
						}
					});
				}else{
					$.messager.alert('提示','至少分配一项权限!','warning');
				}
			})
		})
		
	</script>
</html>