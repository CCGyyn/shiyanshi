<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/common/header.jsp" %>
<!DOCTYPE html>
<html>
	<head> 
	<title>商品添加</title>
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
			title:'商品添加',
			iconCls:'icon-add',
			fit:true
			">
			<form id="forminfo" method="post" enctype="multipart/form-data">
				<table cellpadding="5" class="tableStyle">
					<tr>
						<td>管理处:</td>
						<td>
							<input  id="pManagerId" class="easyui-combobox" name="pManagerId" style="width:100px" 
                       				data-options="valueField:'managerId',
                       				required:true,
                       				editable:false,
                       				 panelHeight: 80,
                       				textField:'managerName',
                       				url:'/ezsh/build/findManager',
                       				onSelect: function(rec){
                         				$('#pMerchantId').combobox('clear');	
									    var url = '/ezsh/merchantA/selectSimple?pManagerId='+rec.managerId;
									    $('#pMerchantId').combobox('reload', url);
								    }
                       				"/>
						</td>
						<td>商家:</td>
						<td>
							<input  id="pMerchantId" class="easyui-combobox" name="pMerchantId" style="width:100px" 
                       				data-options="valueField:'merchantId',
                       				required:true,
                       				editable:false,
                       				textField:'merchantName',
                       				url:'/ezsh/merchantA/selectSimple',
                       				"/>
						</td>
						<td>商品展示类型:</td>
						<td>
							<select id="goods_show_classfy" class="easyui-combobox" name="goodsShowClassfy" data-options="editable:false" panelHeight="auto" style="width:100px" onchange="change(this)">
								<option value="1">普通展示</option>
								<option value="2">每日推荐</option>
								<option value="3">活动优惠</option>
							</select>
						</td>
						
						<td>商品分类</td>
						<td>
							<input  id="pGoodsClassfyIdValue" class="easyui-textbox" readonly="readonly" data-options="required:true" style="width:100px"/>
							<input  id="pGoodsClassfyId" name="pGoodsClassfyId" style="display:none;" data-options="required:true" style="width:100px"/>
						</td>
					</tr>
					<tr>
		    			<td>商品名:</td>
		    			<td colspan="3">
		    				<input class="easyui-textbox" type="text" name="goodsName" style="width:100%;height:28px" data-options="required:true"/>
		    			</td>
		    		</tr>
		    		
		    		<tr>
		    			<td>运费:</td>
		    			<td>
		    				<input type="text" class="easyui-numberbox"  name="goodsCarriage" style="height:28px" data-options="required:true,min:0,precision:2"/>
		    			</td>
		    			<td>配送方式:</td>
		    			<td>
		    				<select id="cc" class="easyui-combobox" data-options="editable:false" name="goodsDistribution" style="height:28px;width:100%;">
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
								    <img id="goodsSlideShow1">
							    </div>
							    <button style="margin-left:20px;height:25px;width:50px;">上传
							    <input  type="file" name="goodsSlideShowFiles" onchange="changePic(this,'goodsSlideShow1')" style="position:relative;right:10px;bottom:15px;height:35px;width:45px; opacity: 0;">
							    </button>
						    </td>
						    <td>
					    		<div style="border:1px solid #ccc;height:100px;width:100px;">
								    <img id="goodsSlideShow2">
							    </div>
							    <button style="margin-left:20px;height:25px;width:50px;">上传
							    <input  type="file" name="goodsSlideShowFiles" onchange="changePic(this,'goodsSlideShow2')" style="position:relative;right:10px;bottom:15px;height:35px;width:45px; opacity: 0;">
							    </button>
						    </td>
						    <td>
					    		<div style="border:1px solid #ccc;height:100px;width:100px;">
								    <img id="goodsSlideShow3">
							    </div>
							    <button style="margin-left:20px;height:25px;width:50px;">上传
							    <input type="file" name="goodsSlideShowFiles" onchange="changePic(this,'goodsSlideShow3')" style="position:relative;right:10px;bottom:15px;height:35px;width:45px; opacity: 0;">
							    </button>
						    </td> 
						    <td>
					    		<div style="border:1px solid #ccc;height:100px;width:100px;">
								    <img id="goodsSlideShow4">
							    </div>
							    <button style="margin-left:20px;height:25px;width:50px;">上传
							    <input type="file" name="goodsSlideShowFiles" onchange="changePic(this,'goodsSlideShow4')" style="position:relative;right:10px;bottom:15px;height:35px;width:45px; opacity: 0;">
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
									<input class="easyui-textbox" style="width:80%;height:30px;padding:12px" name="showSectionOneValue" data-options="prompt:'附加标题一',required:false">
								</div>
								<div style="margin-bottom:10px">
						    		<span>标题二</span>
									<input class="easyui-textbox" style="width:80%;height:30px;padding:12px" name="showSectionTwoValue" data-options="prompt:'附加标题二',required:false">
								</div>
								<div style="margin-bottom:10px">
						    		<span>标题三</span>
									<input class="easyui-textbox" style="width:80%;height:30px;padding:12px" name="showSectionThreeValue" data-options="prompt:'附加标题三',required:false">
								</div>
						    </td>     			    	    
					    </tr>
				    </table>
		    		<table class="tableStyle">	    		
		    		<tr>
		    			<td>类型属性一:</td>
		    			<td>
		    				<input id="section_one_name" type="checkbox">
		    				<input id="sectionOneName" readonly="readonly" type="text" value="颜色分类" style="height:28px" data-options="required:true"/>
		    			</td>
		    			
		    			<td>类型属性二:</td>
		    			<td>
		    				<input id="section_two_name" type="checkbox">
		    				<input id="sectionTwoName"  type="text"  style="height:28px"/>
		    			</td>
		    			
		    			<td>类型属性三:</td>
		    			<td>
		    				<input id="section_three_name" type="checkbox">
		    				<input id="sectionThreeName" type="text"  style="height:28px"/>
		    			</td>	
		    		</tr>
		    		
		    		<tr>
		    			<td>属性一值:</td>
		    			<td id="section_one_value">
		    				<div>
		    					<input type="checkbox" class="section_one_value" onclick="section_value_select(this)">
		    					<input class="sectionOneValue" type="text"  onchange="changeText(0,1)" data-options="required:true" style="height:28px;width:35%"/>
		    					<input class="goodsColorImage1" type="file" style="width:45%">
		    				</div>
		    				<div>
		    					<input type="checkbox" class="section_one_value" onclick="section_value_select(this)">
		    					<input class="sectionOneValue" type="text"  onchange="changeText(1,1)" data-options="required:true" style="height:28px;width:35%"/>
		    					<input class="goodsColorImage2" type="file" style="width:45%">
		    				</div>
		    				<div>
		    					<input type="checkbox" class="section_one_value" onclick="section_value_select(this)">
		    					<input class="sectionOneValue" type="text"  onchange="changeText(2,1)" data-options="required:true" style="height:28px;width:35%"/>
		    					<input class="goodsColorImage3" type="file"  style="width:45%">
		    				</div>
		    				<div>
		    					<input type="checkbox" class="section_one_value" onclick="section_value_select(this)">
		    					<input class="sectionOneValue" type="text"  onchange="changeText(3,1)" data-options="required:true"  style="height:28px;width:35%"/>
		    					<input class="goodsColorImage4" type="file"  style="width:45%">
		    				</div>
		    				<div>
		    					<input type="checkbox" class="section_one_value" onclick="section_value_select(this)">
		    					<input class="sectionOneValue" type="text"  onchange="changeText(4,1)" data-options="required:true"  style="height:28px;width:35%"/>
		    					<input class="goodsColorImage5" type="file" style="width:45%">
		    				</div>	    			
		    			</td>
		    			
		    			<td>属性二值:</td>
		    			<td id="section_two_value">
		    				<div>
		    					<input type="checkbox" class="section_two_value" onclick="section_value_select(this)">
		    					<input type="text"  onchange="changeText(0,2)" style="height:28px"/>
		    				</div>
		    				<div>
		    					<input type="checkbox" class="section_two_value" onclick="section_value_select(this)">
		    					<input type="text"  onchange="changeText(1,2)" style="height:28px"/>
		    				</div>
		    				<div>
		    					<input type="checkbox" class="section_two_value" onclick="section_value_select(this)">
		    					<input type="text"  onchange="changeText(2,2)" style="height:28px"/>
		    				</div>
		    				<div>
		    					<input type="checkbox" class="section_two_value" onclick="section_value_select(this)">
		    					<input type="text"  onchange="changeText(3,2)" style="height:28px"/>
		    				</div>
		    				<div>
		    					<input type="checkbox" class="section_two_value" onclick="section_value_select(this)">
		    					<input type="text"  onchange="changeText(4,2)" style="height:28px"/>
		    				</div>	
		    			</td>
		    			
		    			<td>属性三值:</td>
		    			<td id="section_three_value">
		    				<div>
		    					<input type="checkbox" class="section_three_value" onclick="section_value_select(this)">
		    					<input type="text" onchange="changeText(0,3)" style="height:28px"/>
		    				</div>
		    				<div>
		    					<input type="checkbox" class="section_three_value" onclick="section_value_select(this)">
		    					<input type="text" onchange="changeText(1,3)" style="height:28px"/>
		    				</div>
		    				<div>
		    					<input type="checkbox" class="section_three_value" onclick="section_value_select(this)">
		    					<input type="text" onchange="changeText(2,3)" style="height:28px"/>
		    				</div>
		    				<div>
		    					<input type="checkbox" class="section_three_value" onclick="section_value_select(this)">
		    					<input type="text" onchange="changeText(3,3)" style="height:28px"/>
		    				</div>
		    				<div>
		    					<input type="checkbox" class="section_three_value" onclick="section_value_select(this)">
		    					<input type="text" onchange="changeText(4,3)" style="height:28px"/>
		    				</div>
		    			</td>
		    		</tr>	    		
				</table>
				
				<table  style="border:1px solid #ccc;margin:10px 0px 10px 0px;" cellpadding="5" class="table-b">
					<thead>
					    <tr>
						    <th id="section_one_value_head"></th>
						    <th id="section_two_value_head"></th>
						    <th id="section_three_value_head"></th>
						    <!-- <th class="section_four_value_head">four</th>
						    <th class="section_five_value_head">five</th> -->
						    <th id="section_five_value_head">price(单价)</th>
						    <th id="section_five_value_head">amount(库存)</th>
					    </tr>
					</thead>
					
					<tbody id="value_list">
			    		<tr>
			    			<td>
			    				<input type="text" class="sectionOneValue" style="height:20px;width:80px;" disabled="disabled"/>
			    			</td>
			    			<td>
			    				<input type="text" style="height:20px;width:80px;" disabled="disabled"/>
			    			</td>
			    			<td>
			    				<input type="text" style="height:20px;width:80px;" disabled="disabled"/>
			    			</td>	
			    			<td>
			    				<input type="text" name="price"  class="easyui-numberbox" style="height:20px;width:100px;" data-options="required:true,min:0,precision:2"/>
			    			</td>
			    			<td>
			    				<input type="text" name="amount" class="easyui-numberbox" style="height:20px;width:100px;" data-options="required:true,min:0"/>
			    			</td>
			    		</tr>
		    		</tbody>
				</table>
				<br>
				<div><span style="font-size:14px;"><b>商品参数详情：</b></span></div>
				<div id="editor" style="width:600px;">
					<p>版型款式:</p><br>
			        <p>工艺/流行:</p><br>
			        <p>关键:</p><br>
			        <p>其他:</p>
			    </div>
			    <br>
			    <div><span style="font-size:14px;"><b>商品图文详情：</b></span></div>
				<div id="editorImgText" style="width:600px;">
			        <p>在此处编辑商品的图文详情！</p>
			    </div>
			</form>
			<div style="text-align:center;padding:5px">
		    	<a href="javascript:void(0)" class="easyui-linkbutton" style="width:60px;height:30px;" onclick="submitForm()">提交</a>
		    	<a href="javascript:void(0)" class="easyui-linkbutton" style="width:60px;height:30px;" onclick="clearForm()">取消</a>
	    	</div>
		</div>
		
	</body>
	<script type="text/javascript" src="/ezsh/js/shopping-mall/shopClassify.js"></script>
	<script type="text/javascript">
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
			        img.width  =  "100";  
			        img.height =  "100";  
			        img.src = evt.target.result;  
			    }  
			    reader.readAsDataURL(file.files[0]);
			}	 
		}
	</script>
	<script type="text/javascript" src="/ezsh/js/shopping-mall/addShop.js"></script>
	<script type="text/javascript" src="/ezsh/wang-Editor/wangEditor.min.js"></script>
    <script type="text/javascript">
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
        var wImgText = window.wangEditor;
        var editorImgText = new wImgText('#editorImgText');
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
	<script type="text/javascript" src="/ezsh/js/shopping-mall/submit.js"></script>
</html>