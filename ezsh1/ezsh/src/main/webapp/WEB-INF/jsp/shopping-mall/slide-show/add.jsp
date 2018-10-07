<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/common/header.jsp" %>
<!DOCTYPE html>
<html>
	<head> 
	<title>添加商城首页轮播图</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">    
		<meta http-equiv="description" content="添加商城首页轮播图">
		<style type="text/css">
			.tableStyle{
				font-size:12px;
				padding:2% 0px 0px 20%;
			}
		</style>
	</head>
	
	<body>
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
			    		</tr>	
			    		
			    		<tr>
                         	<td>
					    		<div style="border:1px solid #ccc;height:200px;width:200px;">
								    <img id="goodsSlideShow">
							    </div>
							    <button style="margin-left:70px;height:25px;width:50px;">上传
							    <input  type="file" name="goodsSlideShow" onchange="changePic(this,'goodsSlideShow')" style="position:relative;right:10px;bottom:15px;height:35px;width:45px; opacity: 0;">
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
	</body>
	<script type="text/javascript">
	var formdata=new FormData($('#forminfo')[0]);
	function changePic(file,imgPreviewId){
		if(file.files!=null){
			var img = document.getElementById(imgPreviewId); 
			var reader = new FileReader();
		    //读取File对象的数据  
		    reader.onload = function(evt){  
		        img.width  =  "200";  
		        img.height =  "200";  
		        img.src = evt.target.result;  
		    }  
		    reader.readAsDataURL(file.files[0]);
		}	 
	}
    </script>
	<script type="text/javascript">
		var win;
		$(function(){
			win = parent.$("iframe[title='商城首页轮播图']").get(0).contentWindow;
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
		    		url: "/ezsh/slideShowA/adSlideShowImage",
		    		onSubmit: function(param){
		    			
		    		},
		    		success: function(data){
		    			$.messager.progress('close');
		    			var data = eval('(' + data + ')'); 
		    			if ( data.status==0 ) {
		    				$.messager.alert('提示',data.message,function(){	
		    				});
		    			} else if (data.status == 1) {
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
