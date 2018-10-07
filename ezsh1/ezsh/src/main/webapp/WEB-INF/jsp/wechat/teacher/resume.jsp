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
		      	编辑简历
		    </a>
		</h1>

	</header>
	<body>
	<div class="am-panel am-panel-default" style="margin-bottom:0px;">
    	<div id="up-img-touch" class="am-panel-bd am-center" style="width:100px;">
    		<img  class="am-circle am-center" style="margin:0px auto;width:80px;height:80px;" src="${teacher.teacherIcon}"/>
    	</div>
	</div>

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
		<input type="hidden" id="teacherResumeId" name="teacherResumeId" value="${teacher.resumeInfo.teacherResumeId}">
		<input type="hidden" id="teacherId" name="ptTeacherId" value="${teacher.teacherId}">
		
		<hr data-am-widget="divider" style="" class="am-divider am-divider-default" />
		<div>
			<span><i style="color:#00BFFF;font-size:20px;line-height:20px;" class="iconfont icon-xuexiao"></i>所在学校:</span>
			<input type="text" id="placeSchool" value="${teacher.resumeInfo.placeSchool}" name="placeSchool" style="width:60%;display:inline-block;border:1px solid rgba(255,255,255,.15);" class="am-form-field" placeholder="所在学校" required>
		</div>
	
		<hr data-am-widget="divider" style="" class="am-divider am-divider-default"/>
		<div>
			<span><i style="color:#00BFFF;font-size:20px;line-height:20px;" class="iconfont icon-lesson1"></i>期望课程:</span>
			<input type="text" style="width:60%;display:inline-block;border:1px solid rgba(255,255,255,.15);" name="expectCourse" value="${teacher.resumeInfo.expectCourse}" class="am-form-field" placeholder="期望课程" required>
		</div>
	
		<hr data-am-widget="divider" style="" class="am-divider am-divider-default"/>
		<div>
			<span><i style="color:#00BFFF;font-size:20px;line-height:20px;" class="iconfont icon-xinchou"></i>期望薪酬:</span>
			<input type="number" style="width:50%;display:inline-block;border:1px solid rgba(255,255,255,.15);" name="expectSalary" value="${teacher.resumeInfo.expectSalary}" class="am-form-field" placeholder="期望薪酬(元/小时)" required>
			<span style="font-size:12px;">(元/h)</span>
		</div>
	
		<hr data-am-widget="divider" style="" class="am-divider am-divider-default" />
		<div>
			<span><i style="color:#00BFFF;font-size:20px;line-height:20px;" class="iconfont icon-shijian"></i>时间段:</span>
			<div style="width:72%;display:inline-block;">
				<select id="timeBucket" multiple name="timeBucket" value="周一" style="border-color:1px solid rgba(255,255,255,.15);" data-am-selected="{dropUp: 1,maxHeight: 200,btnWidth: '70%'}" required>
					<option value="周一">(周一可教)</option>
					<option value="周二">(周二可教)</option>
					<option value="周三">(周三可教)</option>
					<option value="周四">(周四可教)</option>
					<option value="周五">(周五可教)</option>
					<option value="周六">(周六可教)</option>
					<option value="周日">(周日可教)</option>
				</select>
				<span style="font-size:12px;">(可多选)</span>
			</div>
		</div>
		
		<button id="submit" class="am-btn am-btn-secondary am-center" style="margin-top:5%;width:90%;" type="button">提交编辑</button>
		</form>
	</div>

	<script src="/ezsh/js/jquery-3.2.1.min.js"></script>
	<script src="/ezsh/assets/js/amazeui.min.js"></script>
	<script src="/ezsh/assets/js/app.js"></script>
	<!-- <script class="resources library" src="/ezsh/js/area.js" type="text/javascript"></script>
    <script type="text/javascript">_init_area();</script> -->
    <script src="/ezsh/js/dist/amazeui.dialog.min.js" charset="utf-8"></script>
    <script src="/ezsh/wechat/up-head-img/js/cropper.min.js" charset="utf-8"></script>
    <script src="/ezsh/wechat/up-head-img/js/custom_up_img.js" charset="utf-8"></script>
    <script type="text/javascript">
    var timeBucketJson="${teacher.resumeInfo.timeBucket}";
    if(timeBucketJson!=null && timeBucketJson!="" ){
    	var timeBucketArray=timeBucketJson.split(",");
        for(var i=0;i<timeBucketArray.length;i++){
        	var timeBucket=$("#timeBucket").find("option[value='" + timeBucketArray[i] + "']");
        	timeBucket.attr("selected",true);
        }
    }
    
    $("#submit").click(function(){
    	if($('#formInfo').validator('isFormValid')){
    		var formData = new FormData(document.getElementById("formInfo"));
    		$.ajax({
    		    type: "POST",
    		    url:"/ezsh/teacherResumeT/update",
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
	function back(){
		window.history.back();  
	}
	</script>
	</body>
</html>