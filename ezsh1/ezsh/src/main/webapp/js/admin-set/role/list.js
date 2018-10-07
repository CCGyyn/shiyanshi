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
					keyWord: $("#search").val()
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
					keyWord: $("#search").val()
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
					keyWord: $("#search").val()
				}
			});
		}
	}
});

$("#add-role").click(function(){
	self.location.href="/ezsh/ro/jumpToAddRole";
})

$('#search').searchbox({
    searcher:function(value,name){
    	/*if(value==null||value==""){
    		alert("请输入名称搜索！");
    	}else{    		
    		self.location.href="/ezsh/ro/roList.html?startPage=1&pageSize=10&keyWord="+value;
    	}*/
    	var selected = $('#tree').tree('getSelected');
    	if(selected){
    		var temp = selected.id.substring(selected.id.indexOf("-") + 1);
    		$('#formTable').datagrid({
        		queryParams: {
        			keyWord: value,
        			ptManagerId:temp
        		}
        	});	
    	} else {
    		alert("请选择楼宇！");
    	}
    	
    },
    prompt:'名称搜索'
});