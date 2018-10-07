$(function(){
	$('#tree').tree({
	    url:'/ezsh/management/gtManagerTree',
	    lines:true
	});
})
		
$('#tree').tree({
	onClick: function(node){
		if(node.id.indexOf("-")){
			var select=node.id.substring(0,node.id.indexOf("-"));
			if(select==0){//管理处
				$('#pManagerId').combobox('setValue', node.text);
				var managerId=node.id.substring((node.id.indexOf("-")+1));
				var url = '/ezsh/grid/selectSimple?pManagerId='+managerId;
			    $('#pChargeItemId').combobox('reload', url);
			}
			if(select==2){
				var selected = $('#tree').tree('getSelected');
				var nodeParent = $("#tree").tree("getParent",selected.target);
				var nodeParent1=$("#tree").tree("getParent",nodeParent.target);
				alert(nodeParent1.text);
				var managerId=nodeParent1.id.substring((node.id.indexOf("-")+1));
				$("#pRoomId").textbox('setValue',node.id.substring((node.id.indexOf("-")+1)));
				$("#roomNum").textbox('setValue',node.text);
				$("#pBuildId").textbox('setValue',nodeParent.id.substring((node.id.indexOf("-")+1)));
				$("#buildName").textbox('setValue',nodeParent.text);
				$('#pManagerId').combobox('setValue', nodeParent1.id.substring((node.id.indexOf("-")+1)));
				var url = '/ezsh/grid/selectSimple?pManagerId='+managerId;
			    $('#pChargeItemId').combobox('reload', url);
			}
		}
	}
});
	
var win = parent.$("iframe[title='表计类别设置']").get(0).contentWindow;
/**
 * @author qwc
 * 2017年10月23日下午9:09:40 
 * void 抄表录入添加
 */
function sub() {
	$("#formInfo").form("enableValidation");
	if ($("#formInfo").form("validate")) {
		$.messager.progress();	
    	$('#formInfo').form('submit', {
    		url: "/ezsh/gridRecord/add",
    		onSubmit: function(param) {

    		},
    		success: function(data) {
    			$.messager.progress('close');
    			var data = eval('(' + data + ')'); 
    			if (data.status == 1) {
    				$.messager.alert('提示','添加成功!','info');
    			} else {		
    				$.messager.alert('提示','添加失败!',
    						function() {});
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