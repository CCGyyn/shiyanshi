$(function() {
	$('#tree').tree({
		url : '/ezsh/tree/gtTreeBuild',
		lines : true
	});
})

var managerId = 0, managerName = null, roomId = '0', roomNum = null, userId = 0, userName = null;
$('#tree').tree({
	onClick : function(node) {
		// alert node text property when clicked
		if (node.id.substring(0, node.id.indexOf("-")) == 0) { // 点击了管理处
			var temp = node.id.substring(node.id.indexOf("-") + 1);
			managerId = temp.substring(temp.indexOf("-") + 1);
			$('#formTable').datagrid({
				queryParams : {
					ptManagerId : managerId
				}
			});
		}
		if (node.id.substring(0, node.id.indexOf("-")) == 1) { // 点击了楼宇
			var temp = node.id.substring(node.id.indexOf("-") + 1);
			managerId = temp.substring(0, temp.indexOf("-"));
			temp = temp.substring(temp.indexOf("-") + 1);
			buildId = temp;
			$('#formTable').datagrid({
				queryParams : {
					ptManagerId : managerId,
					ptBuildId : buildId
				}
			});
		}
		if (node.id.substring(0, node.id.indexOf("-")) == 2) { // 房间ID
			var swap = node.id.substring((node.id.indexOf("-") + 1));// 管理处ID-楼宇ID-房间ID-userId
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
			roomNum = node.text.substring(0, node.text.indexOf("|"));
			userName = node.text.substring(node.text.indexOf("|") + 1);
			$('#formTable').datagrid({
				queryParams : {
					ptManagerId : managerId,
					ptBuildId : buildId
				}
			});
		}
	}
});

/**
 * 初始化列表
 */
$('#formTable').datagrid({
	url : '/ezsh/deviceLockA/select',
	columns : [ [ {
		field : 'lockId',
		hidden : 'true',
		title : 'ID',
		width : 100,
		align : 'center'
	}, {
		field : 'deviceId',
		title : '设备ID',
		width : 100,
		align : 'center'
	}, {
		field : 'devicePass',
		title : '设备密码',
		width : 100,
		align : 'center'
	}, {
		field : 'ptManagerId',
		hidden : 'true',
		title : '指向管理处ID',
		width : 100,
		align : 'center'
	}, {
		field : 'managerName',
		title : '管理处名',
		width : 100,
		align : 'center'
	}, {
		field : 'ptBuildId',
		hidden : 'true',
		title : '指向楼宇ID',
		width : 100,
		align : 'center'
	}, {
		field : 'buildName',
		title : '楼宇名',
		width : 100,
		align : 'center'
	} ] ]
});

function search() {
	if (managerId != null && managerId != 0) {
		$('#formTable').datagrid({
			queryParams : {
				ptManagerId : managerId,
				tradeNum : $("#tradeNum").val()
			}
		});
	} else {
		$.messager.alert('提示', '请选择小区!', 'info');
	}
}

function add() {
	var selected = $('#tree').tree('getSelected');
	if (selected) {
		var depth = selected.id.substring(0, selected.id.indexOf("-"));
		if (depth == 1) {
			parent
					.$('#win')
					.window(
							{
								title : '添加门锁设备',
								width : 350,
								height : 280,
								modal : true,
								maximizable : false,
								minimizable : false,
								resizable : false,
								content : "<iframe src='/ezsh/base/goURL/deviceLock/add' height='100%' width='100%' frameborder='0px' ></iframe>"
							});
		} else {
			alert("请选择楼宇！");
		}
	} else {
		alert("请选择楼宇！");
	}
}

function remove() {
	var row = $('#formTable').datagrid('getSelected');
	if (row) {
		$.ajax({
			type : "GET",
			url : "/ezsh/deviceLockA/delete",
			data : {
				"lockId" : row.lockId
			},
			dataType : "json",
			success : function(data) {
				if (data.status > 0) {
					$.messager.alert('提示', '删除成功!', 'info');
				} else {
					$.messager.alert('提示', '删除失败!', 'info');
				}
				$('#formTable').datagrid('reload');
			}
		})
	} else {
		alert("请至少选中一条数据！");
	}
}

function changePass() {
	var row = $('#formTable').datagrid('getSelected');
	if (row) {
		$('#win1').window('open');
		$('#formInfo1').form('load', {
			lockId : row.lockId,
			devicePassOld : row.devicePass
		});
	} else {
		alert("请至少选中一条数据！");
	}
}

function edit() {
	var row = $('#formTable').datagrid('getSelected');
	if (row) {
		$('#win').window('open');
		$('#formInfo').form('load', {
			lockId : row.lockId,
			ptManagerId : row.ptManagerId,
			deviceId : row.deviceId
		});
	} else {
		alert("请至少选中一条数据！");
	}
}

function sub() {
	$("#formInfo").form("enableValidation");
	if ($("#formInfo").form("validate")) {
		$('#formInfo').form('submit', {
			url : "/ezsh/deviceLockA/update",
			onSubmit : function(param) {
            
			},
			success : function(data) {
				var data = eval('(' + data + ')');
				if (data.status == 1) {
					$.messager.alert('提示', '修改成功!', 'info');
				} else {
					$.messager.alert('提示', '修改失败!', function() {
					});
				}
				$('#formTable').datagrid('reload');
			}
		});
	}
}

function subPassChange() {
	$("#formInfo1").form("enableValidation");
	if ($("#formInfo1").form("validate")) {
		$('#formInfo1').form('submit', {
			url : "/ezsh/deviceLockA/updatePass",
			onSubmit : function(param) {

			},
			success : function(data) {
				var data = eval('(' + data + ')');
				if (data.status == 1) {
					$.messager.alert('提示', '修改成功!', 'info');
				} else {
					alert(win);
					$.messager.alert('提示', '修改失败!', function() {
					});
				}
				$('#formTable').datagrid('reload');
			}
		});
	}
}

function close() {
	$('#win').window('close');
}
function close1() {
	$('#win1').window('close');
}