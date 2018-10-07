<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/common/common.jspf"%>
<title>新增管理处</title>
</head>
<style>
   .tableStyle1{
      font-size: 12px;
      font-style: normal;
   }
</style>
<body>
	<div>
	    <form id="ff" method="post">
	    	<table class="tableStyle1" cellpadding="5">
	    		<tr>
	    			<td>管理处名称 <span style="color:red;">*</span></td>
	    			<td><input class="easyui-textbox" type="text" name="managerName" data-options="required:true"></input></td>
	    		    <td>省市区 <span style="color:red;">*</span></td>
	    			<td>
		    			<select id="pProvinceId" name="pProvinceId" onchange="changeProvince()" style="width:50px">
		    					
		    			</select>
		    			<select id="pCityId" name="pCityId" onchange="changeRegion(this.value)" style="width:50px">
		    					
		    			</select>
		    			<select id="pDistrictId" name="pDistrictId" style="width:50px">
		    					
		    			</select>
	    			</td>	
	    		</tr>
	    		<tr>
	    			<td>详细地址  <span style="color:red;">*</span></td>
	    			<td><input class="easyui-textbox" type="text" name="managerAddr" data-options="required:true"></input></td>
	    		</tr>
	    		<tr>
	    			<td>高层楼宇数量</td>
	    			<td><input class="easyui-textbox" type="text" name="heightBuildNum" data-options="required:true"></input></td>
	    			<td>多层楼宇数量</td>
	    			<td><input class="easyui-textbox" type="text" name="manyBuildNum" data-options="required:true"></input></td>
	    		</tr>
	    		<tr>
	    			<td>建筑面积</td>
	    			<td><input class="easyui-textbox" type="text" name="buildArea" data-options="required:true"></input></td>
	    			<td>占地面积</td>
	    			<td><input class="easyui-textbox" type="text" name="floorArea" data-options="required:true"></input></td>
	    		</tr>
	    		<tr>
	    			<td>绿化面积</td>
	    			<td><input class="easyui-textbox" type="text" name="greenArea" data-options="required:true"></input></td>
	    			<td>公共场所面积</td>
	    			<td><input class="easyui-textbox" type="text" name="publicArea" data-options="required:true"></input></td>
	    		</tr>
	    		<tr>
	    			<td>车位数</td>
	    			<td><input class="easyui-textbox" type="text" name="parkingNum" data-options="required:true"></input></td>
	    			<td>车库面积</td>
	    			<td><input class="easyui-textbox" type="text" name="parkingArea" data-options="required:true"></input></td>
	    		</tr>
	    			<tr>
	    			<td>联系人</td>
	    			<td><input class="easyui-textbox" type="text" name="contact" data-options="required:true"></input></td>
	    			<td>负责人</td>
	    			<td><input class="easyui-textbox" type="text" name="head" data-options="required:true"></input></td>
	    		</tr>
	    		<tr>
	    			<td>联系电话</td>
	    			<td><input class="easyui-textbox" type="text" name="contactNum" data-options="required:true"></input></td>
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
		$("[name='managerName']").validatebox({
			required : true,
			missingMessage : '请填写管理处名称！'
		});
		$("[name='managerAddr']").validatebox({
			required : true,
			missingMessage : '地址！'
		});
		//禁用验证
		$("#ff").form("disableValidation");
	})
	   
	function submitForm(){
		var win = parent.$("iframe[title='管理处']").get(0).contentWindow;//返回ifram页面窗体对象（window)
		$("#ff").form("enableValidation");
		if ($("#ff").form("validate")) {
			$('#ff').form('submit', {
				url : '${proPath}/management/insert.action',
				onSubmit : function() {
					return true;
				},
				success : function(count) {							
						//可以定义为对应消息框
						if(count==1){
							alert("添加成功！");									
						}else if(count==2){
							alert("名字重复,添加失败！");
						} else {
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
	
	$(document).ready(function(){
		var provinceId,select="";
		var select="";
		$.ajax({
			url:"/ezsh/prCiDistr/gtProvince",
			type:'post',
			dataType:'json',
			success:function(data){
				data=data.data;
				for(var i=0;i<data.length;i++){
					/* if(data[i].name==province){
						select+="<option selected='selected' value='"+data[i].id+"'>"+data[i].name+"</option>";
						provinceId=data[i].id;
					}else{
						select+="<option value='"+data[i].id+"'>"+data[i].name+"</option>";
					} */
					if(data[i].id == pProvinceId) {
						select += "<option selected='selected' value='" + data[i].id + "'>" + data[i].name + "</option>";
						provinceId = data[i].id;
					}else{
						select += "<option value='" + data[i].id + "'>" + data[i].name + "</option>";
					}
				}
				if(provinceId==""||provinceId==null){
					provinceId=data[0].id;
				}
				document.getElementById('pProvinceId').innerHTML=select;
				getCity(provinceId);
			}
		});

		function getCity(provinceId){
			var cityId,select="";
			var cityChange=0;
			if(provinceId>=0){
				$.ajax({
					url:"/ezsh/prCiDistr/gtCity?provinceId="+provinceId,
					type:'post',
					dataType:'json',
					success:function(data){
						data=data.data;
						for(var i=0;i<data.length;i++){
							/* if(data[i].name==city){
								cityId=data[i].id;
								cityChange=1;
								select+="<option selected='selected' value='"+data[i].id+"'>"+data[i].name+"</option>";
							}else{
								select+="<option value='"+data[i].id+"'>"+data[i].name+"</option>";
							}	 */
							if (data[i].id == pCityId) {
								cityId = data[i].id;
								select += "<option selected='selected' value='" + data[i].id + "'>" + data[i].name + "</option>";
							} else {
								select += "<option value='" + data[i].id + "'>" + data[i].name + "</option>";
							}	
						}
						
						if(cityId==""||cityId==null){
							cityId=data[0].id;
						}
						document.getElementById('pCityId').innerHTML=select;
						getRegion(cityId);
					}
				});
			}
		}
		
		function getRegion(cityId){
			var select="";
			if(cityId>=0){
				$.ajax({
					url:"/ezsh/prCiDistr/gtDistrict?cityId="+cityId,
					type:'post',
					dataType:'json',
					success:function(data){
						data=data.data;
						for(var i=0;i<data.length;i++){
							/* if(data[i].name==region){
								select+="<option selected='selected' value='"+data[i].id+"'>"+data[i].name+"</option>";
							}else{
								select+="<option value='"+data[i].id+"'>"+data[i].name+"</option>";
							} */
							if (data[i].id == pDistrictId) {
								select += "<option selected='selected' value='" + data[i].id + "'>" + data[i].name + "</option>";
							} else {
								select += "<option value='" + data[i].id + "'>" + data[i].name + "</option>";
							}
						}
						document.getElementById('pDistrictId').innerHTML=select;
					}
				});
			}
		}
	});
	function changeProvince() {
		var pProvinceId, pCityId, pDistrictId;
		var proviceId = document.getElementById("pProvinceId").value;
		changeCity(proviceId);
	}

	function changeCity(provinceId) {
		var cityId,select = "";
		if (provinceId >= 0) {
			$.ajax({
				url:"/ezsh/prCiDistr/gtCity?provinceId="+provinceId,
				type:'post',
				dataType:'json',
				success:function(data) {
					data = data.data;
					for (var i = 0; i < data.length; i++) {	
						select += "<option value='" + data[i].id + "'>" + data[i].name + "</option>";
					}
					
					if (cityId == "" || cityId == null) {
						cityId = data[0].id;
					}
					document.getElementById('pCityId').innerHTML = select;
					changeRegion(cityId);
				}
			});
		}
	}

	/* 获取城区列表*/
	function changeRegion(cityId) {
		var select = "";
		if (cityId >= 0) {
			$.ajax({
				url:"/ezsh/prCiDistr/gtDistrict?cityId=" + cityId,
				type:'post',
				dataType:'json',
				success:function(data) {
					data = data.data;
					for (var i = 0; i < data.length; i++) {
						select += "<option value='" + data[i].id+"'>" + data[i].name + "</option>";
					}
					document.getElementById('pDistrictId').innerHTML = select;
				}
			});
		}
	}
	</script>
</body>
</html>
