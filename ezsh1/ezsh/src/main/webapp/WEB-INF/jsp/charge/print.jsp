<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/common/header.jsp" %>
<!DOCTYPE html>
<html>
	<head> 
	<title>收费单打印</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">    
		<meta http-equiv="description" content="表计类别列表">
		<style type="text/css">
			.tableStyle{
				font-size:12px;
				padding:2% 2% 0px 5%;
			}
			
			.table-b td,th{
				border-left:0.1px solid #000;
				border-top:0.1px solid #000;
				border-right:0.1px solid #000;
			}
			tfoot td{
				border:0.1px solid #000;
				/* border-bottom:0.1px solid #000; */
			}
		</style>
	</head>
	
	<body>
		<div class="easyui-layout" fit="true">
			<!-- <div data-options="region:'west',split:false," title="选择房间" style="width:180px">
				<ul id="tree"></ul>
			</div> -->
			<div id = "myElementId" region="center" border="false" >
				<form id="formInfo" class="tableStyle" method="post">
					<div style="margin:0px auto;width:43%;text-decoration: underline;">
						<h2>惠州市金山湖物业管理处有限公司</h2>
					</div>
					<div style="margin-left:40%;"><h2>收款收据</h2></div>
					<div>
						<div style="display:inline-block;width:25%;margin:0px 10% 2% 1%;">单元：<span id="buildName"></span></div>
						<div style="display:inline-block;width:25%;margin:0px 10% 2% 1%;">收款方式：<span>人工收费</span></div>
						<div style="display:inline-block;width:25%;margin:0px 0px 2% 1%;">No.：<span></span></div>
					</div>
					<div>
						<div style="display:inline-block;width:25%;margin:0px 10% 2% 1%;">客户：<span id="userName"></span></div>
						<div style="display:inline-block;width:25%;margin:0px 10% 2% 1%;">建筑面积：<span></span></div>
						<div style="display:inline-block;width:25%;margin:0px 0px 2% 1%;">收入日期：<span id="time"></span></div>
					</div>
					<!-- <div>
						客户：<span style="margin-right:0 30% 2% 1%;"></span>
						建筑面积：<span style="margin-right:0 30% 2% 1%;"></span>
						收入日期：<span></span>
					</div> -->
			    	<table class="table-b" cellpadding="5" style="border:solid #ccc 1px;border-collapse:collapse;">
			    		<thead>
							<tr>
							  <th style="width:280px;">收费项目</th>
							  <th style="width:100px;">单价</th>
							  <th style="width:100px;">用量</th>
							  <th style="width:100px;">费用期间</th>
							  <th style="width:140px;">金额</th>
							</tr>
						</thead>
			    		
						<tbody id="print-centent" style="font-size:12px;">
							<tr>
								<td>January</td>
								<td>$100</td>
								<td>January</td>
								<td>$100</td>
								<td>$100</td>
							</tr>
							<tr>
								<td>February</td>
								<td>$80</td>
								<td>January</td>
								<td>$100</td>
								<td>$100</td>
							</tr>
							<tr>
								<td>February</td>
								<td>$80</td>
								<td>January</td>
								<td>$100</td>
								<td>$100</td>
							</tr>
							<tr>
								<td>February</td>
								<td>$80</td>
								<td>January</td>
								<td>$100</td>
								<td>$100</td>
							</tr>
							<tr>
								<td style="height:25px;"></td>
								<td style="height:25px;"></td>
								<td style="height:25px;"></td>
								<td style="height:25px;"></td>
								<td style="height:25px;"></td>
							</tr>
							<tr>
								<td style="height:25px;"></td>
								<td style="height:25px;"></td>
								<td style="height:25px;"></td>
								<td style="height:25px;"></td>
								<td style="height:25px;"></td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td style="width:40%;">合计：<span id="total-price"></span>元</td>
								<td colspan="4"></td>
							</tr>
						</tfoot>
			    	</table>
			    	<div style="margin:1% 0 0 0;">
				    	<div style="display:inline-block;margin:1% 50% 0 1%;">收款人：<span></span></div>
				    	<div style="display:inline-block;"><h3>单位盖章：</h3><span></span></div>
			    	</div>
			    	<div>
				    	<div style="display:inline-block;margin:0 52% 0 1%;">备注：<span></span></div>
				    	<div style="display:inline-block;">联系电话：<span></span></div>
			    	</div>
		    	</form>
			</div>
			
			<div region="south" border="true" style="text-align:right;height:50px;line-height:30px;padding:10px 15px 0px 0px;">
				<button class="print-link no-print" onclick="print()">打印</button>
			</div>
		</div>	
	</body>
	
	<script type="text/javascript" src="/ezsh/js/jQuery.print.js"></script>
	<script type="text/javascript">
	function print(){
		$("#formInfo").print({
		    globalStyles: true,
		    mediaPrint: false,
		    stylesheet: null,
		    noPrintSelector: ".no-print",
		    iframe: true,
		    append: null,
		    prepend: null,
		    manuallyCopyFormValues: true,
		    deferred: $.Deferred()
		});
	}
	
	var win = parent.$("iframe[title='应收费记录']").get(0).contentWindow;
	
	var data = win.$('#formTable').datagrid('getSelections');
	// 冒泡排序
	var temp,totalPrice = 0;
	for(var i = 0;i < data.length;i++){
		totalPrice += data[i].totalPrice;
		for(var j = i; j < data.length-1; j++){
			 if (data[j + 1].chargeItem.printOrder < data[i].chargeItem.printOrder) {
		          //4 交换
		          temp = data[i];
		          data[i] = data[j + 1];
		          data[j + 1] = temp;
		        }
		}
	}
	
	var htmlText='';
	for(var i=0;i<data.length;i++){
		htmlText+="<tr>"+
			"<td style='width:80px;'>"+data[i].chargeItemName+"</td>"+
			"<td style='width:50px;'>"+data[i].unitPrice+"</td>"+
			"<td style='width:50px;'>"+data[i].chargeAmount+"</td>"+
			"<td style='width:60px;'>"+data[i].chargeOfDate+"</td>"+
			"<td style='width:30px;'>"+data[i].totalPrice+"</td>"+
		"</tr>";
	}
	
	var html_list = document.getElementById("print-centent");
	html_list.innerHTML = htmlText;
	document.getElementById("total-price").innerHTML = totalPrice;
	
	var myDate = new Date();
	var date = myDate.toLocaleDateString();
	document.getElementById("time").innerHTML = date;
	
	var node = win.$('#tree').tree('getSelected');
	if(node.id.substring(0,node.id.indexOf("-"))==2){ //房间ID
		var swap=node.id.substring((node.id.indexOf("-")+1));//管理处ID-楼宇ID-房间ID-userId
		var selected = win.$('#tree').tree('getSelected');
		var nodeParent = win.$("#tree").tree("getParent",selected.target);//获取父节点
		var nodeParent1=win.$("#tree").tree("getParent",nodeParent.target);//获取父父节点
		managerId=swap.substring(0,swap.indexOf("-"));
		managerName=nodeParent1.text;
		swap=swap.substring((swap.indexOf("-")+1));//楼宇ID-房间ID-userId
		swap=swap.substring((swap.indexOf("-")+1));//房间ID-userId
		var roomId=swap.substring(0,swap.indexOf("-"));
		userId=swap.substring((swap.indexOf("-")+1));
		
		//2-管理处ID-楼宇ID-房间ID-userId
		roomNum=node.text.substring(0,node.text.indexOf("|"));
		userName=node.text.substring(node.text.indexOf("|")+1);
		document.getElementById("userName").innerHTML = userName;
		document.getElementById("buildName").innerHTML = nodeParent.text;
	}	
	</script>
</html>
