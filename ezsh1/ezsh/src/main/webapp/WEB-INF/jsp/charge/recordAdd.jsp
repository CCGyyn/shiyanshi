<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/common/header.jsp" %>
<!DOCTYPE html>
<html>
	<head> 
	<title>收费项目新增</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">    
		<meta http-equiv="description" content="表计类别列表">
		<style type="text/css">
			.tableStyle{
				font-size:12px;
				padding:5% 0px 0px 10%;
			}
		</style>
	</head>
	<body>
		<div class="easyui-layout" fit="true">
			<div region="center" border="false" >
				<form id="formInfo" method="post">
					<table class="tableStyle" cellpadding="5">
			    	<%--	<tr>
			    			<td>管理处:</td>
			    			<td>
			    				<input  id="pManagerId" class="easyui-combobox" name="pManagerId"   
                      				data-options="valueField:'managerId',
                      				textField:'managerName',
                      				required:true,
                      				url:'/ezsh/build/findManager',
                      				onSelect: function(rec){	
									    var url = '/ezsh/charge/selectByManagerId?pManagerId='+rec.managerId;
									    $('#pChargeItemId').combobox('reload', url);
								    }
                      				"/> 
			    			</td>
			    		</tr>--%>
			    		
			    		<tr>
			    			<td>收费项目名称:</td>
			    			<td>
				    			<input  id="chargeItemName" class="easyui-textbox" name="chargeItemName">
			    			</td>
			    		</tr>
			    		
			    		<tr>
			    			<td>数量:</td>
			    			<td>
			    				<input id="chargeAmount" class="easyui-numberbox" value="0" min="0"  max="10000" required="required" type="text" name="chargeAmount" ></input>
			    			</td>
			    		</tr>
			    		
			    		<tr>
			    			<td>收费项目单价:</td>
			    			<td>
			    				<input id="unitPrice" class="easyui-numberbox" min="0"  max="10000"  precision="2" required="required" type="text" name="unitPrice"></input>
			    			</td>
			    		</tr>
			    		<%--
			    		<tr>
			    			<td>总金额:</td>
			    			<td>
			    				<input id="totalPrice" class="easyui-numberbox" value="0" min="0"  max="100000" precision="2" required="required" type="text" name="totalPrice" ></input>
			    			</td>
			    		</tr>
			    		
			    		<tr>
			    			<td>所属月份:</td>
			    			<td>
			    				<input id="chargeOfDate" class="easyui-datebox" data-options="formatter:myYMformatter,parser:myYMparser" required="required" type="text" name="chargeOfDate"></input>
			    			</td>
			    		</tr>
			    		
			    		<tr>
			    			<td>费用截止日期:</td>
			    			<td>
			    				<input id="chargeEndDate" class="easyui-datebox" data-options="formatter:myYMDformatter,parser:myYMDparser" required="required" type="text" name="chargeEndDate" />
			    			</td>
			    		</tr>--%>
			    		
			    		<tr style="display:none;">
			    			<td>
			    				<input id="pRoomId" class="easyui-textbox" value="0" min="0"  max="100" required="required" type="text" name="pRoomId" />
			    			</td>
			    			<!-- <td>
			    				<input id="pUserId" class="easyui-textbox" min="1"  max="20" required="required" type="text" name="pUserId"></input>
			    			</td> -->
			    		</tr>
			    	<%--	<tr style="display:none;">
			    			<td>
			    				<input id="pBuildingId" class="easyui-textbox" value="0" min="0"  max="100" required="required" type="text" name="pBuildingId" />
			    			</td>
			    		</tr>--%>
			    	</table>
		    	</form>
			</div>
			<div region="south" border="true" style="text-align:right;height:50px;line-height:30px;padding:10px 15px 0px 0px;">
				<a class="easyui-linkbutton" icon="icon-ok" href="javascript:sub()">确定</a>
				<a class="easyui-linkbutton" icon="icon-cancel" href="javascript:close()">取消</a>
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
	
	
	function myYMformatter(date){  
		var y = date.getFullYear();  
		var m = date.getMonth()+1;  
		return y+'-'+(m<10?('0'+m):m);  
	}    
	function myYMparser(s){  
	    if (!s) return new Date();  
	    var ss = (s.split('-'));  
	    var y = parseInt(ss[0],10);  
	    var m = parseInt(ss[1],10);  
	    if (!isNaN(y) && !isNaN(m)){  
	        return new Date(y,m-1);  
	    } else {  
	        return new Date();  
	    }  
	}  

		
	$(function(){
		var win = parent.$("iframe[title='应收费记录']").get(0).contentWindow;
		var swap=win.selected.id.substring(win.selected.id.indexOf("-")+1);
		var managerId=swap.substring(0,swap.indexOf("-"));
		swap=swap.substring(swap.indexOf("-")+1);
		var buildId=swap.substring(0,swap.indexOf("-"));
		var roomId=win.selected.id.substring(win.selected.id.lastIndexOf("-")+1);
		var roomNum=win.selected.text.substring(0,win.selected.text.indexOf("|"));
		swap=swap.substring(swap.indexOf("-")+1);
	/*	$('#pManagerId').combobox('setValue', managerId);//设置管理处DI
		$('#pBuildingId').textbox('setValue', buildId);//设置楼宇ID*/
		$('#pRoomId').textbox('setValue', roomId);//设置房间ID
		//房间收费项目列表
		$('#chargeItem').combobox({
			formatter: function(row){
				var opts = $(this).combobox('options');
				return row[opts.textField]=row.chargeItemInfo.chargeName;
			}
		});
		var url='/ezsh/chargeRoom/getRoomItemsList?roomId='+roomId;
		$('#chargeItem').combobox('reload', url);
	})
	
	//提交添加
    function sub(){
    	$("#formInfo").form("enableValidation");
    	if ($("#formInfo").form("validate")) {
    		$.messager.progress();	
	    	$('#formInfo').form('submit', {
	    		url: "/ezsh/chargeRecord/add",
	    		onSubmit: function(param){

	    		},
	    		success: function(data){
	    			$.messager.progress('close');
	    			var data = eval('(' + data + ')'); 
	    			if (data.status==1){
	    				$.messager.alert('提示','添加成功!','info');
                        parent.$('#formTable').datagrid('reload');
                        parent.$('#win').window('close');
                        parent.$('#win').find('#formTable').datagrid('reload');
                    } else if (data.status==0){
	    				$.messager.alert('提示','添加失败!','info');
	    			}
	    		}
	    	});
    	}
    }
	    
    function close(){	
    	parent.$('#win').window('close');
    }
	</script>
</html>
