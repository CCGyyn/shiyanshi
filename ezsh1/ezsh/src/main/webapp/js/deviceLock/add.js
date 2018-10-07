var win = parent.$("iframe[title='门锁列表']").get(0).contentWindow;
$(function() {
	// win.$('#tree').tree('getSelected');
	var selected = win.$('#tree').tree('getSelected');
	var depth = selected.id.substring(0, selected.id.indexOf("-"));
	if (depth == 1) {
		var nodeParent = win.$("#tree").tree("getParent", selected.target);
		var managerId = nodeParent.id.substring((selected.id.indexOf("-") + 1));
		var buildId = selected.id.substring((selected.id.lastIndexOf("-") + 1));
		var managerName = nodeParent.text;
		var buildName = selected.text;
		var url = '/ezsh/build/gtBuildListById?buildManagerId=' + managerId;
		$('#ptBuildId').combobox('reload', url);
		$("#ptBuildId").combobox('setValue', buildId);
		$("#buildName").textbox('setValue', buildName);
		$("#ptManagerId").combobox('setValue', managerId);
		$("#managerName").textbox('setValue', managerName);
	}
})

/**
 * @author qwc
 * void 提交门锁信息添加
 */
function sub() {
	$("#formInfo").form("enableValidation");
	if ($("#formInfo").form("validate")) {
		$.messager.progress();
		$('#formInfo').form('submit', {
			url : "/ezsh/deviceLockA/add",
			onSubmit : function(param) {

			},
			success : function(data) {
				$.messager.progress('close');
				var data = eval('(' + data + ')');
				if (data.status == 1) {
					$.messager.alert('提示', '添加成功!', 'info');
				} else if (data.status == 2) {
					$.messager.alert('提示', '添加失败,不能重复添加!', 'info');
				} else {
					$.messager.alert('提示', '添加失败!', function() {
					});
				}
				win.$('#formTable').datagrid('reload');
			}
		});
	}
}

function close() {
	parent.$('#win').window('close');
}