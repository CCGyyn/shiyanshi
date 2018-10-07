<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/common/header.jsp" %>
<!DOCTYPE html>
<html>
	<head> 
	<title>添加商家</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">    
		<meta http-equiv="description" content="添加商家">
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
			    		<tr style="display:none;">
			    			<td>
			    				<input  id="pManagerId" class="easyui-textbox" required="required" name="pManagerId">
			    			</td>
			    			<td>
			    				<input  id="managerName" class="easyui-textbox" required="required" name="managerName">
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
			    		
			    		<tr>
			    			<td>商家描述:</td>
			    			<td>
			    				<div id="editorImgText" style="width:500px;">
							        <p>在此处编辑商家描述！</p>
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
            'underline'/* ,
            'image',  // 插入图片 */
        ];
        editorImgText.create();
    </script>
	<script type="text/javascript">
		var win;
		$(function(){
			win = parent.$("iframe[title='商家管理']").get(0).contentWindow;
			var selected = win.$('#tree').tree('getSelected');
			$("#pManagerId").textbox('setValue',selected.id.substring((selected.id.indexOf("-")+1)));
			$("#managerName").textbox('setValue',selected.text);
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
		    	$('#formInfo').form('submit', {
		    		url: "/ezsh/merchantA/add",
		    		onSubmit: function(param){
		    			param.merchantDescr = editorImgText.txt.html();
		    		},
		    		success: function(data){
		    			$.messager.progress('close');
		    			var data = eval('(' + data + ')'); 
		    			if (data.status==-1) {
		    				$.messager.alert('提示',data.message,'info');
		    			} else if (data.status==0){
		    				$.messager.alert('提示',data.message,function(){	
		    				});
		    			} else if (data.status==1){
		    				$.messager.alert('提示',data.message,function(){	
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
