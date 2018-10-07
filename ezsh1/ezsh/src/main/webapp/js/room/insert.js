$(function() {

	$("[name='managerName']").validatebox({
		required : true,
		missingMessage : '请填写管理处名称！'
	});
	$("[name='managerAddr']").validatebox({
		required : true,
		missingMessage : '地址！'
	});
	// 禁用验证
	//$("#ff").form("disableValidation");
})

function submitForm() {
	var win = parent.$("iframe[title='房间档案']").get(0).contentWindow;// 返回ifram页面窗体对象（window)
	$("#ff").form("enableValidation");
	if ($("#ff").form("validate")) {
		$('#ff').form('submit', {
			url : '/ezsh/room/insert.action',
			onSubmit : function() {
				return true;
			},
			success : function(count) {
				// 可以定义为对应消息框
				if (count == 1) {
					alert("添加成功！");
				} else if (count == 2) {
					alert("房间号重复，添加失败！");
				} else {
					alert("添加失败！");
				}
				parent.$("#win").window("close");
				win.$("#dg").datagrid("reload");
			}
		});
	}
}

function closeWin() {
	parent.$("#win").window("close");
}