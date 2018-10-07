<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/common/header.jsp" %>
<!DOCTYPE html>
<html>
	<head> 
	<title>家教老师列表</title>
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
				<a href="javascript:pass()" class="easyui-linkbutton" iconCls="fa fa-check fa-fw fa-lg" plain="true"></a>
				<!-- <a href="javascript:edit()" class="easyui-linkbutton" iconCls="icon-edit" plain="true"></a> -->
				<a href="javascript:failure()" class="easyui-linkbutton" iconCls="fa fa-times-circle fa-fw fa-lg" plain="true"></a>
			</div>
		</div>
		
		<!-- query module start -->
		<div data-options="region:'north',split:false," title="查询条件" style="height:100px">
			<div style="padding:15px;height:auto">
				姓名查询: 
				<input id="teacherName" class="easyui-textbox" style="width:100px;height:25px">
				<a href="javascript:search()" class="easyui-linkbutton" iconCls="icon-search">Search</a>
			</div>
		</div>
		<!-- tree end -->
		
		<!-- tree start -->
		<!-- <div data-options="region:'west',title:'楼宇',split:true" style="width:150px;">
			<ul class="easyui-tree" id="tree">
		        
		    </ul>
		</div> -->
		<!-- tree end -->
		
		<div data-options="region:'center',title:'家教老师列表',iconCls:'icon-ok'">
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
		
		<div id="win" class="easyui-window" title="老师信息-查看" style="width:650px;height:450px"
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
		
				    		<!-- <tr>
				    			<td>商家描述:</td>
				    			<td>
				    				<div id="editorImgText" style="width:500px;">
								        <p>在此处编辑描述内容！</p>
								    </div>
	                         	</td>
				    		</tr> -->
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
			data-options="title:'请输入理由',buttons:'#bb',modal:true,closed:true">
			<div style="margin:20px 0px 0px 20px;width:250px;height:100px">
				<input id="failureReason" class="easyui-textbox" data-options="multiline:true" value="请输入理由..." style="width:100%;height:100%;">
			</div>
			
		</div>
		<div id="bb">
			<a href="javascript:subUnPass()" class="easyui-linkbutton">Save</a>
			<a href="javascript:close()" class="easyui-linkbutton">Close</a>
		</div>		
	</body>
	<script type="text/javascript">
	/**
	 * 初始化列表
	 */
	$('#formTable').datagrid({
	    url:'/ezsh/teacherA/select',
	    columns:[[
			{field:'teacherId',title:'教师ID',width:100,align:'center'},
			{field:'teacherName',title:'教师姓名',width:100,align:'center'},
			{field:'teacherNickName',title:'昵称',width:100,align:'center'},
			{field:'teacherTelephone',title:'联系电话',width:100,align:'center'},
			{field:'teacherProvince',title:'所在省',width:100,align:'center'},
			{field:'teacherCity',title:'所在市',width:100,align:'center'},
			{field:'teacherDistrict',title:'所在区',width:100,align:'center'},
			{field:'teacherIcon',hidden:'true',title:'头像路径',width:100,align:'center'},
			{field:'identityCardImg',hidden:'true',title:'身份证',width:100,align:'center'},
			{field:'studentCard',hidden:'true',title:'学生证',width:100,align:'center'},
			{field:'completeStatus',title:'完善状态',width:100,align:'center',
				formatter: function(value,row,index){
					if (value == 0) {
						return "未完善";
					} else if (value == 1) {
						return "已完善";
					}	
				}
			},
			{field:'checkStatus',title:'审核状态',width:100,align:'center',
				formatter: function(value,row,index){
					if (value == 0) {
						return "待审核";
					} else if (value == 1) {
						return "通过";
					} else if (value == 2) {
						return "未通过";
					}		
				}
			},
			{field:'failureReason',title:'不通过原因',width:200,align:'center'}
	    ]],
	    view: detailview,
	    detailFormatter: function(rowIndex, rowData){
			return '<table><tr>' +
					'<td rowspan=2 style="border:0">' + "昵称" + '</td>' +
					'<td rowspan=2 style="border:0"><img src="' + rowData.teacherIcon + '" style="height:50px;"></td>' +
					'<td style="border:0">' +
					'</td>' +
					'<td rowspan=2 style="border:0">' + "身份证" + '</td>' +
					'<td rowspan=2 style="border:0"><img src="' + rowData.teacherIcon + '" style="height:50px;"></td>' +
					'<td style="border:0">' +
					'</td>' +
					'<td rowspan=2 style="border:0">' + "学生证" + '</td>' +
					'<td rowspan=2 style="border:0"><img src="' + rowData.teacherIcon + '" style="height:50px;"></td>' +
					'<td style="border:0">' +
					'</td>' +
					'</tr></table>';
		}
	});


	/**
	 * @author qwc
	 * 2017年10月23日下午7:46:48 
	 * void 添加
	 */
	/* function add(){
		var selected = $('#tree').tree('getSelected');
		if(selected==null || selected.id.substring(0,selected.id.indexOf("-"))!=0){
			alert("请选择小区！");
		} else {
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
	} */

		    
	function search(){
		$('#formTable').datagrid({
			queryParams: {
				teacherName:$("#teacherName").val()
			}
		});	
	}
	
	function pass(){
		var row = $('#formTable').datagrid('getSelected');
		if (row) {
			$.messager.confirm('提示','确定通过审核?',function(r){
			    if (r){
					$.ajax({
					    type: "GET",
					    url:"/ezsh/teacherA/check",
					    data:{teacherId:row.teacherId,checkStatus:1},
					    dataType:"json",
					    success: function(data){
						    if(data.status>0){
						    	$.messager.alert('提示', data.message, 'info');
						    }else{
						    	$.messager.alert('提示', data.message, 'info');
						    }
						    $('#formTable').datagrid('reload');
						}
					})
			    }
			});
		} else {
			$.messager.alert('Warning','至少选择一条数据');
		}
	}
	
	function failure(){
		var row = $('#formTable').datagrid('getSelected');
		if (row) {
			$('#win1').window('open');
		} else {
			$.messager.alert('Warning','至少选择一条数据');
		}
	}
	
	function subUnPass(){
		alert(1);
		var row = $('#formTable').datagrid('getSelected');
		$.ajax({
		    type: "GET",
		    url:"/ezsh/teacherA/check",
		    data:{teacherId:row.teacherId,checkStatus:2,failureReason:$('#failureReason').textbox('getValue')},
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
		$('#win1').window('close');
	}
	</script>
</html>
