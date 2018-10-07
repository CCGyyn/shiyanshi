$(function(){
	$('#dg').datagrid({    
	    //支持多条件查询
	    url:'/ezsh/room/selectPage.action', 
	    nowrapL:true,
	    idField:'roomId',
	    fitColumns:false,
	    rownumbers:true,
	    pagination:true,
	    pageSize:10,
	    fit:true,
	    pageList:[5,10,15,20],
	    pagePosition:'bottom',
	    
	    
	    queryParams:{
	    	roomId: '',                 
	    	managerId: '',           
	    	buildId: '',               
	    	roomStatus: '',         
	    	roomType: '',             
	    	buildArea: '',           
	    	roomFloor: '', 
	    	roomUse:'%%',
	    	chargeMan:'%%',   
	    	position:'%%',     
	    	toward:'%%',         
	    	rent:'',                     
	    	managementFee:'',   
	    	sumPrice:'',             
	    	singlePrice:'',       
	    	
	    },
	    
	    toolbar: [{
			iconCls: 'icon-reload',
			text:'刷新',
			handler : function(){
				 $("#dg").datagrid("load",{
					 	roomId: '',                 
				    	managerId: '',           
				    	buildId: '',               
				    	roomStatus: '',         
				    	roomType: '',             
				    	buildArea: '',           
				    	roomFloor: '', 
				    	roomUse:'%%',
				    	chargeMan:'%%',   
				    	position:'%%',     
				    	toward:'%%',         
				    	rent:'',                     
				    	managementFee:'',   
				    	sumPrice:'',             
				    	singlePrice:'',     
				});
				}
	        },'-',{
				iconCls: 'icon-add',
				text:'新增',
				handler: function(){
					parent.$('#win').window({    
						title :'添加房间档案',						
					    width:600,    
					    height:450,    
					    modal:true,
					    resizable:false,
					    content:"<iframe src='/ezsh/base/goURL/room/insert.action' height='100%' width='100%' frameborder='0px' ></iframe>"  
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
						title :'修改房间档案',
					    width:600,    
					    height:400,    
					    modal:true,
					    content:"<iframe src='/ezsh/base/goURL/room/update.action' height='100%' width='100%' frameborder='0px' ></iframe>"  
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
						ids[i] = array[i].roomId;
					}
					//如果需要锁整个页面，前面加parent.
					parent.$.messager.confirm('删除对话框', '您确认要删除吗？', function(r) {
						if (r) {
							$.ajax({
							  url: "/ezsh/room/deleteList.action",
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
							  //重新刷新页面
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
			} ,/*'-',{
				iconCls: 'icon-redo',
				text:'导入',
				handler: function(){
				parent.$('#win').window({    
					title :'导入房间档案',						
				    width:300,    
				    height:200,    
				    modal:true,
				    resizable:false,
				    content:"<iframe src='/ezsh/base/goURL/room/importExcel.action' height='100%' width='100%' frameborder='0px' ></iframe>"  
				}); 
				}
			}  ,*//*'-',{
			iconCls: 'icon-undo',
			text:'导出',
			handler: function(){
				window.open("/ezsh/room/export.action");
			}
		}*/],			       
	    

		columns : [ [{
			checkbox:true,
		}, {
			field : 'managerId',
			title : '管理处名称',
			formatter: function(value,row,index){
				if (row.management){
					return row.management.managerName;
				} else {
					return value;
		}
		}
		},{
			field : 'buildId',
			title : '楼宇',
			formatter: function(value,row,index){
				if (row.building){
					return row.building.buildName;
				} else {
					return value;
		}
		}
		},/* {
			field : 'roomId',
			title : '房间编号'
		},*/ {
			field : 'buildArea',
			title : '建筑面积',
		}, {
			field : 'roomStatus',
			title : '房间状态',
			formatter: function(value,row,index){
				if (value==0){	return "未售";} 
				else if(value==1) {return "已售";}
				else if(value==2) {return "未租";}
				else if(value==3) {return "已租";}
				else if(value==4) {return "自用";}
				else if(value==5) {return "入住";}
				else if(value==6) {return "空置";}
				else if(value==7) {return "未交付";}
			}
		}, {
			field : 'roomType',
			title : '房间类型',
			formatter: function(value,row,index){
				if (value==0){	return "住宅";}  
				else if(value==1) {return "公寓";}
				else if(value==2) {return "办公";}
				else if(value==3) {return "厂房";}
				else if(value==4) {return "仓库";}
				else if(value==5) {return "宿舍";}
				else if(value==6) {return "其他";}
			}
		}, {
			field : 'roomFloor',
			title : '楼层',
		}
		, {
			field : 'roomNum',
			title : '房间号',
		}
		, {
			field : 'chargeMan',
			title : '收费服务对象',
		}
		, {
			field : 'greenArea',
			title : '入住日期',
		}, {
			field : 'roomUse',
			title : '房间用途',
		}
		, {
			field : 'publicArea',
			title : '房间顺序号',
		}
		, {
			field : 'decorate',
			title : '装修情况',
		}
		, {
			field : 'position',
			title : '位置',
		}
		, {
			field : 'toward',
			title : '朝向',
		}
		, {
			field : 'isAgreement',
			title : '是否签署协议',
		}, {
			field : 'rent',
			title : '租金',
		},
		{
			field : 'managementFee',
			title : '管理费',
		},
		{
			field : 'singlePrice',
			title : '单价',
		},
		{
			field : 'sumPrice',
			title : '总价',
		},
		{
			field : 'contract',
			title : '合同签署流程',
		} ,
		{
			field : 'effectiveStatus',
			title : '房间是否有效',
		}
		,
		{
			field : 'remark',
			title : '备注',
		}] ]
	});
	
	$('#tt').tree({
		onSelect: function(node){
			if(node.id!=0){
			$('#dg').datagrid('load',{
				buildId: node.id
			});
			}
		}
	});
});