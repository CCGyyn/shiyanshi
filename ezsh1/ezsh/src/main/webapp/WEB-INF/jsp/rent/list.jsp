<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/common/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>出租管理</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="description" content="出租管理">
<style type="text/css">
.tableStyle {
	font-size: 12px;
	padding: 10% 0px 0px 10%;
}
</style>
</head>

<body class="easyui-layout">
	<!-- toolbar start -->
	<div id="tb" style="padding:5px;height:auto">
		<div style="margin-bottom:5px">
			<a href="javascript:add()" class="easyui-linkbutton"
				iconCls="icon-add" plain="true"></a> <a href="javascript:edit()"
				class="easyui-linkbutton" iconCls="icon-edit" plain="true"></a> <a
				href="javascript:del()" class="easyui-linkbutton"
				iconCls="icon-remove" plain="true"></a>
		</div>
	</div>
	<!-- toolbar end -->

	<!-- query module start -->
	<div data-options="region:'north',split:false," title="查询条件"
		style="height:100px">
		<div style="padding:15px;height:auto">
			<!-- 起始时间: <input class="easyui-datebox" style="width:100px">
				终止时间: <input class="easyui-datebox" style="width:100px"> -->
			姓名查询: <input id="rentName" class="easyui-textbox"
				style="width:100px;height:25px"> <a
				href="javascript:search()" class="easyui-linkbutton"
				iconCls="icon-search">Search</a>
		</div>
	</div>
	<!-- query module end -->

	<!-- tree start -->
	<div data-options="region:'west',title:'楼宇',split:true"
		style="width:150px;">
		<ul class="easyui-tree" id="tree">

		</ul>
	</div>
	<!-- tree end -->

	<!-- datagrid start -->
	<div data-options="region:'center',title:'出租记录列表',iconCls:'icon-ok'">
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
	<!-- datagrid end -->

	<!-- edit window start -->
	<div id="win" class="easyui-window" title="出租记录-修改"
		style="width:450px;height:480px"
		data-options="iconCls:'icon-edit',
		    modal:true,
		    closed:true,
		    collapsible:false,
		    minimizable:false,
		    maximizable:false,
		    resizable:false
		    ">
		<div class="easyui-layout" fit="true">
			<div region="center" border="false">
				<form id="formInfo" method="post">
					<table class="tableStyle" cellpadding="5">
						<tr>
							<td style="display:none"><input id="rentId"
								class="easyui-textbox" type="text" name="rentId" /></td>
							<!-- <td style="display:none">
				    				<input id="managerName"  class="easyui-textbox" type="text" name="managerName"/>
				    			</td> -->
							<td>管理处:</td>
							<td><input id="ptManagerId" class="easyui-combobox"
								readonly="readonly" style="width:200px;" name="ptManagerId"
								data-options="valueField:'managerId',
	                      				required:true,
	                      				textField:'managerName',
	                      				 panelHeight: 80,
	                      				url:'/ezsh/build/findManager',
	                      				onSelect: function(rec){	
									    	
									    }
	                      				" />
							</td>
						</tr>

						<!-- <tr>
				    			<td style="display:none">
				    				<input id="buildName"  class="easyui-textbox" type="text" name="buildName"/>
				    			</td>
				    			<td style="display:none">
				    				<input id="ptBuildId"  class="easyui-textbox" type="text" name="ptBuildId"/>
				    			</td>
				    		</tr> -->

						<tr>
							<td>出租人姓名:</td>
							<td><input id="rentName" class="easyui-textbox"
								style="width:200px;" type="text" name="rentName" /></td>
						</tr>

						<tr>
							<td>联系方式:</td>
							<td><input id="rentTelephone" class="easyui-textbox"
								style="width:200px;" type="text" name="rentTelephone" /></td>
						</tr>

						<tr>
							<!-- <td style="display:none">
				    				<input id="ptRoomId"  class="easyui-textbox" type="text" name="ptRoomId"/>
				    			</td> -->
							<td>房间编号:</td>
							<td><input id="roomNum" class="easyui-textbox"
								style="width:200px;" type="text" readonly="readonly"
								name="roomNum" required="required" /></td>
						</tr>

						<tr>
							<td>出租类型:</td>
							<td><select id="rentClassify" class="easyui-combobox"
								data-options="editable:false" name="rentClassify"
								style="width:200px;">
									<option value="房屋">房屋</option>
									<option value="车位">车位</option>
									<option value="商铺">商铺</option>
							</select></td>
						</tr>

						<tr>
							<td>详细描述:</td>
							<td><input id="rentDescr" class="easyui-textbox"
								data-options="multiline:true" value="" name="rentDescr"
								style="width:200px;height:140px"></td>
						</tr>
					</table>
				</form>
			</div>
			<div region="south" border="true"
				style="text-align:right;height:50px;line-height:30px;padding:10px 15px 0px 0px;">
				<a class="easyui-linkbutton" icon="icon-ok" href="javascript:sub()">确定</a>
				<a class="easyui-linkbutton" icon="icon-cancel"
					href="javascript:close()">取消</a>
			</div>
		</div>
	</div>
	<!-- edit window end -->

</body>
<script type="text/javascript" src="/ezsh/js/rent/list.js"></script>
</html>
