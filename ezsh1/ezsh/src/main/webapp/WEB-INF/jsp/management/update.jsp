<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
<%@ include file="/common/common.jspf"%>
<title>修改管理处</title>
<style>
    .tableStyle1{
      font-size: 12px;
      font-style: normal;
   	}
</style>
</head>
<body>
	<div >
	    <form id="ff" method="post">
	    	<table class="tableStyle1" cellpadding="5">
	    		<tr>
	    			<td>编号 <span style="color:red;">*</span></td>
	    			<td><input class="easyui-textbox" type="text" name="managerId" data-options="required:true" readonly="true"></input></td>
	    		</tr>
	    		
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
	    			<td>详细地址<span style="color:red;">*</span></td>
	    			<td><input class="easyui-textbox" type="text" name="managerAddr" data-options="required:true"></input></td>
	    		</tr>
	    		<tr>
	    			<td>高层楼宇数量</td>
	    			<td><input class="easyui-numberbox" type="text" name="heightBuildNum" data-options="required:true,min:0"></input></td>
	    			<td>多层楼宇数量</td>
	    			<td><input class="easyui-numberbox" type="text" name="manyBuildNum" data-options="required:true,min:0"></input></td>
	    		</tr>
	    		<tr>
	    			<td>建筑面积(平方米)</td>
	    			<td><input class="easyui-numberbox" type="text" name="buildArea" data-options="required:true,min:0,precision:2"></input></td>
	    			<td>占地面积(平方米)</td>
	    			<td><input class="easyui-numberbox" type="text" name="floorArea" data-options="required:true,min:0,precision:2"></input></td>
	    		</tr>
	    		<tr>
	    			<td>绿化面积(平方米)</td>
	    			<td><input class="easyui-numberbox" type="text" name="greenArea" data-options="required:true,min:0,precision:2"></input></td>
	    			<td>公共场所面积(平方米)</td>
	    			<td><input class="easyui-numberbox" type="text" name="publicArea" data-options="required:true,min:0,precision:2"></input></td>
	    		</tr>
	    		<tr>
	    			<td>车位数</td>
	    			<td><input class="easyui-numberbox" type="text" name="parkingNum" data-options="required:true,min:0"></input></td>
	    			<td>车库面积(平方米)</td>
	    			<td><input class="easyui-numberbox" type="text" name="parkingArea" data-options="required:true,min:0,precision:2"></input></td>
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
</body>
<script type="text/javascript" src="/ezsh/js/management/update.js"></script>
</html>
