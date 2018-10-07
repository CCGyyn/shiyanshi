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
				padding:2% 0px 0px 15%;
			}
		</style>
	</head>
	
	<body class="easyui-layout">
		<div id="tb" style="padding:5px;height:auto">
			<div style="margin-bottom:5px">
				<a href="javascript:add()" class="easyui-linkbutton" iconCls="icon-add" plain="true"></a>
				<a href="javascript:edit()" class="easyui-linkbutton" iconCls="icon-edit" plain="true"></a>
				<a href="javascript:remove()" class="easyui-linkbutton" iconCls="icon-remove" plain="true"></a>
			</div>
		</div>
		
		<!-- query module start -->
		<!-- <div data-options="region:'north',split:false," title="查询条件" style="height:100px">
			<div style="padding:15px;height:auto">
				名称查询: 
				<input id="merchantName" class="easyui-textbox" style="width:100px;height:25px">
				<a href="javascript:search()" class="easyui-linkbutton" iconCls="icon-search">Search</a>
			</div>
		</div> -->
		<!-- query module end -->
		
		<!-- tree start -->
		<div data-options="region:'west',title:'楼宇',split:true" style="width:150px;">
			<ul class="easyui-tree" id="tree">
		        
		    </ul>
		</div>
		<!-- tree end -->
		
		<div data-options="region:'center',title:'轮播图列表',iconCls:'icon-ok'">
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
		
		<div id="win" class="easyui-window" title="商家信息修改" style="width:350px;height:380px"
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
					<form id="formInfo" method="post" enctype="multipart/form-data">
				    	<table class="tableStyle" cellpadding="5">
				    		<tr style="display:none;">
				    			<td>
				    				<input  id="pManagerId" class="easyui-textbox" required="required" name="pManagerId">
				    			</td>
				    			<td>
				    				<input  id="managerName" class="easyui-textbox" required="required" name="managerName">
				    			</td>
				    			<td>
				    				<input  id="goodsSlideShowId" class="easyui-textbox" required="required" name="goodsSlideShowId">
				    			</td>
				    		</tr>	
				    		
				    		<tr>
	                         	<td>
						    		<div style="border:1px solid #ccc;height:200px;width:200px;">
									    <img id="goodsSlideShow">
								    </div>
								    <button style="margin-left:70px;height:25px;width:50px;">上传
								    <input  type="file" id="goodsSlideShow" name="goodsSlideShow" onchange="changePic(this,'goodsSlideShow')" style="position:relative;right:10px;bottom:15px;height:35px;width:45px; opacity: 0;">
								    </button>
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
		
		var managerId=0;
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
	    url:'/ezsh/slideShowA/select',
	    columns:[[
			{field:'goodsSlideShowId',title:'轮播图ID',width:100,align:'center'},
			{field:'pManagerId',title:'管理处ID',width:100,align:'center'},
			{field:'slideShowImgUrl',title:'轮播图路径',width:400,align:'center'}
	    ]],
	    view: detailview,
	    detailFormatter: function(rowIndex, rowData){
			return '<table><tr>' +
					'<td rowspan=2 style="border:0"><img src="' + rowData.slideShowImgUrl + '" style="height:50px;"></td>' +
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
				goodsSlideShowId:row.goodsSlideShowId,	
				pManagerId:row.pManagerId
			});
			$("#goodsSlideShow").attr("src",row.slideShowImgUrl);
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
		if($("#goodsSlideShow").get(0).files == null){
			$.messager.alert('提示', "图片未发生变化" ,'info');
		} else {
			$.messager.progress();	
			$('#formInfo').form('submit', {
				url: "/ezsh/slideShowA/update",
				onSubmit: function(param){
					
				},
				success: function(data){
					$.messager.progress('close');
					var data = eval('(' + data + ')'); 
					if(data.status==1){
						$.messager.alert('提示', data.message ,'info');
					}else{
						$.messager.alert('提示', data.message ,function(){	
						});
					}
				}
			});
		}
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
			title :'添加商城首页轮播图',						
		    width:350,    
		    height:380,    
		    modal:true,
		    maximizable:false,
		    minimizable:false,
		    resizable:false,
		    content:"<iframe src='/ezsh/base/goURLT/shopping-mall/slide-show/add' height='100%' width='100%' frameborder='0px' ></iframe>"  
			}); 
		}
	}

	function remove(){
		var row = $('#formTable').datagrid('getSelected');
		$.ajax({
		    type: "GET",
		    url:"/ezsh/slideShowA/delete",
		    data:{"pManagerId":row.pManagerId,"goodsSlideShowId":row.goodsSlideShowId},
		    dataType:"json",
		    success: function(data){
			    if(data.status>0){
			    	$.messager.alert('提示', data.message ,'info');
			    }else{
			    	$.messager.alert('提示', data.message ,'info');
			    }
			    $('#formTable').datagrid('reload');
			}
		})
	}
		    
	/* function search(){
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
					ptManagerId:selected.id.substring(0,selected.id.indexOf("-")),
					merchantName:$("#merchantName").val()
				}
			});	
		}
	} */
	</script>
</html>
