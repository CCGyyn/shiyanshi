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
					roomId:roomId
				}
			});	
		}
	}
});

/**
 * 初始化列表
 */
$('#formTable').datagrid({
    url:'/ezsh/chargeRoom/getRCItems',
    columns:[[
    	{field:'chargeRoomId',hidden:'true',title:'房间收费项目ID',width:100,align:'center'},
		{field:'pManagerId',hidden:'true',title:'管理处ID',width:100,align:'center'},
		{field:'managerName',title:'管理处名称',width:100,align:'center',
			formatter: function(value,row,index){
				return managerName;
			}
		},
		{field:'pRoomId',hidden:'true',title:'房间ID',width:100,align:'center'},
		{field:'roomNum',title:'房间编号',width:100,align:'center',
			formatter: function(value,row,index){
				return roomNum;
			}
		},
		{field:'chargeItemInfo',hidden:'true',title:'收费项目实体信息',width:100,align:'center'},
		{field:'pChargeItemId',title:'收费项目ID',width:100,align:'center',
			formatter: function(value,row,index){
				return row.chargeItemInfo.chargeId;
			}
		},
		{field:'chargeItemName',title:'收费项目名称',width:100,align:'center',
			formatter: function(value,row,index){
				return row.chargeItemInfo.chargeName;
			}
		},
		{field:'chargeClassify',title:'收费类型',width:100,align:'center',
			formatter: function(value,row,index){
				if (row.chargeItemInfo.chargeClassify==1){
					return '正常';
				} else if (row.chargeItemInfo.chargeBillingWay==2){
					return '押金';
				} 
			}
		},
		{field:'chargeBillingWay',title:'计费方式',width:200,align:'center',
			formatter: function(value,row,index){
				if (row.chargeItemInfo.chargeBillingWay==1){
					return '（建面：单价*建筑面积）';
				} else if (row.chargeItemInfo.chargeBillingWay==2){
					return '（抄表：单价*度数）';
				} else  if (row.chargeItemInfo.chargeBillingWay==3){
					return '（定额：金额=单价）';
				}
			}
		},
		{field:'chargeBillingCycleCount',title:'计费周期数',width:100,align:'center',
			formatter: function(value,row,index){
				if (row.chargeItemInfo.chargeBillingCycleCount==0){
					return '循环';
				} else{
					return '无';
				}
			}
		},
		{field:'chargeBillingCycleUnit',title:'计费周期单位',width:100,align:'center',
			formatter: function(value,row,index){
				if (row.chargeItemInfo.chargeBillingCycleUnit==1){
					return '月';
				} else if (row.chargeItemInfo.chargeBillingCycleUnit==2){
					return '日';
				}
			}
		},
		{field:'chargeBillingUnitPrice',title:'计费单价',width:100,align:'center',
			formatter: function(value,row,index){
				return row.chargeItemInfo.chargeBillingCycleUnit;
			}	
		},
		{field:'printOrder',title:'打印次序',width:100,align:'center',
			formatter: function(value,row,index){
				return row.chargeItemInfo.printOrder;
			}	
		}
    ]]
});


var selected;
function edit(){
	var row = $('#formTable').datagrid('getSelected');
	alert(JSON.stringify(row));
	if (row){
		$('#win').window('open');
		$('#formInfo').form('load',{
			pChargeItemId:row.pChargeItemId,
			chargeWay:row.chargeItemInfo.chargeWay,
			chargeClassify:row.chargeItemInfo.chargeClassify,
			chargeBillingWay:row.chargeItemInfo.chargeBillingWay,
			chargeBillingCycleUnit:row.chargeItemInfo.chargeBillingCycleUnit,
			chargeName:row.chargeName,
			chargeBillingCycleCount:row.chargeBillingCycleCount,
			chargeBillingUnitPrice:row.chargeBillingUnitPrice,
			chargeOverdueFine:row.chargeOverdueFine,
		    printOrder:row.printOrder,
		});
	}else{
		alert("请至少选中一条数据！");
	}
}

function add(){
	selected = $('#tree').tree('getSelected');
	if (selected!=null&&selected.id.substring(0,selected.id.indexOf("-"))==2){
		parent.$('#win').window({    
			title :'房间收费项目-新增',						
		    width:350,    
		    height:400,
		    modal:true,
		    maximizable:false,
		    minimizable:false,
		    resizable:true,
		    content:"<iframe src='/ezsh/base/goURL/charge/room_charge_add' height='100%' width='100%' frameborder='0px' ></iframe>"  
		}); 
	}else{
		alert("请选择要添加的房间！");
	}
}

function sub(){
	$.messager.progress();	
	$('#formInfo').form('submit', {
		url: "/ezsh/charge/update",
		onSubmit: function(param){

		},
		success: function(data){
			$.messager.progress('close');
			var data = eval('(' + data + ')'); 
			if(data.status==1){
				$.messager.alert('提示','修改成功!','info');
			}else{
				$.messager.alert('提示','修改失败!',function(){	
				});
			}
		}
	});
}

function del(){
	var row = $('#formTable').datagrid('getSelected');
	if(row!=null){
		$.ajax({
		    type: "GET",
		    url:"/ezsh/chargeRoom/delete",
		    data:{"chargeRoomId":row.chargeRoomId},
		    dataType:"json",
		    success: function(data){
			    if(data.status>0){
			    	$.messager.alert('提示','删除成功!','info');
			    }else{
			    	$.messager.alert('提示','删除失败!','info');
			    }
			    $("#formTable").datagrid("reload");
		   }
		})
	}else{
		alert("至少选择一条数据！");
	}
	
}

function reload(){
	location.reload();
}

function close(){
	$('#win').window('close');
}

$(function(){
	
})