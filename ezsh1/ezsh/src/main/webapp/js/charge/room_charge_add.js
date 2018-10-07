$(function(){
	var win = parent.$("iframe[title='房间收费设置']").get(0).contentWindow;
	$('#pManagerId').combobox('setValue',""); 
	var swap=win.selected.id.substring(win.selected.id.indexOf("-")+1);
	var managerId=swap.substring(0,swap.indexOf("-"));
	var roomId=win.selected.id.substring(win.selected.id.lastIndexOf("-")+1);
	var roomNum=win.selected.text.substring(0,win.selected.text.indexOf("|"));
	swap=swap.substring(swap.indexOf("-")+1);
	$("#pBuildId").textbox('setValue',swap.substring(0,swap.indexOf("-")));
	$("#pRoomId").textbox('setValue',roomId);
	$("#roomNum").textbox('setValue',roomNum);
	$('#pManagerId').combobox('setValue', 1);
})

function sub(){
	var win = parent.$("iframe[title='房间收费设置']").get(0).contentWindow;
	$("#formInfo").form("enableValidation");
	if ($("#formInfo").form("validate")) {
		$.messager.progress();	
    	$('#formInfo').form('submit', {
    		url: "/ezsh/chargeRoom/add",
    		onSubmit: function(param){

    		},
    		success: function(data){
    			$.messager.progress('close');
    			var data = eval('(' + data + ')'); 
    			if(data.status==1){
    				$.messager.alert('提示','添加成功!','info');
    			}else{
    				$.messager.alert('提示','添加失败!',function(){	
    				});
    			}
    			win.$("#formTable").datagrid("reload");
    		}
    	});
	}
}

function close(){	
	parent.$('#win').window('close');
}