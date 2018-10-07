$("#billWay").combobox({
	onChange: function (n,o) {
		if(n!=1){
			$("#cycleCount").combobox({  
                disabled:true 
            });
			$("#cycleUnit").combobox({  
                disabled:true 
            });
		}else{
			$("#cycleCount").combobox({  
                disabled:false  
            });
			$("#cycleUnit").combobox({  
                disabled:false 
            });
		}
	}
})

$(function(){
	var win = parent.$("iframe[title='房间收费设置']").get(0).contentWindow;
	var swap=win.selected.id.substring(win.selected.id.indexOf("-")+1);
	var managerId=swap.substring(0,swap.indexOf("-"));
	var roomId=win.selected.id.substring(win.selected.id.lastIndexOf("-")+1);
	var roomNum=win.selected.text.substring(0,win.selected.text.indexOf("|"));
	swap=swap.substring(swap.indexOf("-")+1);
	$("#pBuildId").textbox('setValue',swap.substring(0,swap.indexOf("-")));
	$("#pRoomId").textbox('setValue',roomId);
	$("#roomNum").textbox('setValue',roomNum);
	/*$('#pManagerId').combobox('setValue', 1);*/
})

function sub(){
	$("#formInfo").form("enableValidation");
	if ($("#formInfo").form("validate")) {
		$.messager.progress();	
    	$('#formInfo').form('submit', {
    		url: "/ezsh/charge/add",
    		onSubmit: function(param){

    		},
    		success: function(data){
    			$.messager.progress('close');
    			var data = eval('(' + data + ')'); 
    			if (data.status==1){
    				$.messager.alert('提示','添加成功!','info');
    			} else if (data.status==0){
    				$.messager.alert('提示','添加失败!','info');
    			} else if (data.status==-1){
    				$.messager.alert('提示','添加失败!'+data.message,'info');
    			} else if (data.status==-2){
    				$.messager.alert('提示','打印次数已存在!'+data.message,'info');
    			}
    			parent.$('#formTable').datagrid('reload'); 
    		}
    	});
	}
}

function close(){	
	parent.$('#win').window('close');
}