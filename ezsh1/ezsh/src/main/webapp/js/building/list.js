$(function(){
	$('#dg').datagrid({    
	    //支持多条件查询
	    url:'/ezsh/build/selectPage.action', 
	    nowrapL:true,
	    fit:true,
	    idField:'buildId',
	    rownumbers:true,
	    pagination:true,
	    pageSize:5,
	    pageList:[3,5,10,20],
	    queryParams:{
	    	buildManagerId:  '',
	    	buildId :  '',
	    	buildName:'%%',
	    	buildAddr:'%%',
	        buildType:'%%',
	        toward:'%%',
	        remark:'%%'
		},	    
	    toolbar: [
	        {
				iconCls: 'icon-reload',
				text:'刷新',
				handler : function(){
					 $("#dg").datagrid("reload");
				}
	        },'-',{
			iconCls: 'icon-add',
			text:'新增',
			handler: function(){
				parent.$('#win').window({    
					title :'添加楼宇信息',						
				    width:350,    
				    height:350,    
				    modal:true,
				    maximizable:false,
				    minimizable:false,
				    resizable:false,
				    content:"<iframe src='/ezsh/base/goURL/building/insert.action' height='100%' width='100%' frameborder='0px' ></iframe>"  
				}); 
				}
	        },'-',{
			iconCls: 'icon-edit',
			text:'修改',
			handler: function(){
				//判断是否选中一行，并且只能选中一行进行修改
				var array = $('#dg').datagrid("getSelections");
				if(array.length!=1){
					alert("请选择需要修改的记录，并且只能选中一条！");
					return false;							
				}
				
				//打开修改窗口
				parent.$('#win').window({    
					title :'修改楼宇信息',						
				    width:350,    
				    height:350,    
				    modal:true,
				    maximizable:false,
				    minimizable:false,
				    resizable:false,
				    content:"<iframe src='/ezsh/base/goURL/building/update.action' height='100%' width='100%' frameborder='0px' ></iframe>"  
				}); 
				}
	        },'-',{
			iconCls: 'icon-remove',
			text:'删除',
			handler: function(){
				var array = $('#dg').datagrid("getSelections");
				if(array.length>0){
					//定义数组，通过下边的用来存储选中记录的Id
					var ids = new Array();
					for (i = 0; i < array.length; i++) {
						ids[i] = array[i].buildId;
					}
					//如果需要锁整个页面，前面加parent.
					parent.$.messager.confirm('删除对话框', '您确认要删除吗？', function(data) {
						if (data) {
							$.ajax({
							  url: "/ezsh/build/deleteList.action",
							  type:"POST",
							  //设置为传统方式传送参数
							  traditional:true,
							  data:{pks:ids},
							  success: function(html){
								  if(html>0){
								  	alert("恭喜您 ，删除成功，共删除了"+html+"条记录");
								  }else{
								  	alert("对不超 ，删除失败");
								  }
							    $("#dg").datagrid("reload");
							    //请除所有勾选的行
							    $("#dg").datagrid("clearSelections");
							  },
							  error: function (XMLHttpRequest, textStatus, errorThrown) {
								    $.messager.alert('删除错误','请联系管理员！','error');
								},
							  dataType:'json'
							});
						}
					});
				}else{
					alert("请选择需要删除的记录！");
				}
			}
		}],			       
	    

		columns : [ [{
			checkbox:true,
		}, {
			field : 'buildManagerId',
			title : '管理处',
			formatter: function(value,row,index){
					if (row.management){
						return row.management.managerName;
					} else {
						return value;
			}
			}
		}, {
			field : 'buildId',
			title : '楼宇编号',
		}, {
			field : 'buildName',
			title : '楼宇名称',
			width : 300
		}, {
			field : 'buildAddr',
			title : '楼宇地址',
		}, {
			field : 'buildType',
			title : '楼宇类型',
		}
		, {
			field : 'structure',
			title : '楼宇结构',
		}
		, {
			field : 'toward',
			title : '楼宇朝向',
		}
		, {
			field : 'remark',
			title : '备注',
			width : 300
		}] ]
	});
});