<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/common/common.jspf" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert titl</title>
</head>
<body>
	<form action="${proPath }/housekeeping/addHkItem" method="post" enctype="multipart/form-data">
		<p>公司id<input type="text" name="hkId"></p>
		<p>类型id<input type="text" name="typeId"></p>
		<p>项目<input type="text" name="item"></p>
		<p>项目属性<input type="text" name="itemProperty"></p>
		<p>价格<input type="text" name="price"></p>
		<p>备注<input type="text" name="comment"></p>
		
		<!--<p>小区名：<input type="text" name="communityName"></p>
		<p>业主名：<input type="text" name="proprietorName"></p>
		<p>业主手机号：<input type="text" name="proprietorPhone"></p>
		<p>房间号：<input type="text" name="roomNumber"></p>
		<p>期待上门时间:<input type="text" name="expectTime"></p>
		<p>报修内容:<input type="text" name="repairDesc"></p>
		-->
		<p>上传图片:<input type="file" name="imgs" multiple="multiple" /></p>
		<p>提交:<input type="submit"></p>
	</form>
</body>
</html>