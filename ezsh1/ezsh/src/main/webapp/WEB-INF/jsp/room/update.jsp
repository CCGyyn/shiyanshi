<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
<%@ include file="/common/common.jspf"%>
<title>修改管理处</title>
</head>
<body>
  <style>
     .tableStyle1{
      font-size: 12px;
      font-style: normal;
   }
  
  </style>
<div >
	  <form id="ff" method="post">
	<div id="tt" class="easyui-tabs" style="width:540px;height:280px;">   
    <div title="基本信息" style="padding:10px;">   
        <form id="ff" method="post">
	    	<table class="tableStyle1" cellpadding="5">
	    		<tr>
	    	    <td> 管理处   &nbsp<font color="red" size="2px">*</font></td> 
               <td> <input name="managerId" class="easyui-combobox" data-options="    
                  valueField: 'managerId',    
                  textField: 'managerName',
                   panelHeight: 80,
                  url: '${proPath }/build/findManager.action',    
                 onSelect: function(rec){
                   var url = '${proPath }/room/findBuild.action?managerId='+rec.managerId;    
                   $('#buildId').combobox('reload', url);    
                  }" />  </td>    
          <td> 楼宇 &nbsp<font color="red" size="2px" >*</font></td> 
          <td><input id="buildId" name="buildId" class="easyui-combobox" data-options="valueField:'buildId',textField:'buildName'" />  </td>
	    </tr>
	    		<tr>
	    			<td> 房间类型： </td>
	    			<td> 
	     <select name="roomType" class="easyui-combobox" style="width:160px;font-size: 12px">
               <option value="" selected="selected">--请选择--</option>
               <option value="0">住宅</option>  
               <option value="1" >公寓</option> 
                <option value="2" >办公</option> 
               <option value="3" >厂房</option> 
               <option value="4" >仓库</option> 
                <option value="5" >宿舍</option> 
                <option value="6" >其他</option> 
         </select></td>
	    			<td> 房间状态</td>
	   <td><select name="roomStatus" class="easyui-combobox" style="width:160px;font-size: 12px">
               <option value="" selected="selected">--请选择--</option>
               <option value="0">未售</option>  
               <option value="1" >已售</option> 
                <option value="2" >未租</option> 
               <option value="3" >已租</option> 
               <option value="4" >自用</option> 
                <option value="5" >入住</option> 
                <option value="6" >空置</option> 
                 <option value="7" >未交付</option> 
                 </select></td>
	    		</tr>
	    		<tr>
	    			<td>建筑面积</td>
	    			<td><input name="buildArea" class="easyui-textbox" type="text"  data-options="required:true"></input></td>
	    			<td>收费服务对象</td>
	    			<td>
	    	<select name="chargeMan" class="easyui-combobox" style="width:160px;font-size: 12px">
               <option value="" selected="selected">--请选择--</option>
               <option value="无">无</option>  
               <option value="业主" >业主</option>
                <option value="租户" >租户</option>
               </select></td>
	    		</tr>
	    		<tr>
	    			<td>楼层数</td>
	    			<td><input  class="easyui-textbox" type="text" name="roomFloor" data-options="required:true"></input></td>
	    			<td>房间用途</td>
	    			<td><input class="easyui-textbox" type="text" name="roomUse" data-options="required:true"></input></td>
	    		</tr>
	    		<tr>
	    			<td>单元号</td>
	    			<td><input class="easyui-textbox" type="text" name="roomNum" data-options="required:true"></input></td>
	    			<td>装修情况</td>
	    			<td><input class="easyui-textbox" type="text" name="decorate" data-options="required:true"></input></td>
	    		</tr>
	    			<tr>
	    			<td>位置</td>
	    			<td><input class="easyui-textbox" type="text" name="position" data-options="required:true"></input></td>
	    			<td>朝向</td>
	    			<td><input class="easyui-textbox" type="text" name="toward" data-options="required:true"></input></td>
	    		</tr>
	    		<tr>
	    			<td>联系人</td>
	    			<td><input class="easyui-textbox" type="text" name="contact" data-options="required:true"></input></td>
	    			<td>备注</td>
	    			<td><input class="easyui-textbox" type="text" name="remark" data-options="required:true"></input></td>
	    		</tr>
	    	</table>
	   
    </div>   
    <div title="扩展信息" data-options="closable:true" style="overflow:auto;padding:10px;">   
        <table  class="tableStyle1" cellpadding="5">
           <tr>
              <td>租金</td>
              <td><input class="easyui-textbox" type="text" name="rent" data-options="required:true"></input></td>
              <td>管理费</td>
              <td><input class="easyui-textbox" type="text" name="managementFee" data-options="required:true"></input></td>
           </tr>
             <tr>
              <td>单价</td>
              <td><input class="easyui-textbox" type="text" name="singlePrice" data-options="required:true"></input></td>
              <td>总价</td>
              <td><input class="easyui-textbox" type="text" name="sumPrice" data-options="required:true"></input></td>
           </tr>
            <tr>
              <td>是否有效</td>
              <td><select name="effectiveStatus" class="easyui-combobox" style="width:160px;font-size: 12px">
                   <option value="0">否</option>
                   <option value="1">是</option>
              </select></td>
           </tr>
           <input name="roomId" type="hidden" />
        </table>
    </div>   
      </form> 
	</div>
	   
	    <div style="text-align:right;">
	    	<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok'"  onclick="submitForm()">提交</a>
	    	<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-no'" onclick="closeWin()">关闭</a>
	    </div>

	<script type="text/javascript">
		$(function() {
			var win = parent.$("iframe[title='房间档案']").get(0).contentWindow;//返回ifram页面窗体对象（window)
			
			var row = win.$('#dg').datagrid("getSelected");
			
			$('#ff').form('load',{
				roomId:row.roomId,
				managerId:row.managerId,
				buildId:row.buildId,
				roomType:row.roomType,
				roomStatus:row.roomStatus,
				chargeMan:row.chargeMan,
				buildArea:row.buildArea,
				roomFloor:row.roomFloor,
				roomUse:row.roomUse,
				roomNum:row.roomNum,
				decorate:row.decorate,
				position:row.position,
				toward:row.toward,
				contact:row.contact,
				remark:row.remark,
				rent:row.rent,
				managementFee:row.managementFee,
				singlePrice:row.singlePrice,
				sumPrice:row.sumPrice,
				effectiveStatus:row.effectiveStatus,
			});
			var url='${proPath }/room/findBuild.action?managerId='+row.managerId;
			$('#buildId').combobox('reload',url);  
			
		
			$("[name='managerId']").validatebox({
				required : true,
				missingMessage : '请填写编号！'
			});
			$("[name='managerName']").validatebox({
				required : true,
				missingMessage : '请填写管理处名称！'
			});
			$("[name='managerAddr']").validatebox({
				required : true,
				missingMessage : '请填写地址！'
			});
			//禁用验证
			$("#ff").form("disableValidation");

			

		});
		function submitForm(){
			var win = parent.$("iframe[title='房间档案']").get(0).contentWindow;//返回ifram页面窗体对象（window)
			$("#ff").form("enableValidation");
			if ($("#ff").form("validate")) {
				$('#ff').form('submit', {
					url : '${proPath}/room/update.action',
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
