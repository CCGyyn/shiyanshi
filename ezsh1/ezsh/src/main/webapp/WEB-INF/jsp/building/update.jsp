<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
<%@ include file="/common/common.jspf"%>
<title>修改管理处</title>
	<style type="text/css">
	     .tableStyle1{
	      font-size: 12px;
	      font-style: normal;
	   }
	</style>
</head>
<body>
	<div >
	    <form id="ff" method="post">
	    	<table class="tableStyle1" cellpadding="3">
	    		<tr>
	    			<td>编号 <span style="color:red;">*</span></td>
	    			<td><input class="easyui-textbox" type="text" name="buildId" readonly="true" data-options="required:true"></input></td>
	    		</tr>
	    		<tr>
	    			<td>管理处名称 <span style="color:red;">*</span></td>
	    			<td><input  class="easyui-combobox" name="buildManagerId"   
                          data-options="valueField:'managerId',
                          required:true,textField:'managerName',
                            panelHeight: 80,
                          url:'${proPath }/build/findManager.action'" /> 
                    </td>
	    		</tr>
	    		<tr>
	    			<td>楼宇名称<span style="color:red;">*</span></td>
	    			<td><input class="easyui-textbox" type="text" name="buildName" data-options="required:true"></input></td>
	    		</tr>
	    		<tr>
	    			<td>楼宇地址</td>
	    			<td><input class="easyui-textbox" type="text" name="buildAddr" data-options="required:true"></input></td>
	    		</tr>
	    		<tr>
	    			<td>楼宇类型</td>
	    			<td><input class="easyui-textbox" type="text" name="buildType" data-options="required:true"></input></td>
	    	    </tr>
	    		<tr>
	    			<td>楼宇结构</td>
	    			<td><input class="easyui-textbox" type="text" name="structure" data-options="required:true"></input></td>
	    		</tr>
	    		<tr>
	    			<td>楼宇朝向</td>
	    			<td><input class="easyui-textbox" type="text" name="toward" data-options="required:true"></input></td>
	    		 </tr>
	    		 <tr>
	    			<td>备注</td>
	    			<td><input class="easyui-textbox" type="text" name="remark" data-options="required:true"></input></td>
	    		 </tr>
	    	</table>
	    </form>
	    <div style="text-align:right;">
	    	<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok'"  onclick="submitForm()">提交</a>
	    	<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-no'" onclick="closeWin()">关闭</a>
	    </div>
	</div>

	<script type="text/javascript">
		$(function() {
			var win = parent.$("iframe[title='楼宇管理']").get(0).contentWindow;//返回ifram页面窗体对象（window)	
			var row = win.$('#dg').datagrid("getSelected");	
			$('#ff').form('load',{
				buildId:row.buildId,
				buildManagerId:row.buildManagerId,
				buildName:row.buildName,
				buildAddr:row.buildAddr,
				buildType:row.buildType,
				structure:row.structure,
				toward:row.toward,
				remark:row.remark,
			});
			$("[name='buildManagerId']").validatebox({
				required : true,
				missingMessage : '请填写管理处名称！'
			});
			$("[name='buildName']").validatebox({
				required : true,
				missingMessage : '请填写楼宇名字！'
			});
			//禁用验证
			$("#ff").form("disableValidation");	
		});
		
		function submitForm(){
			var win = parent.$("iframe[title='楼宇管理']").get(0).contentWindow;//返回ifram页面窗体对象（window)
			$("#ff").form("enableValidation");
			if ($("#ff").form("validate")) {
				$('#ff').form('submit', {
					url : '${proPath}/build/update.action',
					onSubmit : function() {
						return true;
					},
					success : function(count) {							
						//可以定义为对应消息框
						if(count>0){
							alert("修改成功！");									
						}else{
							alert("修改失败！");
						}
						parent.$("#win").window("close");
						win.$("#dg").datagrid("reload");
						win.$("#dg").datagrid("clearSelections");
					}
				});
			}
		}
		function closeWin(){
			parent.$("#win").window("close");
		}
	</script>
</body>
</html>
