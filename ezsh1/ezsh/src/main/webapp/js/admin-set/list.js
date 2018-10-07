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
					adName: $("#search").val()
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
					adName: $("#search").val()
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
					ptManagerId : managerId,
					adName: $("#search").val()
				}
			});
		}
	}
});


/* 添加管理员 */
function add(){
	parent.$('#win').window({    
		title :'添加管理员',						
	    width:400,    
	    height:380,    
	    modal:true,
	    maximizable:false,
	    minimizable:false,
	    resizable:false,
	    content:"<iframe src='/ezsh/base/goURL/admin-set/add' height='100%' width='100%' frameborder='0px' ></iframe>"  
	}); 
}

/* 修改管理员信息 */
$("#edit").click(function(){
	var row = $('#formTable').datagrid('getSelected');
	if (row){
		$("#adRoleId").combobox('setValue',row.adRoleId)
		$('#win').window('open');
		$('#formInfo').form('load',{
			adId:row.adId,
			adName:row.adName,
			adAccount:row.adAccount,
			adTelephone:row.adTelephone,
			adManagerId:row.adManagerId,
		});
	}else{
		alert("请至少选中一条数据！");
	}
})

$("#changePass").click(function(){
	var row = $('#formTable').datagrid('getSelected');
	if (row){
		$('#win1').window('open');
		$('#formInfo1').form('load',{
			adId:row.adId,
			adAccount:row.adAccount,
		});
	}else{
		alert("请至少选中一条数据！");
	}
})

/* 提交修改 */
function sub(){
	$("#formInfo").form("enableValidation");
	if ($("#formInfo").form("validate")) {
    	$('#formInfo').form('submit', {
    		url: "/ezsh/ad/update",
    		onSubmit: function(param){

    		},
    		success: function(data){
    			var data = eval('(' + data + ')'); 
    			if(data.status==1){
    				$.messager.alert('提示','修改成功!','info');
    			}else{
    				alert(win);
    				$.messager.alert('提示','修改失败!',function(){	
    				});
    			}
    		}
    	});
	}
}

function subNewPass(){
	$("#formInfo1").form("enableValidation");
	if ($("#formInfo1").form("validate")) {
    	$('#formInfo1').form('submit', {
    		url: "/ezsh/ad/changePass",
    		onSubmit: function(param){

    		},
    		success: function(data){
    			var data = eval('(' + data + ')'); 
    			if(data.status==1){
    				$.messager.alert('提示','密码修改成功!','info');
    			}else{
    				$.messager.alert('提示','密码修改失败!',function(){	
    				});
    			}
    		}
    	});
	}
}

$('#search').searchbox({
    searcher:function(value,name){
    	var selected = $('#tree').tree('getSelected');
    	if(selected){
    		var temp = selected.id.substring(selected.id.indexOf("-") + 1);
			//managerId = temp;
    		$('#formTable').datagrid({
    			queryParams: {
    				adName: value,
    				adManagerId:temp
    			}
    		}); 
    	} else {
    		alert("请选择楼宇！");
    	}
    	
    	/*if(value==null||value==""){
    		alert("请输入姓名搜索！");
    	}else{
    		$('#formTable').datagrid({
    			queryParams: {
    				adName: value,
    			}
    		}); 
    	}*/
    },
    prompt:'姓名搜索'
});

$.extend($.fn.validatebox.defaults.rules, {
    equals: {
		validator: function(value,param){
			return value == $(param[0]).val();
		},
		message: '两次密码不一致！'
    }
});

function close2(){
	$("#win").window('close');
}

function close1(){
	$("#win1").window('close');
}