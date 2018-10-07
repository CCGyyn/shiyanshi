<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page contentType="text/html;charset=UTF-8"%>

<!DOCTYPE HTML>
<html>
	<head>
		<title>注册</title>
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
		    <a href="javascript:back()" class="">
		          <i class="am-header-icon am-icon-home"></i>
		    </a>
		</div>
		
		<h1 class="am-header-title">
		    <a href="#title-link" class="">
		      	找回密码
		    </a>
		</h1>
		
		<div class="am-header-right am-header-nav">
		    <a href="#right-link" class="">
		
		          <i class="am-header-icon am-icon-bars"></i>
		    </a>
		</div>
	</header>
	<body>
		<br>
		<div class="am-g am-container">
	  		<div class="am-u-lg-6 am-u-md-8 am-u-sm-centered">
			<form method="post" class="am-form">
	      		<div class="am-input-group am-input-group-primary">
					<span class="am-input-group-label">
						<i class="am-icon-mobile am-icon-fw am-icon-sm"></i>
					</span>
					<input type="text" class="am-form-field" placeholder="手机号码">
				</div>
				<br>
				
				<div class="am-input-group am-input-group-primary">
					<span class="am-input-group-label">
						<i class="am-icon-lock am-icon-fw"></i>
					</span>
					<input type="password" class="am-form-field" name="" placeholder="新密码">
				</div>
				<br>
				
				<div class="am-input-group am-input-group-primary">
					<span class="am-input-group-label">
						<i class="am-icon-lock am-icon-fw"></i>
					</span>
					<input type="password" class="am-form-field" placeholder="确认密码">
				</div>
				<br>
				
				<div class="am-input-group am-input-group-primary">
					<span class="am-input-group-label">
						<i class="am-icon-code-fork am-icon-fw"></i>
					</span>
					<input type="text" class="am-form-field" style="width:40%;" placeholder="验证码">
					<span>
						<button type="button" class="am-btn am-btn-default am-radius" style="margin-left:10px;">获取验证码</button>
					</span>
				</div>
			    <br>
			    
		     	<div class="am-form-group am-cf">
				    <button type="submit" class="am-btn am-btn-primary  am-cf am-u-sm-12">提交</button>
				</div>
	    	</form>
			</div>
		</div>
		<script src="/ezsh/js/jquery-3.2.1.min.js"></script>
		<script src="/ezsh/assets/js/amazeui.min.js"></script>
		<script src="/ezsh/assets/js/app.js"></script>
		<script type="text/javascript">
			function back(){
				window.history.back();  
			}
		</script>
	</body>
</html>
