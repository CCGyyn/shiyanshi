<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/common/header.jsp" %>
<!DOCTYPE html>
<html>
	<head> 
	<title>表计类别列表</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">    
		<meta http-equiv="description" content="表计类别列表">
		<style type="text/css">
			.tableStyle{
				font-size:12px;
				padding:5% 0px 0px 15%;
			}
		</style>
	</head>
	
	<body>
		<div class="easyui-layout" fit="true">
			<div region="center" border="false" >
				<form id="formInfo" method="post">
			    	<table class="tableStyle" cellpadding="5">
			    		<tr>
			    			<td>姓名:</td>
			    			<td><input type="text" id="adName" class="easyui-textbox"  name="adName" style="height:28px;" missingMessage="不能为空！" required="required"></input></td>
			    		</tr>
			    		<tr>
			    			<td>账号:</td>
			    			<td><input type="text" id="adAccount" class="easyui-textbox"  style="height:28px;" name="adAccount" missingMessage="不能为空！" required="required"></input></td>
			    		</tr>
			    		<tr>
			    			<td>密码:</td>
			    			<td><input type="password" id="adPassword" class="easyui-textbox"  style="height:28px;" name="adPassword"  missingMessage="不能为空！" data-options="required:true"></input></td>
			    		</tr>
			    		<tr>
			    			<td>确认密码:</td>
			    			<td><input type="password" class="easyui-textbox"  style="height:28px;" name="adPassword-again"  missingMessage="不能为空！" data-options="required:true" validType="equals['#adPassword']"></input></td>
			    		</tr>
			    		<tr>
			    			<td>手机号码:</td>
			    			<td><input type="text" class="easyui-textbox"  style="height:28px;" name="adTelephone"  missingMessage="不能为空！" data-options="required:true"></input></td>
			    		</tr>
			    		<tr>
			    			<td>管理小区:</td>
			    			<td>
			    				<input  id="adManagerId" class="easyui-combobox" name="adManagerId"   
                       				data-options="valueField:'managerId',
                       				required:true,textField:'managerName',
                       				 panelHeight: 80,
                       				url:'/ezsh/build/findManager',
                       				onSelect: function(rec){	
								    var url = '/ezsh/charge/selectByManagerId?pManagerId='+rec.managerId;
								    $('#pChargeItemId').combobox('reload', url);
								 }"/>
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
		/* var win = parent.$("iframe[title='表计类别设置']").get(0).contentWindow; */
	    function sub(){
	    	$("#formInfo").form("enableValidation");
	    	if ($("#formInfo").form("validate")) {
	    		$.messager.progress();	
		    	$('#formInfo').form('submit', {
		    		url: "/ezsh/ad/add",
		    		onSubmit: function(param){

		    		},
		    		success: function(data){
		    			$.messager.progress('close');
		    			var data = eval('(' + data + ')'); 
		    			if(data.status==1){
		    				$.messager.alert('提示','添加成功!','info');
		    			}else{
		    				/* alert(win); */
		    				$.messager.alert('提示','添加失败!',function(){	
		    				});
		    			}
		    			/* win.location.reload(); */
		    		}
		    	});
	    	}
	    }
	    
	    $.extend($.fn.validatebox.defaults.rules, {
		    equals: {
				validator: function(value,param){
					return value == $(param[0]).val();
				},
				message: '两次密码不一致！'
		    }
		});
	    
	    function reload(){
	    	location.reload();
		}
	    
	    function close(){
	    	$('#win').window('close');
	    }
	</script>
</html>
