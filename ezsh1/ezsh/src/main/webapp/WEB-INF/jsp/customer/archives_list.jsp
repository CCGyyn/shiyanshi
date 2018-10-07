<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/common/header.jsp" %>
<!DOCTYPE html>
<html>
	<head> 
	<title>房间客户档案</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">    
		<meta http-equiv="description" content="房间客户档案">
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
				<!-- <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true"></a> -->
				<a href="javascript:edit()" class="easyui-linkbutton" iconCls="icon-edit" plain="true"></a>
				<!-- <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true"></a> -->
			</div>
		</div>
		
		<!-- tree start -->
		<div data-options="region:'west',title:'楼宇',split:true" style="width:150px;">
			<ul class="easyui-tree" id="tree">
		        
		    </ul>
		</div>
		<!-- tree end -->
		
		<div data-options="region:'north',split:false," title="查询条件" style="height:100px">
			<div style="padding:15px;height:auto">
				管理处：<input  id="pManagerId" class="easyui-combobox" name="pManagerId" style="width:100px"  
                     		  data-options="valueField:'managerId',
	          				  required:true,textField:'managerName',
	          				  editable:false,
	          				   panelHeight: 80,
	          				  url:'/ezsh/build/findManager',
	          				  onSelect: function(rec){	
								var url = '/ezsh/build/gtBuildListById?buildManagerId='+rec.managerId;
								$('#buildId').combobox('reload', url);
							  }
	          				  "/>		
                <!-- 楼宇：<input  id="buildId" class="easyui-combobox" name="buildId" style="width:100px"  
                      				data-options="valueField:'buildId',
                      				required:true,textField:'buildName',				
                      				"/> -->                        
				客户名称: 
				<input id="customerName" class="easyui-textbox" style="width:100px;height:25px">
				<!-- <select class="easyui-combobox" panelHeight="auto" style="width:100px">
					<option value="1">业主</option>
					<option value="2">租客</option>
				</select> -->
				<a href="javascript:search()" class="easyui-linkbutton" iconCls="icon-search">Search</a>
			</div>
		</div>
		
		<div data-options="region:'center',title:'客户档案列表',iconCls:'icon-ok'">
			<table id="formTable" class="easyui-datagrid"
					data-options="url:'',method:'get', 
					border:false,
					fit:true,
					fitColumns:false,
					striped:true,
					pagination:true,
					toolbar:'#tb',
					rownumbers:true,
					singleSelect:true,
					">
			</table>
		</div>
		<div id="win" class="easyui-window" title="资料-修改" style="width:580px;height:350px"
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
				    		<tr>				    		
				    			<td style="display:none;">
				    				<input id="customerId" class="easyui-textbox" type="text" name="customerId"/>
				    			</td>
				    			<!-- <td>房间编号:</td>
				    			<td>
				    				<input id="roomNum" class="easyui-textbox" type="text" name="roomNum"/>
				    			</td> -->
				    		</tr>
				    		<tr>
				    			<td>客户名称:</td>
				    			<td>
				    				<input id="customerName" class="easyui-textbox" type="text" required="required" name="customerName"/>
				    			</td>
				    			<td>身份证号:</td>
				    			<td>
				    				<input id="customerIdCardNumber" class="easyui-textbox" type="text" required="required" name="customerIdCardNumber" />
				    			</td>
				    			
				    		</tr>
				    		<tr>
				    			<td>手机号码:</td>
				    			<td>
				    				<input id="customerTelephone" class="easyui-textbox" type="text" required="required" name="customerTelephone"/>
				    			</td>
				    			<td>入住日期:</td>
				    			<td>
				    				<input id="customerCheckInTime" class="easyui-datebox" data-options="formatter:myYMDformatter,parser:myYMDparser" type="text" name="customerCheckInTime"/>
				    			</td>
				    		</tr>
				    		<tr>
				    			<td>收费起始日:</td>
				    			<td>
				    				<input id="chargeStartDate" class="easyui-datebox" data-options="formatter:myYMDformatter,parser:myYMDparser" type="text"  name="chargeStartDate"/>
				    			</td>
				    			<td>收费结束日:</td>
				    			<td>
				    				<input id="chargeEndDate" class="easyui-datebox" data-options="formatter:myYMDformatter,parser:myYMDparser" type="text"  name="chargeEndDate"/>
				    			</td>
				    			
				    		</tr>
				    		<tr>
				    			<td>合同开始日:</td>
				    			<td>
				    				<input id="contractStartDate" class="easyui-datebox" data-options="formatter:myYMDformatter,parser:myYMDparser"  type="text" name="contractStartDate" />
				    			</td>
				    			<td>合同结束日:</td>
				    			<td>
				    				<input id="contractEndDate" class="easyui-datebox" data-options="formatter:myYMDformatter,parser:myYMDparser"  type="text" name="contractEndDate"/>
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
	</body>
	<script type="text/javascript">
	function myYMDformatter(date){  
        var y = date.getFullYear();  
        var m = date.getMonth()+1;  
        var d = date.getDate();  
        return y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d);  
    }
	
	function myYMDparser(s){  
        if (!s) return new Date();  
        var ss = (s.split('-'));  
        var y = parseInt(ss[0],10);  
        var m = parseInt(ss[1],10);  
        var d = parseInt(ss[2],10);  
        if (!isNaN(y) && !isNaN(m) && !isNaN(d)){  
            return new Date(y,m-1,d);  
        } else {  
            return new Date();  
        }  
    }
	</script>
	<script type="text/javascript">
		/* 构建管理处树 */
		$(function(){
			$('#tree').tree({
			    url:'/ezsh/tree/gtTreeUserId',
			    lines:true
			});
		})
		
		var managerId = 0, managerName = null, buildId = 0, buildName = null, roomId = 0, roomNum = null, userId = 0, userName = null;
		$('#tree').tree({
			onClick: function(node){
				 // alert node text property when clicked
				if (node.id.substring(0, node.id.indexOf("-")) == 0){ //点击了管理处
					managerId = node.id.substring(node.id.indexOf("-")+1);
					$('#formTable').datagrid({
						queryParams: {
							customerName: $("#customerName").val(),
							pManagerId:managerId
						}
					});
				}
				if (node.id.substring(0, node.id.indexOf("-")) == 1){ //点击了楼宇
					buildId = node.id.substring(node.id.lastIndexOf("-")+1);
					$('#formTable').datagrid({
						queryParams: {
							customerName: $("#customerName").val(),
							ptBuildId:buildId
						}
					});
				}
				if (node.id.substring(0, node.id.indexOf("-")) == 2) { //点击房间节点
					var swap = node.id.substring((node.id.indexOf("-")+1)); // 管理处ID-楼宇ID-房间ID-userId
					var selected = $('#tree').tree('getSelected');
					var nodeParent = $("#tree").tree("getParent",selected.target); // 获取父节点（管理处节点）
					var nodeParent1 = $("#tree").tree("getParent",nodeParent.target); // 获取父父节点（楼宇节点）
					managerId = swap.substring(0,swap.indexOf("-")); // 管理处ID
					managerName = nodeParent1.text; // 管理处名称
					
					swap = swap.substring((swap.indexOf("-") + 1)); // 楼宇ID-房间ID-userId
					var buildId = swap.substring(0,swap.indexOf("-")); // 楼宇ID
					
					swap = swap.substring((swap.indexOf("-") + 1)); // 房间ID-userId
					var roomId = swap.substring(0,swap.indexOf("-")); // 房间ID
					userId = swap.substring((swap.indexOf("-") + 1)); //用户ID
					
					//2-管理处ID-楼宇ID-房间ID-userId
					roomNum = node.text.substring(0,node.text.indexOf("|"));
					userName = node.text.substring(node.text.indexOf("|") + 1);
				
					$('#formTable').datagrid({
						queryParams: {
							pRoomId:roomId,
							customerName: $("#customerName").val(),
							pManagerId:managerId
						}
					});	
				}	
			}
		});
	</script>
	
	<script type="text/javascript">
	/**
	 * 初始化列表
	 */
	$('#formTable').datagrid({
	    url:'/ezsh/customer/gtArchivesList',
	    fitColumns:false,
	    columns:[[
	    	{field:'customerId',title:'客户ID',width:100,align:'center'},
			{field:'managerName',hidden:'true',title:'管理处',width:100,align:'center'},
			{field:'roomNum',title:'房间编号',width:100,align:'center',
				formatter: function(value,row,index){
					if(row.roomInfo!=null){
						return row.roomInfo.roomNum;
					} 
				}
			},
			{field:'customerName',title:'客户名称',width:100,align:'center'},
			{field:'customerClassify',title:'客户类型',width:100,align:'center',
				formatter: function(value,row,index){
					if(row.customerClassify==1){
						return "业主";
					} else if (row.customerClassify==2){
						return "租客";
					}	
				}
			},
			{field:'resideStatus',title:'居住状态',width:100,align:'center',
				formatter: function(value,row,index){
					if(row.resideStatus==0){
						return "居住";
					} else if (row.resideStatus==1){
						return "已迁出";
					}	
				}
			},
			{field:'customerTelephone',title:'客户联系电话',width:100,align:'center'},
			{field:'customerCheckInTime',title:'客户迁入时间',width:100,align:'center'},
			{field:'customerIdCardNumber',title:'客户身份证号',width:100,align:'center'},
			{field:'chargeStartDate',title:'收费开始时间',width:100,align:'center'},
			{field:'chargeEndDate',title:'收费结束时间',width:100,align:'center'},
			{field:'contractStartDate',title:'合同开始时间',width:100,align:'center'},
			{field:'contractEndDate',title:'合同结束时间',width:100,align:'center'},
			{field:'remark',title:'备注',width:100,align:'center'},
	    ]]
	});
	</script>
	
	<script type="text/javascript">
	function search(){
		if($("#pManagerId").combobox('getValue')==null || $("#pManagerId").combobox('getValue')==''){
			alert("请选择小区！");
		} else{
			$('#formTable').datagrid({
				queryParams: {
					customerName: $("#customerName").val(),
					pManagerId:$("#pManagerId").combobox('getValue')
				}
			});	
		}
	}
	</script>
	
	<script type="text/javascript">
	function edit(){
		var row = $('#formTable').datagrid('getSelected');
		if (row){
			$('#win').window('open');
			$('#formInfo').form('load',{
				customerId:row.customerId,
				roomNum:row.roomNum,
				customerName:row.customerName,
				customerTelephone:row.customerTelephone,
				customerCheckInTime:row.customerCheckInTime,
				customerIdCardNumber:row.customerIdCardNumber,
				chargeStartDate:row.chargeStartDate,
				chargeEndDate:row.chargeEndDate,
				contractStartDate:row.contractStartDate,
				contractEndDate:row.contractEndDate,
			});
		}else{
			alert("请至少选中一条数据！");
		}
    }
	    
    function sub(){
    	$.messager.progress();	
    	$('#formInfo').form('submit', {
    		url: "/ezsh/customer/updateArchives",
    		onSubmit: function(param){

    		},
    		success: function(data){
    			var data = eval('(' + data + ')'); 
    			$.messager.progress('close');
    			if(data.status==1){
    				$.messager.alert('提示','修改成功!',function(){});
    				$('#formTable').datagrid('reload');
    			}else{
    				$.messager.alert('提示','修改失败!',function(){});
    			}
    		}
    	});
    }
    
    function close(){
    	$('#win').window('close');
    }
	</script>
</html>
