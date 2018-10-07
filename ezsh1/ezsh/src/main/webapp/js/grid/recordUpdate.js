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

function sub() {
	$.messager.progress();
	$('#formInfo').form('submit', {
		url : "/ezsh/charge/update",
		onSubmit : function(param) {

		},
		success : function(data) {
			$.messager.progress('close');
			var data = eval('(' + data + ')');
			if (data.status == 1) {
				$.messager.alert('提示', '修改成功!', 'info');
			} else {
				$.messager.alert('提示', '修改失败!', function() {
				});
			}
		}
	});
}

function reload() {
	location.reload();
}

function close() {
	$('#win').window('close');
}

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
			location.reload();
			var pager = $('#formTable').datagrid('getPager');// 获取分页对象
			pager
					.pagination({
						onSelectPage : function(pageNumber, pageSize) {
							$(this).pagination('loading');
							alert('pageNumber:' + pageNumber + ',pageSize:'
									+ pageSize);
							$(this).pagination('loaded');
						},
						onBeforeRefresh : function() {
							alert('before refresh');
							$(this).pagination('loading');
							return true;
						},
						onRefresh : function() {
							$(this).pagination('loading');
						}
					});
		}
	})
}

function search() {
	if ($("#pManagerId").combobox('isValid')
			&& $("#pBuildId").combobox('isValid')
			&& $("#pGridId").combobox('isValid')) {
		$
				.ajax({
					type : "GET",
					url : "/ezsh/gridRecord/select",
					data : {
						"pManagerId" : $("#pManagerId").combobox('getValue'),
						"pBuildId" : $("#pBuildId").combobox('getValue'),
						"pGridId" : $("#pGridId").combobox('getValue')
					},
					dataType : "json",
					success : function(data) {
						var rowData = new Array();
						for (var i = 0; i < data.rows.length; i++) {
							rowData[i] = {
								pManagerId : data.rows[i].pManagerId,
								managerName : $("#pManagerId").combobox(
										'getText'),
								pBuildId : data.rows[i].pBuildId != null ? data.rows[i].pBuildId
										: "",
								buildName : $("#pBuildId").combobox('getText'),
								pRoomId : data.rows[i].PRoomId,
								roomNum : data.rows[i].roomNum,
								pGridId : data.rows[i].pGridId,
								gridName : $("#pGridId").combobox('getText'),
								beginDosage : data.rows[i].beginDosage,
								dosage : data.rows[i].dosage,
								enteringTime : data.rows[i].enteringTime,
								gridReadTime : data.rows[i].gridReadTime,
								gridReadType : data.rows[i].gridReadType,
								gridReadPeople : data.rows[i].gridReadPeople,
							}
						}
						$('#formTable').datagrid({
							data : rowData
						});
						var pager = $('#formTable').datagrid('getPager');// 获取分页对象
						pager.pagination({
							onSelectPage : function(pageNumber, pageSize) {
								$(this).pagination('loading');
								alert('pageNumber:' + pageNumber + ',pageSize:'
										+ pageSize);
								$(this).pagination('loaded');
							},
							onBeforeRefresh : function() {
								alert('before refresh');
								$(this).pagination('loading');
								return true;
							},
							onRefresh : function() {
								$(this).pagination('loading');
							}
						});
					}
				})
	}
}