<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/common/header.jsp" %>
<!DOCTYPE html>
<html>
	<head> 
	<title>应收费记录列表</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">    
		<meta http-equiv="description" content="房间收费设置项目列表">
		<style type="text/css">
			.tableStyle{
				font-size:12px;
				padding:10% 0px 0px 10%;
			}
		</style>
	</head>
	
	<body class="easyui-layout">
		<!-- toolbar start -->
		<div id="tb" style="padding:5px;height:auto">
			<div style="margin-bottom:5px">
				<a href="javascript:add()" class="easyui-linkbutton" iconCls="icon-add" plain="true"></a>
				<a href="javascript:edit()" class="easyui-linkbutton" iconCls="icon-edit" plain="true"></a>
				<a href="javascript:del()" class="easyui-linkbutton" iconCls="icon-remove" plain="true"></a>
				<a href="javascript:Ok()" class="easyui-linkbutton" iconCls="icon-ok" plain="true"></a>
				<a href="javascript:printPaper()" class="easyui-linkbutton" iconCls="fa fa-print fa-fw fa-lg" plain="true"></a>
			</div>
		</div>
		<!-- toolbar end -->
		
		<!-- query module start -->
		<div data-options="region:'north',split:false," title="查询条件" style="height:100px">
			<div style="padding:15px;height:auto">
				<!-- 起始时间: <input class="easyui-datebox" style="width:100px">
				终止时间: <input class="easyui-datebox" style="width:100px"> -->
				项目名查询: 
				<input class="easyui-textbox" style="width:100px;height:25px">
				收费项目：
				<input  id="chargeItem" class="easyui-combobox" name="pChargeItemId" style="width:100px"
				data-options="valueField:'pChargeItemId',
				textField:'chargeName',
				 panelHeight: 80
				">
				<a href="javascript:search()" class="easyui-linkbutton" iconCls="icon-search">Search</a>	
			</div>
		</div>
		<!-- query module end -->
		
		<!-- tree start -->
		<div data-options="region:'west',title:'楼宇',split:true" style="width:120px;">
			<ul class="easyui-tree" id="tree">
		        
		    </ul>
		</div>
		<!-- tree end -->
		
		<!-- datagrid start -->
		<div data-options="region:'center',title:'应收费记录列表',iconCls:'icon-ok'">
			<table id="formTable" class="easyui-datagrid"
					data-options="url:'',method:'get', 
					border:false,
					fit:true,
					fitColumns:false,
					striped:true,
					pagination:true,
					toolbar:'#tb',
					rownumbers:true,
					singleSelect:false,
					">
			</table>
		</div>
		<!-- datagrid end -->
		
		<!-- edit window start -->
		<div id="win" class="easyui-window" title="应收费记录-修改" style="width:300px;height:250px"
		    data-options="iconCls:'icon-edit',
		    modal:true,
		    closed:true,
		    collapsible:false,
		    minimizable:false,
		    maximizable:false,
		    resizable:false
		    ">
			<div class="easyui-layout" fit="true">
				<div region="center" border="false" >
					<form id="formInfo" method="post">
				    	<table class="tableStyle" cellpadding="5">
				    		<tr style="display:none">
				    			<td>应收费记录ID:</td>
				    			<td>
				    				<input id="chargeRecordId" class="easyui-textbox" type="text" name="chargeRecordId"></input>
				    			</td>
				    		</tr>	
				    		    		
				    		<tr>
				    			<td>计费单价:</td>
				    			<td>
				    				<input id="unitPrice" class="easyui-textbox" required="required" type="text" name="unitPrice"></input>
				    			</td>
				    		</tr>
				    		
				    		<tr>
				    			<td>数量:</td>
				    			<td>
				    				<input id="chargeAmount" class="easyui-textbox" required="required" type="text" name="chargeAmount" ></input>
				    			</td>	
				    		</tr>

				    	</table>
			    	</form>
				</div>
				<div region="south" border="true" style="text-align:right;height:50px;line-height:30px;padding:10px 15px 0px 0px;">
					<a class="easyui-linkbutton" icon="icon-ok" href="javascript:sub()">确定</a>
					<a class="easyui-linkbutton" icon="icon-cancel" href="javascript:close()">取消</a>
				</div>
			</div>
		</div>
		<!-- edit window end -->
		
		<!-- import window start -->
		<%--<div id="win1" class="easyui-window" title="应收费记录-修改" style="width:100px;height:150px"
		    data-options="iconCls:'icon-edit',
		    modal:true,
		    closed:true,
		    collapsible:false,
		    minimizable:false,
		    maximizable:false,
		    resizable:false
		    ">
			<div class="easyui-layout" fit="true">
				<div region="center" border="false" >
					<form id="formInfo" method="post">
				    	<table class="tableStyle" cellpadding="5">
				    		<tr style="display:none">
				    			<td>应收费记录ID:</td>
				    			<td>
				    				<input id="chargeRecordId" class="easyui-textbox" type="text" name="chargeRecordId"></input>
				    			</td>
				    		</tr>	
				    		    		
				    		<tr>
				    			<td>计费单价:</td>
				    			<td>
				    				<input id="unitPrice" class="easyui-textbox" required="required" type="text" name="unitPrice"></input>
				    			</td>
				    		</tr>
				    		
				    		<tr>
				    			<td>数量:</td>
				    			<td>
				    				<input id="chargeAmount" class="easyui-textbox" required="required" type="text" name="chargeAmount" ></input>
				    			</td>	
				    		</tr>
				    		
				    		<tr>
				    			<td>总价格:</td>
				    			<td>
				    				<input id="totalPrice" class="easyui-textbox" type="text" name="totalPrice"></input>
				    			</td>
				    		</tr>
				    	</table>
			    	</form>
				</div>
				<div region="south" border="true" style="text-align:right;height:50px;line-height:30px;padding:10px 15px 0px 0px;">
					<a class="easyui-linkbutton" icon="icon-ok" href="javascript:sub()">确定</a>
					<a class="easyui-linkbutton" icon="icon-cancel" href="javascript:close()">取消</a>
				</div>
			</div>
		</div>--%>
		<!-- import window end -->
	</body>
	
	<script type="text/javascript">
		/* 构建管理处树 */
		$(function(){
			$('#tree').tree({
			    url:'/ezsh/tree/gtTree',
			    lines:true
			});

		})
		
		var managerId,managerName,roomId=0,roomNum;
		$('#tree').tree({
			onClick: function(node){
				 // alert node text property when clicked
				if(node.id.substring(0,node.id.indexOf("-"))==2){//房间ID
					var swap=node.id.substring((node.id.indexOf("-")+1));//继承解析ID
					var selected = $('#tree').tree('getSelected');//获取选中对象
					var nodeParent = $("#tree").tree("getParent",selected.target);//父节点
					var nodeParent1=$("#tree").tree("getParent",nodeParent.target);//父父节点
					managerId=swap.substring(0,swap.indexOf("-"));//从ID中获取管理处ID
					managerName=nodeParent1.text;//从ID中获取管理处名称
					swap=swap.substring((swap.indexOf("-")+1));
					roomId=swap.substring((swap.indexOf("-")+1));//从ID中获取房间ID
					roomNum=node.text.substring(0,node.text.indexOf("|"));/*
					$('#formTable').datagrid({
						queryParams: {
							pRoomId:roomId
						}
					});*/

                    /**
                     * 初始化列表
                     */
                    console.log(roomId);
                    $('#formTable').datagrid({
                        url:'/ezsh/chargeRecord/select?pRoomId='+roomId+'&checkStatus=0',
                        columns:[[
                            {field:'checkbox',checkbox:'true',align:'center'},
							{field:'chargeRecordId',hidden:'true',title:'应收费记录ID',width:100,align:'center'},
							{field:'pUserId',hidden:'true',title:'用户ID',width:100,align:'center'},
							{field:'pManagerId',hidden:'true',title:'管理处ID',width:100,align:'center'},
							{field:'pBuildingId',hidden:'true',title:'楼宇ID',width:100,align:'center'},
							{field:'pRoomId',hidden:'true',title:'房间ID',width:100,align:'center'},
							{field:'pChargeItemId',hidden:'true',title:'收费项目ID',width:100,align:'center'},
                            {field:'chargeItemName',title:'收费项目名称',width:100,align:'center'},
                            {field:'unitPrice',title:'收费项目单价',width:100,align:'center'},
                            {field:'chargeAmount',title:'数量',width:100,align:'center'},
                            {field:'totalPrice',title:'总价',width:100,align:'center'},
                            {field:'chargeOfDate',title:'费用所属月份',width:100,align:'center'},
                            {field:'chargeEndDate',title:'收费结束时间',width:100,align:'center'},
                            {field:'overdueTime',title:'超期开始时间',width:100,align:'center'},
                            {field:'status',title:'缴费状态',width:100,align:'center',
                                formatter: function(value,row,index){
                                    if(row.status==-1){
                                        return "欠费";
                                    } else if (row.status==0){
                                        return "待缴费";
                                    } else if (row.status==1){
                                        return "已缴费";
                                    }

                                }
                            },
                            /*		{field:'chargeForPeople',title:'收费人',width:100,align:'center'},
                                    {field:'chargeTime',title:'收费时间',width:100,align:'center'},*/
                            {field:'checkStatus',title:'审核状态',width:100,align:'center',
                                formatter: function(value,row,index){
                                    if (row.checkStatus==0){
                                        return "待审核";
                                    } else if (row.checkStatus==1){
                                        return "已审核";

                                    }

                                }
                            },
                            /*	{field:'checkPeople',title:'审核人',width:100,align:'center'},
                                {field:'checkTime',title:'审核时间',width:100,align:'center'},	*/
                        ]]
                    });
					$('#chargeItem').combobox({
						formatter: function(row){
							var opts = $(this).combobox('options');
							return row[opts.textField]=row.chargeItemInfo.chargeName;
						}
					});
					var url='/ezsh/chargeRoom/getRoomItemsList?roomId='+roomId;
					$('#chargeItem').combobox('reload', url);
				}
			}
		});
	</script>
	<%--<script type="text/javascript">
	/**
	 * 初始化列表
	 */
	$('#formTable').datagrid({
	    url:'/ezsh/chargeRecord/select',
	    columns:[[
			{field:'checkbox',checkbox:'true',align:'center'},
	 /*   	{field:'chargeRecordId',title:'应收费记录ID',width:100,align:'center'},*/
/*			{field:'pUserId',hidden:'true',title:'用户ID',width:100,align:'center'},
			{field:'pManagerId',hidden:'true',title:'管理处ID',width:100,align:'center'},
			{field:'pBuildingId',hidden:'true',title:'楼宇ID',width:100,align:'center'},
			{field:'pRoomId',hidden:'true',title:'房间ID',width:100,align:'center'},
			{field:'pChargeItemId',hidden:'true',title:'收费项目ID',width:100,align:'center'},*/
			{field:'chargeItemName',title:'收费项目名称',width:100,align:'center'},
			{field:'unitPrice',title:'收费项目单价',width:100,align:'center'},
			{field:'chargeAmount',title:'数量',width:100,align:'center'},
			{field:'totalPrice',title:'总价',width:100,align:'center'},
			{field:'chargeOfDate',title:'费用所属月份',width:100,align:'center'},
			{field:'chargeEndDate',title:'收费结束时间',width:100,align:'center'},
			{field:'overdueTime',title:'超期开始时间',width:100,align:'center'},
			{field:'status',title:'缴费状态',width:100,align:'center',
				formatter: function(value,row,index){
					if(row.status==-1){
						return "欠费";
					} else if (row.status==0){
						return "代缴费";
					} else if (row.status==1){
						return "已缴费";
					}
					
				}
			},
	/*		{field:'chargeForPeople',title:'收费人',width:100,align:'center'},
			{field:'chargeTime',title:'收费时间',width:100,align:'center'},*/
			{field:'checkStatus',title:'审核状态',width:100,align:'center',
				formatter: function(value,row,index){
					if (row.checkStatus==0){
						return "代审核";
					} else if (row.checkStatus==1){
						return "已审核";
					}
					
				}
			},
		/*	{field:'checkPeople',title:'审核人',width:100,align:'center'},
			{field:'checkTime',title:'审核时间',width:100,align:'center'},	*/
	    ]]
	});
	</script>--%>

	<script type="text/javascript">
		var selected;
		function edit(){
			var row = $('#formTable').datagrid('getSelected');
			if (row){
			/*	alert(row.pChargeItemId);*/
				$('#win').window('open');
				$('#formInfo').form('load',{
					chargeRecordId:row.chargeRecordId,
					unitPrice:row.unitPrice,	
					chargeAmount:row.chargeAmount,
				});
			}else{
				alert("请至少选中一条数据！");
			}
	    }
		
		function add(){
			selected = $('#tree').tree('getSelected');
			if (selected!=null&&selected.id.substring(0,selected.id.indexOf("-"))==2){
				parent.$('#win').window({    
					title :'应收费记录-新增',						
				    width:400,    
				    height:380,    
				    modal:true,
				    maximizable:false,
				    minimizable:false,
				    resizable:false,
				    content:"<iframe src='/ezsh/base/goURL/charge/recordAdd' height='100%' width='100%' frameborder='0px' ></iframe>"  
				});
			}
			else{
				alert("请选择要添加的房间！");
			}
	    }
		
		
		function printPaper(){
			parent.$('#win').window({    
				title :'收费单-打印',						
			    width:800,    
			    height:550,    
			    modal:true,
			    maximizable:false,
			    minimizable:false,
			    resizable:false,
			    content:"<iframe src='/ezsh/base/goURL/charge/print' height='100%' width='100%' frameborder='0px' ></iframe>"  
			});
	    }
		
		
	    function sub(){
	    	$.messager.progress();	
	    	$('#formInfo').form('submit', {
	    		url: "/ezsh/chargeRecord/update",
	    		onSubmit: function(param){

	    		},
	    		success: function(data){
	    			$.messager.progress('close');
	    			var data = eval('(' + data + ')'); 
	    			if(data.status==1){
	    				$.messager.alert('提示','修改成功!','info');
                        $('#win').window('close');
                        $('#formTable').datagrid('reload');
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
				    url:"/ezsh/chargeRecord/delete",
				    data:{"chargeRecordId":row.chargeRecordId},
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
	    
	    function search(){
	    	$('#formTable').datagrid({
    			queryParams: {
    				pChargeItemId:$("#chargeItem").combobox('getValue'),
    				pRoomId:roomId,
    				/* pGridId:$("#pGridId").combobox('getValue') */
    			}
    		});	
	    }
	    function close(){
	    	$('#win').window('close');
	    }

	    function Ok() {
		    var row=$('#formTable').datagrid('getSelected');
		    if(row){
		        var chargeRecordId=row.chargeRecordId;
                alert(chargeRecordId);
		        $.ajax({
					type:'POST',
					url:"/ezsh/chargeRecord/checkRecord",
					data:{
                        chargeRecordId:chargeRecordId
					},
					success:function (json) {
					    console.log(json);
						if(1){
						    alert("审核成功");
                            $('#formTable').datagrid('reload');
						}
						else {
                            alert("审核失败");
                        }
                    }

				});
			}
			else {
		        alert("请至少选择一项!");
			}

        }
	</script>
</html>
