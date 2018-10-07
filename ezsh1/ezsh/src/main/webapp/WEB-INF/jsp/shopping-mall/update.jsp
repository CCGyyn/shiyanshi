<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%-- <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> --%>
<%@ include file="/common/header.jsp" %>
<!DOCTYPE html>
<html>
	<head> 
	<title>商品基本信息</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">    
		<meta http-equiv="description" content="商品添加">
		<style type="text/css">
			.tableStyle{
				font-size:12px;
				/* padding:10% 0px 0px 10%; */
			}
			.table-b td,th{
				font-size:12px;
				border:1px solid #ccc;
			}
			.section_one_value td{
				border:1px solid #ccc;
			}
		</style>
	</head>
	
	<body class="easyui-layout">
		<div data-options="region:'west',title:'商品类型选择',split:true" style="width:150px;">
			<ul id="tree"></ul>
		</div>
		
		<div style="padding:10px 0px 100px 10px" data-options="region:'center',
			title:'商品基本信息编辑',
			iconCls:'icon-edit',
			fit:true
			">
			<form id="forminfo" method="post" enctype="multipart/form-data">
				<table cellpadding="5" class="tableStyle">
					<tr>
						<td style="display:none;">
							<input name="goodsInfoId" value="${goodsInfo.goodsInfoId}">
						</td>
						<td>管理处:</td>
						<td>
							<input  id="pManagerId" class="easyui-combobox" name="pManagerId" style="width:100px" 
                       				data-options="valueField:'managerId',
                       				required:true,textField:'managerName',
                       				 panelHeight: 80,
                       				url:'/ezsh/build/findManager',
                       				"/>
						</td>
						
						<td>商品展示类型:</td>
						<td>
							<select id="goods_show_classfy" class="easyui-combobox" panelHeight="auto" style="width:100px" onchange="change(this)">
								<option value="1">普通展示</option>
								<option value="2">每日推荐</option>
								<option value="3">活动优惠</option>
							</select>
						</td>
						
						<td>商品分类</td>
						<td>
							<input  id="pGoodsClassfyIdValue" class="easyui-textbox" value="${goodsInfo.goodsClassfyInfo.classfyName}" readonly="readonly" data-options="required:true" style="width:100px"/>
							<input  id="pGoodsClassfyId" name="pGoodsClassfyId" value="${goodsInfo.goodsClassfyInfo.classfyId}" style="display:none;" data-options="required:true" style="width:100px"/>
						</td>
					</tr>
					<tr>
		    			<td>商品名:</td>
		    			<td colspan="3">
		    				<input class="easyui-textbox" type="text" name="goodsName" value="${goodsInfo.goodsName}" style="width:100%;height:28px" data-options="required:true"/>
		    			</td>
		    		</tr>
		    		
		    		<tr>
		    			<td>运费:</td>
		    			<td>
		    				<input class="easyui-textbox" type="text" name="goodsCarriage" value="${goodsInfo.goodsCarriage}" style="height:28px" data-options="required:true"/>
		    			</td>
		    			<td>配送方式:</td>
		    			<td>
		    				<select id="goodsDistribution" class="easyui-combobox" name="goodsDistribution"  style="height:28px;width:100%;">
							    <option value="1">人工配送</option>
							    <option value="2">快递</option>
							</select>
		    			</td>
		    		</tr>
		    		</table>
		    		<table class="tableStyle">
			    		<tr>
			    			<td>商品（轮播图）</td>
			    			<td></td>
			    			<td></td>
			    			<td></td>
			    			<td></td>
			    			<td class="discountsBgImage" style="visibility:hidden;">优惠商品（展示背景图）</td>
			    			<td class="discountsBgImage" style="visibility:hidden">附加标题</td>
			    		</tr>
			    		<tr>
				    		<td>
					    		<div style="border:1px solid #ccc;height:100px;width:100px;">
								    <img id="goodsSlideShow1" style="height:100%;width:100%;">
								    <input type="hidden" id="goodsSlideShowHidden1" name="goodsSlideShowHidden1">
							    </div>
							    <button style="margin-left:20px;height:25px;width:50px;">上传
							    <input  type="file" name="goodsSlideShowFile1" onchange="changePic(this,'goodsSlideShow1')" style="position:relative;right:10px;bottom:15px;height:35px;width:45px; opacity: 0;">
							    </button>
						    </td>
						    <td>
					    		<div style="border:1px solid #ccc;height:100px;width:100px;">
								    <img id="goodsSlideShow2" style="height:100%;width:100%;">
								    <input type="hidden" id="goodsSlideShowHidden2" name="goodsSlideShowHidden2">
							    </div>
							    <button style="margin-left:20px;height:25px;width:50px;">上传
							    <input  type="file" name="goodsSlideShowFile2" onchange="changePic(this,'goodsSlideShow2')" style="position:relative;right:10px;bottom:15px;height:35px;width:45px; opacity: 0;">
							    </button>
						    </td>
						    <td>
					    		<div style="border:1px solid #ccc;height:100px;width:100px;">
								    <img id="goodsSlideShow3" style="height:100%;width:100%;">
								    <input type="hidden" id="goodsSlideShowHidden3" name="goodsSlideShowHidden3">
							    </div>
							    <button style="margin-left:20px;height:25px;width:50px;">上传
							    <input type="file" name="goodsSlideShowFile3" onchange="changePic(this,'goodsSlideShow3')" style="position:relative;right:10px;bottom:15px;height:35px;width:45px; opacity: 0;">
							    </button>
						    </td> 
						    <td>
					    		<div style="border:1px solid #ccc;height:100px;width:100px;">
								    <img id="goodsSlideShow4" style="height:100%;width:100%;">
								    <input type="hidden" id="goodsSlideShowHidden4" name="goodsSlideShowHidden4">
							    </div>
							    <button style="margin-left:20px;height:25px;width:50px;">上传
							    <input type="file" name="goodsSlideShowFile4" onchange="changePic(this,'goodsSlideShow4')" style="position:relative;right:10px;bottom:15px;height:35px;width:45px; opacity: 0;">
							    </button>
						    </td>
						    <td></td>
						    <td class="discountsBgImage" style="visibility:hidden;">
					    		<div style="border:1px solid #ccc;height:100px;width:100px;">
								    <img id="fileBackgroundImage">
							    </div>
							    <button style="margin-left:20px;height:25px;width:50px;">上传
							    	<input type="file" name="fileBackgroundImage" onchange="changePic(this,'fileBackgroundImage')" style="position:relative;right:10px;bottom:15px;height:35px;width:45px; opacity: 0;">
							    </button>
						    </td>
						    <td class="discountsBgImage" style="visibility:hidden">
						    	<div style="margin-bottom:10px">
						    		<span>标题一</span>
									<input class="easyui-textbox" style="width:80%;height:30px;padding:12px" data-options="prompt:'附加标题一',required:false">
								</div>
								<div style="margin-bottom:10px">
						    		<span>标题二</span>
									<input class="easyui-textbox" style="width:80%;height:30px;padding:12px" data-options="prompt:'附加标题二',required:false">
								</div>
								<div style="margin-bottom:10px">
						    		<span>标题三</span>
									<input class="easyui-textbox" style="width:80%;height:30px;padding:12px" data-options="prompt:'附加标题三',required:false">
								</div>
						    </td>     			    	    
					    </tr>
				    </table>
		    		<table class="tableStyle">	    		
		    		<tr>
		    			<c:if test="${goodsInfo.sectionOneName!=null}">
		    				<td>类型属性一:</td>
			    			<td>		
	    						<input id="section_one_name" type="checkbox" checked="checked">
	    						<input id="sectionOneName" disabled="disabled" type="text" name="sectionOneName" value="颜色分类" required="required" style="height:28px" data-options="required:true"/>
			    			</td>
		    			</c:if>
		    			
		    			<c:if test="${goodsInfo.sectionTwoName!=null}">
		    				<td>类型属性二:</td>
			    			<td>
	    						<input id="section_two_name" type="checkbox" checked="checked">
	    						<input id="sectionTwoName"  class="easyui-validatebox" type="text" name="sectionTwoName" value="${goodsInfo.sectionTwoName}" class="easyui-validatebox"required="required" style="height:28px"/> 				
			    			</td>
		    			</c:if>
		    			
		    			<c:if test="${goodsInfo.sectionThreeName!=null}">
		    				<td>类型属性三:</td>
			    			<td>	
			    				<input id="section_three_name" type="checkbox" checked="checked">
			    				<input id="sectionThreeName" class="easyui-validatebox" type="text" name="sectionThreeName" value="${goodsInfo.sectionThreeName}" required="required" style="height:28px"/>	 
			    			</td>
		    			</c:if>	
		    		</tr>	    		
				</table>
				
				<br>
				<div><span style="font-size:14px;"><b>商品参数详情：</b></span></div>
				<div id="editor" style="width:600px;">
					<!-- <p>版型款式:</p><br>
			        <p>工艺/流行:</p><br>
			        <p>关键:</p><br>
			        <p>其他:</p> -->
			        ${goodsInfo.goodsParameter}
			    </div>
			    <br>
			    <div><span style="font-size:14px;"><b>商品图文详情：</b></span></div>
				<div id="editorImgText" style="width:600px;">
			        <!-- <p>在此处编辑商品的图文详情！</p>  -->
			        ${goodsInfo.goodsImageText} 
			    </div>
			    <input type="hidden" name="goodsSlideShow" value="${goodsInfo.goodsSlideShow}">
			</form>
			<div style="text-align:center;padding:5px">
		    	<a href="javascript:void(0)" class="easyui-linkbutton" style="width:60px;height:30px;" onclick="submitForm()">提交</a>
		    	<a href="javascript:void(0)" class="easyui-linkbutton" style="width:60px;height:30px;" onclick="clearForm()">取消</a>
	    	</div>
		</div>
		<!-- <div>
			<form id="forminfo1" method="post" enctype="multipart/form-data">
			</form>
		</div> -->
	</body>
	<script type="text/javascript" src="/ezsh/js/shopping-mall/shopClassify.js"></script>
	<script type="text/javascript" src="/ezsh/wang-Editor/wangEditor.min.js"></script>
    <script type="text/javascript">
    	//第一个富文本编辑器
        var E = window.wangEditor;
        var editor = new E('#editor');
        editor.customConfig.menus = [
            'head',
            'bold',
            'italic',
            'underline',
        ];
        editor.create();
    </script>
    <script type="text/javascript">
  		//第二个富文本编辑器
        var wImgText = window.wangEditor;
        var editorImgText = new E('#editorImgText');
        editorImgText.customConfig.uploadImgShowBase64 = true;
        editorImgText.customConfig.showLinkImg = false;//隐藏网络图片
        editorImgText.customConfig.uploadFileName = 'goodsImageTextFiles';
        editorImgText.customConfig.menus = [
            'head',
            'bold',
            'italic',
            'underline',
            'image',  // 插入图片
        ];
        editorImgText.create();
    </script>
	<script type="text/javascript">
	$(function(){
		$("#pManagerId").combobox('setValue',${goodsInfo.pManagerId});
		$("#goods_show_classfy").combobox('setValue',${goodsInfo.goodsShowClassfy});
		$("#goodsDistribution").combobox('setValue',${goodsInfo.goodsDistribution});//初步阶段配置方式
		
		var imgUrl="${goodsInfo.goodsSlideShow}";//图片轮播图
		var strs= new Array(); //定义一数组
		strs=imgUrl.split(",");
		for(var i=0;i<strs.length;i++){
			alert(strs[i]);
			$("#goodsSlideShowHidden"+(i+1)).val(strs[i]);
			$("#goodsSlideShow"+(i+1)).attr('src',strs[i]);
		}	
		
	})
	
	$('#goods_show_classfy').combobox({
		onSelect: function(record){
			if(record.value==3){
				$(".discountsBgImage").find("input").each(function(){ 
				    $(this).prop("required","required");
				    $(this).validatebox({
				        required: true
				    });
				});
				$(".discountsBgImage").each(function(){ 
				    $(this).css("visibility","visible");
				    
				});
			}else{
				$(".discountsBgImage").find("input").each(function(){ 
				    $(this).prop("required","required");
				    $(this).validatebox({
				        required: false
				    });
				});
				$(".discountsBgImage").each(function(){ 
				    $(this).css("visibility","hidden");
				});
			}
		}
	});
		
	$(".goodsSlideShow").filebox({
		buttonText:'选取图片'
	})
	
	function changePic(file,imgPreviewId){
		if(file.files!=null){
			var img = document.getElementById(imgPreviewId); 
			var reader = new FileReader();
		    //读取File对象的数据  
		    reader.onload = function(evt){  
		        //data:img base64 编码数据显示  
		        img.width  =  "100";  
		        img.height =  "100";  
		        img.src = evt.target.result;  
		    }  
		    reader.readAsDataURL(file.files[0]);
		}
		var formdata=new FormData($('#forminfo1')[0]);
	}
	</script>	
	<script type="text/javascript" src="/ezsh/js/shopping-mall/update.js"></script>
</html>