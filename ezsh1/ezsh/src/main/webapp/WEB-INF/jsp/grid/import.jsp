<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/common/header.jsp" %>
<!DOCTYPE html>
<html>
	<head> 
	<title>表计类别列表</title>
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
		<!-- importExcel window start -->
		<div class="easyui-layout" fit="true">
			<div region="center" border="false" >
				<form id="formInfo" method="post" enctype="multipart/form-data">
					<div style="margin:5% 0 0 5%;"><span style="font-size:20px;">提示:</span>导出的表格用于更新抄表记录！</div>
			    	<table class="tableStyle" cellpadding="5">
			    		<tr>				    		    			
			    			<td>管理处:</td>
			    			<td>
			    				<input  id="ptManagerId" class="easyui-combobox" name="managerId"   
                      				data-options="valueField:'managerId',
                      				required:true,
                      				textField:'managerName',
                      				editable:false,
                      				 panelHeight: 80,
                      				url:'/ezsh/build/findManager',
                      				onSelect: function(rec){
	                      			<%--	var url = '/ezsh/build/gtBuildListById?buildManagerId='+rec.managerId;
					    				$('#ptBuildId').combobox('reload', url);	--%>
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
			    		
			   <%-- 		<tr>
			    			<td>楼宇ID:</td>
			    			<td>
			    				<input id="ptBuildId" class="easyui-combobox" name="ptBuildId"
					   				data-options="valueField:'buildId',
					   				textField:'buildName',
					   				editable:false,
					   				required:true,
					   				">
			    			</td>	
			    		</tr>--%>
			    		
			    		<tr>
			    			<td>表计类别:</td>
			    			<td>
			    				<input  id="ptGridId" class="easyui-combobox" name="gridId"
                        			data-options="valueField:'chargeId',
                        			required:true,
                        			editable:false,
                        			textField:'chargeName'
                        			 panelHeight: 80,
                       			" />
                       		</td>			    			
			    		</tr>
			    		
			    		<tr>
			    			<td>选择文件:</td>
			    			<td>
			    				<input class="easyui-filebox" name="upfile" data-options="prompt:'选择文件'">
                       		</td>			    			
			    		</tr>
			    	</table>
		    	</form>
			</div>
			<div region="south" border="true" style="text-align:right;height:50px;line-height:30px;padding:10px 15px 0px 0px;">
				<a class="easyui-linkbutton" icon="icon-ok" href="javascript:exportIn()">确定</a>
				<a class="easyui-linkbutton" icon="icon-cancel" href="javascript:close()">取消</a>
			</div>
		</div>
		<!-- importExcel window end -->
		
	</body>
	<script type="text/javascript">
	var win = parent.$("iframe[title='抄表录入管理']").get(0).contentWindow;
	function exportIn(){
		$("#formInfo").form("enableValidation");
		if ($("#formInfo").form("validate")) {
			$.messager.progress();	
	    	$('#formInfo').form('submit', {
	    		url: "/ezsh/gridRecord/import",
	    		onSubmit: function(param){

	    		},
	    		success: function(data){
	    			$.messager.progress('close');
	    			var data = eval('(' + data + ')'); 
	    			if(data.status == 1){
	    				$.messager.alert('提示','更新成功!','info');
	    			}else{
	    				$.messager.alert('提示','更新失败!',function(){	
	    				});
	    			}
	    			win.$('#formTable').datagrid('reload');
	    		}
	    	});
		}
	}
	</script>
</html>
