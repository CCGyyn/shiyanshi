<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/common/header.jsp" %>
<!DOCTYPE html>
<html>
	<head> 
	<title>商家列表</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">    
		<meta http-equiv="description" content="商家列表">
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
				<a href="javascript:detailsList()" ><i class="fa fa-arrow-circle-right fa-lg"  style="color:green;" aria-hidden="true"></i></a>
				<!-- <a href="javascript:add()" class="easyui-linkbutton" iconCls="icon-add" plain="true"></a>
				<a href="javascript:edit()" class="easyui-linkbutton" iconCls="icon-edit" plain="true"></a> -->
				<!-- <a href="javascript:remove()" class="easyui-linkbutton" iconCls="icon-remove" plain="true"></a> -->
			</div>
		</div>
		
		<!-- query module start -->
		<div data-options="region:'north',split:false," title="查询条件" style="height:100px">
			<div style="padding:15px;height:auto">
				名称查询: 
				<input id="merchantName" class="easyui-textbox" style="width:100px;height:25px">
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
		
		<div data-options="region:'center',title:'商家列表',iconCls:'icon-ok'">
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
		
		<div id="win" class="easyui-window" title="商家信息修改" style="width:650px;height:450px"
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
				    				<input id="merchantId" class="easyui-textbox" type="text" name="merchantId" required="required" style="width:100%;"/>
				    			</td>
				    		</tr>
				    		
				    		<tr>	
				    			<td>商家名称:</td>
				    			<td>
				    				<input id="merchantName" class="easyui-textbox" type="text" name="merchantName" required="required" style="width:100%;"/>
				    			</td>
				    		</tr>
				    		
				    		<tr>	
				    			<td>商家联系电话:</td>
				    			<td>
				    				<input id="merchantLinkPhone" class="easyui-textbox" type="text" name="merchantLinkPhone" required="required" style="width:100%;"/>
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
	<script type="text/javascript">
		$(function(){
			$('#tree').tree({
			    url:'/ezsh/tree/gtTreeManager',
			    lines:true
			});
		})
		
		var managerId = 0;
		$('#tree').tree({
			onClick: function(node){
				 // alert node text property when clicked
				if(node.id.substring(0,node.id.indexOf("-"))==0){//
					var managerId=node.id.substring((node.id.indexOf("-")+1));
					
					$('#formTable').datagrid({
						queryParams: {
							pManagerId:managerId
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
	    url:'/ezsh/userA/select',
	    columns:[[
			{field:'userId',title:'用户ID',width:100,align:'center'},
			{field:'userAccount',title:'用户账号D',width:100,align:'center'},
			{field:'userName',title:'真实姓名',width:100,align:'center'},
			{field:'userNickname',title:'昵称',width:100,align:'center'},
			{field:'userSex',title:'性别',width:100,align:'center'},
			{field:'userLinkphone',title:'联系电话',width:100,align:'center'},
			{field:'userSerialNumber',title:'编号',width:100,align:'center'},
			{field:'userIdentityCard',title:'身份证号码',width:100,align:'center'},
			{field:'userIcon',hidden:'true',title:'头像',width:100,align:'center'}
	    ]],
	    view: detailview,
	    detailFormatter: function(rowIndex, rowData){
			return '<table><tr>' +
					'<td rowspan=2 style="border:0">' + "头像" + '</td>' +
					'<td rowspan=2 style="border:0"><img src="' + rowData.userIcon + '" style="height:50px;"></td>' +
					'<td style="border:0">' +
					'</td>' +
					'</tr></table>';
		}
	});

	/**
	 * @author qwc
	 * 2017年10月23日下午7:45:19 
	 * void 编辑
	 */
	function edit(){
		var row = $('#formTable').datagrid('getSelected');
		if (row){
			$('#win').window('open');
			$('#formInfo').form('load',{
				merchantId:row.merchantId,	
				merchantName:row.merchantName,
				merchantLinkPhone:row.merchantLinkPhone
			});
			editorImgText.txt.html(row.merchantDescr);
		}else{
			alert("请至少选中一条数据！");
		}
	}

	/**
	 * @author qwc
	 * 2017年10月23日下午7:45:47 
	 * void 提交编辑更新
	 */
	function sub(){
		$.messager.progress();	
		$('#formInfo').form('submit', {
			url: "/ezsh/merchantA/update",
			onSubmit: function(param){
				param.merchantDescr = editorImgText.txt.html();
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
		    

	function close(){
		$('#win').window('close');
	}

	/**
	 * @author qwc
	 * 2017年10月23日下午7:46:48 
	 * void 添加
	 */
	function add(){
		var selected = $('#tree').tree('getSelected');
		if(selected==null || selected.id.substring(0,selected.id.indexOf("-"))!=0){
			alert("请选择小区！");
		}else {
			parent.$('#win').window({    
			title :'添加消息',						
		    width:700,    
		    height:550,    
		    modal:true,
		    maximizable:false,
		    minimizable:false,
		    resizable:false,
		    content:"<iframe src='/ezsh/base/goURLT/shopping-mall/merchant/add' height='100%' width='100%' frameborder='0px' ></iframe>"  
			}); 
		}
	}

	/* function remove(){
		var row = $('#formTable').datagrid('getSelected');
		$.ajax({
		    type: "GET",
		    url:"",
		    data:{"newsId":row.newsId},
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
	} */
	
	function detailsList(){
		var row = $('#formTable').datagrid('getSelected');
		if (row){
			var userId=row.userId;
			location.href="${pageContext.request.contextPath}/userA/jumpToDetailsView?userId="+userId;
		}else{
			alert("请至少选中一条数据！");
		}	
	}
	
	function search(){
		var selected = $('#tree').tree('getSelected');
		if(selected==null){
			$('#formTable').datagrid({
				queryParams: {
					merchantName:$("#merchantName").val()
				}
			});	
		}else {
			$('#formTable').datagrid({
				queryParams: {
					pManagerId:selected.id.substring(selected.id.indexOf("-")+1),
					merchantName:$("#merchantName").val()
				}
			});	
		}
	}
	</script>
</html>
