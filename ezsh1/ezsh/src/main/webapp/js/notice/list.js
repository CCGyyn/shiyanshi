var wImgText = window.wangEditor;
var editorImgText = new wImgText('#editorImgText');
editorImgText.customConfig.uploadImgShowBase64 = true;
editorImgText.customConfig.showLinkImg = false;// 隐藏网络图片
editorImgText.customConfig.uploadFileName = 'goodsImageTextFiles';
editorImgText.customConfig.menus = [ 'head', 'bold', 'italic', 'underline',
		'image', // 插入图片
];
editorImgText.create();


$(function() {
	$('#tree').tree({
		url : '/ezsh/tree/gtTreeManager',
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
					ptManagerId : managerId,
					noticeTitle : $("#noticeTitle").val()
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
					ptManagerId : $("#ptManagerId").combobox('getValue'),
					noticeTitle : $("#noticeTitle").val()
				}
			});
		}
		if (node.id.substring(0, node.id.indexOf("-")) == 2) {// 房间ID
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
					ptManagerId : $("#ptManagerId").combobox('getValue'),
					noticeTitle : $("#noticeTitle").val()
				}
			});
		}
	}
});

/**
 * 初始化列表
 */
$('#formTable').datagrid({
	url : '/ezsh/notice/select',
	columns : [ [ {
		field : 'noticeId',
		hidde : true,
		title : '公告ID',
		width : 100
	}, {
		field : 'noticeTitle',
		title : '公告标题',
		width : 100
	}, {
		field : 'noticeTime',
		title : '创建时间',
		width : 100,
		align : 'right'
	} ] ]
});

/**
 * @author qwc 2017年10月23日下午7:45:19 void 编辑
 */
function edit() {
	var row = $('#formTable').datagrid('getSelected');
	/*
	 * var url = '/ezsh/charge/selectByManagerId?pManagerId='+row.pManagerId;
	 * $('#pChargeItemId').combobox('reload', url);
	 */
	if (row) {
		$('#win').window('open');
		$('#formInfo').form('load', {
			noticeId : row.noticeId,
			noticeTitle : row.noticeTitle,
			ptManagerId : row.ptManagerId,
		});
		editorImgText.txt.html(row.noticeContent);
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
		url : "/ezsh/notice/update",
		onSubmit : function(param) {
			param.noticeContent = editorImgText.txt.html();
			param.managerName = $('#ptManagerId').combobox('getText');
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

function close() {
	$('#win').window('close');
}

/**
 * @author qwc 2017年10月23日下午7:46:48 void 添加
 */
function add() {
	parent
			.$('#win')
			.window(
					{
						title : '添加公告',
						width : 700,
						height : 550,
						modal : true,
						maximizable : false,
						minimizable : false,
						resizable : false,
						content : "<iframe src='/ezsh/base/goURL/notice/add' height='100%' width='100%' frameborder='0px' ></iframe>"
					});
}

/**
 * @author qwc 2017年10月23日下午7:46:28 void 删除公告
 */
function remove() {
	var row = $('#formTable').datagrid('getSelected');
	$.ajax({
		type : "GET",
		url : "/ezsh/notice/delete",
		data : {
			"noticeId" : row.noticeId
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
	var selected = $('#tree').tree('getSelected');
	if(selected){
		var temp = selected.id.substring(selected.id.indexOf("-") + 1);
		$('#formTable').datagrid({
			queryParams : {
				ptManagerId : temp,
				noticeTitle : $("#noticeTitle").val()
			}
		});
	} else {
		alert("请选择楼宇！");
	}
}