<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/common/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>欢迎登陆e众生活后台管理系统</title>
	<style type="text/css">
	body {
	    font-family: verdana,helvetica,arial,sans-serif;
	    padding: 0px;
	    font-size: 12px;
	    margin: 0;
	}
	
	/* 面板header里面的title样式 */
	.panel-title {
	  text-align: center;
	  font-size: 16px;
	}
	.panel-title {
	    font-size: 22px;
	    font-weight: bold;
	    color: #0E2D5F;
	    height: 46px;
	    line-height:46px;
	    text-align:center;
	}
	.panel-header, .panel-body {
    	background-color: #426374;
	}

	/* 输入框的行间距 */
	.textbox.easyui-fluid{
		margin-bottom:18px;
	}
	
	/* 整个登录框偏右32% */
	.panel{
		margin-right:32%;
	}
	</style>
</head>
<body>
    <div class="easyui-panel" title="e众生活后台管理系统" style="width:100%;height:610px; background: #3283AC url(${pageContext.request.contextPath}/images/login/ad-login.jpg) no-repeat center;" data-options="footer:'#ft'">
    	 
  		 <div class="easyui-panel" title="用户登录" style="width:400px;text-align: center;" data-options="
	  		 region:'center',
	  		 split:true,
	  		 style:{position:'absolute',right:50,top:150}
	  		 ">
	  		<div><span style="color:white;">账号密码:15768616002</span></div>
            <div style="padding:10px 60px 20px 60px">
                <form id="formInfo" class="easyui-form" method="post">
                    <table>
                        <tr>
                            <td style="width:300px;">
                            	<input  class="easyui-textbox" id="adAccount" name="adAccount" data-options="prompt:'账号',required:true,validType:'name',iconCls:'icon-man',iconAlign:'left'"   style="width:100%;height:32px;"/>
                            </td>
                        </tr>
                        <tr>
                            <td style="width:300px;">
                            	<input  class="easyui-textbox" id="adPassword" type="password" name="adPassword" data-options="prompt:'密码',required:true,validType:'password',iconAlign:'left',iconCls:'icon-lock'"  style="width:100%;height:32px;"/>
                            </td>
                        </tr>
                        <tr>
                            <td>
	                            <input  class="easyui-textbox" name="code" data-options="prompt:'验证码',required:'true',iconAlign:'left',iconCls:'icon-filter'"  missingMessage="必填！" style="width:48%;height:32px" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	                            <img src="/ezsh/kaptcha/getKaptchaImage" alt="验证码" onclick="this.src='/ezsh/kaptcha/getKaptchaImage'" width="80px" height="32" /> 
                            </td>
                        </tr>
                    </table>
                </form>
                <div style="text-align:center;padding:5px;">
                    <a href="javascript:void(0)" onclick="sub()" class="easyui-linkbutton"  style="width:270px;height:32px">登录</a>&nbsp;&nbsp;&nbsp;&nbsp;
                </div>
            </div>
        </div>
        <!-- <div class="easyui-panel" title="用户登录" style="width:400px;padding:30px 70px 20px 70px" data-options="
	  		 region:'center',
	  		 split:true,
	  		 style:{position:'absolute',right:50,top:150}
	  		 ">
			<div style="margin-bottom:10px">
				<input class="easyui-textbox" style="width:100%;height:40px;padding:12px" data-options="prompt:'Username',iconCls:'icon-man',iconWidth:38">
			</div>
			<div style="margin-bottom:20px">
				<input class="easyui-textbox" type="password" style="width:100%;height:40px;padding:12px" data-options="prompt:'Password',iconCls:'icon-lock',iconWidth:38">
			</div>
			<div style="margin-bottom:20px">
				<input type="checkbox" checked="checked">
				<span>Remember me</span>
			</div>
			<div>
				<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" style="padding:5px 0px;width:100%;">
					<span style="font-size:14px;">Login</span>
				</a>
			</div>
		</div> -->
    </div>
    
    <!-- footer start -->
    <div id="ft" style="height:50px;text-align:center;line-height:50px;background:#E8F1FF;" class="panel-footer">
    	&copy;Copyright 2017 All Rights Reserved huizhouxueyuan development team
    </div>
    <!-- footer end -->
</body>
<script type="text/javascript">
	if (top.location != self.location){     
		top.location=self.location;     
	}
</script>
<script type="text/javascript" src="/ezsh/js/login/login.js"></script>
</html>

