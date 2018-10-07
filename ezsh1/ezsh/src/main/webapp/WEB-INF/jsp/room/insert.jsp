<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
<%@ include file="/common/common.jspf"%>
<title>新增房间</title>
</head>
<style>
.tableStyle1 {
	font-size: 12px;
	font-style: normal;
}
</style>
<body>
	<form id="ff" method="post">
		<div id="tt" class="easyui-tabs" style="width:540px;height:320px;">
			<div title="基本信息" style="padding:10px;">
				<form id="ff" method="post">
					<table class="tableStyle1" cellpadding="5">
						<tr>
							<td>管理处 &nbsp<span style="color:red;font-size:2px">*</span></td>
							<td>
							<input name="managerId" class="easyui-combobox"
								data-options="
								required:true,
								editable:false,    
					            valueField: 'managerId',    
					            textField: 'managerName',
					             panelHeight: 80,
					            url: '${proPath }/build/findManager.action',    
					            onSelect: function(rec){    
					            var url = '${proPath }/room/findBuild.action?managerId='+rec.managerId;    
					            $('#buildId').combobox('reload', url);    
					            }" />
							</td>
							
							<td>楼宇 &nbsp<span style="color:red;font-size:2px">*</span></td>
							<td><input id="buildId" name="buildId"
								class="easyui-combobox"
								data-options="
								required:true,
								editable:false,
								valueField:'buildId',
								textField:'buildName'" />
							</td>
						</tr>
						<tr>
							<td>房间类型：</td>
							<td>
								<select name="roomType" class="easyui-combobox"
								    data-options="
									required:true,
									editable:false,"
									style="width:153px;font-size: 12px">
										<option value="" selected="selected">--请选择--</option>
										<option value="0">住宅</option>
										<option value="1">公寓</option>
										<option value="2">办公</option>
										<option value="3">厂房</option>
										<option value="4">仓库</option>
										<option value="5">宿舍</option>
										<option value="6">其他</option>
								</select>
							</td>
							<td>房间状态</td>
							<td>
							<select name="roomStatus" class="easyui-combobox"
								data-options="
								required:true,
								editable:false,"
								style="width:153px;font-size: 12px">
									<option value="" selected="selected">--请选择--</option>
									<option value="0">未售</option>
									<option value="1">已售</option>
									<option value="2">未租</option>
									<option value="3">已租</option>
									<option value="4">自用</option>
									<option value="5">入住</option>
									<option value="6">空置</option>
									<option value="7">未交付</option>
							</select>
							</td>
						</tr>
						<tr>
							<td>建筑面积</td>
							<td><input name="buildArea" class="easyui-textbox"
								type="text" data-options="required:true"></input></td>
							<td>收费服务对象</td>
							<td>
							<select name="chargeMan" class="easyui-combobox"
								data-options="
								required:true,
								editable:false,"
								style="width:153px;font-size: 12px">
								<option value="" selected="selected">--请选择--</option>
								<option value="无">无</option>
								<option value="业主">业主</option>
								<option value="租户">租户</option>
							</select>
							</td>
						</tr>
						<tr>
							<td>楼层数</td>
							<td><input class="easyui-textbox" type="text"
								name="roomFloor" data-options="required:true"></input></td>
							<td>房间用途</td>
							<td><input class="easyui-textbox" type="text" name="roomUse"
								data-options="required:true"></input></td>
						</tr>
						<tr>
							<td>房间号<span style="color:red;font-size:2px">*</span></td>
							<td><input class="easyui-validatebox" type="text" name="roomNum"
								required="required"></input></td>
							<td>装修情况</td>
							<td><input class="easyui-textbox" type="text"
								name="decorate" data-options="required:true"></input></td>
						</tr>
						<tr>
							<td>位置</td>
							<td><input class="easyui-textbox" type="text"
								name="position" data-options="required:true"></input></td>
							<td>朝向</td>
							<td><input class="easyui-textbox" type="text" name="toward"
								data-options="required:true"></input></td>
						</tr>
						<tr>
							<td>联系人</td>
							<td><input class="easyui-textbox" type="text" name="contact"
								data-options="required:true"></input></td>
							<td>备注</td>
							<td><input class="easyui-textbox" type="text" name="remark"
								data-options="required:true"></input></td>
						</tr>
					</table>
				</form>
			</div>
			<div title="扩展信息" data-options="closable:true"
				style="overflow:auto;padding:10px;">
				<table class="tableStyle1" cellpadding="5">
					<tr>
						<td>租金</td>
						<td><input class="easyui-textbox" type="text" name="rent"
							data-options="required:true"></input></td>
						<td>管理费</td>
						<td><input class="easyui-textbox" type="text"
							name="managementFee" data-options="required:true"></input></td>
					</tr>
					<tr>
						<td>单价</td>
						<td><input class="easyui-textbox" type="text"
							name="singlePrice" data-options="required:true"></input></td>
						<td>总价</td>
						<td><input class="easyui-textbox" type="text" name="sumPrice"
							data-options="required:true"></input></td>
					</tr>
					<tr>
						<td>是否有效</td>
						<td><select name="effectiveStatus" class="easyui-combobox"
							style="width:160px;font-size: 12px">
								<option value="0">否</option>
								<option value="1">是</option>
						</select></td>
					</tr>
				</table>
			</div>

		</div>

		<div style="text-align:right;">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				data-options="iconCls:'icon-ok'" onclick="submitForm()">提交</a> <a
				href="javascript:void(0)" class="easyui-linkbutton"
				data-options="iconCls:'icon-no'" onclick="closeWin()">关闭</a>
		</div>
</body>
<script type="text/javascript" src="/ezsh/js/room/insert.js"></script>
</html>
