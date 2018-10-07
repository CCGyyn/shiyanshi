/**
 * 初始化列表
 */
$('#formTable').datagrid({
    url:'/ezsh/charge/select',
    columns:[[
    	{field:'chargeId',title:'收费项目ID',width:100,align:'center'},
    	{field:'pManagerId',hidden:'true',title:'管理处ID',width:100,align:'center'},
		{field:'chargeName',title:'收费项目',width:100,align:'center'},
		{field:'chargeWay',title:'收费方式',width:100,align:'center',
			formatter: function(value,row,index){
				if (value==1){
					return '周期性';
				} else  if (value==2){
					return '临时性';
				} else  if (value==3){
					return '一次性';
				}
			}
		},
		{field:'chargeClassify',hidden:'true',title:'收费类型',width:100,align:'center'},
		{field:'chargeBillingWay',title:'计费方式',width:200,align:'center',
			formatter: function(value,row,index){
				if (value==1){
					return '（建面：单价*建筑面积）';
				} else if (value==2){
					return '（抄表：单价*度数）';
				} else  if (value==3){
					return '（定额：金额=单价）';
				}
			}
		},
		{field:'chargeBillingCycleCount',hidden:'true',title:'计费周期数',width:100,align:'center'},
		{field:'chargeBillingCycleUnit',title:'计费周期单位',width:100,align:'center',
			formatter: function(value,row,index){
				if (value==1){
					return '月';
				} else if (value==2){
					return '日';
				}
			}
		},
		{field:'chargeBillingUnitPrice',title:'计费单价',width:100,align:'center'},
		{field:'chargeOverdueFine',title:'滞纳金',width:100,align:'center'},
		{field:'printOrder',title:'打印次序',width:100,align:'center'},
    ]]
});

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
function add(){
  	parent.$('#win').window({    
	title :'添加收费项目',						
    width:600,    
    height:380,    
    modal:true,
    maximizable:false,
    minimizable:false,
    resizable:false,
    content:"<iframe src='/ezsh/base/goURL/charge/itemAdd' height='100%' width='100%' frameborder='0px' ></iframe>"  
	}); 
}
function edit(){
	var row = $('#formTable').datagrid('getSelected');
	if (row){
		$('#win').window('open');
		$('#formInfo').form('load',{
			pManagerId:$("#pManagerId").combobox('getValue'),
			chargeId:row.chargeId,	
			chargeName:row.chargeName,
			chargeWay:row.chargeWay,
			chargeClassify:row.chargeClassify,
			chargeBillingWay:row.chargeBillingWay,
			chargeBillingCycleCount:row.chargeBillingCycleCount,
			chargeBillingCycleUnit:row.chargeBillingCycleUnit,
			chargeBillingUnitPrice:row.chargeBillingUnitPrice,
			chargeOverdueFine:row.chargeOverdueFine,
		    printOrder:row.printOrder,
		});
	}else{
		alert("请至少选中一条数据！");
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
			} else if(data.status==0){
				$.messager.alert('提示','修改失败!','info');
			} else if(data.status==-1){
		    	$.messager.alert('提示','收费项目名已存在!','info');
		    } else if(data.status==-2){
		    	$.messager.alert('提示','打印次序已存在!','info');
		    }
			$('#formTable').datagrid('reload'); 
		}
	});
}
    
function remove(){
	var row = $('#formTable').datagrid('getSelected');
	$.ajax({
	    type: "GET",
	    url:"/ezsh/charge/delete",
	    data:{"chargeId":row.chargeId,pManagerId:row.pManagerId},
	    dataType:"json",
	    success: function(data){
		    if(data.status>0){
		    	$.messager.alert('提示','删除成功!','info');
		    }else{
		    	$.messager.alert('提示','删除失败!<br>'+data.message,'info');
		    }
		    $('#formTable').datagrid('reload');  
	    }
	})
}
    
function search(){
	$('#formTable').datagrid({
		queryParams: {
			pManagerId:$("#pManagerId").combobox('getValue'),
			chargeName:$("#chargeName").textbox('getValue')
		}
	});	
}
 
function close(){
	$('#win').window('close');
}
