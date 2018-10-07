<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
	<head>
		<title>个人中心</title>
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">    
		<link rel="stylesheet" href="/ezsh/assets/css/amazeui.min.css"/>
		<link rel="stylesheet" href="/ezsh/assets/css/admin.css">
		<link rel="stylesheet" href="/ezsh/css/wechat/menu-icon/iconfont.css">  
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
			/* .ezsh_center_nav {
			  	padding:25px 10px 5px;
			  	max-width: 100%;  
			 } */
			
			.ezsh_center_nav_list span { 
				display: block; 
				width: 100%; 
				text-align: center; 
				padding-top: 5px; 
				font-size: 14px;
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
		      	个人中心
		    </a>
		</h1>
		
		<!-- <div class="am-header-right am-header-nav">
		    <a href="#right-link" class="">
		
		          <i class="am-header-icon am-icon-bars"></i>
		    </a>
		</div> -->
	</header>
	<body>
	<div class="am-panel am-panel-default">
    	<div class="am-panel-bd">
    	<img class="am-circle" src="${teacher.teacherIcon}" width="80" height="80"/>
    	<dl  style="display:inline-block;margin-left:20px;">
	    	<%-- <dd>账号${teacher.teacherIcon}</dd> --%>
	    	<dd>昵称:${teacher.teacherNickName}</dd>
    	</dl>
    	</div>
    	<!-- <div class="am-panel-footer">
    		<ul class="ezsh_center_nav_list am-avg-sm-4">
		        <li>
		        	<a href="" ><i style="color:#F4A460;font-size:46px;" class="iconfont icon-yuwen"></i></a>
		        	<span>语文</span>
		        </li>
		        <li><a href="" ><i style="color:#9ACD32;font-size:46px;" class="iconfont icon-shuxue"></i></a>
		        	<span>数学</span>
		        </li>
		        
	    	</ul>
    	</div> -->
	</div>
	<hr data-am-widget="divider" style="" class="am-divider am-divider-dashed" />
	<div>
		<ul class="am-list admin-sidebar-list" id="collapase-nav-1">
			<li class="am-panel">
			  <a href="/ezsh/routeW/wcPerson">
			      <i style="color:#00BFFF;font-size:20px;line-height:20px;" class="iconfont icon-iconcopy"></i>个人信息
			      <i class="am-icon-angle-right am-fr am-margin-right"></i>
			  </a>
			</li>
		</ul>
		
		<!-- <ul class="am-list admin-sidebar-list" style="margin-top:5px;" id="collapase-nav-2">
			<li class="am-panel">
			  <a>
			      <i style="color:#00BFFF;font-size:20px;line-height:20px;" class="iconfont icon-mima"></i>密码重设<i class="am-icon-angle-right am-fr am-margin-right"></i>
			  </a>
			</li>
		</ul> -->
		
		<ul class="am-list admin-sidebar-list" style="margin-top:5px;" id="collapase-nav-2">
			<li class="am-panel">
			  <a href="javaScript:reSume()">
			      <i style="color:#00BFFF;font-size:20px;line-height:20px;" class="iconfont icon-icon"></i>我的简历<i class="am-icon-angle-right am-fr am-margin-right"></i>
			  </a>
			</li>
		</ul>
	</div>
	
	<script src="/ezsh/js/jquery-3.2.1.min.js"></script>
	<script src="/ezsh/assets/js/amazeui.min.js"></script>
	<script src="/ezsh/assets/js/app.js"></script>
	<script src="/ezsh/js/dist/amazeui.dialog.min.js" charset="utf-8"></script>
	<script type="text/javascript">
		function back(){
			window.history.back();  
		}
		
		function reSume(){
			if(${teacher.checkStatus!=1}){
				AMUI.dialog.alert({
				     title: '提示',
				     content: '未通过审核',
				     onConfirm: function() {
				     console.log('close');
				     }
				});
			} else {
				location.href="/ezsh/routeW/resume";
			}
		}
	</script>
	</body>
</html>