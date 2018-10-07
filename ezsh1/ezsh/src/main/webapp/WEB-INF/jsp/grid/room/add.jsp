<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/common/header.jsp" %>
<!DOCTYPE html>
<html>
	<head> 
	<title>房间表计类别新增</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">    
		<meta http-equiv="description" content="表计类别列表">
		<style type="text/css">
			.tableStyle{
				font-size:12px;
				padding:10% 0px 0px 10%;
			}
		</style>
	</head>
	
	<body>
		<div class="easyui-layout" fit="true">
			<div region="center" border="false" >
				<form id="formInfo" method="post">
			    	<table class="tableStyle" cellpadding="5">
			    		<tr>				    		    			
			    			<td>管理处:</td>
			    			<td>
			    				<input  id="ptManagerId" class="easyui-combobox" name="ptManagerId"   
                      				data-options="valueField:'managerId',
                      				required:true,
                      				textField:'managerName',
                      				 panelHeight: 80,
                      				url:'/ezsh/build/findManager',
                      				onSelect: function(rec){	
								    var url = '/ezsh/grid/selectSimple?pManagerId='+rec.managerId;
								    	$('#ptGridId').combobox('reload', url);
								    },
								    onLoadSuccess:function(data){
									    var url = '/ezsh/grid/selectSimple?pManagerId='+data[0].managerId;
										$('#ptGridId').combobox('reload', url);
								    }
                      				"/> 
			    			</td>
			    		</tr>
			    		
			    		<tr>
			    			<td style="display:none">
			    				<input id="ptBuildId"  class="easyui-textbox" type="text" name="ptBuildId"/>
			    			</td>
			    		</tr>
			    		
			    		<tr>
			    			<td style="display:none">
			    				<input id="ptRoomId"  class="easyui-textbox" type="text" name="ptRoomId"/>
			    			</td>
			    			<td>房间编号:</td>
			    			<td>
			    				<input id="roomNum" class="easyui-textbox" type="text" name="roomNum" required="required"/>
			    			</td>
			    		</tr>
			    		
			    		<tr>
			    			<td>表计类别:</td>
			    			<td>
			    				<input  id="ptGridId" class="easyui-combobox" name="ptGridId"   
                        			data-options="valueField:'gridId',
                        			required:true,
                        			textField:'gridName'
                        			 panelHeight: 80,
                       			" />
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
	</body>
	
	<script type="text/javascript">
		$(function(){
			var win = parent.$("iframe[title='房间表计设置']").get(0).contentWindow;
			$('#pManagerId').combobox('setValue',""); 
			var swap=win.selected.id.substring(win.selected.id.indexOf("-")+1);
			var managerId=swap.substring(0,swap.indexOf("-"));
			var roomId=win.selected.id.substring(win.selected.id.lastIndexOf("-")+1);
			var roomNum=win.selected.text.substring(0,win.selected.text.indexOf("|"));
			swap=swap.substring(swap.indexOf("-")+1);
			$("#ptBuildId").textbox('setValue',swap.substring(0,swap.indexOf("-")));
			$("#ptRoomId").textbox('setValue',roomId);
			$("#roomNum").textbox('setValue',roomNum);
			/* $('#pManagerId').combobox('setValue', 1); */
		})
		
	    function sub(){
	    	$("#formInfo").form("enableValidation");
	    	if ($("#formInfo").form("validate")) {
	    		$.messager.progress();	
		    	$('#formInfo').form('submit', {
		    		url: "/ezsh/gridRoom/add",
		    		onSubmit: function(param){
		    			param.roomGridName = $("#ptGridId").combobox("getText");
		    		},
		    		success: function(data){
		    			$.messager.progress('close');
		    			var data = eval('(' + data + ')'); 
		    			if(data.status==1){
		    				$.messager.alert('提示', data.message ,'info');
		    			}else{
		    				alert(win);
		    				$.messager.alert('提示', data.message ,function(){	
		    				});
		    			}
		    			win.location.reload();
		    		}
		    	});
	    	}
	    }
	    
	    function reload(){
	    	location.reload();
		}
	    
	    function close(){	
	    	$('#win').window('close');
	    }
	</script>
</html>
