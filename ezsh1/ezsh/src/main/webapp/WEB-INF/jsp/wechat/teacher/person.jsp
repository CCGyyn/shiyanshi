<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<title>修改信息</title>
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate" />
		<link rel="stylesheet" href="/ezsh/font-awesome-4.7.0/css/font-awesome.min.css"/>    
		<link rel="stylesheet" href="/ezsh/assets/css/amazeui.min.css"/>
		<link rel="stylesheet" href="/ezsh/wechat/menu-icon/iconfont.css">  
		<link rel="stylesheet" href="/ezsh/wechat/up-head-img/css/amazeui.cropper.css"> 
		<link rel="stylesheet" href="/ezsh/wechat/up-head-img/css/custom_up_img.css">   
		<style>
			.header {
			  text-align: center;
			}
			.header h1 {
			  font-size: 120%;
			  color: #333;
			  margin-top: 20px;
			  font-family:"STKaiti","Microsoft YaHei",微软雅黑,"MicrosoftJhengHei",华文细黑,STHeiti,MingLiu ;
			}
			.header p {
			  font-size: 14px;
			}
		
			.ezsh_center_nav_list span { 
				display: block; 
				width: 100%; 
				text-align: center; 
				padding-top: 5px; 
				font-size: 14px;
			}
			
			.am-divider {
			    height: 0;
			    margin: 0.5rem auto;
			    overflow: hidden;
			    clear: both;
			}
			.ezsh_change_info input{
				border: 1px solid #fff;
			}
			.ezsh_change_info div>span{
				vertical-align: middle;
				margin-left:10px;
			}
		</style>
	</head>
  	<header data-am-widget="header" class="am-header am-header-default">
		<div class="am-header-left am-header-nav">
		    <a href="javascript:back()" class="">
		          <i class="am-header-icon am-icon-chevron-left"></i>
		    </a>
		</div>
		
		<h1 class="am-header-title">
		    <a href="#title-link" class="">
		      	修改信息
		    </a>
		</h1>
		
		<!-- <div class="am-header-right am-header-nav">
		    <a href="#right-link" class="">
		          <i class="am-header-icon am-icon-bars"></i>
		    </a>
		</div> -->
	</header>
	<body>
	<div class="am-panel am-panel-default" style="margin-bottom:0px;">
    	<div id="up-img-touch" class="am-panel-bd am-center" style="width:100px;">
    		<img  class="am-circle am-center" style="margin:0px auto;width:80px;height:80px;" src="${teacher.teacherIcon}"/>
    	</div>
	</div>
	
	<!-- <div><a style="text-align: center; display: block;"  id="pic"></a></div> -->
	<c:choose>
		<c:when test="${teacher.completeStatus==0}">
			<div><span style="text-align: center;color:red; display: block;">资料未完善</span></div>
		</c:when>
		<c:when test="${teacher.completeStatus==1 && teacher.checkStatus==0}">
			<div><span style="text-align: center;color: green; display: block;">待审核</span></div>
		</c:when>
		<c:when test="${teacher.completeStatus==1 && teacher.checkStatus==2}">
			<div>
				<span style="text-align: center;color: red; display: block;">(审核失败)${teacher.failureReason}</span>
			</div>
		</c:when>
		<c:otherwise>
			<div><span style="text-align: center; display: block;">--欢迎--</span></div>
		</c:otherwise>
	</c:choose>
	
	<div class="am-modal am-modal-no-btn" tabindex="0" z-index="" id="doc-modal-1">
	  <div class="am-modal-dialog">
	    <div class="am-modal-hd">
	    	<label>选择图片</label>
	      <a href="javascript: void(0)" class="am-close am-close-spin" data-am-modal-close>&times;</a>
	    </div>
	    <div class="am-modal-bd">
			<div class="am-form-group am-form-file">
			  <div class="am-fl">
				<button type="button" class="am-btn am-btn-default am-btn-sm">
				  <i class="am-icon-cloud-upload"></i> 选择要上传的文件</button>
			  </div>
			  <input type="file" id="inputImage">
			</div>
			<div class="am-g am-fl" style="width:280px;height:200px;">
				<div class="up-pre-before up-frame-radius">
					<img style="width:280px;height:200px;" alt="" src="" id="image" >
				</div>
				<!-- <div class="up-pre-after up-frame-radius">
				</div> -->
			</div>
			<div class="am-g am-fl">
				<div class="up-control-btns">
					<span class="am-icon-rotate-left am-icon-sm" style="margint:5px 5px 0px 0px;" onclick="rotateimgleft()"></span>
					<span class="am-icon-rotate-right am-icon-sm" style="margin:5px 5px 0px 0px;" onclick="rotateimgright()"></span>
					<span class="am-icon-check am-icon-sm"  style="margin:5px 0px 0px 0px;" id="up-btn-ok" url="/ezsh/teacherW/updateIcon"></span>
				</div>
			</div>
	    </div>
	  </div>
	</div>
	
	<div class="ezsh_change_info" style="margin-bottom:20%;">
		<form action="##" id="formInfo" class="am-form" data-am-validator>
		<input type="hidden" id="completeStatus" value="${teacher.completeStatus}">
		<input type="hidden" id="checkStatus" name="checkStatus" value="${teacher.checkStatus}">
		<input type="hidden" id="teacherId" name="teacherId" value="${teacher.teacherId}">
		<hr data-am-widget="divider" style="" class="am-divider am-divider-default" />
		<div>
			<span><i style="color:#00BFFF;font-size:20px;line-height:20px;" class="iconfont icon-xingming"></i>姓名:</span>
			<input type="text" id="teacherName" value="${teacher.teacherName}" name="teacherName" style="width:60%;display:inline-block;border:1px solid rgba(255,255,255,.15);" class="am-form-field" placeholder="你的大名" required>
		</div>
	
		<hr data-am-widget="divider" style="" class="am-divider am-divider-default" />
		<div id="teacherSex" class="am-form-group">
			<span><i style="color:#00BFFF;font-size:20px;line-height:20px;" class="iconfont icon-xingbie"></i>性别:</span>
			<c:choose>
				<c:when test="${teacher.teacherSex == 1}">
					<label class="am-radio-inline">
						<input type="radio" checked="checked" value="1" name="teacherSex" required> 男
					</label>
					<label class="am-radio-inline">
						<input type="radio" value="2" name="teacherSex" required> 女
					</label>
				</c:when>
				<c:when test="${teacher.teacherSex == 2}">
					<label class="am-radio-inline">
						<input type="radio"  value="1" name="teacherSex" required> 男
					</label>
					<label class="am-radio-inline">
						<input type="radio" checked="checked" value="2" name="teacherSex" required> 女
					</label>
				</c:when>
				<c:otherwise>
					<label class="am-radio-inline">
						<input type="radio"  value="1" name="teacherSex" required> 男
					</label>
					<label class="am-radio-inline">
						<input type="radio" value="2" name="teacherSex" required> 女
					</label>
				</c:otherwise>
			</c:choose>
		</div>
	
		<hr data-am-widget="divider" style="" class="am-divider am-divider-default"/>
		<div>
			<span><i style="color:#00BFFF;font-size:20px;line-height:20px;" class="iconfont icon-nicheng1"></i>昵称:</span>
			<input type="text" style="width:60%;display:inline-block;border:1px solid rgba(255,255,255,.15);" name="teacherNickName" value="${teacher.teacherNickName}" class="am-form-field" placeholder="昵称" required>
		</div>
	
		<hr data-am-widget="divider" style="" class="am-divider am-divider-default"/>
		<div>
			<span><i style="color:#00BFFF;font-size:20px;line-height:20px;" class="iconfont icon-lianxihaoma"></i>联系方式:</span>
			<input type="text" style="width:60%;display:inline-block;border:1px solid rgba(255,255,255,.15);" name="teacherTelephone" value="${teacher.teacherTelephone}" class="am-form-field" placeholder="联系方式" required>
		</div>
	
		<hr data-am-widget="divider" style="" class="am-divider am-divider-default" />
		<div>
			<span><i style="color:#00BFFF;font-size:20px;line-height:20px;" class="iconfont icon-dizhi"></i>地址:</span>
			<div id="teacherPlace" style="width:78%;display:inline-block;">
				<select id="s_province" name="teacherProvince" style="border-color:1px solid rgba(255,255,255,.15);" data-am-selected="{dropUp: 1,maxHeight: 200,btnWidth: '32%'}" required>
					<option value="a">Apple</option>
					<option value="b">Banana</option>
					<option value="o">Orange</option>
				</select>
				<select id="s_city" name="teacherCity" style="border-color:1px solid rgba(255,255,255,.15);" data-am-selected="{dropUp: 1,maxHeight: 200,btnWidth: '32%'}" required> 
					<option value="a">Apple</option>
					<option value="b">Banana</option>
					<option value="o">Orange</option>
				</select>
				<select id="s_county" name="teacherDistrict" style="border-color:1px solid rgba(255,255,255,.15);" data-am-selected="{dropUp: 1,maxHeight: 200,btnWidth: '32%'}" required>
					<option value="a">Apple</option>
					<option value="b">Banana</option>
					<option value="o">Orange</option>
				</select>
			</div>
		</div>
		
		<hr data-am-widget="divider" style="" class="am-divider am-divider-default" />
		<div id="up-img-touch-student-card"  style="margin:0px 0px 10px 10px;width:90%;">
    		<img  class="am-img-thumbnail" id="student_card" style="margin:0px auto;width:40%;height:100px;" src="${teacher.studentCard}"/>
    		<div class="am-form-group am-form-file" style="display:inline-block;">
				<c:choose>
					<c:when test="${teacher.checkStatus==2||teacher.completeStatus==0}">
						<i class="am-icon-cloud-upload"></i> 上传学生证
						<input type="file" name="studentCardFile" onchange="changePic(this,'student_card')">
					</c:when>
					<c:otherwise>
						<i class="fa-id-card"></i> 学生证
					</c:otherwise>
				</c:choose>
			</div>
    	</div>
    	
		<button id="submit" class="am-btn am-btn-secondary am-center" style="margin-top:5%;width:90%;" type="button">提交修改</button>
		</form>
	</div>

	<script src="/ezsh/js/jquery-3.2.1.min.js"></script>
	<script src="/ezsh/assets/js/amazeui.min.js"></script>
	<script src="/ezsh/assets/js/app.js"></script>
	<script class="resources library" src="/ezsh/js/area.js" type="text/javascript"></script>
    <script type="text/javascript">_init_area();</script>
    <script src="/ezsh/js/dist/amazeui.dialog.min.js" charset="utf-8"></script>
    <script src="/ezsh/wechat/up-head-img/js/cropper.min.js" charset="utf-8"></script>
    <script src="/ezsh/wechat/up-head-img/js/custom_up_img.js" charset="utf-8"></script>
    <script type="text/javascript">
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
    
    $("#submit").click(function(){
    	if($('#formInfo').validator('isFormValid')){
    		var formData = new FormData(document.getElementById("formInfo"));
    		$.ajax({
    		    type: "POST",
    		    url:"/ezsh/teacherW/update",
    		    data: formData,
                processData: false,
                contentType: false,
                dataType:"json",
    		    success: function(data) {
    		    	console.log(data.status);
    		    	AMUI.dialog.alert({
					     title: '提示',
					     content: data.message,
					     onConfirm: function() {
					     console.log('close');
					     }
					});
    		   }
    		})
    	 } else {
			AMUI.dialog.alert({
			     title: '提示',
			     content: '未填填完整',
			     onConfirm: function() {
			     console.log('close');
			     }
			});
    	 } 
    })
     
    </script>
    <script type="text/javascript">
	var Gid  = document.getElementById ;
	var showArea = function(){
		Gid('show').innerHTML = "<h3>省" + Gid('s_province').value + " - 市" + 	
		Gid('s_city').value + " - 县/区" + 
		Gid('s_county').value + "</h3>"
	}
	/* Gid('s_county').setAttribute('onchange','showArea()'); */
	</script>
	
	<script type="text/javascript">
	window.onload = function(){
		$("#s_province").find("option[value='${teacher.teacherProvince}']").attr("selected",true);
		 $("#s_province").trigger("change");
		 $("#s_city").find("option[value='${teacher.teacherCity}']").attr("selected",true);
		 $("#s_city").trigger("change");
		 $("#s_county").find("option[value='${teacher.teacherDistrict}']").attr("selected",true);
		 
		 if(($("#checkStatus").val()==0 && $("#completeStatus").val()==1) || ($("#checkStatus").val()==1 && $("#completeStatus").val()==1)){
			 $("#teacherName").attr("disabled","true");
			 var list = $('#teacherSex').find("input");
			 for(var i=0;i<list.length;i++){ 
				 list[i].setAttribute("disabled", "disabled"); 
			 }
		 } 
	}


	function back(){
		window.history.back();  
	}
	</script>
	</body>
</html>