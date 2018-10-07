/* 构建管理处树 */
$(function(){
	$('#tree').tree({
	    url:'/ezsh/tree/gtTree',
	    lines:true
	});
})

var managerId,managerName,roomId,roomNum;
$('#tree').tree({
	onClick: function(node){
		 // alert node text property when clicked
		if(node.id.substring(0,node.id.indexOf("-"))==2){//房间ID
			
			var swap=node.id.substring((node.id.indexOf("-")+1));
			var selected = $('#tree').tree('getSelected');
			var nodeParent = $("#tree").tree("getParent",selected.target);//父节点
			var nodeParent1=$("#tree").tree("getParent",nodeParent.target);//父父节点
			managerId=swap.substring(0,swap.indexOf("-"));
			managerName=nodeParent1.text;
			swap=swap.substring((swap.indexOf("-")+1));
			var roomId=swap.substring((swap.indexOf("-")+1));
			roomNum=node.text.substring(0,node.text.indexOf("|"));
			$('#formTable').datagrid({
				queryParams: {
					ptRoomId:roomId
				}
			});	
		}
	}
});

/**
 * 初始化列表
 */
$('#formTable').datagrid({
    url:'/ezsh/rentA/select',
    columns:[[
    	{field:'rentId',hidden:'true',title:'ID',width:100,align:'center'},
		{field:'rentName',title:'出租人名字',width:100,align:'center'},
		{field:'rentTelephone',title:'联系电话',width:100,align:'center'},
		{field:'ptRoomId',hidden:'true',title:'房间ID',width:100,align:'center'},
		{field:'roomNum',title:'房间编号',width:100,align:'center',
			formatter: function(value,row,index){
				return value;
			}
		},
		{field:'ptBuildId',hidden:'true',title:'建筑ID',width:100,align:'center'},
		{field:'buildName',title:'楼宇名称',width:100,align:'center',
			formatter: function(value,row,index){
				return value;
			}
		},
		{field:'rentDescr',title:'出租信息描述',width:200,align:'center'},
		{field:'rentClassify',title:'出租类型',width:100,align:'center'},
		{field:'rentTime',title:'出租时间',width:100,align:'center'},
		{field:'rentStatus',title:'出租状态',width:100,align:'center',
			formatter: function(value,row,index){
				if(value==0){
					return "出租中";
				} else {
					return "已租出";
				}
			}	
		}
    ]]
});

var selected;
function edit(){
	var row = $('#formTable').datagrid('getSelected');
	if (row){
		$('#win').window('open');
		$('#formInfo').form('load',{
			rentId:row.rentId,
			ptManagerId:row.ptManagerId,
			rentName:row.rentName,
			rentTelephone:row.rentTelephone,
			roomNum:row.roomNum,
			rentClassify:row.rentClassify,
		    rentDescr:row.rentDescr,
		});
	}else{
		alert("请至少选中一条数据！");
	}
}

function add(){
	selected = $('#tree').tree('getSelected');
	if (selected!=null&&selected.id.substring(0,selected.id.indexOf("-"))==2){
		parent.$('#win').window({    
			title :'出租记录-新增',						
		    width:450,    
		    height:480,    
		    modal:true,
		    maximizable:false,
		    minimizable:false,
		    resizable:false,
		    content:"<iframe src='/ezsh/base/goURL/rent/add' height='100%' width='100%' frameborder='0px' ></iframe>"  
		}); 
	}else{
		alert("请选择要添加的房间！");
	}
}

function sub(){
	$('#formInfo').form('submit', {
		url: "/ezsh/rentA/update",
		onSubmit: function(param){

		},
		success: function(data){
			var data = eval('(' + data + ')'); 
			if(data.status==1){
				$.messager.alert('提示','修改成功!','info');
			}else{
				$.messager.alert('提示','修改失败!',function(){	
				});
			}
			$('#formTable').datagrid('reload');  
		}
	});
}

function del(){
	var row = $('#formTable').datagrid('getSelected');
	if(row!=null){
		$.ajax({
		    type: "GET",
		    url:"/ezsh/rentA/delete",
		    data:{"rentId":row.rentId},
		    dataType:"json",
		    success: function(data){
			    if(data.status>0){
			    	$.messager.alert('提示','删除成功!','info');
			    }else{
			    	$.messager.alert('提示','删除失败!','info');
			    }
			    $('#formTable').datagrid('reload');  
		   }
		})
	}else{
		alert("至少选择一条数据！");
	}
	
}

function close(){
	$('#win').window('close');
}

function search(){
	$('#formTable').datagrid({
		queryParams: {
			rentName:$("#rentName").textbox('getValue')
		}
	});	
}