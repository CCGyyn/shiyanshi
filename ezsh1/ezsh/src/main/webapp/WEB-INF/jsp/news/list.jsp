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
				<a href="javascript:add()" class="easyui-linkbutton" iconCls="icon-add" plain="true"></a>
				<a href="javascript:edit()" class="easyui-linkbutton" iconCls="icon-edit" plain="true"></a>
				<a href="javascript:remove()" class="easyui-linkbutton" iconCls="icon-remove" plain="true"></a>
			</div>
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
				标题查询: 
				<input id="newsTitle" class="easyui-textbox" style="width:100px;height:25px">
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
		
		<div data-options="region:'center',title:'消息列表',iconCls:'icon-ok'">
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
		
		<div id="win" class="easyui-window" title="个人消息修改" style="width:650px;height:450px"
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
		
				    		<tr>
				    			<td>消息内容:</td>
				    			<td>
				    				<div id="editorImgText" style="width:500px;">
								        <p>在此处编辑消息内容！</p>
								    </div>
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
			    url:'/ezsh/tree/gtTreeUserId',
			    lines:true
			});
		})
		
		var managerId=0,managerName=null,roomId='0',roomNum=null,userId=0,userName=null;
		$('#tree').tree({
			onClick: function(node){
				 // alert node text property when clicked
				if(node.id.substring(0,node.id.indexOf("-"))==2){//房间ID
					var swap=node.id.substring((node.id.indexOf("-")+1));//管理处ID-楼宇ID-房间ID-userId
					var selected = $('#tree').tree('getSelected');
					var nodeParent = $("#tree").tree("getParent",selected.target);//获取父节点
					var nodeParent1=$("#tree").tree("getParent",nodeParent.target);//获取父父节点
					managerId=swap.substring(0,swap.indexOf("-"));
					managerName=nodeParent1.text;
					swap=swap.substring((swap.indexOf("-")+1));//楼宇ID-房间ID-userId
					swap=swap.substring((swap.indexOf("-")+1));//房间ID-userId
					var roomId=swap.substring(0,swap.indexOf("-"));
					userId=swap.substring((swap.indexOf("-")+1));
					
					//2-管理处ID-楼宇ID-房间ID-userId
					roomNum=node.text.substring(0,node.text.indexOf("|"));
					userName=node.text.substring(node.text.indexOf("|")+1);
					$('#formTable').datagrid({
						queryParams: {
							ptUserId:userId,
							noticeTitle:$("#noticeTitle").val()
						}
					});	
				}	
			}
		});
	</script>
	<script type="text/javascript" src="/ezsh/wang-Editor/wangEditor.min.js"></script>
	<script type="text/javascript">
        var wImgText = window.wangEditor;
        var editorImgText = new wImgText('#editorImgText');
        editorImgText.customConfig.uploadImgShowBase64 = true;
        editorImgText.customConfig.showLinkImg = false;//隐藏网络图片
        editorImgText.customConfig.uploadFileName = 'goodsImageTextFiles';
        editorImgText.customConfig.menus = [
            'head',
            'bold',
            'italic',
            'underline',
            'image',  // 插入图片
        ];
        editorImgText.create();
    </script>
	<script type="text/javascript">
	/**
	 * 初始化列表
	 */
	$('#formTable').datagrid({
	    url:'/ezsh/news/select',
	    columns:[[
			{field:'newsId',hidde:true,title:'消息ID',width:100,align:'center'},
			{field:'ptUserId',hidden:'true',title:'用户ID',width:100,align:'center'},
			{field:'newsTitle',title:'消息标题',width:100,align:'center'},
			{field:'newsTime',title:'创建时间',width:100,align:'center'}	
	    ]]
	});

	/**
	 * @author qwc
	 * 2017年10月23日下午7:45:19 
	 * void 编辑
	 */
	function edit(){
		var row = $('#formTable').datagrid('getSelected');
		/* var url = '/ezsh/charge/selectByManagerId?pManagerId='+row.pManagerId;
	    $('#pChargeItemId').combobox('reload', url); */
	   // alert(row.newsId);
		if (row){
			$('#win').window('open');
			$('#formInfo').form('load',{
				newsId:row.newsId,	
				newsTitle:row.newsTitle,	
			});
			editorImgText.txt.html(row.newsContent);
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
			url: "/ezsh/news/update",
			onSubmit: function(param){
				param.newsContent = editorImgText.txt.html();
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
		if(selected==null || selected.id.substring(0,selected.id.indexOf("-"))!=2){
			alert("请选择用户！");
		}else {
			parent.$('#win').window({    
			title :'添加消息',						
		    width:700,    
		    height:550,    
		    modal:true,
		    maximizable:false,
		    minimizable:false,
		    resizable:false,
		    content:"<iframe src='/ezsh/base/goURL/news/add' height='100%' width='100%' frameborder='0px' ></iframe>"  
			}); 
		}
	}

	/**
	 * @author qwc
	 * 2017年10月23日下午7:46:28 
	 * void 删除个人消息
	 */
	function remove(){
		var row = $('#formTable').datagrid('getSelected');
		$.ajax({
		    type: "GET",
		    url:"/ezsh/news/delete",
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
	}
		    
	function search(){
		var selected = $('#tree').tree('getSelected');
		if(selected){
			if (selected.id.substring(0, selected.id.indexOf("-")) == 0) { // 点击了管理处
				var temp = selected.id.substring(selected.id.indexOf("-") + 1);
				managerId = temp.substring(temp.indexOf("-") + 1);
				$('#formTable').datagrid({
					queryParams : {
						ptManagerId : managerId,
						newsTitle:$("#newsTitle").val()
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
						newsTitle:$("#newsTitle").val()
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
				userName = selected.text.substring(node.text.indexOf("|") + 1);
				$('#formTable').datagrid({
					queryParams : {
						ptUserId:userId,
						ptManagerId : managerId,
						newsTitle:$("#newsTitle").val()
					}
				});
			}
		} else {
			$('#formTable').datagrid({
					queryParams : {
					newsTitle:$("#newsTitle").val()
				}
			});
		}
	}
	</script>
</html>
