var wImgText = window.wangEditor;
var editorImgText = new wImgText('#editorImgText');
editorImgText.customConfig.uploadImgShowBase64 = true;
editorImgText.customConfig.showLinkImg = false;// 隐藏网络图片
editorImgText.customConfig.uploadFileName = 'goodsImageTextFiles';
editorImgText.customConfig.menus = [ 'head', 'bold', 'italic', 'underline',
		'image', // 插入图片
];
editorImgText.create();


var win = parent.$("iframe[title='公告管理']").get(0).contentWindow;

/**
 * @author qwc 2017年10月23日下午7:41:26 void 提交添加
 */
function sub() {
	$("#formInfo").form("enableValidation");
	if ($("#formInfo").form("validate")) {
		$.messager.progress();
		alert(editorImgText.txt.html());
		$('#formInfo').form('submit', {
			url : "/ezsh/notice/add",
			onSubmit : function(param) {
				param.noticeContent = editorImgText.txt.html();
				param.managerName = $('#ptManagerId').combobox('getText');
			},
			success : function(data) {
				$.messager.progress('close');
				var data = eval('(' + data + ')');
				if (data.status == 1) {
					$.messager.alert('提示', '添加成功!', 'info');
				} else {
					alert(win);
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