$(function(){
	$('#dg').datagrid({    
	    //支持多条件查询
	    url:'/ezsh/management/selectPage.action', 
	    fit:true,
	    nowrapL:true,
	    idField:'managerId',
	    rownumbers:true,
	    pagination:true,
	    pageSize:5,
	    pageList:[3,5,10,20],	    
	    toolbar: [ {
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
					title :'添加管理处',						
				    width:600,    
				    height:400,    
				    modal:true,
				    resizable:false,
				    content:"<iframe src='/ezsh/base/goURL/management/insert.action' height='100%' width='100%' frameborder='0px' ></iframe>"  
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
					title :'修改管理处',
				    width:650,    
				    height:500,    
				    modal:true,
				    content:"<iframe src='/ezsh/base/goURL/management/update.action' height='100%' width='100%' frameborder='0px' ></iframe>"  
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
					ids[i] = array[i].managerId;
					alert(ids[i]);
				}
				//如果需要锁整个页面，前面加parent.
				parent.$.messager.confirm('删除对话框', '您确认要删除吗？', function(data) {
					if (data) {
						alert(data);
						$.ajax({
							url: "/ezsh/management/deleteList.action",
							type:"POST",
							//设置为传统方式传送参数
							traditional:true,
							data:{pks:ids},
						    dataType:'json',
							success: function(html){
								if(html>0){
									alert("恭喜您 ，删除成功，共删除了"+html+"条记录");
								}else{
									alert("对不超 ，删除失败");
								}
								//重新刷新页面
								$("#dg").datagrid("reload");
								//请除所有勾选的行
								$("#dg").datagrid("clearSelections");
						  	},
						    error: function (XMLHttpRequest, textStatus, errorThrown) {
							    $.messager.alert('删除错误','请联系管理员！','error');
							},
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
			field : 'managerId',
			title : '管理处编号'
		}, {
			field : 'managerName',
			title : '管理处名称',
		},{
			field : 'pProvinceId',
			title : '省',
			hidden : true
		},{
			field : 'pCityId',
			title : '市',
			hidden : true
		},{
			field : 'pDistrictId',
			title : '区',
			hidden : true
		}, {
			field : 'managerAddr',
			title : '详细地址',
			width : 200
		}, {
			field : 'heightBuildNum',
			title : '高层楼宇数量',
		}, {
			field : 'manyBuildNum',
			title : '多层楼宇数量',
		}
		, {
			field : 'buildArea',
			title : '建筑面积',
		}
		, {
			field : 'floorArea',
			title : '占地面积',
		}
		, {
			field : 'greenArea',
			title : '绿化面积',
		}
		, {
			field : 'publicArea',
			title : '公共面积',
		}
		, {
			field : 'parkingArea',
			title : '停车面积',
		}
		, {
			field : 'parkingNum',
			title : '车位',
		}
		, {
			field : 'contact',
			title : '联系人',
		}
		, {
			field : 'head',
			title : '负责人',
		}, {
			field : 'contactNum',
			title : '联系电话',
		},
		{
			field : 'remark',
			title : '备注',
			align : 'right'
		} ] ]
	});
});