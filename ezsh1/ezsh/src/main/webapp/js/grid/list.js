/**
 * 初始化列表
 */
$('#formTable').datagrid({
	url : '/ezsh/grid/select',
	columns : [ [ {
		field : 'pManagerId',
		hidde : true,
		title : '管理处ID',
		width : 100
	}, {
		field : 'gridId',
		title : '类别ID',
		width : 100
	}, {
		field : 'gridName',
		title : '类别名称',
		width : 100,
		align : 'right'
	}, {
		field : 'chargeItemName',
		title : '收费项目',
		width : 100,
		align : 'right'
	}, {
		field : 'managerName',
		title : '管理处名称',
		width : 100,
		align : 'right'
	} ] ]
});

/**
 * @author qwc 2017年10月23日下午7:45:19 void 编辑表计类别
 */
function edit() {
	var row = $('#formTable').datagrid('getSelected');
	var url = '/ezsh/charge/selectByManagerId?pManagerId=' + row.pManagerId;
	$('#pChargeItemId').combobox('reload', url);
	if (row) {
		$('#win').window('open');
		$('#formInfo').form('load', {
			gridId : row.gridId,
			gridName : row.gridName,
			pChargeItemId : row.pChargeItemId,
			pManagerId : row.pManagerId,
		});
	} else {
		alert("请至少选中一条数据！");
	}
}

/**
 * @author qwc 2017年10月23日下午7:45:47 void 提交编辑更新
 */
function sub() {
	$.messager.progress();
	$('#formInfo').form('submit', {
		url : "/ezsh/grid/update",
		onSubmit : function(param) {

		},
		success : function(data) {
			$.messager.progress('close');
			var data = eval('(' + data + ')');
			if (data.status == 1) {
				$.messager.alert('提示', '修改成功!', 'info');
			}else if (data.status == 2) {
				$.messager.alert('提示', '表计名重复，修改失败!', 'info');
			} else {
				$.messager.alert('提示', '修改失败!', function() {
				});
			}
		}
	});
}

function close() {
	$('#win').window('close');
}

/**
 * @author qwc 2017年10月23日下午7:46:48 void 添加表计类别
 */
function add() {
	parent
			.$('#win')
			.window(
					{
						title : '添加表计类别',
						width : 350,
						height : 280,
						modal : true,
						maximizable : false,
						minimizable : false,
						resizable : false,
						content : "<iframe src='/ezsh/base/goURL/grid/gridAdd' height='100%' width='100%' frameborder='0px' ></iframe>"
					});
}

/**
 * @author qwc 2017年10月23日下午7:46:28 void 删除表计类别
 */
function remove() {
	var row = $('#formTable').datagrid('getSelected');
	$.ajax({
		type : "GET",
		url : "/ezsh/grid/delete",
		data : {
			"gridId" : row.gridId
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
}

function search() {
	$('#formTable').datagrid({
		queryParams : {
			pManagerId:$("#pManagerId").combobox('getValue'),
			gridName : $("#gridName").val()
		}
	});
}