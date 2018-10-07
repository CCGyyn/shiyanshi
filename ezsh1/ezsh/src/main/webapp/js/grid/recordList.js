/**
 * 初始化列表
 */
/*$('#formTable').datagrid({
    url:'/ezsh/gridRecord/select',
    columns:[[
    	{field:'gridRecordId',hidden:'true',title:'抄表记录ID',width:100},
		{field:'pManagerId',hidden:'true',title:'管理处ID',width:100},
		{field:'pBuildId',hidden:'true',title:'楼宇ID',width:100},
		{field:'pRoomId',hidden:'true',title:'房间ID',width:100,align:'right'},
		{field:'roomNum',title:'房间编号',width:100,align:'right'},
		{field:'pGridId',hidden:'true',title:'表计类别ID',width:100,align:'right'},
		{field:'beginDosage',title:'起数',width:100,align:'right'},
		{field:'dosage',title:'用量',width:100,align:'right'},
		{field:'enteringTime',title:'录入时间',width:100,align:'right'},
		{field:'gridReadTime',title:'抄表时间',width:100,align:'right'},
		{field:'gridReadType',title:'抄表方式',width:100,align:'right'},
		{field:'gridReadPeople',title:'抄表人员',width:100,align:'right'}
    ]]
});*/
				
function edit(){
	var row = $('#formTable').datagrid('getSelected');
	if (row){
		$('#win').window('open');
		$('#formInfo').form('load',{
			gridRecordId:row.gridRecordId,
			managerName:$("#pManagerId").combobox('getText'),
			buildName:$("#pBuildId").combobox('getText'),
			roomNum:row.roomNum,
			gridName:$("#pGridId").combobox('getText'),
			dosage:row.dosage,
		});
	}else{
		alert("请至少选中一条数据！");
	}
}
	    
function sub(){
	$.messager.progress();	
	$('#formInfo').form('submit', {
		url: "/ezsh/gridRecord/update",
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
	    
function reload(){
	location.reload();
}
   
function close(){
	$('#win').window('close');
}
	    
function add(){
  	parent.$('#win').window({    
	title :'添加抄表记录',						
    width:450,    
    height:380,    
    modal:true,
    maximizable:false,
    minimizable:false,
    resizable:false,
    content:"<iframe src='/ezsh/base/goURL/grid/record_add' height='100%' width='100%' frameborder='0px' ></iframe>"  
	}); 
}
	    
function remove(){
	var row = $('#formTable').datagrid('getSelected');
	$.ajax({
	    type: "GET",
	    url:"/ezsh/gridRecord/delete",
	    data:{"gridRecordId":row.gridRecordId},
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
}
	    
function search(){
	if($("#pManagerId").combobox('isValid')&&$("#pGridId").combobox('isValid')){
		/*$('#formTable').datagrid({
			queryParams: {
				pManagerId:$("#pManagerId").combobox('getValue'),
				pGridId:$("#pGridId").combobox('getValue')
			}
		});	*/
		var pManagerId=$("#pManagerId").combobox('getValue');
		var pGridId=$("#pGridId").combobox('getValue');
        $('#formTable').datagrid({
            url:'/ezsh/gridRecord/select?pManagerId='+pManagerId+'&pGridId='+pGridId,
            columns:[[
                {field:'roomNum',title:'房间编号',width:100,align:'right'},
                {field:'pGridId',hidden:'true',title:'表计类别ID',width:100,align:'right'},
                {field:'beginDosage',title:'起数',width:100,align:'right'},
                {field:'dosage',title:'用量',width:100,align:'right'},
                {field:'enteringTime',title:'录入时间',width:100,align:'right'},
                {field:'gridReadTime',title:'抄表时间',width:100,align:'right'},
                {field:'gridReadType',title:'抄表方式',width:100,align:'right'},
                {field:'gridReadPeople',title:'抄表人员',width:100,align:'right'}
            ]]
        });

	}
}