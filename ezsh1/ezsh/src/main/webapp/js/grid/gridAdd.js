var win = parent.$("iframe[title='表计类别设置']").get(0).contentWindow;
/**
 * @author qwc 2017年10月23日下午7:41:26 void 提交添加
 */
function sub() {
	$("#formInfo").form("enableValidation");
	if ($("#formInfo").form("validate")) {
		$.messager.progress();
		$('#formInfo').form('submit', {
			url : "/ezsh/grid/add",
			onSubmit : function(param) {

			},
			success : function(data) {
				$.messager.progress('close');
				var data = eval('(' + data + ')');
				if (data.status == 1) {
					$.messager.alert('提示', data.message, 'info');
				} else {
					//alert(win);
					$.messager.alert('提示', data.message, function() {
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