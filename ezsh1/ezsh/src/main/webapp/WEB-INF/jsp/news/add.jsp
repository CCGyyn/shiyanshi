<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/common/header.jsp" %>
<!DOCTYPE html>
<html>
	<head> 
	<title>添加消息</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">    
		<meta http-equiv="description" content="添加公告">
		<style type="text/css">
			.tableStyle{
				font-size:12px;
				padding:2% 0px 0px 10%;
			}
		</style>
	</head>
	
	<body>
		<div class="easyui-layout" fit="true">
			<div region="center" border="false" >
				<form id="formInfo" method="post">
			    	<table class="tableStyle" cellpadding="5">
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
			    		<tr style="display:none;">
			    			<td>
			    				<input  id="ptManagerId" class="easyui-textbox" required="required" name="ptManagerId">
			    			</td>
			    			<td>
			    				<input  id="ptUserId" class="easyui-textbox" required="required" name="ptUserId">
			    			</td>
			    		</tr>	
			    		<tr>	
			    			<td>用户名称:</td>
			    			<td>
			    				<input id="userName" class="easyui-textbox" type="text" name="userName" required="required" readonly="readonly"/>
			    			</td>
			    		</tr>
			    		<tr>	
			    			<td>消息名称:</td>
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
	</body>
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
		var win;
		$(function(){
			win = parent.$("iframe[title='个人消息']").get(0).contentWindow;
			var selected = win.$('#tree').tree('getSelected');
			$("#ptManagerId").textbox('setValue',win.managerId);
			$("#ptUserId").textbox('setValue',win.userId);
			$("#userName").textbox('setValue',win.userName);
		})
		/**
		 * @author qwc
		 * 2017年10月23日下午7:41:26 
		 * void 提交添加
		 */
		function sub(){
			$("#formInfo").form("enableValidation");
			if ($("#formInfo").form("validate")) {
				$.messager.progress();	
				alert(editorImgText.txt.html());
		    	$('#formInfo').form('submit', {
		    		url: "/ezsh/news/add",
		    		onSubmit: function(param){
		    			param.newsContent = editorImgText.txt.html();
		    		},
		    		success: function(data){
		    			$.messager.progress('close');
		    			var data = eval('(' + data + ')'); 
		    			if(data.status==1){
		    				$.messager.alert('提示','添加成功!','info');
		    			}else{
		    				alert(win);
		    				$.messager.alert('提示','添加失败!',function(){	
		    				});
		    			}
		    			
		    			win.$('#formTable').datagrid('reload');
		    		}
		    	});
			}
		}
		
		function close(){
			parent.$('#win').window('close');
		}
	</script>
</html>
