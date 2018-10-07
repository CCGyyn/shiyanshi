<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/common/header.jsp" %>
<!DOCTYPE html>
<html>
	<head> 
	<title>商品列表</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">    
		<meta http-equiv="description" content="商品列表">
		<style type="text/css">
			.tableStyle{
				font-size:12px;
				padding:10% 0px 0px 10%;
			}
		</style>
	</head>
	
	<body class="easyui-layout">
		<div id="tb" style="padding:5px;height:auto">
			<div style="margin-bottom:5px">
				<a href="javascript:add()" class="easyui-linkbutton" iconCls="icon-add" plain="true"></a>
				<a href="javascript:edit()" class="easyui-linkbutton" iconCls="icon-edit" plain="true"></a>
				<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true"></a>
			</div>
		</div>
		
		<!-- query module start -->
		<div data-options="region:'north',split:false," title="查询条件" style="height:100px">
			<div style="padding:15px;height:auto">
				起始时间: <input id="startTime" class="easyui-datebox" data-options="formatter:myYMDformatter,parser:myYMDparser" style="width:100px">
				终止时间: <input id="endTime" class="easyui-datebox" data-options="formatter:myYMDformatter,parser:myYMDparser" style="width:100px">
				订单状态: 
				<select id="condition" class="easyui-combobox" name="condition" panelHeight="auto" style="width:100px">
					<option value=""></option>
					<option value="1">未支付</option>
					<option value="2">待收货</option>
					<option value="3">未评价</option>
					<option value="4">退货订单</option>
				</select>
				<a href="#" id="search" class="easyui-linkbutton" iconCls="icon-search">Search</a>
			</div>
		</div>
		<!-- query module end -->
		
		<!-- tree start -->
		<div data-options="region:'west',title:'楼宇',split:true" style="width:150px;">
			<ul class="easyui-tree" id="tree">
		        
		    </ul>
		</div>
		<!-- tree end -->
		
		<div data-options="region:'center',title:'商品库存列表',iconCls:'icon-ok'">
			<table  id="formTable"  class="easyui-datagrid"
					data-options="url:'',method:'get', 
					border:false,
					fit:true,
					fitColumns:false,
					striped:true,
					pagination:true,
					rownumbers:true,
					singleSelect:true,
					">
			</table>
		</div>
		<!-- <div id="win" class="easyui-window" title="资料-修改" style="width:600px;height:400px"
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
					<form id="customerInfo" method="post">
				    	<table class="tableStyle" cellpadding="5">
				    		<tr>				    		
				    			<td style="display:none;"><input id="customerId" class="easyui-textbox" type="text" name="customerId"></input></td>
				    			<td>管理处:</td>
				    			<td><input id="managerName" class="easyui-textbox" type="text" name="managerName" ></input></td>
				    			<td>房间编号:</td>
				    			<td><input id="roomSerialNumber" class="easyui-textbox" type="text" name="roomSerialNumber"></input></td>
				    		</tr>
				    		<tr>
				    			<td>客户名称:</td>
				    			<td><input id="customerName" class="easyui-textbox" type="text" name="customerName"></input></td>
				    			<td>收费对象:</td>
				    			<td><input id="chargeMan" class="easyui-textbox" type="text" name="chargeMan" ></input></td>
				    		</tr>
				    		<tr>
				    			<td>手机号码:</td>
				    			<td><input id="customerTelephone" class="easyui-textbox" type="text" name="customerTelephone"></input></td>
				    			<td>入住日期:</td>
				    			<td><input id="customerCheckInTime" class="easyui-textbox" type="text" name="customerCheckInTime"></input></td>
				    		</tr>
				    		<tr>
				    			<td>收费起始日:</td>
				    			<td><input id="chargeStartDate" class="easyui-textbox" type="text" name="chargeStartDate"></input></td>
				    			<td>收费结束日:</td>
				    			<td><input id="chargeEndDate" class="easyui-textbox" type="text" name="chargeEndDate"></input></td>
				    			
				    		</tr>
				    		<tr>
				    			<td>合同开始日:</td>
				    			<td><input id="contractStartDate" class="easyui-textbox" type="text" name="contractStartDate" ></input></td>
				    			<td>合同结束日:</td>
				    			<td><input id="contractEndDate" class="easyui-textbox" type="text" name="contractEndDate"></input></td>
				    		</tr>
				    		<tr>
				    			<td>身份证号:</td>
				    			<td><input id="customerIdCardNumber" class="easyui-textbox" type="text" name="customerIdCardNumber" ></input></td>
				    		</tr>
				    	</table>
			    	</form>
				</div>
				<div region="south" border="true" style="text-align:right;height:50px;line-height:30px;padding:10px 15px 0px 0px;">
					<a class="easyui-linkbutton" icon="icon-ok" href="javascript:sub()">确定</a>
					<a class="easyui-linkbutton" icon="icon-cancel" href="javascript:close()">取消</a>
				</div>
			</div>
		</div> -->
	</body>
	<script type="text/javascript" src="/ezsh/common/yy-mm-dd.js"></script>
	<script type="text/javascript">
	/* 构建管理处树 */
	$(function(){
		$('#tree').tree({
		    url:'/ezsh/tree/gtTreeUserId',
		    lines:true
		});
	})
	
	var managerId,managerName,roomId,roomNum,userId;
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
				/* swap=swap.substring((swap.indexOf("-")+1));
				var roomId=swap.substring((swap.indexOf("-")+1)); */
				userId = swap.substring(swap.lastIndexOf("-")+1);
				roomNum=node.text.substring(0,node.text.indexOf("|"));
				$('#formTable').datagrid({
					queryParams: {
						pUserId: userId,
						startTime: $('#startTime').datebox('getValue'),
						endTime: $('#endTime').datebox('getValue'),
						condition: $('#condition').combobox('getValue')
					}
				});	
			}
		}
	});
	</script>
	<script type="text/javascript">
	$('#formTable').datagrid({
	    url:'/ezsh/orderAd/gtGoodsOrderAd',
	    columns:[[
			{field:'pOrderId',title:'订单基本信息编码',width:100,align:'center'},
			{field:'addTime',title:'创建时间',width:100,align:'center',
				formatter: function(value,row,index){
					return row.goodsOrder.addTime;
				}
			},
			{field:'linkMan',title:'联系人',width:100,align:'center',
				formatter: function(value,row,index){
					return row.goodsOrder.linkMan;
				}	
			},
			{field:'linkPhone',title:'联系电话',width:100,align:'center',
				formatter: function(value,row,index){
					return row.goodsOrder.linkPhone;
				}	
			},
			{field:'buyAmount',title:'购买数量',width:100,align:'center'},
			{field:'orderSerialNum',title:'订单编号',width:100,align:'center',
				formatter: function(value,row,index){
					return row.goodsOrder.orderSerialNum;
				}		
			},
			{field:'orderStatus',title:'订单状态',width:100,align:'center',
				formatter: function(value,row,index){
					if(row.goodsOrder.orderStatus == -1){
						return "取消订单";
					} else if(row.goodsOrder.orderStatus == 1){
						return "等待发货";
					} else if(row.goodsOrder.orderStatus == 2){
						return "配送中";
					} else if(row.goodsOrder.orderStatus == 3){
						return "已签收";
					}
				}		
			},
			{field:'payStatus',title:'支付状态',width:100,align:'center',
				formatter: function(value,row,index){
					if(row.goodsOrder.payStatus==0){
						return "待支付";
					} else if(row.goodsOrder.payStatus==1){
						return "支付成功";
					}
				}
			},
			{field:'payTime',title:'支付时间',width:100,align:'center',
				formatter: function(value,row,index){
					return row.goodsOrder.payTime;
				}
			},
			{field:'price',title:'单价',width:100,align:'center'},
			{field:'transactionNum',title:'交易流水号',width:100,align:'center',
				formatter: function(value,row,index){
					return row.goodsOrder.transactionNum;
				}
			},
			{field:'afterSaleStatus',title:'售后状态',width:100,align:'center',
				formatter: function(value,row,index){
					if(value == 0){
						return "无";
					} else if(value == 1){
						return "已进入售后";
					}
				}
			},
			{field:'refundMoney',title:'退款金额',width:100,align:'center'},
	    ]]
	});
	
	$("#search").click(function(){
		var startTime = $('#startTime').datebox('getValue');
		var endTime = $('#endTime').datebox('getValue');
		var condition = $('#condition').combobox('getValue');
		var selected = $('#tree').tree('getSelected');
		if(selected != null) {
			$('#formTable').datagrid({
				queryParams: {
					pUserId:userId,
					startTime: startTime,
					endTime: endTime,
					condition: condition
				}
			});
		} else {
			$('#formTable').datagrid({
				queryParams: {
					startTime: startTime,
					endTime: endTime,
					condition: condition
				}
			});
		}
	})
	
	</script>
</html>
