$(function(){
	var win = parent.$("iframe[title='出租登记']").get(0).contentWindow;
	//$('#pManagerId').combobox('setValue',""); 
	/* alert($("#ptManagerId").combobox('getValue')); */
	var swap=win.selected.id.substring(win.selected.id.indexOf("-")+1);
	var nodeParent = win.$("#tree").tree("getParent",win.selected.target);//父节点
	var nodeParent1=win.$("#tree").tree("getParent",nodeParent.target);//父父节点
	var buildName=nodeParent.text;
	var managerName=nodeParent1.text;
	var managerId=swap.substring(0,swap.indexOf("-"));
	
	swap=swap.substring(swap.indexOf("-")+1);
	var buildId=swap.substring(0,swap.indexOf("-"));
	
	var roomId=win.selected.id.substring(win.selected.id.lastIndexOf("-")+1);
	var roomNum=win.selected.text.substring(0,win.selected.text.indexOf("|"));
	swap=swap.substring(swap.indexOf("-")+1);
	/* $("#pBuildId").textbox('setValue',swap.substring(0,swap.indexOf("-"))); */
	$("#ptRoomId").textbox('setValue',roomId);
	$("#roomNum").textbox('setValue',roomNum);
	$('#ptManagerId').combobox('setValue', managerId);
	$('#managerName').textbox('setValue', managerName);
	$('#buildName').textbox('setValue', buildName);
	$('#ptBuildId').textbox('setValue', buildId);
})

function sub(){
	$("#formInfo").form("enableValidation");
	if ($("#formInfo").form("validate")) {
		$.messager.progress();	
    	$('#formInfo').form('submit', {
    		url: "/ezsh/rentA/add",
    		onSubmit: function(param){

    		},
    		success: function(data){
    			$.messager.progress('close');
    			var data = eval('(' + data + ')'); 
    			if(data.status==1){
    				$.messager.alert('提示','添加成功!','info');
    			}else{
    				alert(win);
    				$.messager.alert('提示','添加失败!',function(){	
    				});
    			}
    			win.location.reload();
    		}
    	});
	}
}

function reload(){
	location.reload();
}

function close(){	
	$('#win').window('close');
}