<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
<%@ include file="/common/common.jspf"%>
<title>导入房间档案</title>
</head>
<style>
   .tableStyle1{
      font-size: 12px;
      font-style: normal;
   }

</style>
<body>
   <form id="ff" method="post" enctype ="multipart/form-data">
	    上传文件： <input  name="excelFile" type="file"> 
    </form>	   
	    <div style="text-align:right;">
	    	<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok'"  onclick="submitForm()">提交</a>
	    	<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-no'" onclick="closeWin()">关闭</a>
	    </div>

	<script type="text/javascript">
		function submitForm(){
			var win = parent.$("iframe[title='房间档案']").get(0).contentWindow;//返回ifram页面窗体对象（window)
			$("#ff").form("enableValidation");
			if ($("#ff").form("validate")) {
				$('#ff').form('submit',{
					url : '${proPath}/room/importExcel.action',
					onSubmit : function() {
						return true;
					},
					success : function(count) {							
							//可以定义为对应消息框
							if(count>0){
								alert("添加成功！");									
							}else{
								alert("添加失败！");
							}
							parent.$("#win").window("close");
							win.$("#dg").datagrid("reload");
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
