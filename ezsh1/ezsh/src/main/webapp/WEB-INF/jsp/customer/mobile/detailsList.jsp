<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/common/header.jsp" %>
<!DOCTYPE html>
<html>
	<head> 
	<title>商品列表</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">    
		<meta http-equiv="description" content="商品列表">
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
				<a href="javascript:pass()" class="easyui-linkbutton" iconCls="fa fa-check fa-fw fa-lg" plain="true"></a>
				<!-- <a href="javascript:edit()" class="easyui-linkbutton" iconCls="icon-edit" plain="true"></a> -->
				<a href="javascript:failure()" class="easyui-linkbutton" iconCls="fa fa-times-circle fa-fw fa-lg" plain="true"></a>
			</div>
		</div>
		
		<!-- <div data-options="region:'north',split:false," title="查询条件" style="height:100px">
			<div style="padding:15px;height:auto">
				起始时间: <input id="startTime" class="easyui-datebox" style="width:100px">
				终止时间: <input id="endTime" class="easyui-datebox" style="width:100px">
				展示类型: 
				<select id="goodsShowClassfy" class="easyui-combobox" panelHeight="auto" style="width:100px">
					<option value="1">普通展示</option>
					<option value="2">每日推荐</option>
					<option value="3">活动优惠</option>
				</select>
				商品名称：
				<input id="goodsName" class="easyui-textbox" style="width:8%;">
				<a href="#" id="search" class="easyui-linkbutton" iconCls="icon-search">Search</a>
			</div>
		</div> -->
		
		<div data-options="region:'center',title:'商品库存列表',iconCls:'icon-ok'">
			<table  id="formTable"  class="easyui-datagrid"
					data-options="url:'',method:'post', 
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
		<div id="win" class="easyui-window" title="商品详细信息-修改" style="width:600px;height:400px"
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
					<form id="formInfo" method="post" enctype="multipart/form-data">
				    	<table class="tableStyle" cellpadding="5">
				    		<tr>				    		
				    			<td style="display:none;">
				    				<input id="goodsId" class="easyui-textbox" type="text" name="goodsId"/>
				    			</td>
				    			<td>基本信息ID:</td>
				    			<td>
				    				<input id="pGoodsInfoId" class="easyui-textbox" type="text" name="pGoodsInfoId" />
				    			</td>
				    		</tr>
				    		
				    		<tr>
				    			<td style="display:none;">
				    				<input id="sectionOneValue" class="easyui-textbox" type="hidden" name="sectionOneValue"/>
				    			</td>
				    			<td>类别属性一(值):</td>
				    			<td>
				    				<input id="sectionOneValueNew" class="easyui-textbox" type="text" name="sectionOneValueNew"/>
				    			</td>
				    			<td>
						    		<div style="border:1px solid #ccc;height:50px;width:50px;">
									    <img id="goodsColorImage" style="width:50px;height:50px;">
									    <!-- <div style="display:inline-block;font-size:25px;margin:5px 0px 0px 15px;">+
									    	<input type="file" name="goodsColorImage" onchange="changePic(this,'goodsColorImage')" style="height:45px;width:45px;position:relative;right:10px;bottom:30px;opacity: 0;">
									    </div> -->
								    </div>
								    <button style="margin-left:0px;height:20px;width:50px;">上传
							    		<input type="file" name="goodsColorImage" onchange="changePic(this,'goodsColorImage')" style="position:relative;bottom:20px;right:10px;height:20px;width:45px; opacity: 0;">
							    	</button>
						    	</td>
				    		</tr>
				    		
				    		<tr>
				    			<td style="display:none;">
				    				<input id="sectionTwoValue" class="easyui-textbox" type="hidden" name="sectionTwoValue"/>
				    			</td>
				    			<td>类别属性二(值):</td>
				    			<td>
				    				<input id="sectionTwoValueNew" class="easyui-textbox" type="text" name="sectionTwoValueNew"/>
				    			</td>
				    			<td style="display:none;">
				    				<input id="sectionThreeValue" class="easyui-textbox" type="hidden" name="sectionThreeValue"/>
				    			</td>
				    			<td>类别属性三(值):</td>
				    			<td>
				    				<input id="sectionThreeValueNew" class="easyui-textbox" type="text" name="sectionThreeValueNew"/>
				    			</td>
				    		</tr>
				    		
				    		<tr>
				    			<td>商品单价:</td>
				    			<td>
				    				<input id="goodsPrice" class="easyui-textbox" type="text" name="goodsPrice"/>
				    			</td>
				    			<td>库存量:</td>
				    			<td>
				    				<input id="goodsAmount" class="easyui-textbox" type="text" name="goodsAmount"/>
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
		
		<div id="win1" class="easyui-dialog" style="width:300px;height:250px"
			data-options="title:'请输入理由',buttons:'#bb',modal:true,closed:true">
			<div style="margin:20px 0px 0px 20px;width:250px;height:100px">
				<input id="failureReason" class="easyui-textbox" data-options="multiline:true" value="请输入理由..." style="width:100%;height:100%;">
			</div>
			
		</div>
		<div id="bb">
			<a href="javascript:subUnPass()" class="easyui-linkbutton">Save</a>
			<a href="javascript:close()" class="easyui-linkbutton">Close</a>
		</div>
	</body>
	<script type="text/javascript">
	$('#formTable').datagrid({
	    url:'/ezsh/userA/selectD?userId=${userId}',
	    columns:[[
	    	{field:'uMsId',hidden:'true',title:'uMsId',width:100,align:'center'},
			{field:'identifyClassify',title:'身份类型',width:100,align:'center'},
			{field:'pManagerId',title:'管理处ID',width:100,align:'center'},
			{field:'managerName',title:'管理处名称',width:80,align:'center'},
			{field:'pBuildId',title:'楼宇ID',width:100,align:'center'},
			{field:'pRoomId',title:'房间ID',width:100,align:'center'},		
			{field:'privilege',title:'开门权限',width:100,align:'center'},
			{field:'registerName',title:'登记名',width:100,align:'center'},
			{field:'registerTelephone',title:'登记电话',width:100,align:'center'},
	    ]]
	});
	
	function edit(){
		var row = $('#formTable').datagrid('getSelected');
		if (row){
			$('#win').window('open');
				$('#formInfo').form('load',{
					goodsId: row.goodsId,
					pGoodsInfoId: row.pGoodsInfoId,
					sectionOneValue: row.sectionOneValue,
					sectionOneValueNew: row.sectionOneValue,
					sectionTwoValue: row.sectionTwoValue,
					sectionTwoValueNew: row.sectionTwoValue,
					sectionThreeValue: row.sectionThreeValue,
					sectionThreeValueNew: row.sectionThreeValue,
					goodsPrice: row.goodsPrice,
					goodsAmount: row.goodsAmount,
				});
				$("#goodsColorImage").attr("src",row.sectionOneImage);
		}else{
			alert("请至少选中一条数据！");
		}
	}
	
	/**
	 * @author qwc
	 * 2017年10月23日下午7:45:47 
	 * void 提交编辑更新
	 */
	/* function sub() {
		$.messager.progress();	
		$('#formInfo').form('submit', {
			url: "/ezsh/goodsAd/updateDetail",
			onSubmit: function(param) {
			},
			success: function(data) {
				$.messager.progress('close');
				var data = eval('(' + data + ')'); 
				if(data.status == 1) {
					$.messager.alert('提示', data.message, 'info');
				} else {
					$.messager.alert('提示', data.message, 
					function(){});
				}
			}
		});
	}
	
	function changePic(file,imgPreviewId){
		if(file.files!=null){
			var img = document.getElementById(imgPreviewId); 
			var reader = new FileReader();
		    //读取File对象的数据  
		    reader.onload = function(evt){  
		        img.width  =  "50";  
		        img.height =  "50";
		        position = "relative";
		        img.src = evt.target.result;  
		    }  
		    reader.readAsDataURL(file.files[0]);
		}	 
	} */
		
	function back(){
		window.history.back();
	}
	
	function pass(){
		var row = $('#formTable').datagrid('getSelected');
		if (row) {
			$.messager.confirm('提示','确定通过审核?',function(r){
			    if (r){
					$.ajax({
					    type: "GET",
					    url:"/ezsh/userA/check",
					    data:{uMsId:row.uMsId,checkStatus:1},
					    dataType:"json",
					    success: function(data){
						    if(data.status>0){
						    	$.messager.alert('提示', data.message, 'info');
						    }else{
						    	$.messager.alert('提示', data.message, 'info');
						    }
						    $('#formTable').datagrid('reload');
						}
					})
			    }
			});
		} else {
			$.messager.alert('Warning','至少选择一条数据');
		}
	}
	
	function failure(){
		var row = $('#formTable').datagrid('getSelected');
		if (row) {
			$('#win1').window('open');
		} else {
			$.messager.alert('Warning','至少选择一条数据');
		}
	}
	
	function subUnPass(){
		alert(1);
		var row = $('#formTable').datagrid('getSelected');
		$.ajax({
		    type: "GET",
		    url:"/ezsh/userA/check",
		    data:{uMsId:row.uMsId,checkStatus:0,failureInfo:$('#failureReason').textbox('getValue')},
		    dataType:"json",
		    success: function(data){
			    if(data.status>0){
			    	$.messager.alert('提示', data.message, 'info');
			    }else{
			    	$.messager.alert('提示', data.message, 'info');
			    }
			    $('#formTable').datagrid('reload');
			    $('#win1').window('close');
			}
		})
	}
	
	function close(){
		$('#win1').window('close');
	}	
	</script>
</html>
