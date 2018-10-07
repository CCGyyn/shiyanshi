<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/common/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>表计类别列表</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="description" content="表计类别列表">
<style type="text/css">
.tableStyle {
	font-size: 12px;
	padding: 5% 0px 0px 15%;
}
</style>
</head>

<body>
	<div class="easyui-layout" fit="true">
		<div region="center" border="false">
			<form id="formInfo" method="post">
				<table class="tableStyle" cellpadding="5">
					<tr>
						<td>管理小区:</td>
						<td><input id="ptManagerId" class="easyui-combobox"
							name="ptManagerId"
							data-options="valueField:'managerId',
                       				required:true,textField:'managerName',
                       				 panelHeight: 80,
                       				url:'/ezsh/build/findManager',
                       				onSelect: function(rec){
								    var url = '/ezsh/build/gtBuildListById?buildManagerId='+rec.managerId;
								    $('#ptBuildId').combobox('reload', url);
								 }" />
						</td>
					</tr>
					<tr style="display:none;">
						<td>管理处名:</td>
						<td><input id="managerName" class="easyui-textbox"
							type="text" name="managerName" required="required"
							style="width:100%;" /></td>
					</tr>

					<tr>
						<td>楼栋:</td>
						<td><input id="ptBuildId" class="easyui-combobox"
							name="ptBuildId"
							data-options="valueField:'buildId',
				   				textField:'buildName',
				   				required:true,
				   				">
						</td>
					</tr>

					<tr style="display:none;">
						<td>楼栋名:</td>
						<td><input id="buildName" class="easyui-textbox" type="text"
							name="buildName" required="required" style="width:100%;" /></td>
					</tr>

					<tr>
						<td>设备ID:</td>
						<td><input id="deviceId" class="easyui-textbox" type="text"
							name="deviceId" required="required" style="width:100%;" /></td>
					</tr>
					<tr>
						<td>设备密码:</td>
						<td><input id="devicePass" class="easyui-numberbox"
							type="text" name="devicePass" required="required"
							style="width:100%;" /></td>
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
<script type="text/javascript" src="/ezsh/js/deviceLock/add.js"></script>
</html>
