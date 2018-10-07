<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page contentType="text/html;charset=UTF-8"%>

<!DOCTYPE HTML>
<html>
	<head>
		<title>登录</title>
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">    
		<link rel="stylesheet" href="/ezsh/assets/css/amazeui.min.css"/>
		<!-- <link rel="stylesheet" href="/ezsh/assets/css/admin.css"> -->
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
		</style>
	</head>
  	<header data-am-widget="header" class="am-header am-header-default">
		<div class="am-header-left am-header-nav">
		    <a href="#left-link" class="">
		          <i class="am-header-icon am-icon-home"></i>
		    </a>
		</div>
		
		<h1 class="am-header-title">
		    <a href="#title-link" class="">
		      	登录
		    </a>
		</h1>
		
		<div class="am-header-right am-header-nav">
		    <a href="#right-link" class="">
		
		          <i class="am-header-icon am-icon-bars"></i>
		    </a>
		</div>
	</header>
	<body>
		<div class="header">
			<div class="am-g">
				<h1>勤工助学家教分部</h1>
				<span>
					<img class="am-circle" src="/ezsh/images/wechat/320X320.jpg" width="60" height="60"/>
				</span>
			</div>
		</div>
		<hr/>
		<div class="am-g">
	  		<div class="am-u-lg-6 am-u-md-8 am-u-sm-centered">
			<form method="post" class="am-form">
	      		<div class="am-input-group am-input-group-primary">
					<span class="am-input-group-label">
						<i class="am-icon-user am-icon-fw"></i>
					</span>
					<input type="text" class="am-form-field" placeholder="Username">
				</div>
				<br>
				<div class="am-input-group am-input-group-primary">
					<span class="am-input-group-label">
						<i class="am-icon-lock am-icon-fw"></i>
					</span>
					<input type="password" class="am-form-field" placeholder="Password">
				</div>
				<br>
				<div class="am-cf">
			        <input type="button" onclick="register()" value="注册新账号" class="am-btn am-btn-primary am-btn-sm am-fl">
			        <input type="button" onclick="findPass()" value="忘记密码 ? " class="am-btn am-btn-default am-btn-sm am-fr">
		     	</div>
		     	<br>
		     	<div class="am-form-group">
				    <button type="submit" class="am-btn am-btn-primary am-u-sm-12">登录</button>
				</div>
	    	</form>
			</div>
		</div>
		
		<footer data-am-widget="footer" class="am-footer am-footer-default" data-am-footer="{  }">
		    <div class="am-footer-miscs ">
		        <p>CopyRight©2017  HuizhouXueYuan Team.</p>
		    </div>
	  	</footer>
		<script src="/ezsh/js/jquery-3.2.1.min.js"></script>
		<script src="/ezsh/assets/js/amazeui.min.js"></script>
		<script src="/ezsh/assets/js/app.js"></script>
		<script type="text/javascript">
			function register(){
				location.href="/ezsh/routeW/register?identify=wechat";
			}
			
			function findPass(){
				location.href="/ezsh/routeW/findPass?identify=wechat";
			}
		</script>
	</body>
</html>
